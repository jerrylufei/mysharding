package com.mysharding.parsing.parser.sql;

import com.mysharding.constant.SQLType;
import com.mysharding.context.table.Tables;
import com.mysharding.parsing.parser.token.SQLToken;

import java.util.List;

/**
 * Created by yaoyw on 2017/11/1.
 */
public interface SQLStatement {

  /**
   * Get SQL type.
   *
   * @return SQL type
   */
  SQLType getType();

  /**
   * Get tables.
   *
   * @return tables
   */
  Tables getTables();

  /**
   * Get SQL Tokens.
   *
   * @return SQL Tokens
   */
  List<SQLToken> getSqlTokens();

}
