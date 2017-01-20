package platform.base.db.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import platform.base.db.model.ConnectParamBean;

public class DBConnection implements Serializable {

	private static final long serialVersionUID = 7374923665427448572L;

	public static Map connectMap = new HashMap();

	public static Connection getConnection(ConnectParamBean param) {
		String url = param.getUrl();
//		if (connectMap.get(url) != null) {
//			Connection connect = (Connection) connectMap.get(url);
//			try {
//				if (connect.isClosed()) {
//					connect = null;
//					Connection connect1 = connect(param);
//					connectMap.put(url, connect1);
//					return connect1;
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return connect;
//		} else {
//			Connection connect = connect(param);
//			connectMap.put(url, connect);
//			return connect;
//		}
		Connection connect = connect(param);
		connectMap.put(url, connect);
		return connect;
	}

	public static Connection connect(ConnectParamBean param) {
		
		String typeName = param.getDbtype();
		Connection conn = null;
		try {
			Class.forName(param.getDbdriver());
		} catch (ClassNotFoundException e) {
			System.out.println("Load JDBC Driver Class出错:" + param.getDbdriver());
			throw new RuntimeException("Load JDBC Driver Class出错:" + param.getDbdriver());
		}
		try {
			String url = param.getUrl();
			String user = param.getUser();
			String passwd = param.getPassword();
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("数据库连接成功！！！！！");
		} catch (SQLException e) {
			System.out.println("数据库连接出错！！！！！" + e.getMessage());
		}
		return conn;

	}

	/**
	 * 释放数据库链接，销毁链接描述对象，用于Logout~~
	 */
	public static void destroy(Connection dbConnection) {
		// close 链接..
		if (dbConnection != null) {
			try {
				if (!dbConnection.isClosed()) {
					dbConnection.close();
				}
				System.out.println("数据库释放成功！！！！！");
			} catch (SQLException e) {
				System.out.println(" 释放数据库链接失败！！！！！" + e.getMessage());
			}
		}
	}

}
