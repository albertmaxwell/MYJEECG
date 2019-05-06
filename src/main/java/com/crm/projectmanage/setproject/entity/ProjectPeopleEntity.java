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
@Table(name = "project_people", schema = "")
@SuppressWarnings("serial")
public class ProjectPeopleEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**主表ID*/
	@Excel(name="主表ID",width=15)
	private String mainTable;
	/**编号*/
	@Excel(name="编号",width=15)
	private String code;
	/**项目人员名称*/
	@Excel(name="项目人员名称",width=15)
	private String personName;
	
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
	 *@return: java.lang.String  项目人员名称
	 */
	
	@Column(name ="PERSON_NAME",nullable=true,length=100)
	public String getPersonName(){
		return this.personName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目人员名称
	 */
	public void setPersonName(String personName){
		this.personName = personName;
	}
	
}

