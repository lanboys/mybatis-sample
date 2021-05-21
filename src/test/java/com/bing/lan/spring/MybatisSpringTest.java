package com.bing.lan.spring;

import com.bing.lan.mybatis.entity.Employee;
import com.bing.lan.mybatis.mapper.packagee.EmpMapper;
import com.bing.lan.mybatis.mapper.xml.EmployeeMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MybatisSpringTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  EmployeeMapper employeeMapper;

  @Autowired
  EmpMapper empMapper;

  @Test
  public void testMapper() {
    Employee limit = employeeMapper.limit();
    System.out.println("testSqlSession(): " + limit);

    Employee limit1 = empMapper.limit();
    System.out.println("testSqlSession(): " + limit1);
  }
}