package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DataFormat;

public class BeanPlan {
	public static final String[] tableTitles={"编号","名称","计划设立时间","步骤数","已完成数","状态"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */

	private int id;
	private String name;
	private long create_time;
	private long done_time;
	private int step_number;
	private int done_number;
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDone_time(long done_time) {
		this.done_time = done_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(long create_time) {
		this.create_time = create_time;
	}

	public long getDone_time() {
		return done_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStep_number() {
		return step_number;
	}

	public void setStep_number(int step_number) {
		this.step_number = step_number;
	}

	public int getDone_number() {
		return done_number;
	}

	public void setDone_number(int done_number) {
		this.done_number = done_number;
	}

//	public String getCell(int col){
//		if(col==0) return String.valueOf(getId());
//		else if(col==1) return getName();
//		else if(col==2) return String.valueOf(getStep_number());
////		else if(col==2) return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(System.currentTimeMillis() * 1000));
//		else if(col==3) return String.valueOf(getDone_number());
//		else return "";
//	}


	// java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date(System.currentTimeMillis()));

	public String getCell(int col, int n){
		if (col==0) return String.valueOf(n);
		else if(col==1) return getName();
		else if(col==2) return DataFormat.TimeStampToString(getCreate_time());
		else if(col==3) return String.valueOf(getStep_number());
		else if(col==4) return String.valueOf(getDone_number());
		else if(col==5) return ((getStatus() == 1) ? "已完成" : "未完成");
		else return "";
	}

	public BeanPlan(){}


	public BeanPlan(int id,String name, long create_time, long done_time, int step_number, int done_number, int status) {
		this.id = id;
		this.name = name;
		this.create_time = create_time;
		this.done_time = done_time;
		this.step_number = step_number;
		this.done_number = done_number;
		this.status = status;
	}

}
