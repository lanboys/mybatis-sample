package com.bing.lan.mybatis.mapper.xml;

import com.bing.lan.mybatis.entity.Employee;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * xml跟注解混合使用, xml会先解析，并且两者之间不能重复，报错
 * <p>
 * Caused by: java.lang.IllegalArgumentException: Mapped Statements collection already contains
 * value for com.bing.lan.mybatis.mapper.xml.EmployeeMapper.list. please check mapper/EmployeeMapper.xml
 * and com/bing/lan/mybatis/mapper/xml/EmployeeMapper.java (best guess)
 */
public interface EmployeeMapper {

  void save(Employee e);

  List<Employee> list();

  @Select("select * from employee limit 0,1")
  Employee limit();
}
