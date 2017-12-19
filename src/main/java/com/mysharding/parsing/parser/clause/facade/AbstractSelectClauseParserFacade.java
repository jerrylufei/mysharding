package com.mysharding.parsing.parser.clause.facade;

import com.mysharding.parsing.parser.clause.DistinctClauseParser;
import com.mysharding.parsing.parser.clause.TableReferencesClauseParser;
import com.mysharding.parsing.parser.clause.WhereClauseParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by yaoyw on 2017/11/2.
 */
@Getter
@RequiredArgsConstructor
public class AbstractSelectClauseParserFacade {

  private final DistinctClauseParser distinctClauseParser;
  private final TableReferencesClauseParser tableReferencesClauseParser;
  private final WhereClauseParser whereClauseParser;

}
