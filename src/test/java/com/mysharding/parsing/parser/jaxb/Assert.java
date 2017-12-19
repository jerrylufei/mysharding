package com.mysharding.parsing.parser.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by yaoyw on 2017/11/1.
 */
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public final class Assert {
  @XmlAttribute
  private String id;
  @XmlElement(name = "tables")
  private Tables tables;

}
