package com.mysharding.parsing.lexer.analyzer;

import com.mysharding.parsing.lexer.token.DefaultKeyword;
import com.mysharding.parsing.lexer.token.Keyword;
import com.mysharding.parsing.lexer.token.TokenType;

import java.util.HashMap;
import java.util.Map;

/**
 * 词法标记字典
 *
 * @author yaoyw
 */
public class Dictionary {

  private final Map<String, Keyword> keywordMap = new HashMap<String, Keyword>(1024);

  public Dictionary(final Keyword... dialectKeywords) {
    fill(dialectKeywords);
  }

  private void fill(Keyword... dialectKeywords) {
    for (DefaultKeyword defaultKeyword : DefaultKeyword.values()) {
      keywordMap.put(defaultKeyword.name(), defaultKeyword);
    }

    for (Keyword keyword : dialectKeywords) {
      keywordMap.put(keyword.toString(), keyword);
    }
  }

  protected TokenType findTokenType(final String literals, final TokenType defaultTokenType) {
    String key = (literals == null) ? null : literals.toUpperCase();
    return keywordMap.containsKey(key) ? keywordMap.get(key) : defaultTokenType;
  }

  TokenType findTokenType(final String literals) {
    String key = null == literals ? null : literals.toUpperCase();
    if (keywordMap.containsKey(key)) {
      return keywordMap.get(key);
    }
    throw new IllegalArgumentException();
  }

}
