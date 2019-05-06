package com.crm.projectmanage.taskcenter.entity;

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
 * @Description: 任务中心
 * @author onlineGenerator
 * @date 2019-05-01 13:26:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "task_center", schema = "")
@SuppressWarnings("serial")
public class TaskCenterEntity implements java.io.Serializable {
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
	/**任务编号*/
	@Excel(name="任务编号",width=15)
	private String taskCode;
	/**项目ID*/
	@Excel(name="项目ID",width=15)
	private String projectId;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private String projectName;
	/**任务处理人员ID*/
	@Excel(name="任务处理人员ID",width=15)
	private String taskPersonId;
	/**任务处理人员名称*/
	@Excel(name="任务处理人员名称",width=15)
	private String taskPersonName;
	/**任务名称*/
	@Excel(name="任务名称",width=15)
	private String taskName;
	/**内容详情*/
	@Excel(name="内容详情",width=15)
	private String taskContent;
	/**规定完成时间*/
	@Excel(name="规定完成时间",width=15,format = "yyyy-MM-dd")
	private Date setTime;
	/**实际完成时间*/
	@Excel(name="实际完成时间",width=15,format = "yyyy-MM-dd")
	private Date actualComptime;
	/**状态*/
	@Excel(name="状态",width=15)
	private String status;
	/**任务派发人ID*/
	@Excel(name="任务派发人ID",width=15)
	private String handPersonId;
	/**任务派发人名称*/
	@Excel(name="任务派发人名称",width=15)
	private String handoverPersonName;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private String phone;
	/**优先级*/
	@Excel(name="优先级",width=15)
	private String priority;
	
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
	 *@return: java.lang.String  任务编号
	 */

	@Column(name ="TASK_CODE",nullable=true,length=100)
	public String getTaskCode(){
		return this.taskCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务编号
	 */
	public void setTaskCode(String taskCode){
		this.taskCode = taskCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目ID
	 */

	@Column(name ="PROJECT_ID",nullable=true,length=100)
	public String getProjectId(){
		return this.projectId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目ID
	 */
	public void setProjectId(String projectId){
		this.projectId = projectId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="PROJECT_NAME",nullable=true,length=100)
	public String getProjectName(){
		return this.projectName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务处理人员ID
	 */

	@Column(name ="TASK_PERSON_ID",nullable=true,length=100)
	public String getTaskPersonId(){
		return this.taskPersonId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务处理人员ID
	 */
	public void setTaskPersonId(String taskPersonId){
		this.taskPersonId = taskPersonId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务处理人员名称
	 */

	@Column(name ="TASK_PERSON_NAME",nullable=true,length=100)
	public String getTaskPersonName(){
		return this.taskPersonName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务处理人员名称
	 */
	public void setTaskPersonName(String taskPersonName){
		this.taskPersonName = taskPersonName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务名称
	 */

	@Column(name ="TASK_NAME",nullable=true,length=100)
	public String getTaskName(){
		return this.taskName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务名称
	 */
	public void setTaskName(String taskName){
		this.taskName = taskName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内容详情
	 */

	@Column(name ="TASK_CONTENT",nullable=true,length=100)
	public String getTaskContent(){
		return this.taskContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内容详情
	 */
	public void setTaskContent(String taskContent){
		this.taskContent = taskContent;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  规定完成时间
	 */

	@Column(name ="SET_TIME",nullable=true,length=100)
	public Date getSetTime(){
		return this.setTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  规定完成时间
	 */
	public void setSetTime(Date setTime){
		this.setTime = setTime;
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
	 *@return: java.lang.String  联系电话
	 */

	@Column(name ="PHONE",nullable=true,length=100)
	public String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际完成时间
	 */

	@Column(name ="ACTUAL_COMPTIME",nullable=true,length=50)
	public Date getActualComptime(){
		return this.actualComptime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际完成时间
	 */
	public void setActualComptime(Date actualComptime){
		this.actualComptime = actualComptime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务派发人ID
	 */

	@Column(name ="HAND_PERSON_ID",nullable=true,length=100)
	public String getHandPersonId(){
		return this.handPersonId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  任务派发人ID
	 */
	public void setHandPersonId(String handPersonId){
		this.handPersonId = handPersonId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  任务派发名称
	 */

	@Column(name ="HANDOVER_PERSON_NAME",nullable=true,length=100)
	public String getHandoverPersonName(){
		return this.handoverPersonName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String   任务派发名称
	 */
	public void setHandoverPersonName(String handoverPersonName){
		this.handoverPersonName = handoverPersonName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优先级
	 */

	@Column(name ="PRIORITY",nullable=true,length=5)
	public String getPriority(){
		return this.priority;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优先级
	 */
	public void setPriority(String priority){
		this.priority = priority;
	}
	

}
