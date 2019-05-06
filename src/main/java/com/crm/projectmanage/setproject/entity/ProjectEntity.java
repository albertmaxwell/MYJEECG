package com.crm.projectmanage.setproject.entity;

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
 * @date 2019-05-06 11:13:15
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project", schema = "")
@SuppressWarnings("serial")
public class ProjectEntity implements java.io.Serializable {
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
	/**编号*/
	@Excel(name="编号",width=15)
	private String code;
	/**项目背景*/
	@Excel(name="项目背景",width=15)
	private String background;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private String name;
	/**项目详细说明*/
	@Excel(name="项目详细说明",width=15)
	private String detail;
	/**项目负责部门*/
	@Excel(name="项目负责部门",width=15)
	private String department;
	/**负责人ID*/
	@Excel(name="负责人ID",width=15)
	private String headId;
	/**负责人名称*/
	@Excel(name="负责人名称",width=15)
	private String headName;
	/**项目成立时间*/
	@Excel(name="项目成立时间",width=15,format = "yyyy-MM-dd")
	private Date setTime;
	/**项目开始时间*/
	@Excel(name="项目开始时间",width=15,format = "yyyy-MM-dd")
	private Date startTime;
	/**项目预计结束时间*/
	@Excel(name="项目预计结束时间",width=15,format = "yyyy-MM-dd")
	private Date expectEndtime;
	/**实际项目结束时间*/
	@Excel(name="实际项目结束时间",width=15,format = "yyyy-MM-dd")
	private Date actualEndtime;
	/**项目状态*/
	@Excel(name="项目状态",width=15)
	private String status;
	/**项目类型*/
	@Excel(name="项目类型",width=15)
	private String type;
	/**项目客户ID*/
	@Excel(name="项目客户ID",width=15)
	private String customerId;
	/**项目客户名称*/
	@Excel(name="项目客户名称",width=15)
	private String customerName;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private String phone;
	/**备注*/
	@Excel(name="备注",width=15)
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
	 *@return: java.lang.String  项目背景
	 */

	@Column(name ="BACKGROUND",nullable=true,length=100)
	public String getBackground(){
		return this.background;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目背景
	 */
	public void setBackground(String background){
		this.background = background;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="NAME",nullable=true,length=100)
	public String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目详细说明
	 */

	@Column(name ="DETAIL",nullable=true,length=100)
	public String getDetail(){
		return this.detail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目详细说明
	 */
	public void setDetail(String detail){
		this.detail = detail;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目负责部门
	 */

	@Column(name ="DEPARTMENT",nullable=true,length=100)
	public String getDepartment(){
		return this.department;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目负责部门
	 */
	public void setDepartment(String department){
		this.department = department;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人ID
	 */

	@Column(name ="HEAD_ID",nullable=true,length=100)
	public String getHeadId(){
		return this.headId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人ID
	 */
	public void setHeadId(String headId){
		this.headId = headId;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人名称
	 */

	@Column(name ="HEAD_NAME",nullable=true,length=100)
	public String getHeadName(){
		return this.headName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人名称
	 */
	public void setHeadName(String headName){
		this.headName = headName;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目成立时间
	 */

	@Column(name ="SET_TIME",nullable=true,length=100)
	public Date getSetTime(){
		return this.setTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目成立时间
	 */
	public void setSetTime(Date setTime){
		this.setTime = setTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目开始时间
	 */

	@Column(name ="START_TIME",nullable=true,length=100)
	public Date getStartTime(){
		return this.startTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目开始时间
	 */
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目预计结束时间
	 */

	@Column(name ="EXPECT_ENDTIME",nullable=true,length=100)
	public Date getExpectEndtime(){
		return this.expectEndtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  项目预计结束时间
	 */
	public void setExpectEndtime(Date expectEndtime){
		this.expectEndtime = expectEndtime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  实际项目结束时间
	 */

	@Column(name ="ACTUAL_ENDTIME",nullable=true,length=100)
	public Date getActualEndtime(){
		return this.actualEndtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  实际项目结束时间
	 */
	public void setActualEndtime(Date actualEndtime){
		this.actualEndtime = actualEndtime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目状态
	 */

	@Column(name ="STATUS",nullable=true,length=5)
	public String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目状态
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目类型
	 */

	@Column(name ="TYPE",nullable=true,length=5)
	public String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目类型
	 */
	public void setType(String type){
		this.type = type;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目客户ID
	 */

	@Column(name ="CUSTOMER_ID",nullable=true,length=100)
	public String getCustomerId(){
		return this.customerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目客户ID
	 */
	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目客户名称
	 */

	@Column(name ="CUSTOMER_NAME",nullable=true,length=100)
	public String getCustomerName(){
		return this.customerName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目客户名称
	 */
	public void setCustomerName(String customerName){
		this.customerName = customerName;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="NOTE",nullable=true,length=100)
	public String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(String note){
		this.note = note;
	}
	
}
