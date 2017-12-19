package com.mysharding.parsing.lexer;

import com.google.common.collect.Sets;
import com.mysharding.parsing.lexer.token.Token;
import com.mysharding.parsing.lexer.token.TokenType;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Created by yaoyw on 2017/10/31.
 */
@RequiredArgsConstructor
public class LexerEngine {

  private final Lexer lexer;

  public String getInput() {
    return lexer.getInput();
  }

  public Token getCurrentToken() {
    return lexer.getCurrentToken();
  }

  public void nextToken() {
    lexer.nextToken();
  }

  public void skipAll(TokenType... tokenTypes) {
    Set<TokenType> tokenTypeSet = Sets.newHashSet(tokenTypes);
    while (tokenTypeSet.contains(getCurrentToken().getType())) {
      lexer.nextToken();
    }
  }

  /**
   * Throw unsupported exception if current token equals one of input tokens.
   *
   * @param tokenTypes to be adjusted token types
   */
  public void unsupportedIfEqual(final TokenType... tokenTypes) {
    if (equalAny(tokenTypes)) {
      throw new RuntimeException("unsupported token type");
    }
  }

  /**
   * Adjust current token equals one of input tokens or not.
   *
   * @param tokenTypes to be adjusted token types
   * @return current token equals one of input tokens or not
   */
  public boolean equalAny(final TokenType... tokenTypes) {
    for (TokenType each : tokenTypes) {
      if (each == lexer.getCurrentToken().getType()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Skip current token if equals one of input tokens.
   *
   * @param tokenTypes to be adjusted token types
   * @return skipped current token or not
   */
  public boolean skipIfEqual(final TokenType... tokenTypes) {
    if (equalAny(tokenTypes)) {
      lexer.nextToken();
      return true;
    }
    return false;
  }


}
