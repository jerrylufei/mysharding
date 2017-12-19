package com.mysharding.parsing.parser.clause;

import com.google.common.base.Optional;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Literals;
import com.mysharding.parsing.lexer.token.Symbol;
import com.mysharding.util.SQLUtil;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/3.
 */
@RequiredArgsConstructor
public class AliasClauseParser implements SQLClauseParser {

  private final LexerEngine lexerEngine;

  public Optional<String> parse() {
    if (lexerEngine.skipIfEqual(DefaultKeyword.AS)) {
      if (lexerEngine.equalAny(Symbol.values())) {
        return Optional.absent();
      }
      String result = SQLUtil.getExactlyValue(lexerEngine.getCurrentToken().getLiterals());
      lexerEngine.nextToken();
      return Optional.of(result);
    }

    if (lexerEngine.equalAny(
          Literals.IDENTIFIER,
          Literals.CHARS,
          DefaultKeyword.USER,
          DefaultKeyword.END,
          DefaultKeyword.CASE,
          DefaultKeyword.KEY,
          DefaultKeyword.INTERVAL,
          DefaultKeyword.CONSTRAINT)
      ) {
      String result = SQLUtil.getExactlyValue(lexerEngine.getCurrentToken().getLiterals());
      lexerEngine.nextToken();
      return Optional.of(result);
    }
    return Optional.absent();



  }
}
