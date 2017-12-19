package com.mysharding.parsing.parser.clause;

import com.mysharding.parsing.lexer.LexerEngine;
import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Keyword;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/2.
 */
@RequiredArgsConstructor
public class DistinctClauseParser implements SQLClauseParser {

  private final LexerEngine lexerEngine;


  /**
   * Parse distinct.
   *
   * 申明为final,是因为不管哪个dbType,处理distinct的逻辑都一样
   * 不需要重写
   *
   * select distinct name from A
   *
   */
  public final void parse() {
    System.out.println("parse distinct");
    lexerEngine.skipAll(DefaultKeyword.ALL);
    List<Keyword> distinctKeywords = new ArrayList<Keyword>();
    distinctKeywords.add(DefaultKeyword.DISTINCT);
    distinctKeywords.addAll(Arrays.asList(getSynonymousKeywordsForDistinct()));
    lexerEngine.unsupportedIfEqual(distinctKeywords.toArray(new Keyword[distinctKeywords.size()]));
  }

  protected Keyword[] getSynonymousKeywordsForDistinct() {
    return new Keyword[0];
  }

}
