package com.bing.lan.mybatis.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by backend.
 */

public class StatementTest {

  @Test
  public void testStatement() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      // DriverManager 中已经执行过Class.forName
      //Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://14.18.43.48:3306/jdbc?user=root&password=KHwl20170731!@#");

      stmt = conn.createStatement();
      rs = stmt.executeQuery("select * from employee");
      while (rs.next()) {
        System.out.println(rs.getString("name"));
      }
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
