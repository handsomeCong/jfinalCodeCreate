package platform.codecreate.dbconfiginfo.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.stringtree.json.JSONWriter;

import platform.base.ext.BoxAction;
import platform.base.util.PlatformTool;
import platform.codecreate.dbconfiginfo.model.DbConfigInfo;

import com.jfinal.plugin.activerecord.Page;

public class DbConfigInfoAction extends BoxAction {
	public void index() {
		
		String searchSQL="";
		String orderSQL=" order by create_date desc";
		Page pageList = DbConfigInfo.dao.getPageList(searchSQL,orderSQL,getPageBean());
		setAttr("pageList", pageList);
		System.out.println(pageList.getPageNumber());
		render("/WEB-INF/jsp/dbconfiginfo/dbconfiginfo.jsp");
	}

 
	
	public void jsonItem() {
		List<Map> jsonTree = DbConfigInfo.dao.getJsonItem();
		JSONWriter writer = new JSONWriter(false);
		renderText(writer.write(jsonTree));
	}

	public void add() {
		render("/WEB-INF/jsp/dbconfiginfo/dbconfiginfo_add.jsp");
	}

	
	public void saveAdd() {
		DbConfigInfo model = getModel(DbConfigInfo.class);
		model.set("ID",PlatformTool.generateUUID());
		String name=model.getStr("name");
		if(name.equals("MYSQL")){
			model.set("dbdriver","com.mysql.jdbc.Driver");
		}
		model.set("DB_URL","jdbc:mysql://"+model.getStr("host")+":"+model.getStr("post")+"/"+model.getStr("db_name"));
		model.set("CREATE_DATE", new Date());
		model.set("DBSCHEMA","ROOT");
		model.save();
		redirect("/dbconfiginfo");
	}

	public void modify() {
		String id = getPara();
		DbConfigInfo model = DbConfigInfo.dao.findById(id);
		setAttr("model", model);
		render("/WEB-INF/jsp/dbconfiginfo/dbconfiginfo_modify.jsp");
	}

	public void saveModify() {
		DbConfigInfo model = getModel(DbConfigInfo.class);
		String id = model.get("ID");
		model.set("CREATE_DATE", new Date());
		model.update();
		redirect("/dbconfiginfo");
	}

	 

	 
	public void delete() {
		boolean b = DbConfigInfo.dao.delete(this.getDelIds());
		HashMap jsonMap=new HashMap();
		if (b) {
			jsonMap.put("msg","信息删除成功！");
			 
		} else {
			jsonMap.put("msg","信息删除失败！请联系管理员！");
			 
		}
		renderJson(jsonMap);
	}
}