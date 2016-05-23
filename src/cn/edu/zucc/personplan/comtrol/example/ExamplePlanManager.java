package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.ui.FrmMain;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

public class ExamplePlanManager implements IPlanManager {

	@Override
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into plan(user_id,name,create_time,step_number,done_number,status) values(?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,ExampleUserManager.currentUser.getId());
			pst.setString(2,name);
			pst.setLong(3,System.currentTimeMillis());
			pst.setInt(4,0);
			pst.setInt(5,0);
			pst.setInt(6,0);

			pst.execute();
			pst.close();
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
		return null;
	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		List<BeanPlan> result=new ArrayList<BeanPlan>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//			String sql = "select count(*) from plan a, step b where a.id=b.plan_id";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			ResultSet rs = pst.executeQuery();
//			int step_number;
//			if (rs.next()) {
//				step_number = rs.getInt(1);
//			} else {
//				step_number = 0;
//			}
//			pst.close();
//			rs.close();
			String sql = "select name,create_time,done_time,step_number,done_number,status,id from plan where user_id=? ORDER BY id";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,ExampleUserManager.currentUser.getId());
			ResultSet rs = pst.executeQuery();

			int user_id;
			String name;
			long create_time;
			long done_time;
			int done_number;
			int status;
			int id;
			int step_number;

			while (rs.next()) {
				user_id = ExampleUserManager.currentUser.getId();
				name = rs.getString(1);
				create_time = rs.getLong(2);
				done_time = rs.getLong(3);
				step_number = rs.getInt(4);
				done_number = rs.getInt(5);
				status = rs.getInt(6);
				id = rs.getInt(7);
				result.add(new BeanPlan(id,name,create_time,done_time,step_number,done_number,status));
			}
			pst.close();
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
		return result;
	}

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
		// TODO Auto-generated method stub
		int id = plan.getId();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from plan where id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,id);
			pst.execute();
			pst.close();
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

	public void refresh(BeanPlan plan) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update plan set name=?,create_time=?,done_time=?,step_number=?,done_number=?,status=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,plan.getName());
			pst.setLong(2,plan.getCreate_time());
			pst.setLong(3,plan.getDone_time());
			pst.setInt(4,plan.getStep_number());
			pst.setInt(5,plan.getDone_number());
			pst.setInt(6,plan.getStatus());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		}
	}

}
