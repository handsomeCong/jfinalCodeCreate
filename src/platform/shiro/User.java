package platform.shiro;

public class User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 编号
	 */
	private java.lang.String id;
	/**
	 * 姓名
	 */
	private java.lang.String name;
	/**
	 * 工号
	 */
	private java.lang.String workno;
	/**
	 * 账号
	 */
	private java.lang.String account;

	/**
	 * 邮箱
	 */
	private java.lang.String eamil;
	/**
	 * 0.女1.男
	 */
	private java.lang.String sex;
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getWorkno() {
		return workno;
	}
	public void setWorkno(java.lang.String workno) {
		this.workno = workno;
	}
	public java.lang.String getAccount() {
		return account;
	}
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	public java.lang.String getEamil() {
		return eamil;
	}
	public void setEamil(java.lang.String eamil) {
		this.eamil = eamil;
	}
	public java.lang.String getSex() {
		return sex;
	}
	public void setSex(java.lang.String sex) {
		this.sex = sex;
	}
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
