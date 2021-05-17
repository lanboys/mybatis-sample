package com.bing.lan.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by backend.
 */

public class AutoCommitTest {

  @Test
  public void testAutoCommit() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://14.18.43.48:3306/jdbc?user=root&password=KHwl20170731!@#");
      String sql = "INSERT INTO `jdbc`.`employee`(`name`, `phone`) VALUES ('meimei6', '1234567896')";

      //默认是隐式开始事务，并且自动提交
      conn.setAutoCommit(false);
      //不手动开启事务，是否自动提交事务，取决于 autocommit
      //事务中变更自动提交设置，可能会导致当前事务提交，详情见笔记
      stmt = conn.prepareStatement(sql);
      int i = stmt.executeUpdate();
      System.out.println("testAutoCommit(): " + i);
      //手动提交事务
      conn.commit();
      //恢复默认设置
      conn.setAutoCommit(true);
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
