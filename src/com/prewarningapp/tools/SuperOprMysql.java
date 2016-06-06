package com.prewarningapp.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SuperOprMysql {
	protected ConmysqlManager cm ;
	protected Connection con;
	protected PreparedStatement psmt;
	protected ResultSet rs;
	protected String sql;
	public SuperOprMysql() {
		cm =new ConmysqlManager();
	}
}
