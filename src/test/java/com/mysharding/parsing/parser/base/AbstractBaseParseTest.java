package com.mysharding.parsing.parser.base;

import com.mysharding.common.jaxb.SQLStatementHelper;
import com.mysharding.constant.DatabaseType;
import com.mysharding.parsing.parser.jaxb.Assert;
import com.mysharding.parsing.parser.jaxb.Asserts;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yaoyw on 2017/11/1.
 */
public class AbstractBaseParseTest {

  protected static Collection<Object[]> wrapParameters() {
    List<Object[]> result = new ArrayList<Object[]>();

    URL url = AbstractBaseParseTest.class.getClassLoader().getResource("parser/");
    File[] files = new File(url.getPath()).listFiles();

    for (File file : files) {
      result.addAll(wrapParameters(file));
    }

    return result;
  }

  private static Collection<Object[]> wrapParameters(final File file) {
    List<Object[]> result = new ArrayList<Object[]>();

    Asserts asserts = loadAsserts(file);
    List<Assert> assertList = asserts.getAsserts();
    for (Assert each : assertList) {
      for (DatabaseType dbType : SQLStatementHelper.getTypes(each.getId())) {
        result.add(getDataParameters(each, dbType));
      }
    }

    return result;
  }

  private static Object[] getDataParameters(final Assert assertObj, final DatabaseType type) {
    Object[] result = new Object[3];
    result[0] = assertObj.getId();
    result[1] = type;
    result[2] = assertObj;

    return result;
  }

  private static Asserts loadAsserts(final File file) {
    try {
      return (Asserts) JAXBContext.newInstance(Asserts.class).createUnmarshaller().unmarshal(file);
    } catch (JAXBException e) {
      throw new RuntimeException("load error");
    }
  }

  public static void main(String[] args) {
    URL url = AbstractBaseParseTest.class.getClassLoader().getResource("parser/");
    File[] files = new File(url.getPath()).listFiles();

    for (File file : files) {
      Asserts asserts = loadAsserts(file);
      System.out.println(asserts.getAsserts());
    }
  }
}
