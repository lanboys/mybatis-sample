package com.bing.lan.spring;

import com.bing.lan.spring.service.EmployeeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * org.springframework.transaction.UnexpectedRollbackException:
 * Transaction rolled back because it has been marked as rollback-only
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TransactionTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  EmployeeService employeeService;

  @Test
  public void startTransaction() {
    employeeService.startTransaction(false);
    System.out.println("startTransaction(): ");
  }

  @Test
  public void wrapperTransaction() {
    employeeService.wrapperTransaction(false);
    System.out.println("wrapperTransaction(): ");
  }
}