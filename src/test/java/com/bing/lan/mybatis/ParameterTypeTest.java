package com.bing.lan.mybatis;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ParameterTypeTest {

  @Test
  public void testSqlSession() {
    SqlSession sqlSession = null;
    try {
      InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
      XMLConfigBuilder parser = new XMLConfigBuilder(resourceAsStream, null, null);
      Configuration configuration = parser.parse();

      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
      // 手动提交
      sqlSession = factory.openSession(false);
      // 执行sql

      //Employee employee = new Employee();
      //employee.setName("sqlSession");
      //employee.setPhone("123456789");
      //EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
      //mapper.save(employee);

      Map<String, Object> param = new HashMap<>();
      param.put("name", "sqlSession");
      param.put("phone", "123456789");
      /**
       * mapper.xml中定义的 parameterType，不表示传进去的类型一定要一样，只要
       * {@link org.apache.ibatis.scripting.defaults.DefaultParameterHandler#setParameters(java.sql.PreparedStatement)}能做好匹配就行了
       */
      sqlSession.insert("com.bing.lan.mybatis.mapper.xml.EmployeeMapper.save", param);

      // 手动提交
      sqlSession.commit();
      sqlSession.close();
    } catch (Exception e) {
      e.printStackTrace();
      if (sqlSession != null) {
        sqlSession.rollback();
        sqlSession.close();
      }
    }
  }
}
