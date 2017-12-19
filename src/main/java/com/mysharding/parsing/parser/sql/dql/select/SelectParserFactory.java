package com.mysharding.parsing.parser.sql.dql.select;

import com.mysharding.constant.DatabaseType;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.parser.dialect.mysql.sql.MySQLSelectParser;

/**
 * Created by yaoyw on 2017/11/1.
 */
public final class SelectParserFactory {

  public static AbstractSelectParser newInstance(final DatabaseType dbType, final LexerEngine lexerEngine) {
    switch (dbType) {
      case MySQL:
        return new MySQLSelectParser(lexerEngine);
      default:
        return null;
    }
  }


}
