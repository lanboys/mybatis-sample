package com.bing.lan.mybatis.customLog;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lb on 2021/5/13.
 *
 * mybatis的日志关键类{@link LogFactory}
 */

public class MyJdkLoggingImpl implements Log {

  private final Logger log;

  static {
    // 配置jdk日志
    URL logging = MyJdkLoggingImpl.class.getResource("/jdk-logging.properties");
    System.setProperty("java.util.logging.config.file", logging.getPath());
  }

  public MyJdkLoggingImpl(String clazz) {
    log = Logger.getLogger(clazz);
  }

  @Override
  public boolean isDebugEnabled() {
    return log.isLoggable(Level.INFO);
  }

  @Override
  public boolean isTraceEnabled() {
    return log.isLoggable(Level.INFO);
  }

  @Override
  public void error(String s, Throwable e) {
    log.log(Level.SEVERE, s, e);
  }

  @Override
  public void error(String s) {
    log.log(Level.SEVERE, s);
  }

  @Override
  public void warn(String s) {
    log.log(Level.WARNING, s);
  }

  @Override
  public void debug(String s) {
    log.log(Level.INFO, s);
  }

  @Override
  public void trace(String s) {
    log.log(Level.INFO, s);
  }
}
