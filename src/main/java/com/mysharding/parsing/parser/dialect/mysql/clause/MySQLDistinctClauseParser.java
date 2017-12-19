package com.mysharding.parsing.parser.dialect.mysql.clause;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.dialect.mysql.MySQLKeyword;
import com.mysharding.parsing.lexer.token.Keyword;
import com.mysharding.parsing.parser.clause.DistinctClauseParser;

/**
 * Created by yaoyw on 2017/11/2.
 */
public class MySQLDistinctClauseParser extends DistinctClauseParser {


  public MySQLDistinctClauseParser(final LexerEngine lexerEngine) {
    super(lexerEngine);
  }

  protected Keyword[] getSynonymousKeywordsForDistinct() {
    return new Keyword[] {MySQLKeyword.DISTINCTROW};
  }
}
