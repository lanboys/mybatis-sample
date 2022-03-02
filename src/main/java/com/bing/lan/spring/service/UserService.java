package com.bing.lan.spring.service;

import com.bing.lan.mybatis.entity.User;

public interface UserService {

  public void registerUser(User user, boolean throwException);
}
