package com.bing.lan.spring;

import com.bing.lan.spring.service.EmployeeService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

/**
 * org.springframework.transaction.UnexpectedRollbackException:
 * Transaction rolled back because it has been marked as rollback-only
 * <p>
 * {@link AbstractPlatformTransactionManager#processCommit(org.springframework.transaction.support.DefaultTransactionStatus)}
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class NestedTransactionTest extends AbstractJUnit4SpringContextTests {

  @Autowired
  EmployeeService employeeService;

  @Test
  public void nestedTransaction() {
    employeeService.nestedTransaction(false);
    System.out.println("nestedTransaction(): ");
  }
}