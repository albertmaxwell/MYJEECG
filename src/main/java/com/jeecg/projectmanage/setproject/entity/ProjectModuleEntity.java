package com.jeecg.projectmanage.setproject.entity;
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
 * @date 2019-04-30 17:12:28
 * @version V1.0   
 *
 */
@Entity
@Table(name = "project_module", schema = "")
@SuppressWarnings("serial")
public class ProjectModuleEntity implements java.io.Serializable {
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
	/**主表外键*/
	@Excel(name="主表外键",width=15)
	private java.lang.String mainTable;
	/**模块名称*/
	@Excel(name="模块名称",width=15)
	private java.lang.String moduleName;
	/**模块详情*/
	@Excel(name="模块详情",width=15)
	private java.lang.String moduleDetail;
	/**负责人*/
	@Excel(name="负责人",width=15)
	private java.lang.String moduleHead;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String department;
	/**状态*/
	@Excel(name="状态",width=15)
	private java.lang.String status;
	/**设立时间*/
	@Excel(name="设立时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date setTime;
	/**预计完成时间*/
	@Excel(name="预计完成时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date expectEndtime;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private java.lang.String phone;
	
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
	 *@return: java.lang.String  主表外键
	 */
	
	@Column(name ="MAIN_TABLE",nullable=true,length=100)
	public java.lang.String getMainTable(){
		return this.mainTable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表外键
	 */
	public void setMainTable(java.lang.String mainTable){
		this.mainTable = mainTable;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模块名称
	 */
	
	@Column(name ="MODULE_NAME",nullable=true,length=100)
	public java.lang.String getModuleName(){
		return this.moduleName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setModuleName(java.lang.String moduleName){
		this.moduleName = moduleName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模块详情
	 */
	
	@Column(name ="MODULE_DETAIL",nullable=true,length=100)
	public java.lang.String getModuleDetail(){
		return this.moduleDetail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块详情
	 */
	public void setModuleDetail(java.lang.String moduleDetail){
		this.moduleDetail = moduleDetail;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人
	 */
	
	@Column(name ="MODULE_HEAD",nullable=true,length=100)
	public java.lang.String getModuleHead(){
		return this.moduleHead;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setModuleHead(java.lang.String moduleHead){
		this.moduleHead = moduleHead;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */
	
	@Column(name ="DEPARTMENT",nullable=true,length=100)
	public java.lang.String getDepartment(){
		return this.department;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDepartment(java.lang.String department){
		this.department = department;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态
	 */
	
	@Column(name ="STATUS",nullable=true,length=5)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  设立时间
	 */
	
	@Column(name ="SET_TIME",nullable=true,length=100)
	public java.util.Date getSetTime(){
		return this.setTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  设立时间
	 */
	public void setSetTime(java.util.Date setTime){
		this.setTime = setTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预计完成时间
	 */
	
	@Column(name ="EXPECT_ENDTIME",nullable=true,length=100)
	public java.util.Date getExpectEndtime(){
		return this.expectEndtime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预计完成时间
	 */
	public void setExpectEndtime(java.util.Date expectEndtime){
		this.expectEndtime = expectEndtime;
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
	
}
