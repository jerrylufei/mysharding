package com.mysharding.common.jaxb;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sqls")
@Getter
public final class SQLStatements {
    
    @XmlElement(name = "sql")
    private List<SQLStatement> sqls = new ArrayList<SQLStatement>();
}
