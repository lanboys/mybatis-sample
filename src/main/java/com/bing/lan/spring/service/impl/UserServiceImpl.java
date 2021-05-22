package com.bing.lan.spring.service.impl;

import com.bing.lan.mybatis.entity.User;
import com.bing.lan.mybatis.mapper.xml.UserMapper;
import com.bing.lan.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Transactional
  public void registerUser(User user) {
    userMapper.save(user);
    if (1 == 1) {
      throw new RuntimeException("nestedTransaction");
    }
  }
}
