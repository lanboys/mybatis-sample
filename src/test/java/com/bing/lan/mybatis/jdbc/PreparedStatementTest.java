package com.bing.lan.mybatis.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by backend.
 */

public class PreparedStatementTest {

  @Test
  public void testPreparedStatement() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://14.18.43.48:3306/jdbc?user=root&password=KHwl20170731!@#");

      stmt = conn.prepareStatement("select * from employee where name = ?");
      stmt.setString(1, "meimei1");
      rs = stmt.executeQuery();
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
