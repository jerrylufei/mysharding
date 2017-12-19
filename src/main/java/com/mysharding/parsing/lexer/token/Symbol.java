package com.mysharding.parsing.lexer.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 词法符号标记
 *
 * @author yaoyw
 */
@RequiredArgsConstructor
@Getter
public enum Symbol implements TokenType {
  LEFT_PAREN("("),
  RIGHT_PAREN(")"),
  LEFT_BRACE("{"),
  RIGHT_BRACE("}"),
  LEFT_BRACKET("["),
  RIGHT_BRACKET("]"),
  SEMI(";"),
  COMMA(","),
  DOT("."),
  DOUBLE_DOT(".."),
  PLUS("+"),
  SUB("-"),
  STAR("*"),
  SLASH("/"),
  QUESTION("?"),
  EQ("="),
  GT(">"),
  LT("<"),
  BANG("!"),
  TILDE("~"),
  CARET("^"),
  PERCENT("%"),
  COLON(":"),
  DOUBLE_COLON("::"),
  COLON_EQ(":="),
  LT_EQ("<="),
  GT_EQ(">="),
  LT_EQ_GT("<=>"),
  LT_GT("<>"),
  BANG_EQ("!="),
  BANG_GT("!>"),
  BANG_LT("!<"),
  AMP("&"),
  BAR("|"),
  DOUBLE_AMP("&&"),
  DOUBLE_BAR("||"),
  DOUBLE_LT("<<"),
  DOUBLE_GT(">>"),
  AT("@"),
  POUND("#");

  private final String literals;

  private static Map<String, Symbol> symbols = new HashMap<String, Symbol>(128);
  @Getter
  private static Symbol[] operators;

  static {
    for (Symbol symbol : Symbol.values()) {
      symbols.put(symbol.getLiterals(), symbol);
    }

    operators = new Symbol
      [] {PLUS, SUB, STAR, SLASH, EQ, GT, LT, CARET, PERCENT, LT_EQ, GT_EQ, LT_EQ_GT, LT_GT, BANG_EQ, BANG_GT, BANG_LT, AMP, BAR, DOUBLE_AMP, DOUBLE_BAR, DOUBLE_LT, DOUBLE_GT};
  }


  /**
   * 通过字面量查找词法符号.
   *
   * @param literals 字面量
   * @return 词法符号
   */
  public static Symbol literalsOf(final String literals) {
    return symbols.get(literals);
  }

}
