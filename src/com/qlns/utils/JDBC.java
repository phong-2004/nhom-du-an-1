
package com.qlns.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tuann
 */
public class JDBC {
   private   static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
  private   static String dburl = "jdbc:sqlserver://localhost;database=QLNS1;encrypt=false";
  private   static String user = "SA";
  private   static String pass = "mtuan120304";
    
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static ResultSet query(String sql, Object...args) {
        try {
            PreparedStatement stmt = JDBC.getStmt(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection connection = DriverManager.getConnection(dburl, user, pass);
        PreparedStatement pstmt = null;
        if(sql.trim().startsWith("{")){
            pstmt = connection.prepareCall(sql);
        }
        else{
            pstmt = connection.prepareStatement(sql);
        }
        for(int i=0;i<args.length;i++){
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
}
