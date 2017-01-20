package platform.base.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import platform.base.ext.BoxAction;
import platform.shiro.ShiroUser;

public class LoginAction extends BoxAction {

	public void index() {
		System.out.println("========");
		String username = getPara("username");
		String password = getPara("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		Subject user = SecurityUtils.getSubject();
		token.setRememberMe(true);
		try {
			user.login(token);
			int timeout = getSession().getMaxInactiveInterval();
			SecurityUtils.getSubject().getSession().setTimeout(timeout * 1000);
			System.out.println("===成功===");
			if(SecurityUtils.getSubject().isPermitted("/user/list")){
				System.out.println("====come in loginAdd===");
				}
			render("/admin/home.jsp");
		} catch (UnknownAccountException uae) {
			System.out.println("帐号不存在");
			setAttr("msg", "帐号不存在");
			render("/admin/error.jsp");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("密码错误");
			setAttr("msg", "密码错误");
			render("/admin/error.jsp");
		} catch (DisabledAccountException lae) {
			System.out.println("帐号被锁定");
			setAttr("msg", "帐号被锁定");
			render("/admin/error.jsp");
		}
		
	}
	public void loginAdd() {
		if(SecurityUtils.getSubject().isPermitted("/login/loginAdd")){
		System.out.println("====come in loginAdd===");
		}else{
			System.out.println("not permitetd");
		}
	}
	public void loginAddSave() {
		System.out.println("====come in loginAddSave ===");
	}
}
