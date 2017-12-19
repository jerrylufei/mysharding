package com.mysharding.parsing;

import com.mysharding.constant.DatabaseType;
import com.mysharding.parsing.parser.sql.SQLParserFactory;
import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.LexerEngineFactory;
import com.mysharding.parsing.parser.sql.SQLStatement;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/1.
 */
@RequiredArgsConstructor
public class SQLParsingEngine {

  private final DatabaseType dbType;

  private final String sql;


  public SQLStatement parse() {
    LexerEngine lexerEngine = LexerEngineFactory.newInstance(dbType, sql);
    lexerEngine.nextToken();
    return SQLParserFactory.newInstance(dbType, lexerEngine.getCurrentToken().getType(), lexerEngine).parse();
  }

  public static void main(String[] args) {
    SQLParsingEngine parsingEngine = new SQLParsingEngine(DatabaseType.MySQL, "select * from table");
    parsingEngine.parse();
  }


}
