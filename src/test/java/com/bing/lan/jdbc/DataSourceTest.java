package com.bing.lan.jdbc;

import com.bing.lan.jdbc.dataSource.UnpooledDataSource;

import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by backend.
 */

public class DataSourceTest {

  @Test
  public void testDataSource() {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
      UnpooledDataSource dataSource = new UnpooledDataSource("com.mysql.jdbc.Driver",
          "jdbc:mysql://14.18.43.48:3306/jdbc", "root", "KHwl20170731!@#");
      conn = dataSource.getConnection();

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
