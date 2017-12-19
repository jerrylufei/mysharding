package com.mysharding.parsing.parser.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by yaoyw on 2017/11/2.
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public final class Table {
  @XmlAttribute
  private String name;

  @XmlAttribute
  private String alias;
}
