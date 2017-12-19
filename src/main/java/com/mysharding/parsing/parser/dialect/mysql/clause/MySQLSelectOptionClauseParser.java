package com.mysharding.parsing.parser.dialect.mysql.clause;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.parser.clause.SQLClauseParser;
import com.mysharding.parsing.lexer.dialect.mysql.MySQLKeyword;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/3.
 */
@RequiredArgsConstructor
public class MySQLSelectOptionClauseParser implements SQLClauseParser {

  private final LexerEngine lexerEngine;

  public void parse() {
    lexerEngine.skipAll(
      MySQLKeyword.HIGH_PRIORITY,
      MySQLKeyword.STRAIGHT_JOIN,
      MySQLKeyword.SQL_SMALL_RESULT,
      MySQLKeyword.SQL_BIG_RESULT,
      MySQLKeyword.SQL_BUFFER_RESULT,
      MySQLKeyword.SQL_CACHE,
      MySQLKeyword.SQL_NO_CACHE,
      MySQLKeyword.SQL_CALC_FOUND_ROWS
    );
  }
}
