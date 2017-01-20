package platform.base.common;

import platform.shiro.ShiroPlugin;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import com.jfinal.ext.plugin.tablebind.SimpleNameStyles;
import com.jfinal.ext.route.AutoBindRoutes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.JspRender;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class CommonConfig extends JFinalConfig {
	/**
	 * 定义项目常量
	 */
	public static String PROJECT_NAME = "jfinalCodeCreate";

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		loadPropertyFile("project.txt"); // 加载少量必要配置，随后可用getProperty(...)获取值
		me.setDevMode(getPropertyToBoolean("devMode", true));
		 
		me.setBaseViewPath("/WEB-INF/jsp/");
 		me.setViewType(ViewType.JSP); // 设置视图类型为Jsp，否则默认为FreeMarker
	}

	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", CommonAction.class);
		me.add(new AutoBindRoutes());
	}

	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {

		DruidPlugin druidPlugin = new DruidPlugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password"),
				getProperty("driverClass"));
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 配置ActiveRecord插件
		arp.setDialect(new MysqlDialect());
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(druidPlugin,SimpleNameStyles.LOWER_UNDERLINE);   
		me.add(atbp);
		atbp.setIncludeAllJarsInLib();
		arp.setContainerFactory(new CaseInsensitiveContainerFactory());
		arp.setShowSql(true);
		me.add(arp);
		me.add(new ShiroPlugin());
	}

	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
	}

	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextpath"));
	}

}
