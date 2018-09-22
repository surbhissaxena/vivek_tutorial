package com.ss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	
	private static JDBCUtil jdbcUtil;
      private JDBCUtil() {
		// TODO Auto-generated constructor stub
	}
      public static JDBCUtil getInstance(){
		    if(jdbcUtil == null){
		    	jdbcUtil=new JDBCUtil();
		    }
    	  
    	  return jdbcUtil;
      }
      public Connection getConnection() throws ClassNotFoundException,SQLException{
		
    		Connection con = null;
    	//	Properties prop=ReadPropertyUtils.Readproperty("jdbc");
    	//	Class.forName(prop.getProperty("driver"));
    		//con = DriverManager.getConnection(prop.getProperty("mysqlurl"),prop.getProperty("username"),prop.getProperty("password"));
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutorial_db?useSSL=false", "root", "root");
    		return con ;
    	  
      }
}
