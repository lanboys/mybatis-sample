package com.bing.lan.mybatis.customLog;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

/**
 * Created by lb on 2021/5/13.
 */
public class MyConsoleHandler extends ConsoleHandler {

  @Override
  public void publish(LogRecord record) {
    // super.publish(record);
    // 继承ConsoleHandler，所以日志级别一定是INFO及以上, 看ConsoleHandler构造方法
    System.out.println("publish(): " + record.getMessage());
  }
}
