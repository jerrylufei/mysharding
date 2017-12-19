package com.mysharding.context.table;

import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/3.
 */
@ToString
public class Tables {

  private List<Table> tables = new ArrayList<Table>();


  public void add(Table table) {
    tables.add(table);
  }

}
