package platform.base.db.model;

import java.util.ArrayList;
import java.util.List;

public class TableInfoBean {
	private String tableName;
	private String tableComment;
	private List<ColumnBean> columnList = new ArrayList<ColumnBean>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public List<ColumnBean> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<ColumnBean> columnList) {
		this.columnList = columnList;
	}

}
