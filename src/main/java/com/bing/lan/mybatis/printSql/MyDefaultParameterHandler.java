package com.bing.lan.mybatis.printSql;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by backend.
 *
 * 查看 {@link DefaultParameterHandler}
 */

public class MyDefaultParameterHandler implements ParameterHandler {

  private final TypeHandlerRegistry typeHandlerRegistry;
  private final MappedStatement mappedStatement;
  private final Object parameterObject;
  private final BoundSql boundSql;
  private final Configuration configuration;
  private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//===================相对DefaultParameterHandler增加的代码====================

  public MyDefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
    this.mappedStatement = mappedStatement;
    this.configuration = mappedStatement.getConfiguration();
    this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
    this.parameterObject = parameterObject;
    this.boundSql = boundSql;
  }

  @Override
  public Object getParameterObject() {
    return parameterObject;
  }

  @Override
  public void setParameters(PreparedStatement ps) {
    ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    if (parameterMappings != null) {
      String sql = boundSql.getSql();//===================相对DefaultParameterHandler增加的代码====================
      List<Object> parameters = new ArrayList<>();//===================相对DefaultParameterHandler增加的代码====================
      for (int i = 0; i < parameterMappings.size(); i++) {
        ParameterMapping parameterMapping = parameterMappings.get(i);
        if (parameterMapping.getMode() != ParameterMode.OUT) {
          Object value;
          String propertyName = parameterMapping.getProperty();
          if (boundSql.hasAdditionalParameter(propertyName)) { // issue #448 ask first for additional params
            value = boundSql.getAdditionalParameter(propertyName);
          } else if (parameterObject == null) {
            value = null;
          } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            value = parameterObject;
          } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            value = metaObject.getValue(propertyName);
          }
          TypeHandler typeHandler = parameterMapping.getTypeHandler();
          JdbcType jdbcType = parameterMapping.getJdbcType();
          if (value == null && jdbcType == null) {
            jdbcType = configuration.getJdbcTypeForNull();
          }
          try {
            typeHandler.setParameter(ps, i + 1, value, jdbcType);
            parameters.add(value);//===================相对DefaultParameterHandler增加的代码====================
          } catch (TypeException | SQLException e) {
            throw new TypeException("Could not set parameters for mapping: " + parameterMapping + ". Cause: " + e, e);
          }
        }
      }
      //===================相对DefaultParameterHandler增加的代码====================
      Log statementLog = mappedStatement.getStatementLog();
      try {
        if (statementLog.isDebugEnabled()) {
          sql = handleListParameter(sql, parameters);
          statementLog.debug("==>  Preparing Complete SQL: " + SqlSourceBuilder.removeExtraWhitespaces(sql));
        }
      } catch (Exception e) {
        statementLog.error("Print complete sql exception ", e);
      }
      //===================相对DefaultParameterHandler增加的代码====================
    }
  }

  //===================相对DefaultParameterHandler增加的代码====================
  /**
   * 参数转换拼接替换
   * <p>
   * 还需要优化
   */
  private String handleListParameter(String sql, Collection<?> col) {
    if (col == null || col.isEmpty()) {
      return sql;
    }
    for (Object obj : col) {
      String value;
      Class<?> objClass = obj.getClass();
      // 类型匹配输出
      if (isPrimitiveOrPrimitiveWrapper(objClass)) {
        value = obj.toString();
      } else if (objClass.isAssignableFrom(String.class)) {
        value = "\'" + obj.toString() + "\'";
      } else if (objClass.isAssignableFrom(Date.class)) {
        value = "\'" + format.format((Date) obj) + "\'";
      } else {
        value = "\'" + obj.toString() + "\'";
      }
      sql = sql.replaceFirst("\\?", value);
    }
    return sql;
  }

  /**
   * 是否基本数据类型或者基本数据类型的包装类
   */
  private boolean isPrimitiveOrPrimitiveWrapper(Class<?> parameterObjectClass) {
    return parameterObjectClass.isPrimitive() ||
        (parameterObjectClass.isAssignableFrom(BigDecimal.class) ||
            parameterObjectClass.isAssignableFrom(Byte.class) ||
            parameterObjectClass.isAssignableFrom(Short.class) ||
            parameterObjectClass.isAssignableFrom(Integer.class) ||
            parameterObjectClass.isAssignableFrom(Long.class) ||
            parameterObjectClass.isAssignableFrom(Double.class) ||
            parameterObjectClass.isAssignableFrom(Float.class) ||
            parameterObjectClass.isAssignableFrom(Character.class) ||
            parameterObjectClass.isAssignableFrom(Boolean.class));
  }
  //===================相对DefaultParameterHandler增加的代码====================
}
