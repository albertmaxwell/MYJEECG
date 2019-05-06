package com.crm.projectmanage.setproject.entity;
import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 项目立项主表
 * @author onlineGenerator
 * @date 2019-05-06 11:13:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_plan", schema = "")
@SuppressWarnings("serial")
public class ProjectPlanEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**主表ID*/
	@Excel(name="主表ID",width=15)
	private String mainTable;
	/**编号*/
	@Excel(name="编号",width=15)
	private String code;
	/**计划标题*/
	@Excel(name="计划标题",width=15)
	private String planTitle;
	/**计划详情*/
	@Excel(name="计划详情",width=15)
	private String detail;
	/**开始时间*/
	@Excel(name="开始时间",width=15,format = "yyyy-MM-dd")
	private Date startTime;
	/**结束时间*/
	@Excel(name="结束时间",width=15,format = "yyyy-MM-dd")
	private Date endTime;
	/**状态*/
	@Excel(name="状态",width=15)
	private String status;
	/**状态说明*/
	@Excel(name="状态说明",width=15)
	private String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主表ID
	 */
	
	@Column(name ="MAIN_TABLE",nullable=true,length=100)
	public String getMainTable(){
		return this.mainTable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表ID
	 */
	public void setMainTable(String mainTable){
		this.mainTable = mainTable;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */
	
	@Column(name ="CODE",nullable=true,length=100)
	public String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setCode(String code){
		this.code = code;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划标题
	 */
	
	@Column(name ="PLAN_TITLE",nullable=true,length=100)
	public String getPlanTitle(){
		return this.planTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划标题
	 */
	public void setPlanTitle(String planTitle){
		this.planTitle = planTitle;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划详情
	 */
	
	@Column(name ="DETAIL",nullable=true,length=100)
	public String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划详情
	 */
	public void setDetail(String detail){
		this.detail = detail;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	
	@Column(name ="START_TIME",nullable=true,length=100)
	public Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	
	@Column(name ="END_TIME",nullable=true,length=100)
	public Date getEndTime(){
		return this.endTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	
	@Column(name ="STATUS",nullable=true,length=5)
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态说明
	 */
	
	@Column(name ="NOTE",nullable=true,length=100)
	public String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态说明
	 */
	public void setNote(String note){
		this.note = note;
	}
	
}
