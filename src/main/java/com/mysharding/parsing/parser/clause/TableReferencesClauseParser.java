package com.mysharding.parsing.parser.clause;

import com.google.common.base.Optional;
import com.mysharding.context.table.Table;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Keyword;
import com.mysharding.parsing.lexer.token.Symbol;
import com.mysharding.parsing.parser.sql.SQLStatement;
import com.mysharding.parsing.parser.token.TableToken;
import com.mysharding.util.SQLUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/3.
 */
public class TableReferencesClauseParser implements SQLClauseParser {

  @Getter
  private final LexerEngine lexerEngine;

  private final AliasClauseParser aliasClauseParser;

  public TableReferencesClauseParser(final LexerEngine lexerEngine) {
    this.lexerEngine = lexerEngine;
    aliasClauseParser = new AliasClauseParser(lexerEngine);
  }

  /**
   * Parse table references.
   *
   * @param sqlStatement SQL statement
   * @param isSingleTableOnly is parse single table only
   */
  public final void parse(final SQLStatement sqlStatement, final boolean isSingleTableOnly) {
    do {
      parseTableReference(sqlStatement, isSingleTableOnly);
    } while (lexerEngine.skipIfEqual(Symbol.COMMA));
  }

  protected void parseTableReference(final SQLStatement sqlStatement, final boolean isSingleTableOnly) {
    parseTableFactor(sqlStatement, isSingleTableOnly);
  }

  protected final void parseTableFactor(final SQLStatement sqlStatement, final boolean isSingleTableOnly) {
    final int beginPosition =
      lexerEngine.getCurrentToken().getEndPosition() - lexerEngine.getCurrentToken().getLiterals().length();
    String literals = lexerEngine.getCurrentToken().getLiterals();
    lexerEngine.nextToken();
    if (lexerEngine.equalAny(Symbol.DOT)) {
      throw new RuntimeException("Cannot support SQL for `schema.table`");
    }

    String tableName = SQLUtil.getExactlyValue(literals);
    Optional<String> alias = aliasClauseParser.parse();
    if (isSingleTableOnly) {
      sqlStatement.getTables().add(new Table(tableName, alias));
      sqlStatement.getSqlTokens().add(new TableToken(beginPosition, literals));
    }

    parseJoinTable(sqlStatement);
  }

  private void parseJoinTable(final SQLStatement sqlStatement) {
    while (parseJoinType()) {
      if (lexerEngine.equalAny(Symbol.LEFT_PAREN)) {
        throw new UnsupportedOperationException("Cannot support sub query for join table.");
      }

      parseTableFactor(sqlStatement, false);
      //TODO: parse condition
    }



  }

  private boolean parseJoinType() {
    List<Keyword> joinTypeKeywords = new ArrayList<Keyword>();
    joinTypeKeywords.addAll(Arrays.asList(
      DefaultKeyword.INNER,
      DefaultKeyword.OUTER,
      DefaultKeyword.LEFT,
      DefaultKeyword.RIGHT,
      DefaultKeyword.FULL,
      DefaultKeyword.CROSS,
      DefaultKeyword.NATURAL,
      DefaultKeyword.JOIN)
    );
    joinTypeKeywords.addAll(Arrays.asList(getKeywordsForJoinType()));
    Keyword[] keywords = joinTypeKeywords.toArray(new Keyword[joinTypeKeywords.size()]);

    if (!lexerEngine.equalAny(keywords)) {
      return false;
    }
    lexerEngine.skipAll(keywords);
    return true;
  }

  protected Keyword[] getKeywordsForJoinType() {
    return new Keyword[0];
  }

}
