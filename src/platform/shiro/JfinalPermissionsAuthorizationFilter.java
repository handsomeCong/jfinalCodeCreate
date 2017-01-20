package platform.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

public class JfinalPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter{
	@Override
	public boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws IOException {
		Subject user = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) user.getPrincipals().getPrimaryPrincipal();
		Session session = user.getSession(false);
		HttpServletRequest req = (HttpServletRequest) request;
		Subject subject = getSubject(request, response);
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		if(uri.endsWith("/save")){
			uri=uri.substring(0,uri.length()-4);
		}
		int i=uri.indexOf(contextPath);
		if(i>-1){
			uri=uri.substring(i+contextPath.length());
		}
		if(StringUtils.isBlank(uri)){
			uri="/";
		}
		System.out.println("================JfinalPermissionsAuthorizationFilter============:"+uri);
		System.out.println(SecurityUtils.getSubject().isPermitted("/user/list"));
		 boolean permitted = subject.isPermitted(uri);
		 return permitted;
	}
}
