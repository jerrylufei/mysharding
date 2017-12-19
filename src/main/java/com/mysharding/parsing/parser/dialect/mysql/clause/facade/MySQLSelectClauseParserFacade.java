package com.mysharding.parsing.parser.dialect.mysql.clause.facade;

import com.mysharding.parsing.parser.clause.TableReferencesClauseParser;
import com.mysharding.parsing.parser.clause.WhereClauseParser;
import com.mysharding.parsing.parser.clause.facade.AbstractSelectClauseParserFacade;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.parser.dialect.mysql.clause.MySQLDistinctClauseParser;

/**
 * Created by yaoyw on 2017/11/2.
 */
public class MySQLSelectClauseParserFacade extends AbstractSelectClauseParserFacade {

  public MySQLSelectClauseParserFacade(final LexerEngine lexerEngine) {
    super(new MySQLDistinctClauseParser(lexerEngine), new TableReferencesClauseParser(lexerEngine),
      new WhereClauseParser());
  }

}
