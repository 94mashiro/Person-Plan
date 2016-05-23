package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DataFormat;
import cn.edu.zucc.personplan.util.DbException;

public class ExampleStepManager implements IStepManager {

	@Override
	public void add(BeanPlan plan, String name, String planstartdate,
			String planfinishdate) throws BaseException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into step(plan_id,name,plan_start,plan_done,fin_start,fin_done,status) values(?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1,plan.getId());
			pst.setString(2,name);
			pst.setLong(3,DataFormat.StringToTimeStamp(planstartdate));
			pst.setLong(4,DataFormat.StringToTimeStamp(planfinishdate));
			pst.setLong(5,0);
			pst.setLong(6,0);
			pst.setInt(7,0);
			pst.execute();
			pst.close();
			plan.setStep_number(plan.getStep_number()+1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					new ExamplePlanManager().refresh(plan);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		}
		
	}

	@Override
	public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {
		List<BeanStep> result=new ArrayList<BeanStep>();
		if (plan.getStep_number() != 0) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select name,plan_start,plan_done,fin_start,fin_done,status,id from step where plan_id=? ORDER BY id";
				PreparedStatement pst = conn.prepareStatement(sql);
				pst.setInt(1,plan.getId());
				String name;
				long plan_start;
				long plan_done;
				long fin_start;
				long fin_done;
				int status;
				int id;
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					name = rs.getString(1);
					plan_start = rs.getLong(2);
					plan_done = rs.getLong(3);
					fin_start = rs.getLong(4);
					fin_done = rs.getLong(5);
					status = rs.getInt(6);
					id = rs.getInt(7);
					result.add(new BeanStep(plan.getId(),name,plan_start,plan_done,fin_start,fin_done,status,id));
				}
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
		return result;
	}

	@Override
	public void deleteStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishStep(BeanStep step) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
