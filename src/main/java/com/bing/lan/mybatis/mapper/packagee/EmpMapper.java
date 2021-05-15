package com.bing.lan.mybatis.mapper.packagee;

import com.bing.lan.mybatis.entity.Employee;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 配置文件中
 * <mappers>
 * <mapper resource="mapper/EmployeeMapper.xml"/>
 * <package name="com.bing.lan.mybatis.mapper.packagee"/>
 * </mappers>
 *
 * <p>
 * 通过 package 配置的包里面，不能包含 xml 中已经解析的 mapper 类，报错
 * {@link org.apache.ibatis.binding.MapperRegistry#addMapper(java.lang.Class)}
 * <p>
 * 优先解析xml,并通过namespace解析对应的类，然后再解析 package 中的类，不能重复解析
 */
public interface EmpMapper {

  // 调用的时候会报错，因为找不到对应的sql
  List<Employee> list();

  @Select("select * from employee limit 0,1")
  Employee limit();
}
