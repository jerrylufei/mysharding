package com.mysharding.parsing.parser.jaxb;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/2.
 */
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Tables {
    @XmlElement(name = "table")
    private List<Table> tables;


}
