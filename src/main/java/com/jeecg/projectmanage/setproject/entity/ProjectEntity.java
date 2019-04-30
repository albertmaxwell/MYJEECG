package com.jeecg.projectmanage.setproject.entity;

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
 * @Description: 项目立项主表
 * @author onlineGenerator
 * @date 2019-04-30 17:12:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project", schema = "")
@SuppressWarnings("serial")
public class ProjectEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**编号*/
	@Excel(name="编号",width=15)
	private java.lang.String code;
	/**项目背景*/
	@Excel(name="项目背景",width=15)
	private java.lang.String background;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private java.lang.String name;
	/**项目详细说明*/
	@Excel(name="项目详细说明",width=15)
	private java.lang.String detail;
	/**项目负责部门*/
	@Excel(name="项目负责部门",width=15)
	private java.lang.String department;
	/**负责人*/
	@Excel(name="负责人",width=15)
	private java.lang.String head;
	/**项目成立时间*/
	@Excel(name="项目成立时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date setTime;
	/**项目开始时间*/
	@Excel(name="项目开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date startTime;
	/**项目预计结束时间*/
	@Excel(name="项目预计结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date expectEndtime;
	/**实际项目结束时间*/
	@Excel(name="实际项目结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date actualEndtime;
	/**项目状态*/
	@Excel(name="项目状态",width=15)
	private java.lang.String status;
	/**项目类型*/
	@Excel(name="项目类型",width=15)
	private java.lang.String type;
	/**项目客户编号*/
	@Excel(name="项目客户编号",width=15)
	private java.lang.String customerCode;
	/**项目客户名称*/
	@Excel(name="项目客户名称",width=15)
	private java.lang.String customerName;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private java.lang.String phone;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编号
	 */

	@Column(name ="CODE",nullable=true,length=100)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编号
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目背景
	 */

	@Column(name ="BACKGROUND",nullable=true,length=100)
	public java.lang.String getBackground(){
		return this.background;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目背景
	 */
	public void setBackground(java.lang.String background){
		this.background = background;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目详细说明
	 */

	@Column(name ="DETAIL",nullable=true,length=100)
	public java.lang.String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目详细说明
	 */
	public void setDetail(java.lang.String detail){
		this.detail = detail;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目负责部门
	 */

	@Column(name ="DEPARTMENT",nullable=true,length=100)
	public java.lang.String getDepartment(){
		return this.department;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目负责部门
	 */
	public void setDepartment(java.lang.String department){
		this.department = department;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */

	@Column(name ="HEAD",nullable=true,length=100)
	public java.lang.String getHead(){
		return this.head;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setHead(java.lang.String head){
		this.head = head;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目成立时间
	 */

	@Column(name ="SET_TIME",nullable=true,length=100)
	public java.util.Date getSetTime(){
		return this.setTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目成立时间
	 */
	public void setSetTime(java.util.Date setTime){
		this.setTime = setTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目开始时间
	 */

	@Column(name ="START_TIME",nullable=true,length=100)
	public java.util.Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目开始时间
	 */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目预计结束时间
	 */

	@Column(name ="EXPECT_ENDTIME",nullable=true,length=100)
	public java.util.Date getExpectEndtime(){
		return this.expectEndtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目预计结束时间
	 */
	public void setExpectEndtime(java.util.Date expectEndtime){
		this.expectEndtime = expectEndtime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际项目结束时间
	 */

	@Column(name ="ACTUAL_ENDTIME",nullable=true,length=100)
	public java.util.Date getActualEndtime(){
		return this.actualEndtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际项目结束时间
	 */
	public void setActualEndtime(java.util.Date actualEndtime){
		this.actualEndtime = actualEndtime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目状态
	 */

	@Column(name ="STATUS",nullable=true,length=5)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目类型
	 */

	@Column(name ="TYPE",nullable=true,length=5)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目客户编号
	 */

	@Column(name ="CUSTOMER_CODE",nullable=true,length=100)
	public java.lang.String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目客户编号
	 */
	public void setCustomerCode(java.lang.String customerCode){
		this.customerCode = customerCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目客户名称
	 */

	@Column(name ="CUSTOMER_NAME",nullable=true,length=100)
	public java.lang.String getCustomerName(){
		return this.customerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目客户名称
	 */
	public void setCustomerName(java.lang.String customerName){
		this.customerName = customerName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */

	@Column(name ="PHONE",nullable=true,length=100)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=100)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
	
}
