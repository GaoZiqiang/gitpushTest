package cn.edu.sdut.softlab.login;

/**

* 注册、登录的Controller类

* @author GaoZiqiang

* @Time 2016-11-10

*

*/
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@RegisterLogin
public class RegisterLoginController {
	Connection conn = null;

	// 与MySQL数据库建立连接
	public void setRelation() throws ClassNotFoundException, SQLException {
		// MySQL数据库的URL
		String url = "jdbc:mysql://localhost:3306/CdiDb?user=root&password=666";
		// 注册JDBC驱动程序
		Class.forName("com.mysql.jdbc.Driver");
		// 打开一个连接
		System.out.println("connecting to database...");
		conn = (Connection) DriverManager.getConnection(url);
		// 执行一个查询
		System.out.println("Creating statement...");
		// 打印输出conn，看是否创建成功
		System.out.println(conn);
	}

	// 将用户信息存入数据库--注册
	public void depositMySQL(String username, String password) {
		// setRelation
		try {
			try {
				setRelation();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Statement stm = (Statement) conn.createStatement();
			// 需要完善：判断用户是否已经注册，需要对代码进行完善，代码冗余严重
			String sqlIn = "insert into signupTable (username,password) values ('" + username + "','" + password + "')";
			System.out.println(sqlIn);
			// 打印输出sql语句查看insert与具备是否正确
			stm.executeUpdate(sqlIn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("已经成功将用户" + username + "的信息存入数据库");
		}
	}

	// 将用户信息从数据库取出--denglu
	public String userAuthentication(String username, String password) throws ClassNotFoundException, SQLException {
		// 以此决定返回哪个界面
		String flag = null;
		// setRelation
		try {
			try {
				setRelation();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		Statement stm = (Statement) conn.createStatement();
		String sqlSelect = "select * from signupTable where username = '" + username + "'";
		ResultSet rst = stm.executeQuery(sqlSelect);
		System.out.println(sqlSelect);

		if (rst.next()) {// 若用户名存在，则比较密码
			if (rst.getString("password").equals(password)) {
				System.out.println("登录成功");
				flag = "reservation";
			} else {
				System.out.println("密码错误");
				flag = "passwderror";
			}
		} else {// 如果没有用户名就提示没有
			System.out.println("该用户不存在");
			flag = "nouser";
		}

		return flag;
	}

}