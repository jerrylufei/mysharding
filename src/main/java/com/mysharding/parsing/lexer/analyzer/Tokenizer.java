package com.mysharding.parsing.lexer.analyzer;

import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Symbol;
import com.mysharding.parsing.lexer.token.TokenType;
import com.mysharding.parsing.lexer.token.Literals;
import com.mysharding.parsing.lexer.token.Token;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/10/17.
 */
@RequiredArgsConstructor
@Getter
public class Tokenizer {

  private final String input;
  private final Dictionary dictionary;
  private final int offset;

  private static final int MYSQL_SPECIAL_COMMENT_BEGIN_SYMBOL_LENGTH = 1;
  private static final int COMMENT_BEGIN_SYMBOL_LENGTH = 2;
  private static final int HINT_BEGIN_SYMBOL_LENGTH = 3;
  private static final int COMMENT_AND_HINT_END_SYMBOL_LENGTH = 2;
  private static final int HEX_BEGIN_SYMBOL_LENGTH = 2;

  public Token scanChars() {
    return scanChars(charAt(offset));
  }

  private Token scanChars(final char terminatedChar) {
    int length = getLengthUntilTerminatedChar(terminatedChar);
    return new Token(Literals.CHARS, input.substring(offset + 1, offset + length - 1), offset + length);
  }

  private int getLengthUntilTerminatedChar(final char terminatedChar) {
    int length = 1;
    while (terminatedChar != charAt(offset + length) || hasEscapeChar(terminatedChar, offset + length)) {
      if (offset + length > input.length()) {
        throw new RuntimeException();
      }

      if (hasEscapeChar(terminatedChar, offset + length)) {
        length++;
      }

      length++;
    }

    return length + 1;
  }

  private boolean hasEscapeChar(final char charIdentifier, final int offset) {
    return charIdentifier == charAt(offset) && charIdentifier == charAt(offset + 1);
  }

  public Token scanSymbol() {
    int length = 0;
    while (CharType.isSymbol(charAt(offset + length))) {
      length++;
    }

    String literals = input.substring(offset, offset + length);
    Symbol symbol;
    while (null == (symbol = Symbol.literalsOf(literals))) {
      literals = input.substring(offset, offset + --length);
    }

    return new Token(symbol, literals, offset + length);
  }

  public Token scanNumber() {
    int length = 0;
    // 负数
    if ('-' == charAt(offset + length)) {
      length++;
    }

    // 浮点数
    length += getDigitalLength(offset + length);
    boolean isFloat = false;
    // 12.34
    if ('.' == charAt(offset + length)) {
      isFloat = true;
      length++;
      length += getDigitalLength(offset + length);
    }

    // 科学计数表示，例如：SELECT 7.823E5
    if (isScientificNotation(offset + length)) {
      isFloat = true;
      length++;
      if ('+' == charAt(offset + length) || '-' == charAt(offset + length)) {
        length++;
      }
      length += getDigitalLength(offset + length);
    }

    // 浮点数，例如：SELECT 1.333F
    if (isBinaryNumber(offset + length)) {
      isFloat = true;
      length++;
    }

    return new Token(isFloat ? Literals.FLOAT : Literals.INT, input.substring(offset, offset + length), offset + length);
  }

  private int getDigitalLength(final int offset) {
    int length = 0;
    while (CharType.isDigital(charAt(offset + length))) {
      length++;
    }
    return length;
  }

  private boolean isScientificNotation(final int offset) {
    char current = charAt(offset);
    return 'e' == current || 'E' == current;
  }

  private boolean isBinaryNumber(final int offset) {
    char current = charAt(offset);
    return 'f' == current || 'F' == current || 'd' == current || 'D' == current;
  }

  // 十六进制数
  public Token scanHexDecimal() {
    int length = HEX_BEGIN_SYMBOL_LENGTH;
    if ('-' == charAt(offset + length)) {
      length++;
    }

    while (isHex(charAt(offset + length))) {
      length++;
    }

    return new Token(Literals.HEX, input.substring(offset, offset + length), offset + length);
  }

  private boolean isHex(final char ch) {
    return ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f' || CharType.isDigital(ch);
  }

  /**
   * 扫描标识符.
   *
   * @return 标识符标记
   */
  public Token scanIdentifier() {
    // `字段`，例如：SELECT `id` FROM t_user 中的 `id`
    if ('`' == charAt(offset)) {
      int length = getLengthUntilTerminatedChar('`');
      return new Token(Literals.IDENTIFIER, input.substring(offset, offset + length), offset + length);
    }

    int length = 0;
    while (isIdentifierChar(charAt(offset + length))) {
      length++;
    }

    String literals = input.substring(offset, offset + length);
    // 处理 order / group 作为表名
    if (isAmbiguousIdentifier(literals)) {
      return new Token(processAmbiguousIdentifier(offset, literals), literals, offset + length);
    }
    return new Token(dictionary.findTokenType(literals, Literals.IDENTIFIER), literals, offset + length);
  }

