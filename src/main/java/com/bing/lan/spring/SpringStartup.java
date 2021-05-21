package com.bing.lan.spring;

import com.bing.lan.mybatis.entity.Employee;
import com.bing.lan.mybatis.mapper.xml.EmployeeMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringStartup {

  public static void main(String[] args) {
    // 手动启动spring容器
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    EmployeeMapper mapper = context.getBean(EmployeeMapper.class);
    Employee limit = mapper.limit();
    System.out.println("main(): " + limit);
  }
}