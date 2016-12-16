package cn.edu.sdut.softlab.login;

/**

* 注册、登录的Controller类

* @author GaoZiqiang

* @Time 2016-11-10

*

*/
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("restriction")
@Named
@RequestScoped
public class Controller {
	// username password属性
	private String username;
	private String password;

	// getter setter方法
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// 对象注入
	@Inject
	@RegisterLogin
	RegisterLoginController mysql;

	@PostConstruct
	public void init() {
		System.out.println("wildfly from eclipce is posting construct......");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("wildfly from eclipse is preing destroy......");
	}

	// 用户注册
	public String register() {
		mysql.depositMySQL(username, password);
		return "reservation";
	}

	// 用户登录
	public String login() throws ClassNotFoundException, SQLException {
		return mysql.userAuthentication(username, password);
	}

}