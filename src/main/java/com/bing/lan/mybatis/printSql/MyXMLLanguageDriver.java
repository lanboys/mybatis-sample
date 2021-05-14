package com.bing.lan.mybatis.printSql;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

/**
 * Created by backend.
 */

public class MyXMLLanguageDriver extends XMLLanguageDriver {

  @Override
  public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
    // 替换默认的 ParameterHandler
    return new MyDefaultParameterHandler(mappedStatement, parameterObject, boundSql);
  }
}
