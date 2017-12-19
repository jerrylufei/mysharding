package com.mysharding.parsing.parser.sql;

import com.mysharding.constant.SQLType;
import com.mysharding.context.table.Tables;
import com.mysharding.parsing.parser.token.SQLToken;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/3.
 */
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class AbstractSQLStatement implements SQLStatement {

  private final SQLType type;

  private final Tables tables = new Tables();

  private final List<SQLToken> sqlTokens = new LinkedList<SQLToken>();

  private int parametersIndex;

}
