package com.prewarningapp.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String URL = "jdbc:mysql://112.74.50.213:3306/warning_light";

	private static final String USER = "root";

	private static final String PASSWORD = "888888";

	private static Connection conn = null;

	public static Connection getConn() {
		// 1.����������������ƣ�
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 2.������ݿ�����
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) throws Exception {

		DBUtil.getConn();

		// 3.ͨ�����ݿ�����Ӳ������ݿ⣨��ɾ�Ĳ飩
		Statement statement = conn.createStatement();
		// 4.ͨ����ѯ���ؽ��
		ResultSet rs = statement.executeQuery("select * from tpplan limit 30");
		// 5.ѭ��ȥ�� rs �еĽ��
		while (rs.next()) {
			System.out.println(rs.getObject(3));
		}

	}

}
