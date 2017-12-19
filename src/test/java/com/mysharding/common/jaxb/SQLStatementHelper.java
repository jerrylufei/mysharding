package com.mysharding.common.jaxb;

import com.google.common.collect.Sets;
import com.mysharding.constant.DatabaseType;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import javax.xml.bind.JAXBContext;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Load SQL Template
 *
 * @author yaoyw
 * @date 2017-11-02
 */
public class SQLStatementHelper {

  private static final Map<String, SQLStatement> STATEMENT_MAP;

  private static final Map<String, SQLStatement> UNSUPPORTED_STATEMENT_MAP;

  static {
    STATEMENT_MAP = loadSqlStatements("sql");
    UNSUPPORTED_STATEMENT_MAP = loadSqlStatements("sql/unsupported");
  }

  private static Map<String , SQLStatement> loadSqlStatements(final String dictionary) {
    Map<String , SQLStatement> result = new HashMap<String, SQLStatement>();
    URL url = SQLStatementHelper.class.getClassLoader().getResource(dictionary);

    if (url == null) {
      return result;
    }

    File filePath = new File(url.getPath());
    if (!filePath.exists()) {
      return result;
    }

    File[] files = new File(url.getPath()).listFiles();

    for (File file : files) {
      if (file.isDirectory()) {
        for (File each : file.listFiles()) {
          fillSqlStatement(result, each);
        }
      } else {
        fillSqlStatement(result, file);
      }
    }

    return result;
  }

  private static void fillSqlStatement(final Map<String , SQLStatement> result, final File file) {
    try {
      SQLStatements sqlStatements =
        (SQLStatements) JAXBContext.newInstance(SQLStatements.class).createUnmarshaller().unmarshal(file);
      if (sqlStatements != null) {
        for (SQLStatement each : sqlStatements.getSqls()) {
          result.put(each.getId(), each);
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static Set<DatabaseType> getTypes(final String sqlId) {
    SQLStatement sqlStatement = STATEMENT_MAP.get(sqlId);
    if (sqlStatement == null || StringUtils.isBlank(sqlStatement.getTypes())) {
      return Sets.newHashSet(DatabaseType.values());
    }

    Set<DatabaseType> dbTypeSet = new HashSet<DatabaseType>(3);
    String[] dbTypes = sqlStatement.getTypes().split(",");
    for (String type : dbTypes) {
      dbTypeSet.add(DatabaseType.valueOf(type));
    }

    return dbTypeSet;
  }

  public static void main(String[] args) {
    System.out.println(SQLStatementHelper.STATEMENT_MAP);
  }
}
