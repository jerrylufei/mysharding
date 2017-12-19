package com.mysharding.context.table;

import com.google.common.base.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Created by yaoyw on 2017/11/3.
 */
@RequiredArgsConstructor
@Getter
@ToString
public class Table {

  private final String name;

  private final Optional<String> alias;
}
