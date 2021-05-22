package com.bing.lan.spring.service;

public interface EmployeeService {

  public void startTransaction();

  public void wrapperTransaction();

  public void nestedTransaction();
}