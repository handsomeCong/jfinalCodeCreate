package platform.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbKit;

public class JfinalJdbcRealm extends AuthorizingRealm  {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
		String user_id = shiroUser.getId();
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		sazi.addRoles(getRoleCode(user_id));
		sazi.addStringPermissions(getRolePermissions(user_id));
		return sazi;
	}
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String userName=upToken.getUsername();
		User user =findLoginUser(userName,null);
		System.out.println(user==null);
		if (user != null) {
			if (user.getStatus().equals("-1")) { //不正常  
				throw new DisabledAccountException();
			}
			ShiroUser shiroUser = new ShiroUser(user);
			AuthenticationInfo authinfo=new SimpleAccount(shiroUser,upToken.getPassword(),getName());
			System.out.println("用户密码："+user.getPassword());
			String pwd = new String(upToken.getPassword());
			System.out.println("token密码："+pwd);
			if (!pwd.equals(user.getPassword())) {
				throw new IncorrectCredentialsException();
			}
			
			return authinfo;
		} else {
			throw new UnknownAccountException();
		}
	 
	}
	
	/**
	 * 查找用户
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User findLoginUser(String userName, String password)  {
		User user=null;
		String sql="select * from security_user where username='"+userName+"'";
		Connection conn = null;
		try {
			conn =DbKit.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					 user=new User();
					 user.setId(rs.getString("id"));
					 user.setName(rs.getString("username"));
					 user.setEamil(rs.getString("email"));
					 user.setPassword(rs.getString("password"));
					 user.setStatus(rs.getString("status"));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return user;
	}
	
	
	/**
	 * 获取rolecode字符串 根据user_id查找所有的role，然后将rolecode读取出来
	 * @param account
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Set<String> getRoleCode(String user_id)  {
		Set<String> set=new HashSet<String>();
		User user=new User();
		String sql="select code from security_role where  id in (select role_id from security_user_role where user_id='"+user_id+"')";
		Connection conn = null;
		try {
			conn =DbKit.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					set.add(rs.getString("code"));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return set;
	}
	
	
	/**
	 * 根据user_id获取所有的permission
	 * @return
	 */
	public Set<String> getRolePermissions(String user_id)  {
		Set<String> set=new HashSet<String>();
		User user=new User();
		String sql="select url from security_menu where  id in(select menu_id from security_role_menu where role_id in  (select role_id from security_user_role where user_id='"+user_id+"'))";
		Connection conn = null;
		try {
			conn =DbKit.getConnection();
			if (conn != null) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					set.add(rs.getString("url"));
				}
				rs.close();
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}
		return set;
	}


}
