package com.bing.lan.mybatis;

import com.bing.lan.mybatis.entity.Employee;
import com.bing.lan.mybatis.mapper.xml.EmployeeMapper;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

public class MapperInterfaceTest {

  @Test
  public void testSqlSession() {
    Employee employee = new Employee();
    employee.setName("emp-name");
    employee.setPhone("emp-phone");
    SqlSession sqlSession = null;
    try {
      InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
      XMLConfigBuilder parser = new XMLConfigBuilder(resourceAsStream, null, null);
      Configuration configuration = parser.parse();

      SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);
      // 手动提交
      sqlSession = factory.openSession(false);
      // 执行sql
      //sqlSession.select("com.bing.lan.mybatis.mapper.xml.EmployeeMapper.limit", null);
      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
      Employee limit = mapper.limit();
      System.out.println("testSqlSession(): " + limit);

      //EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
      //List<Employee> list = empMapper.list();
      //System.out.println("testSqlSession(): " + list);

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
