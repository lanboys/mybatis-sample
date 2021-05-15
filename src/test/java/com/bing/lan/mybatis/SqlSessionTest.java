package com.bing.lan.mybatis;

import com.bing.lan.mybatis.entity.Employee;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class SqlSessionTest {

  @Test
  public void testSqlSession() {
    Employee employee = new Employee();
    employee.setName("sqlSession");
    employee.setPhone("123456789");
    SqlSession sqlSession = null;
    try {
      InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
      XMLConfigBuilder parser = new XMLConfigBuilder(resourceAsStream, null, null);
      Configuration configuration = parser.parse();

      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
      // 手动提交
      sqlSession = factory.openSession(false);
      // 执行sql
      sqlSession.insert("com.bing.lan.mybatis.mapper.EmployeeMapper.save", employee);
      //if (1 == 1) {
      //  throw new RuntimeException("异常中断");
      //}
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
