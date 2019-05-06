
package com.crm.projectmanage.setproject.page;
import com.crm.projectmanage.setproject.entity.ProjectPeopleEntity;
import com.crm.projectmanage.setproject.entity.ProjectPlanEntity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 项目立项主表
 * @author onlineGenerator
 * @date 2019-05-06 11:13:15
 * @version V1.0   
 *
 */
public class ProjectPage implements java.io.Serializable {
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
    @Excel(name="编号")
	private String code;
	/**项目背景*/
    @Excel(name="项目背景")
	private String background;
	/**项目名称*/
    @Excel(name="项目名称")
	private String name;
	/**项目详细说明*/
    @Excel(name="项目详细说明")
	private String detail;
	/**项目负责部门*/
    @Excel(name="项目负责部门")
	private String department;
	/**负责人*/
    @Excel(name="负责人")
	private String head;
	/**项目成立时间*/
    @Excel(name="项目成立时间",format = "yyyy-MM-dd")
	private Date setTime;
	/**项目开始时间*/
    @Excel(name="项目开始时间",format = "yyyy-MM-dd")
	private Date startTime;
	/**项目预计结束时间*/
    @Excel(name="项目预计结束时间",format = "yyyy-MM-dd")
	private Date expectEndtime;
	/**实际项目结束时间*/
    @Excel(name="实际项目结束时间",format = "yyyy-MM-dd")
	private Date actualEndtime;
	/**项目状态*/
    @Excel(name="项目状态")
	private String status;
	/**项目类型*/
    @Excel(name="项目类型")
	private String type;
	/**项目客户编号*/
    @Excel(name="项目客户编号")
	private String customerCode;
	/**项目客户名称*/
    @Excel(name="项目客户名称")
	private String customerName;
	/**联系电话*/
    @Excel(name="联系电话")
	private String phone;
	/**备注*/
    @Excel(name="备注")
	private String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	 *@return: java.lang.String  负责人
	 */
	public String getHead(){
		return this.head;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人
	 */
	public void setHead(String head){
		this.head = head;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  项目成立时间
	 */
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
	 *@return: java.lang.String  项目客户编号
	 */
	public String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目客户编号
	 */
	public void setCustomerCode(String customerCode){
		this.customerCode = customerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目客户名称
	 */
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

	/**保存-项目人员*/
    @ExcelCollection(name="项目人员")
	private List<ProjectPeopleEntity> projectPeopleList = new ArrayList<ProjectPeopleEntity>();
		public List<ProjectPeopleEntity> getProjectPeopleList() {
		return projectPeopleList;
		}
		public void setProjectPeopleList(List<ProjectPeopleEntity> projectPeopleList) {
		this.projectPeopleList = projectPeopleList;
		}
	/**保存-项目计划*/
    @ExcelCollection(name="项目计划")
	private List<ProjectPlanEntity> projectPlanList = new ArrayList<ProjectPlanEntity>();
		public List<ProjectPlanEntity> getProjectPlanList() {
		return projectPlanList;
		}
		public void setProjectPlanList(List<ProjectPlanEntity> projectPlanList) {
		this.projectPlanList = projectPlanList;
		}
}
