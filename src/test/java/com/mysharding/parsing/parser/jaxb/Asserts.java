package com.mysharding.parsing.parser.jaxb;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/1.
 */
@XmlRootElement(name = "asserts")
@Getter
public final class Asserts {

  @XmlElement(name = "assert")
  private List<Assert> asserts = new ArrayList<Assert>();
}
