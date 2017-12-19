package com.mysharding.parsing.parser.sql.dql;

import com.mysharding.constant.SQLType;
import com.mysharding.parsing.parser.sql.AbstractSQLStatement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DQL statement.
 *
 * @author zhangliang
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DQLStatement extends AbstractSQLStatement {
    
    public DQLStatement() {
        super(SQLType.DQL);
    }
}
