package com.bing.lan.mybatis.entity;

public class User {

  private String name;
  private Long id;
  private String phone;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", id=" + id +
        ", phone='" + phone + '\'' +
        '}';
  }
}
