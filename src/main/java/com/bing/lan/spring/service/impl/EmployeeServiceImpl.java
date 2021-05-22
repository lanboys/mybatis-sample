package com.bing.lan.spring.service.impl;

import com.bing.lan.mybatis.entity.Employee;
import com.bing.lan.mybatis.entity.User;
import com.bing.lan.mybatis.mapper.packagee.EmpMapper;
import com.bing.lan.mybatis.mapper.xml.EmployeeMapper;
import com.bing.lan.spring.service.EmployeeService;
import com.bing.lan.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeMapper employeeMapper;

  @Autowired
  EmpMapper empMapper;

  @Autowired
  UserService userService;

  @Transactional(rollbackFor = Exception.class)
  public void startTransaction() {
    Employee employee = new Employee();
    employee.setName("sqlSession");
    employee.setPhone("1234567891");

    employeeMapper.save(employee);
    System.out.println("startTransaction()");
    if (1 == 1) {
      throw new RuntimeException("error");
    }
    Employee limit1 = empMapper.limit();
    System.out.println("startTransaction(): " + limit1);
  }

  public void wrapperTransaction() {
    Employee employee = new Employee();
    employee.setName("sqlSession");
    employee.setPhone("1234567892");
    // 内部调用事务是不会生效的，因为跳过了动态代理的逻辑，可以debug试试，是跟普通调用一样直接跳过去了
    startTransaction();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void nestedTransaction() {
    Employee employee = new Employee();
    employee.setName("sqlSession");
    employee.setPhone("1234567891");

    employeeMapper.save(employee);
    System.out.println("nestedTransaction()");

    User user = new User();
    user.setName("sqlSession");
    user.setPhone("1234567891");
    userService.registerUser(user);
    System.out.println("nestedTransaction()");

    if (1 == 1) {
      throw new RuntimeException("nestedTransaction");
    }
  }
}

