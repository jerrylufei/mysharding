package com.mysharding.parsing.parser.sql.dql.select;

import com.mysharding.parsing.parser.sql.dql.DQLStatement;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by yaoyw on 2017/11/1.
 */
public class SelectStatement extends DQLStatement {

  @Getter(AccessLevel.NONE)
  @Setter(AccessLevel.NONE)
  private SelectStatement subQueryStatement;



  public void setSubQueryStatement(final SelectStatement subQueryStatement) {
    this.subQueryStatement = subQueryStatement;
    setParametersIndex(subQueryStatement.getParametersIndex());
  }

}
