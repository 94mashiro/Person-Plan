package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.DataFormat;

public class BeanStep {
	public static final String[] tblStepTitle={"编号","名称","计划开始时间","计划完成时间","实际开始时间","实际完成时间"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col,int n){
		if(col==0) return DataFormat.IntToString(n);
		else if(col==1) return name;
		else if(col==2) return DataFormat.TimeStampToString(plan_start);
		else if(col==3) return DataFormat.TimeStampToString(plan_done);
		else if(col==4) return (fin_start == 0) ? "" : DataFormat.TimeStampToString(fin_start);
		else if(col==5) return (fin_done == 0) ? "" : DataFormat.TimeStampToString(fin_done);
		else return "";
	}

	private int id;
	private int plan_id;
	private String name;
	private long plan_start;
	private long plan_done;
	private long fin_start;
	private long fin_done;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPlan_start() {
		return plan_start;
	}

	public void setPlan_start(long plan_start) {
		this.plan_start = plan_start;
	}

	public long getPlan_done() {
		return plan_done;
	}

	public void setPlan_done(long plan_done) {
		this.plan_done = plan_done;
	}

	public long getFin_start() {
		return fin_start;
	}

	public void setFin_start(long fin_start) {
		this.fin_start = fin_start;
	}

	public long getFin_done() {
		return fin_done;
	}

	public void setFin_done(long fin_done) {
		this.fin_done = fin_done;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BeanStep(int plan_id, String name, long plan_start, long plan_done, long fin_start, long fin_done, int status, int id) {
		this.id = id;
		this.plan_id = plan_id;
		this.name = name;
		this.plan_start = plan_start;
		this.plan_done = plan_done;
		this.fin_start = fin_start;
		this.fin_done = fin_done;
		this.status = status;
	}
}