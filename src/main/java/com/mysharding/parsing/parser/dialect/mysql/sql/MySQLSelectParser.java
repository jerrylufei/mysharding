package com.mysharding.parsing.parser.dialect.mysql.sql;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.parser.dialect.mysql.clause.facade.MySQLSelectClauseParserFacade;
import com.mysharding.parsing.parser.sql.dql.select.AbstractSelectParser;
import com.mysharding.parsing.parser.dialect.mysql.clause.MySQLSelectOptionClauseParser;
import com.mysharding.parsing.parser.sql.dql.select.SelectStatement;

/**
 * Created by yaoyw on 2017/11/1.
 */
public final class MySQLSelectParser extends AbstractSelectParser {

  private final MySQLSelectOptionClauseParser selectOptionClauseParser;

  public MySQLSelectParser(final LexerEngine lexerEngine) {
    super(lexerEngine, new MySQLSelectClauseParserFacade(lexerEngine));
    selectOptionClauseParser = new MySQLSelectOptionClauseParser(lexerEngine);
  }

  protected void parseInternal(final SelectStatement statement) {
    //TODO: parse every element of sql. eg: distinct&select&where&from
    parseDistinct();
    parseSelectOption();
    //TODO: parse select list
    parseFrom(statement);
  }

  public void parseSelectOption() {
    selectOptionClauseParser.parse();
  }



}
