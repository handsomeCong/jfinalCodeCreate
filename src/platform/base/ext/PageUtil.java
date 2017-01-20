package platform.base.ext;

import com.jfinal.kit.StringKit;

public class PageUtil {
	public static String getSearch(String[] search) {
		StringBuilder sdb = new StringBuilder();
		if (search != null && search.length > 0) {
			for (String str : search) {
				sdb.append(str);
			}
		}
		return sdb.toString();
	}

	public static String getOrder(PageBean pageBean) {
		if (pageBean == null || StringKit.isBlank(pageBean.getSort()) || StringKit.isBlank(pageBean.getDir()))
			return "";
		StringBuilder sdb = new StringBuilder();
		sdb.append(" order by ").append(pageBean.getSort()).append(" ").append(pageBean.getDir());
		return sdb.toString();
	}
}
