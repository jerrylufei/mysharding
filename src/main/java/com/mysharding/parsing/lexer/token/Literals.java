package com.mysharding.parsing.lexer.token;

/**
 * 词法字面量标记
 *
 * @author yaoyw
 */
public enum Literals implements TokenType {
    INT, FLOAT, HEX, CHARS, IDENTIFIER, VARIABLE
}
