package com.bing.lan.spring.service;

public interface EmployeeService {

  public void startTransaction(boolean throwException);

  public void wrapperTransaction(boolean throwException);

  public void nestedTransaction(boolean throwException);
}