package platform.codecreate.configbaseinfo.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.base.ext.BoxAction;
import platform.base.util.PlatformTool;
import platform.codecreate.configbaseinfo.model.ConfigBasicExtendInfo;
import platform.codecreate.configbaseinfo.model.ConfigBasicInfo;
import platform.codecreate.dbconfiginfo.model.DbConfigInfo;
import platform.codecreate.generate.GenerateEclipseRender;
import platform.codecreate.generate.GenerateRender;

import com.jfinal.kit.StringKit;
import com.jfinal.plugin.activerecord.Page;

public class ConfigBasicInfoAction extends BoxAction {

    public void index() {
        String searchSQL = "";
        String orderSQL = " order by create_date desc";
        Page pageList = ConfigBasicInfo.dao.getPageList(searchSQL, orderSQL, getPageBean());
        for (int i = 0; i < pageList.getList().size(); i++) {
            ConfigBasicInfo basicInfo = (ConfigBasicInfo) pageList.getList().get(i);
            DbConfigInfo info = DbConfigInfo.dao.findById(basicInfo.getStr("db_type"));
            basicInfo.put("db_name", info.getStr("db_name"));
        }
        setAttr("pageList", pageList);
        render("/WEB-INF/jsp/configbasicinfo/configbasicinfo.jsp");
    }

    public void add() {
        List<DbConfigInfo> infoList = DbConfigInfo.dao.find("select * from db_config_info ");
        setAttr("infoList", infoList);
        render("/WEB-INF/jsp/configbasicinfo/configbasicinfo_add.jsp");
    }

    /**
     * 获取table的string
     */
    public void getTableItemString() {
        String db_id = getPara();

        String table_item_string = ConfigBasicInfo.dao.stringTableItem(db_id);
        HashMap jsonMap = new HashMap();
        jsonMap.put("selectString", table_item_string);
        renderJson(jsonMap);
    }

