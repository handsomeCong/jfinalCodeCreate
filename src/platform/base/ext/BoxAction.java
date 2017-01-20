package platform.base.ext;

import com.jfinal.core.Controller;

public abstract class BoxAction extends Controller {

	public PageBean getPageBean() {
		PageBean pageBean = new PageBean();
		int start=1;
		int limit=10;
		if(getPara("pageIndex")!=null){
			start=getParaToInt("pageIndex");
		}
		String sort = getPara("sortField");
		String dir = getPara("sortOrder");
		pageBean.setDir(dir);
		pageBean.setLimit(limit);
		pageBean.setSort(sort);
		pageBean.setStart(start);
		return pageBean;
	}

	public String[] getSearch() {
		return getParaValues("searchBean");
	}

	public String[] getDelIds() {
		String para = getPara("ids");
		String[] paras = para.split(",");
		return paras;
	}
}
