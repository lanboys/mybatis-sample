package com.bing.lan.mybatis.mapper.xml;

import com.bing.lan.mybatis.entity.User;

import java.util.List;

public interface UserMapper {

  void save(User e);

  List<User> list();
}
