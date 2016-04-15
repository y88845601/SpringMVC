package com.xiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CallJDBC {
	
//	private static String orUurl = "jdbc:oracle:thin:@192.168.1.141:1521:orcl";
//	private static String orUsername = "linlang";
//	private static String orPwd = "linlang";
	
	private static Connection conn;
	private static ResultSet rs;
	private static Statement stmt;
	
	/**
	 * oracle数据库连接
	 * 修改者名字   xxy
	 * 修改日期   2014-5-24
	 * 修改内容
	 * @param @param orUurl
	 * @param @param orUsername
	 * @param @param orPwd
	 * @param @return
	 * @return String
	 */
	public String oracleJDBC(String orUurl,String orUsername,String orPwd){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			return "驱动类加载失败！";
		}
		try {
			conn = DriverManager.getConnection(orUurl, orUsername, orPwd);
		} catch (SQLException e) {
			return "驱动类加载成功，数据库连接失败！";
		}
		return "数据库连接成功";
	}
	
	/**
	 * 执行SQL语句
	 * 修改者名字   xxy
	 * 修改日期   2014-5-24
	 * 修改内容
	 * @param @return
	 * @return String
	 */
	public String findData(){
		CallJDBC cj = new CallJDBC();
		String jdbcOK = cj.oracleJDBC("jdbc:oracle:thin:@192.168.1.141:1521:linlang","linlang","linlang");
		System.out.println(jdbcOK);
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			return "创建Statement失败！";
		}
		
		String sql = "select count(*) count from t_card";
		String aa = "";
		try {
			System.out.println("查询语句开始");
			rs = stmt.executeQuery(sql);
			System.out.println("查询语句结束");
		} catch (SQLException e) {
			return "执行SQL语句失败！";
		}
		
		try {
			while(rs.next()){
				aa = rs.getInt("count")+"";
			}
		} catch (SQLException e1) {
			return "获取数据错误！";
		}
		
		try {
			conn.close();
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			return "数据库关闭异常！";
		}
		return aa;
	}
	
	public static void main(String[] args){
		CallJDBC cj = new CallJDBC();
		System.out.println(cj.findData());
	}

}
