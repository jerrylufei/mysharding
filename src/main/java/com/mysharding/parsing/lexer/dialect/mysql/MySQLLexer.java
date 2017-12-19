package com.mysharding.parsing.lexer.dialect.mysql;

import com.mysharding.parsing.lexer.analyzer.Dictionary;
import com.mysharding.parsing.lexer.Lexer;

/**
 * Created by yaoyw on 2017/10/31.
 */

public class MySQLLexer extends Lexer {
  private static Dictionary dictionary = new Dictionary(MySQLKeyword.values());

  public MySQLLexer(final String input) {
    super(input, dictionary);
  }




}
