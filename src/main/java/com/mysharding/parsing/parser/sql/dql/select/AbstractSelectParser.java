package com.mysharding.parsing.parser.sql.dql.select;


import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.Assist;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Symbol;
import com.mysharding.parsing.parser.clause.facade.AbstractSelectClauseParserFacade;
import com.mysharding.parsing.parser.sql.SQLParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/1.
 */
@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public abstract class AbstractSelectParser implements SQLParser {

  private final LexerEngine lexerEngine;

  private final AbstractSelectClauseParserFacade selectClauseParserFacade;

  public final SelectStatement parse() {
    SelectStatement result = parseInternal();

    return result;
  }

  private SelectStatement parseInternal() {
    SelectStatement result = new SelectStatement();
    parseInternal(result);
    return result;
  }

  protected abstract void parseInternal(final SelectStatement statement);

  protected final void parseDistinct() {
    selectClauseParserFacade.getDistinctClauseParser().parse();
  }

  protected final void parseFrom(final SelectStatement selectStatement) {
    lexerEngine.unsupportedIfEqual(DefaultKeyword.INTO);
    if (lexerEngine.skipIfEqual(DefaultKeyword.FROM)) {
      parseTable(selectStatement);
    }
  }

  /**
   * case 1: 单表
   * case 2: 多表关联
   * case 3: 子查询
   */
  private final void parseTable(final SelectStatement selectStatement) {
    if (lexerEngine.skipIfEqual(Symbol.LEFT_PAREN)) {
      selectStatement.setSubQueryStatement(parseInternal());
      if (lexerEngine.equalAny(DefaultKeyword.WHERE, Assist.END)) {
        return;
      }
    }
    selectClauseParserFacade.getTableReferencesClauseParser().parse(selectStatement, false);
  }

  protected void parseWhere() {
    selectClauseParserFacade.getWhereClauseParser().parse();
  }

}
