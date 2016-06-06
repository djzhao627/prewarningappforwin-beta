package com.prewarningapp.tools;

import java.sql.*;

public class ConmysqlManager {
	private String url = "jdbc:mysql://127.0.0.1:3306/prewarningdb?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull&amp;transformedBitIsBoolean=true";
	private String user = "root";
	private String pwd = "888888";
	private Connection con;
	private PreparedStatement psmt;
	public ConmysqlManager() {
		// 1.加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("加载驱动异常！");
			e.printStackTrace();
		}
		// 2.建立连接
		try {
			con = DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return con;
	}
	
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(String sql) {
		try {
			psmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return psmt;
	}
}
