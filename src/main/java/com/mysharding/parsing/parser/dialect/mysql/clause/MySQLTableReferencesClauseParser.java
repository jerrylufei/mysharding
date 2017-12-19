package com.mysharding.parsing.parser.dialect.mysql.clause;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.dialect.mysql.MySQLKeyword;
import com.mysharding.parsing.lexer.token.Keyword;
import com.mysharding.parsing.parser.clause.TableReferencesClauseParser;

/**
 * Created by yaoyw on 2017/11/3.
 */
public class MySQLTableReferencesClauseParser extends TableReferencesClauseParser {



  public MySQLTableReferencesClauseParser(final LexerEngine lexerEngine) {
    super(lexerEngine);
  }

  protected Keyword[] getKeywordsForJoinType() {
    return new Keyword[] {MySQLKeyword.STRAIGHT_JOIN};
  }
}
