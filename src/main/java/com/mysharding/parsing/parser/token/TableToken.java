package com.mysharding.parsing.parser.token;

import com.mysharding.util.SQLUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/3.
 */
@RequiredArgsConstructor
@Getter
public class TableToken implements SQLToken {

  private final int beginPosition;

  private final String originalLiterals;

  public String getTableName() {
    return SQLUtil.getExactlyValue(originalLiterals);
  }
}
