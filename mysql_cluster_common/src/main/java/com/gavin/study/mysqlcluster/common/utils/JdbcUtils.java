package com.gavin.study.mysqlcluster.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gavin.study.mysqlcluster.common.utils.log.LogUtils;


/**
 * jdbc工具类
 * 
 * @author gavin
 * 
 */
public class JdbcUtils {

	private JdbcUtils() {
	}

	public static Connection getConnection(String driveName, String url,
			String user, String password) throws SQLException,
			ClassNotFoundException {
		Class.forName(driveName);
		return DriverManager.getConnection(url, user, password);
	}

	public static void free(ResultSet resultset, Statement st, Connection conn) {
		try {
			if (resultset != null)
				resultset.close();
		} catch (SQLException e) {
			LogUtils.getInstance()
					.errorSystem(LogUtils.GROUNDUPLOSS_MODULE, e);
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				LogUtils.getInstance().errorSystem(
						LogUtils.GROUNDUPLOSS_MODULE, e);
			} finally {
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						LogUtils.getInstance().errorSystem(
								LogUtils.GROUNDUPLOSS_MODULE, e);
					}
			}
		}
	}

	public static Statement createStatement(String driveName, String url,
			String user, String password, int maxRows) throws SQLException,
			ClassNotFoundException {

		// 1.建立连接
		Connection conn = getConnection(driveName, url, user, password);
		// 2.创建语句
		Statement st = conn.createStatement();
		// 3.执行语句
		st.setMaxRows(maxRows);

		return st;
	}
	
	
	public static Statement createStatement(String driveName, String url,
			String user, String password) throws SQLException,
			ClassNotFoundException {

		// 1.建立连接
		Connection conn = getConnection(driveName, url, user, password);
		// 2.创建语句
		Statement st = conn.createStatement();
		// 3.执行语句

		return st;
	}
}
