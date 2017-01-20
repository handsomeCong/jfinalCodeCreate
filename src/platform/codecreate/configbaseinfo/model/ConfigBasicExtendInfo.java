package platform.codecreate.configbaseinfo.model;

import platform.base.ext.PageBean;
import platform.base.ext.PageUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

public class ConfigBasicExtendInfo extends Model<ConfigBasicExtendInfo> {
	public static final ConfigBasicExtendInfo dao = new ConfigBasicExtendInfo();

	public Page getPageList(String searchSQL,String ordreSQL, PageBean pageBean) {
		StringBuilder sql = new StringBuilder("select * ");
		StringBuilder sqlFrom = new StringBuilder("from CONFIG_BASIC_EXTEND_INFO t where 1=1");
		sqlFrom.append(searchSQL);
		sqlFrom.append(ordreSQL);
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
}
