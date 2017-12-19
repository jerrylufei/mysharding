package com.mysharding.parsing.lexer.token;

/**
 * 词法关键词
 *
 * @author yaoyw
 */
public enum DefaultKeyword implements Keyword {
    
    /*
    Common
     */
    SCHEMA,
    DATABASE,
    TABLE,
    COLUMN,
    VIEW,
    INDEX,
    TRIGGER,
    PROCEDURE,
    TABLESPACE,
    FUNCTION,
    SEQUENCE,
    CURSOR,
    FROM,
    TO,
    OF,
    IF,
    ON,
    FOR,
    WHILE,
    DO,
    NO,
    BY,
    WITH,
    WITHOUT,
    TRUE,
    FALSE,
    TEMPORARY,
    TEMP,
    COMMENT,
    
    /*
    Create
    */
    CREATE,
    REPLACE,
    BEFORE,
    AFTER,
    INSTEAD,
    EACH,
    ROW,
    STATEMENT,
    EXECUTE,
    BITMAP,
    NOSORT,
    REVERSE,
    COMPILE,
    
    /*
    Alter
     */
    ALTER,
    ADD,
    MODIFY,
    RENAME,
    ENABLE,
    DISABLE,
    VALIDATE,
    USER,
    IDENTIFIED,
    
    /*
    Truncate
     */
    TRUNCATE,
    
    /*
    Drop
     */
    DROP,
    CASCADE,
    
    /*
    Insert
     */
    INSERT,
    INTO,
    VALUES,
    
    /*
    Update
     */
    UPDATE,
    SET,
    
    /*
    Delete
     */
    DELETE,
    
    /*
    Select
     */
    SELECT,
    DISTINCT,
    AS,
    CASE,
    WHEN,
    ELSE,
    THEN,
    END,
    LEFT, 
    RIGHT,
    FULL,
    INNER,
    OUTER,
    CROSS,
    JOIN,
    USE,
    USING,
    NATURAL,
    WHERE,
    ORDER,
    ASC,
    DESC,
    GROUP,
    HAVING,
    UNION,
    
    /*
    Other Command
     */
    DECLARE,
    GRANT,
    FETCH,
    REVOKE,
    CLOSE,
    
    /*
    Others
     */
    CAST,
    NEW,
    ESCAPE,
    LOCK,
    SOME,
    LEAVE,
    ITERATE,
    REPEAT,
    UNTIL,
    OPEN,
    OUT,
    INOUT,
    OVER,
    ADVISE,
    SIBLINGS,
    LOOP,
    EXPLAIN,
    DEFAULT,
    EXCEPT,
    INTERSECT,
    MINUS,
    PASSWORD,
    LOCAL,
    GLOBAL,
    STORAGE,
    DATA,
    COALESCE,
    
    /*
    Types
     */
    CHAR,
    CHARACTER,
    VARYING,
    VARCHAR,
    VARCHAR2,
    INTEGER,
    INT,
    SMALLINT,
    DECIMAL,
    DEC,
    NUMERIC,
    FLOAT,
    REAL,
    DOUBLE,
    PRECISION,
    DATE,
    TIME,
    INTERVAL,
    BOOLEAN,
    BLOB,
    
    /*
    Conditionals
     */
    AND,
    OR,
    XOR,
    IS,
    NOT,
    NULL,
    IN,
    BETWEEN,
    LIKE,
    ANY,
    ALL,
    EXISTS,
    
    /*
    Functions
     */
    AVG,
    MAX,
    MIN,
    SUM,
    COUNT,
    GREATEST,
    LEAST,
    ROUND,
    TRUNC,
    POSITION,
    EXTRACT,
    LENGTH,
    CHAR_LENGTH,
    SUBSTRING,
    SUBSTR,
    INSTR,
    INITCAP,
    UPPER,
    LOWER,
    TRIM,
    LTRIM,
    RTRIM,
    BOTH,
    LEADING,
    TRAILING,
    TRANSLATE,
    CONVERT,
    LPAD,
    RPAD,
    DECODE,
    NVL,
    
    /*
    Constraints
     */
    CONSTRAINT,
    UNIQUE,
    PRIMARY,
    FOREIGN,
    KEY,
    CHECK,
    REFERENCES
}
