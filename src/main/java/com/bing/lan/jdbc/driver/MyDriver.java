//package com.bing.lan.jdbc.driver;
//
//import java.sql.Connection;
//import java.sql.DriverPropertyInfo;
//import java.sql.SQLException;
//import java.sql.SQLFeatureNotSupportedException;
//import java.util.Properties;
//import java.util.logging.Logger;
//
///**
// * Created by backend.
// */
//
//public class MyDriver implements java.sql.Driver {
//
//  static {
//    try {
//      java.sql.DriverManager.registerDriver(new MyDriver());
//    } catch (SQLException E) {
//      throw new RuntimeException("Can't register driver!");
//    }
//  }
//
//  public MyDriver() {
//    System.out.println("MyDriver(): ");
//  }
//
//  @Override
//  public Connection connect(String url, Properties info) throws SQLException {
//    return null;
//  }
//
//  @Override
//  public boolean acceptsURL(String url) throws SQLException {
//    return false;
//  }
//
//  @Override
//  public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
//    return new DriverPropertyInfo[0];
//  }
//
//  @Override
//  public int getMajorVersion() {
//    return 0;
//  }
//
//  @Override
//  public int getMinorVersion() {
//    return 0;
//  }
//
//  @Override
//  public boolean jdbcCompliant() {
//    return false;
//  }
//
//  @Override
//  public Logger getParentLogger() throws SQLFeatureNotSupportedException {
//    return null;
//  }
//}