  private boolean isIdentifierChar(final char ch) {
    return CharType.isAlphabet(ch) || CharType.isDigital(ch) || '_' == ch || '$' == ch || '#' == ch;
  }

  /**
   * 是否是引起歧义的标识符
   * 例如 "SELECT * FROM group"，此时 "group" 代表的是表名，而非词法关键词
   *
   * @param literals 标识符
   * @return 是否
   */
  private boolean isAmbiguousIdentifier(final String literals) {
    return DefaultKeyword.ORDER.name().equalsIgnoreCase(literals) ||
      DefaultKeyword.GROUP.name().equalsIgnoreCase(literals);
  }

  /**
   * 获取引起歧义的标识符对应的词法标记类型
   *
   * @param offset 位置
   * @param literals 标识符
   * @return 词法标记类型
   */
  private TokenType processAmbiguousIdentifier(final int offset, final String literals) {
    int length = 0;
    while (CharType.isWhitespace(charAt(offset + length))) {
      length++;
    }

    if (DefaultKeyword.BY.name().equalsIgnoreCase(
      String.valueOf(new char[] {charAt(offset + length), charAt(offset + length + 1)}))) {
      return dictionary.findTokenType(literals);
    }

    return Literals.IDENTIFIER;
  }

  /**
   * 扫描变量.
   * 在 MySQL 里，@代表用户变量；@@代表系统变量。
   * 在 SQLServer 里，有 @@。
   *
   * @return 变量标记
   */
  public Token scanVariable() {
    int length = 1;
    if ('@' == charAt(offset + 1)) {
      length++;
    }

    while (isVariableChar(charAt(offset + length))) {
      length++;
    }

    return new Token(Literals.VARIABLE, input.substring(offset, offset + length), offset + length);
  }

  private boolean isVariableChar(final char ch) {
    return isIdentifierChar(ch) || '.' == ch;
  }


  /**
   * skip whitespace.
   *
   * @return offset after whitespace skipped
   */
  public int skipWhitespace() {
    int length = 0;
    while (CharType.isWhitespace(charAt(offset + length))) {
      length++;
    }
    return offset + length;
  }

  /**
   * skip comment.
   *
   * @return offset after comment skipped
   */
  public int skipComment() {
    char current = charAt(offset);
    char next = charAt(offset + 1);
    if (isSingleLineCommentBegin(current, next)) {
      return skipSingleLineComment(COMMENT_BEGIN_SYMBOL_LENGTH);
    } else if ('#' == current) {
      return skipSingleLineComment(MYSQL_SPECIAL_COMMENT_BEGIN_SYMBOL_LENGTH);
    } else if (isMultipleLineCommentBegin(current, next)) {
      return skipMultiLineComment();
    }
    return offset;
  }

  private boolean isSingleLineCommentBegin(final char ch, final char next) {
    return '/' == ch && '/' == next || '-' == ch && '-' == next;
  }

  private int skipSingleLineComment(final int commentSymbolLength) {
    int length = commentSymbolLength;
    while (!CharType.isEndOfInput(charAt(offset + length)) && '\n' != charAt(offset + length)) {
      length++;
    }
    return offset + length + 1;
  }

  private boolean isMultipleLineCommentBegin(final char ch, final char next) {
    return '/' == ch && '*' == next;
  }

  private int skipMultiLineComment() {
    return untilCommentAndHintTerminateSign(COMMENT_BEGIN_SYMBOL_LENGTH);
  }

  /**
   * skip hint.
   *
   * @return offset after hint skipped
   */
  public int skipHint() {
    return untilCommentAndHintTerminateSign(HINT_BEGIN_SYMBOL_LENGTH);
  }

  private int untilCommentAndHintTerminateSign(final int beginSymbolLength) {
    int length = beginSymbolLength;
    while (!isMultipleLineCommentEnd(charAt(offset + length), charAt(offset + length + 1))) {
      if (CharType.isEndOfInput(charAt(offset + length))) {
        throw new RuntimeException("*/");
      }
      length++;
    }
    return offset + length + COMMENT_AND_HINT_END_SYMBOL_LENGTH;
  }

  private boolean isMultipleLineCommentEnd(final char ch, final char next) {
    return '*' == ch && '/' == next;
  }



  private char charAt(final int index) {
    return index >= input.length() ? (char) CharType.EOI : input.charAt(index);
  }

  public static void main(String[] args) {
    Tokenizer tokenizer = new Tokenizer("'jerry'", null, 0);
    Token token = tokenizer.scanChars();
    System.out.println(token.getLiterals());
  }

}
