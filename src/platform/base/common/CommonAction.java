package platform.base.common;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import platform.base.db.model.ConnectParamBean;
import platform.base.db.util.DBConnection;
import platform.codecreate.configbaseinfo.model.ConfigBasicInfo;
import platform.codecreate.dbconfiginfo.model.DbConfigInfo;

import com.jfinal.core.Controller;

public class CommonAction extends Controller {

    public void index() {
        redirect("/dbconfiginfo");
    }

    public void toUpdateSql() {
        List<DbConfigInfo> infoList = DbConfigInfo.dao.find("select * from db_config_info ");
        setAttr("infoList", infoList);
        render("/WEB-INF/jsp/dbconfiginfo/dbupdate.jsp");
    }

    public void executeSql() throws UnsupportedEncodingException {
        HashMap jsonMap = new HashMap();
        String msg = "";
        String db_type = getPara("db_type");
        ConnectParamBean cpb = ConfigBasicInfo.dao.getConnectParamBean(db_type);
        String sql = getPara("sql");
        if (sql != null) {
            sql = java.net.URLDecoder.decode(sql, "UTF-8");
        }
        Connection conn = DBConnection.getConnection(cpb);

        String[] sqlString = sql.split(";");

        for (int i = 0; i < sqlString.length; i++) {
            String inSql = sqlString[i].trim();
            if (!inSql.equals("")) {
                if (inSql.startsWith("/*") && inSql.endsWith("*/")) {
                    System.out.println("=========注释，不执行======");
                } else {
                    System.out.println("执行sql" + (i + 1) + ":" + inSql);
                    try {

                        PreparedStatement ps = conn.prepareStatement(inSql);
                        if (ps.execute()) {
                            ResultSet rs = ps.getResultSet();
                            ResultSetMetaData metaData = rs.getMetaData();
                            int count = metaData.getColumnCount();
                            String head = "<table class=\"table table-hover table-nomargin table-colored-header\"><thead><tr>";
                            String value = "<tbody>";
                            for (int j = 1; j <= count; j++) {
                                head = head + "<th>" + metaData.getColumnLabel(j) + "</th>";
                            }
                            head = head + "</thead></tr>";

                            while (rs.next()) {
                                value = value + "<tr>";
                                for (int k = 1; k <= count; k++) {
                                    value = value + "<td>" + rs.getObject(k) + "</td>";
                                }
                                head = head + "</tr>";
                            }

                            msg = head + value + "</tbody></table>";
                        } else {
                            msg = msg + "<br/>影响" + ps.getUpdateCount() + "行记录;";
                        }
                    } catch (SQLException e) {
                        msg = e.getMessage();
                    }
                }
            }

        }

        jsonMap.put("FLAG", "T");
        jsonMap.put("MSG", msg);
        renderJson(jsonMap);
    }

    public void toGenerateEclipse() {
        render("/WEB-INF/jsp/generateEclipse.jsp");
    }

    public void generateEclipse() {
        render("/WEB-INF/jsp/generateEclipse.jsp");
    }
}
