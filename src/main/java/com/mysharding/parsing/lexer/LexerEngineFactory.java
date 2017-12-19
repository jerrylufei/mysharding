package com.mysharding.parsing.lexer;

import com.mysharding.constant.DatabaseType;
import com.mysharding.parsing.lexer.dialect.mysql.MySQLLexer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Created by yaoyw on 2017/10/31.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LexerEngineFactory {

  public static LexerEngine newInstance(final DatabaseType dbType, final String sql) {
    switch (dbType) {
      case MySQL:
        return new LexerEngine(new MySQLLexer(sql));
      default:
        throw new UnsupportedOperationException(String.format("Cannot support database [%s].", dbType));
    }
  }

}
