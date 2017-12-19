package com.mysharding.util;

import com.google.common.base.CharMatcher;

/**
 * Created by yaoyw on 2017/11/3.
 */
public class SQLUtil {

  /**
   * Get exactly value for SQL expression.
   *
   * <p>remove special char for SQL expression</p>
   *
   * @param value SQL expression
   * @return exactly SQL expression
   */
  public static String getExactlyValue(final String value) {
    return null == value ? null : CharMatcher.anyOf("[]`'\"").removeFrom(value);
  }

  public static void main(String[] args) {
    System.out.println(SQLUtil.getExactlyValue("`loan_app`"));
  }
}
