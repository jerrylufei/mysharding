package com.mysharding.parsing.parser.sql;

import com.mysharding.constant.DatabaseType;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.TokenType;
import com.mysharding.parsing.parser.sql.dql.select.SelectParserFactory;

/**
 * Created by yaoyw on 2017/11/1.
 */
public class SQLParserFactory {


  public static SQLParser newInstance(final DatabaseType dbType, final TokenType tokenType,
    final LexerEngine lexerEngine) {
    if (!(tokenType instanceof DefaultKeyword)) {
      throw new RuntimeException("token type error");
    }

    switch ((DefaultKeyword) tokenType) {
      case SELECT:
        return SelectParserFactory.newInstance(dbType, lexerEngine);
      default:
        return null;
    }

  }


}