    /**
     * 根据选择的表，获取所有的字段信息
     */
    public void getColumnTableItemString() {
        String db_id = getPara("db_id");
        String table_name = getPara("table_name");
        String table_item_string = "";
        try {
            table_item_string = ConfigBasicInfo.dao.getTableColumn(db_id, table_name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        HashMap jsonMap = new HashMap();
        System.out.println(table_item_string);
        jsonMap.put("columnTableString", table_item_string);
        renderJson(jsonMap);
    }

    /**
     * 新增字段对应的类型
     */
    public void saveAdd() {
        ConfigBasicInfo model = getModel(ConfigBasicInfo.class);
        String englishName = model.get("ENGLISH_NAME");
        if (StringKit.notBlank(englishName)) {
            model.set("TABLE_OBJECT_NAME", PlatformTool.conversionObject(englishName));
        }
        model.set("ID", PlatformTool.generateUUID());
        model.set("CREATE_DATE", new Date());
        String id = model.get("ID");
        String[] fieldEnglishNames = getParaValues("FIELD_ENGLISH_NAME");
        String[] fieldDescribes = getParaValues("FIELD_DESCRIBE");
        String[] isTalbeDisplays = getParaValues("IS_TALBE_DISPLAY");
        String[] isCreateDisplays = getParaValues("IS_CREATE_DISPLAY");
        String[] isPks = getParaValues("IS_PK");
        String[] controlTypes = getParaValues("CONTROL_TYPE");
        String[] IS_NEEDS = getParaValues("IS_NEED");
        String[] IS_SEARCHS = getParaValues("IS_SEARCH");
        String[] FIELD_TYPE = getParaValues("DB_FIELD_TYPE");
        String objectType = "String";
        if (fieldEnglishNames != null && fieldEnglishNames.length > 0) {
            for (int i = 0; i < fieldEnglishNames.length; i++) {
                String fieldEnglishName = fieldEnglishNames[i];
                String fieldDescribe = fieldDescribes[i];
                if (fieldDescribe.equals("")) {
                    fieldDescribe = fieldEnglishName;
                }
                String isTalbeDisplay = isTalbeDisplays[i];
                String isCreateDisplay = isCreateDisplays[i];
                // String isPk = isPks[i];
                String controlType = controlTypes[i];
                String validateInfo = IS_NEEDS[i];
                String isSearch = IS_SEARCHS[i];
                String fieldType = FIELD_TYPE[i]; // 数据库类型
                if(fieldEnglishName.equalsIgnoreCase("id")){
                	fieldType="BIGINT";
                }
                ConfigBasicExtendInfo configBasicExtendInfo = new ConfigBasicExtendInfo();
                configBasicExtendInfo.set("ID", PlatformTool.generateUUID());
                configBasicExtendInfo.set("CONFIG_BASIC_INFO_ID", id);
                configBasicExtendInfo.set("FIELD_ENGLISH_NAME", fieldEnglishName);
                configBasicExtendInfo.set("FIELD_DESCRIBE", fieldDescribe);
                configBasicExtendInfo.set("IS_TALBE_DISPLAY", isTalbeDisplay);
                configBasicExtendInfo.set("IS_CREATE_DISPLAY", isCreateDisplay);
                configBasicExtendInfo.set("IS_PK", "false");
                configBasicExtendInfo.set("CONTROL_TYPE", controlType);
                configBasicExtendInfo.set("IS_NEED", validateInfo);
                configBasicExtendInfo.set("DISPLAY_SEQUENCE", i);
                configBasicExtendInfo.set("IS_SEARCH", isSearch);
                configBasicExtendInfo.set("FIELD_TYPE", fieldType);

                if ("CHAR".equalsIgnoreCase(fieldType) || "TEXT".equalsIgnoreCase(fieldType)
                        || "VARCHAR".equalsIgnoreCase(fieldType) || "TEXT".equalsIgnoreCase(fieldType)
                        || "binary".equalsIgnoreCase(fieldType) || "BLOB".equalsIgnoreCase(fieldType)
                        || "longtext".equalsIgnoreCase(fieldType)) {
                    objectType = "String";
                } else if ("INT UNSIGNED".equalsIgnoreCase(fieldType) || "INT".equalsIgnoreCase(fieldType)
                        || "TINYINT".equalsIgnoreCase(fieldType) || "SMALLINT".equalsIgnoreCase(fieldType)
                        || "MEDIUMINT".equalsIgnoreCase(fieldType)) {
                    objectType = "Integer";
                } else if ("BIGINT".equalsIgnoreCase(fieldType)) {
                    objectType = "Long";
                } else if ("DOUBLE".equalsIgnoreCase(fieldType) || "DECIMAL".equalsIgnoreCase(fieldType)) {
                    objectType = "BigDecimal";
                }

                else if ("FLOAT".equalsIgnoreCase(fieldType)) {
                    objectType = "BigDecimal";
                }

                else if ("BOOL".equalsIgnoreCase(fieldType)) {
                    objectType = "Boolean";
                } else if ("DATE".equalsIgnoreCase(fieldType) || "DATETIME".equalsIgnoreCase(fieldType)) {
                    objectType = "Date";
                } else if ("DATETIME".equalsIgnoreCase(fieldType)) {
                    objectType = "Date";
                }
                configBasicExtendInfo.set("OBJECT_ATTRIBUTE_TYPE", objectType);
                configBasicExtendInfo.set("FIRSTLOW_ENGLISH_NAME",
                        PlatformTool.toFirstLowerString(PlatformTool.conversionObject(fieldEnglishName)));
                configBasicExtendInfo.set("FIRSTUP_ENGLISH_NAME", (PlatformTool.conversionObject(fieldEnglishName)));

                configBasicExtendInfo.save();
            }
        }
        model.save();
        redirect("/configbasicinfo");
    }

    public void view() {
        String id = getPara(0);
        ConfigBasicInfo info = ConfigBasicInfo.dao.getConfigBasicInfo(id);
        setAttr("info", info);

        // 数据库信息
        DbConfigInfo configInfo = DbConfigInfo.dao.findById(info.get("DB_TYPE"));
        setAttr("configInfo", configInfo);
        // 字段列表
        List<ConfigBasicExtendInfo> extendList = info.getExtendList();
        setAttr("extendList", extendList);

        render("/WEB-INF/jsp/configbasicinfo/configbasicinfo_view.jsp");
    }

    public void delete() {
        boolean b = ConfigBasicInfo.dao.delete(this.getDelIds());
        HashMap jsonMap = new HashMap();
        if (b) {
            jsonMap.put("msg", "信息删除成功！");
        } else {
            jsonMap.put("msg", "信息删除失败！请联系管理员！");
        }
        renderJson(jsonMap);
    }

    /**
     * 生成源码
     */
    public void outsource() {
        render(new GenerateRender(this.getDelIds()));
    }

    /**
     * 生成eclipse配置文件
     */

    public void outEclipseSource() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", getPara("type"));
        map.put("projectName", getPara("projectName"));
        map.put("jdkVersion", getPara("jdkVersion"));
        render(new GenerateEclipseRender(map));
    }
}