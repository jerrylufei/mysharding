package com.mysharding.parsing.lexer.token;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/10/17.
 */
@RequiredArgsConstructor
@Getter
public class Token {

  private final TokenType type;
  private final String literals;
  private final int endPosition;

}
