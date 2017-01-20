package platform.codecreate.dbconfiginfo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.base.ext.PageBean;
import platform.base.ext.PageUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class DbConfigInfo extends Model<DbConfigInfo>{
	public static final DbConfigInfo dao = new DbConfigInfo();
	public Page getPageList(String searchSQL,String orderSQL,PageBean pageBean) {
		StringBuilder sql = new StringBuilder("select * ");
		StringBuilder sqlFrom = new StringBuilder("from DB_CONFIG_INFO t where 1=1");
		sqlFrom.append(searchSQL);
		sqlFrom.append(orderSQL);
		Page paginate = paginate(pageBean.getStart(), pageBean.getLimit(), sql.toString(), sqlFrom.toString());
		return paginate;
	}
	public boolean delete(String[] delIds) {
		boolean b = false;
		for (String id : delIds) {
			b = deleteById(id);
		}
		return b;
	}
	public List<Map> getJsonItem() {
		List<Map> listMap = new ArrayList<Map>();
		List<DbConfigInfo> list = this.find("select * from DBCONFIGINFO t order by t.CREATE_DATE DESC");
		for (DbConfigInfo dbconfiginfo : list) {
			Map jsonMap = new HashMap();
			String menuId = dbconfiginfo.getStr("ID");
			jsonMap.put("id", menuId);
			jsonMap.put("text", dbconfiginfo.getStr("DB_NAME"));
			listMap.add(jsonMap);
		}
		return listMap;
	}

}
