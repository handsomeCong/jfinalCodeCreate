package platform.base.db.model;

public class ColumnBean {
	// 列名称
	private String name;
	// 列类型
	private String type;
	// sql 类型名称
	private String typeName;
	
	// 列大小
	private int size;
	// 小数位数
	private int digits;
	// 列是否可以为空
	private boolean nullable;
	// 列默认值
	private String defaultValue;

	// 是否是主键列
	private boolean pkColumn = false;

	// 列备注信息
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(boolean pkColumn) {
		this.pkColumn = pkColumn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
