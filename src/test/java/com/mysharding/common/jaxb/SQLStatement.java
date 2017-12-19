package com.mysharding.common.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public final class SQLStatement {
    
    @XmlAttribute
    private String id;
    
    @XmlAttribute(name = "value")
    private String sql;
    
    @XmlAttribute(name = "type")
    private String types;
}
