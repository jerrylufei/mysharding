package com.mysharding.parsing.parser.clause;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.parser.context.selectitem.SelectItem;
import com.mysharding.parsing.parser.sql.SQLStatement;

import java.util.List;


/**
 * Created by yaoyw on 2017/11/6.
 */
public class WhereClauseParser implements SQLClauseParser {

  private final LexerEngine lexerEngine;

  private final AliasClauseParser aliasClauseParser;

  public WhereClauseParser(final LexerEngine lexerEngine) {
    this.lexerEngine = lexerEngine;
    aliasClauseParser = new AliasClauseParser(lexerEngine);
  }

  /**
   * Parse where.
   *
   * @param sqlStatement SQL statement
   * @param items select items
   */
  public void parse(final SQLStatement sqlStatement, final List<SelectItem> items) {
    aliasClauseParser.parse();
    if (lexerEngine.skipIfEqual(DefaultKeyword.WHERE)) {
      //TODO: parse
    }
  }

  private void parseCondition(final SQLStatement sqlStatement, final List<SelectItem> items) {
    do {
      parseComparisonCondition(sqlStatement, items);
    } while (lexerEngine.skipIfEqual(DefaultKeyword.AND));
    lexerEngine.unsupportedIfEqual(DefaultKeyword.OR);
  }

  private void parseComparisonCondition(final SQLStatement sqlStatement, final List<SelectItem> items) {
    System.out.println();
  }

}
