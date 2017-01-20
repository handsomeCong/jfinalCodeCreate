package platform.codecreate.configbaseinfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import platform.base.db.model.ColumnBean;
import platform.base.db.model.ConnectParamBean;
import platform.base.db.util.SQLQueryOperator;
import platform.base.ext.PageBean;
import platform.codecreate.dbconfiginfo.model.DbConfigInfo;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ConfigBasicInfo extends Model<ConfigBasicInfo> {
	public static final ConfigBasicInfo dao = new ConfigBasicInfo();

	public List<ConfigBasicExtendInfo> extendList;

	public Page getPageList(String search,String order, PageBean pageBean) {
		StringBuilder sql = new StringBuilder("select * ");
		StringBuilder sqlFrom = new StringBuilder("from CONFIG_BASIC_INFO t where 1=1");
		sqlFrom.append(search);
		sqlFrom.append(order);
		Page paginate = paginate(pageBean.getStart(), pageBean.getLimit(), sql.toString(), sqlFrom.toString());
		return paginate;
	}

	public boolean delete(String[] delIds) {
		boolean b = false;
		for (String id : delIds) {
			StringBuilder sdb = new StringBuilder();
			sdb.append("delete from CONFIG_BASIC_EXTEND_INFO where CONFIG_BASIC_INFO_ID='").append(id).append("'");
		    Db.update(sdb.toString());
			b = deleteById(id);
		}
		return b;
	}
	public ConnectParamBean getConnectParamBean(String dbId) {
		
		DbConfigInfo dbconfiginfo = DbConfigInfo.dao.findById(dbId);
		if (dbconfiginfo != null) {
			ConnectParamBean cpb = new ConnectParamBean();
			cpb.setUser(dbconfiginfo.getStr("DBUSER_NAME"));
			cpb.setPassword(dbconfiginfo.getStr("PASSWORD"));
			cpb.setDbdriver(dbconfiginfo.getStr("DBDRIVER"));
			cpb.setUrl(dbconfiginfo.getStr("DB_URL"));
			cpb.setSchema(dbconfiginfo.getStr("DBSCHEMA"));
			cpb.setDbtype(dbconfiginfo.getStr("NAME"));
			return cpb;
		}else{
			ConnectParamBean cpb = new ConnectParamBean();
			cpb.setUser("root");
			cpb.setPassword("111111");
			cpb.setDbdriver("com.mysql.jdbc.Driver");
			cpb.setUrl("jdbc:mysql://127.0.0.1:3306/jfinalcodecreate?characterEncoding=GBK&zeroDateTimeBehavior=convertToNull");
			cpb.setSchema("ROOT");
			cpb.setDbtype("MYSQL");
			return cpb;
		}
 
	}
	/**
	 * 获取传进来的数据库对应的所有的表
	 * @param id
	 * @return
	 */
	public String stringTableItem(String db_id) {
		//拼装table select
		StringBuffer sb=new StringBuffer();
		sb.append("<select name=\"configBasicInfo.ENGLISH_NAME\" id=\"table_select\"   class='select2-me input-xlarge' onchange='getColumnTableString();'>");
		List<Map> listMap = new ArrayList<Map>();
		try{
		ConnectParamBean cpb = this.getConnectParamBean(db_id);
		if (cpb != null) {
			SQLQueryOperator sqo = new SQLQueryOperator();
			List<String> allTableList = sqo.getAllTableNames(cpb);
			sb.append("<option value=\"\">===请选择数据库表===</option>");
			for (String string : allTableList) {
				sb.append("<option value=\""+string+"\">"+string+"</option>");
			}
		}
		}catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
		
		sb.append("</select>");
		System.out.println("sb:"+sb.toString());
		return sb.toString();
	}
	
	
	/**
	 * 根据表名、数据库名称查出所有的字段
	 * @param dbId
	 * @param tableName
	 * @return
	 */
	public List<ConfigBasicExtendInfo> getColumnBeanListByCondition(String dbId, String tableName) throws RuntimeException {
		List<ConfigBasicExtendInfo> list = new ArrayList<ConfigBasicExtendInfo>();
		int i = 0;
		List<ColumnBean> columnBeanList = new ArrayList<ColumnBean>();
		SQLQueryOperator sqo = new SQLQueryOperator();
		try{
		columnBeanList = sqo.executeQuery(tableName, this.getConnectParamBean(dbId));
		}catch(Exception e){
			throw new RuntimeException("表不存在");
		}
		for (ColumnBean cb : columnBeanList) {
			++i;
			ConfigBasicExtendInfo basicExtendInfo = new ConfigBasicExtendInfo();
			basicExtendInfo.set("FIELD_ENGLISH_NAME", cb.getName());
			basicExtendInfo.set("FIELD_DESCRIBE", cb.getComment());
			basicExtendInfo.set("FIELD_TYPE", cb.getType());
			basicExtendInfo.set("DISPLAY_SEQUENCE", i);
			if (cb.isPkColumn()) {
				basicExtendInfo.set("IS_PK", "true");
			} else {
				basicExtendInfo.set("IS_PK", "false");
			}
			list.add(basicExtendInfo);
		}
		return list;
	}
	
	/**
	 * 根据所有的字段名称 组成table
	 * @return
	 */
	
	public String getTableColumn(String dbId, String tableName){
		 
		StringBuffer sb=new StringBuffer();
		
		List<ConfigBasicExtendInfo> extendInfoList=getColumnBeanListByCondition(dbId, tableName);
		for(int i=0;i<extendInfoList.size();i++){
			sb.append("<tr>");
			ConfigBasicExtendInfo extendInfo=extendInfoList.get(i);
			//字段英文名称
			String FIELD_ENGLISH_NAME="<td><input type=\"text\" name=\"FIELD_ENGLISH_NAME\"  value=\""+extendInfo.getStr("FIELD_ENGLISH_NAME")+"\"></td>";
			sb.append(FIELD_ENGLISH_NAME);
			//字段描述
			String FIELD_DESCRIBE="<td><input type=\"text\" name=\"FIELD_DESCRIBE\"  value=\""+extendInfo.getStr("FIELD_DESCRIBE")+"\"></td>";
			sb.append(FIELD_DESCRIBE);
			//是否列表显示
			String IS_TALBE_DISPLAY="<td><select name=\"IS_TALBE_DISPLAY\" class=\"input-block-level\"  value=\"true\"><option value=\"false\">否</option><option value=\"true\" selected>是</option></select></td>";
			sb.append(IS_TALBE_DISPLAY);
			//是否新增显示
			String IS_CREATE_DISPLAY="<td><select name=\"IS_CREATE_DISPLAY\" class=\"input-block-level\"  class=\"input-block-level\"  value=\"true\"><option value=\"false\">否</option><option value=\"true\" selected>是</option></select></td>";
			sb.append(IS_CREATE_DISPLAY);
			//控件类型
			String CONTROL_TYPE="<td><select name=\"CONTROL_TYPE\" class=\"input-block-level\"  value=\"text\"><option value=\"date\">date</option><option value=\"select\">select</option><option value=\"bool\">bool</option><option value=\"checkbox\">checkbox</option><option value=\"radio\">radio</option><option value=\"text\" selected>text</option></select></td>";
			sb.append(CONTROL_TYPE);
			//是否必填
			String VALIDATE_INFO="<td><select name=\"IS_NEED\" class=\"input-block-level\"  value=\"true\"><option value=\"false\">否</option><option value=\"true\" selected>是</option></select></td>";
			sb.append(VALIDATE_INFO);
			//是否是搜索条件
			String IS_SEARCH="<td><select name=\"IS_SEARCH\" class=\"input-block-level\"  value=\"true\"><option value=\"false\" selected>否</option><option value=\"true\" >是</option></select></td>";
			sb.append(IS_SEARCH);
			
			String FIELD_TYPE="<td><input type=\"text\" name=\"DB_FIELD_TYPE\" readyonly=\"readyonly\" value=\""+extendInfo.getStr("FIELD_TYPE")+"\"></td>";
			sb.append(FIELD_TYPE);
			sb.append("</tr>");
		}
		
		
		return sb.toString();
	}
	
	/**
	 * 根据configbaseinfo id获取对象
	 * @return
	 */
	
	public ConfigBasicInfo getConfigBasicInfo(String id){
		 ConfigBasicInfo info=ConfigBasicInfo.dao.findById(id);
		 String sql="select * from config_basic_extend_info where CONFIG_BASIC_INFO_ID='"+info.getStr("id")+"'";
		 System.out.println("getConfigBasicInfo_sql:"+sql);
		 List<ConfigBasicExtendInfo> extendInfoList=ConfigBasicExtendInfo.dao.find(sql);
		 info.setExtendList(extendInfoList);
		 
		 return info;
	}
	
	public List<ConfigBasicInfo> getConfigBasicInfoList(String[] allIds) {
		if (allIds != null && allIds.length > 0) {
			StringBuilder sdb = new StringBuilder();
			sdb.append("select * from CONFIG_BASIC_INFO where ");

			sdb.append("(");
			for (int i = 0; i < allIds.length; i++) {
				String id = allIds[i];
				if (i > 0) {
					sdb.append(" OR ");
				}
				sdb.append("ID='").append(id).append("'");
			}
			sdb.append(")");
			List<ConfigBasicInfo> list = find(sdb.toString());
			for (ConfigBasicInfo configBasicInfo : list) {
				StringBuilder sdb1 = new StringBuilder();
				sdb1.append("select * from CONFIG_BASIC_EXTEND_INFO t where t.CONFIG_BASIC_INFO_ID='").append(configBasicInfo.getStr("ID")).append("' order by DISPLAY_SEQUENCE ASC");
				List<ConfigBasicExtendInfo> extendList = ConfigBasicExtendInfo.dao.find(sdb1.toString());
				configBasicInfo.setExtendList(extendList);
			}
			return list;
		} else {
			return null;
		}

	}
	
	public List<ConfigBasicExtendInfo> getExtendList() {
		return extendList;
	}

	public void setExtendList(List<ConfigBasicExtendInfo> extendList) {
		this.extendList = extendList;
	}
}