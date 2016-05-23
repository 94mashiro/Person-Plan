package cn.edu.zucc.personplan.comtrol.example;

import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleUserManager implements IUserManager {

	public static BeanUser currentUser = null;

	@Override
	public void reg(String username, String pwd,String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		if (!pwd.equals(pwd2)) {
			throw new BusinessException("两次密码输入不一致");
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from user where username=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				throw new BusinessException("用户名已被注册");
			}
			sql = "insert into user(username,password) values(?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			pst.setString(2,pwd);
			pst.execute();
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		}
	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		Connection conn = null;
		if (!user.getPassword().equals(oldPwd)) {
			throw new BusinessException("旧密码输入错误");
		}
		if (!newPwd.equals(newPwd2)) {
			throw new BusinessException("两次密码输入不一致");
		}
		try {
			conn = DBUtil.getConnection();
			String sql = "update user set password=? where username=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,newPwd);
			pst.setString(2,user.getUsername());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					user.setPassword(newPwd);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		}
		
	}
	@Override
	public BeanUser login(String username) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select id,username,password from user where username=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,username);
			java.sql.ResultSet rs = pst.executeQuery();
			if(!rs.next()) {
				throw new BusinessException("账号或密码错误");

			}
			int id = rs.getInt(1);
			String password = rs.getString(3);
			pst.close();
			rs.close();
			return new BeanUser(password,id,username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		}
	}

}
