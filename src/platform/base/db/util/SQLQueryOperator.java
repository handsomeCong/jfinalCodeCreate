package platform.base.db.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import platform.base.db.model.ColumnBean;
import platform.base.db.model.ConnectParamBean;

public class SQLQueryOperator {

	public List<ColumnBean> executeQuery(String tableName, ConnectParamBean param) throws RuntimeException {
		ResultSet rs = null;
		ResultSet rss = null;
		Statement stmt = null;
		ResultSet rst = null;
		Connection conn = DBConnection.getConnection(param);

		DatabaseMetaData databaseMetaData = null;
		List<ColumnBean> columnBeanList = new ArrayList<ColumnBean>();
		try {
			databaseMetaData = conn.getMetaData();
			
			rs = databaseMetaData.getColumns(null, param.getSchema().toUpperCase(), tableName, "%");
			while (rs.next()) {
				ColumnBean cb = new ColumnBean();
				cb.setName(rs.getString("COLUMN_NAME"));
				cb.setType(rs.getString("TYPE_NAME"));
				cb.setComment(rs.getString("REMARKS"));
				cb.setSize(rs.getInt("COLUMN_SIZE"));
				columnBeanList.add(cb);
			}

			// 取得主键
			rss = databaseMetaData.getPrimaryKeys(null, param.getSchema().toUpperCase(), tableName);
			String pkName = "";
			while (rss.next()) {
				if (pkName.length() > 0) {
					pkName += ",";
				}
				pkName += rss.getString("COLUMN_NAME");
				for (ColumnBean cb : columnBeanList) {
					if (cb.getName().equals(pkName)) {
						cb.setPkColumn(true);
					} else {
						cb.setPkColumn(false);
					}
				}

			}
			/**
			 * 如果oracle
			 */
			if (param.getDbtype().equalsIgnoreCase("ORACLE10g")) {
				stmt = conn.createStatement();
				StringBuilder sqlStr = new StringBuilder();
				sqlStr.append("select b.column_name,b.comments from user_col_comments b where b.table_name='").append(tableName.toUpperCase()).append("'");
				rst = stmt.executeQuery(sqlStr.toString());
				while (rst.next()) {
					for (ColumnBean cb : columnBeanList) {
						if (rst.getString(1).equalsIgnoreCase(cb.getName())) {
							cb.setComment(rst.getString(2));
						}
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rss != null) {
					rss.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					DBConnection.destroy(conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("不存在该表");
			}

		}
		return columnBeanList;
	}

	public List<String> getAllTableNames(ConnectParamBean param) throws RuntimeException {
		List<String> tableNameList = new ArrayList<String>();
		ResultSet rs = null;
		Connection conn = DBConnection.getConnection(param);
		String[] types = { "TABLE" };
		try {
			DatabaseMetaData dmd = conn.getMetaData();
			rs = dmd.getTables(conn.getCatalog(), param.getSchema(), null, types);
			while (rs.next()) {
				tableNameList.add(rs.getString(3));
			}
		} catch (SQLException e) {
			throw new RuntimeException("不存在该数据库");
		}finally{
		    DBConnection.destroy(conn);
		}
		return tableNameList;
	}
	
	
}
