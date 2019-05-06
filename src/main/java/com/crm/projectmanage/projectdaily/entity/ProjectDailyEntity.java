package com.crm.projectmanage.projectdaily.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.io.UnsupportedEncodingException;
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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 项目日报
 * @author onlineGenerator
 * @date 2019-05-01 15:12:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_daily", schema = "")
@SuppressWarnings("serial")
public class ProjectDailyEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**创建人名称*/
	private String createName;
	/**创建人登录名称*/
	private String createBy;
	/**创建日期*/
	private Date createDate;
	/**更新人名称*/
	private String updateName;
	/**更新人登录名称*/
	private String updateBy;
	/**更新日期*/
	private Date updateDate;
	/**所属部门*/
	private String sysOrgCode;
	/**所属公司*/
	private String sysCompanyCode;
	/**流程状态*/
	private String bpmStatus;
	/**日报标题*/
	@Excel(name="日报标题",width=15)
	private String title;
	/**今日实际完成*/
	@Excel(name="今日实际完成",width=15)
	private String todayPlan;
	/**日报日期*/
	@Excel(name="日报日期",width=15,format = "yyyy-MM-dd")
	private Date dailyDate;
	/**填报人*/
	@Excel(name="填报人ID",width=15)
	private String writePersonId;
	/**填报人*/
	@Excel(name="填报人名称",width=15)
	private String writePersonName;
	/**部门*/
	@Excel(name="部门",width=15)
	private String department;
	/**岗位*/
	@Excel(name="岗位",width=15)
	private String post;
	/**填写日报时间*/
	@Excel(name="填写日报时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private Date writeTime;
	/**隶属项目ID*/
	@Excel(name="隶属项目ID",width=15)
	private String projectId;
	/**隶属项目名称*/
	@Excel(name="隶属项目名称",width=15)
	private String projectName;
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
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  日报标题
	 */

	@Column(name ="TITLE",nullable=true,length=100)
	public String getTitle(){
		return this.title;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  日报标题
	 */
	public void setTitle(String title){
		this.title = title;
	}

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  今日实际完成
	 */

	@Column(name ="TODAY_PLAN",nullable=true,length=100)
	public String getTodayPlan(){
		return this.todayPlan;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  今日实际完成
	 */
	public void setTodayPlan(String todayPlan){
		this.todayPlan = todayPlan;
	}

	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日报日期
	 */

	@Column(name ="DAILY_DATE",nullable=true,length=100)
	public Date getDailyDate(){
		return this.dailyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日报日期
	 */
	public void setDailyDate(Date dailyDate){
		this.dailyDate = dailyDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  填报人ID
	 */

	@Column(name ="WRITE_PERSON_ID",nullable=true,length=100)
	public String getWritePersonId(){
		return this.writePersonId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  填报人ID
	 */
	public void setWritePersonId(String writePersonId){
		this.writePersonId = writePersonId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  填报人名称
	 */

	@Column(name ="WRITE_PERSON_NAME",nullable=true,length=100)
	public String getWritePersonName(){
		return this.writePersonName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  填报人名称
	 */
	public void setWritePersonName(String writePersonName){
		this.writePersonName = writePersonName;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="DEPARTMENT",nullable=true,length=100)
	public String getDepartment(){
		return this.department;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDepartment(String department){
		this.department = department;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  岗位
	 */

	@Column(name ="POST",nullable=true,length=100)
	public String getPost(){
		return this.post;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  岗位
	 */
	public void setPost(String post){
		this.post = post;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  填写日报时间
	 */

	@Column(name ="WRITE_TIME",nullable=true,length=100)
	public Date getWriteTime(){
		return this.writeTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  填写日报时间
	 */
	public void setWriteTime(Date writeTime){
		this.writeTime = writeTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  隶属项目Id
	 */

	@Column(name ="PROJECT_ID",nullable=true,length=100)
	public String getProjectId(){
		return this.projectId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  隶属项目Id
	 */
	public void setProjectId(String projectId){
		this.projectId = projectId;
	}


	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  隶属项目名称
	 */

	@Column(name ="PROJECT_NAME",nullable=true,length=100)
	public String getProjectName(){
		return this.projectName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  隶属项目名称
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}


}
