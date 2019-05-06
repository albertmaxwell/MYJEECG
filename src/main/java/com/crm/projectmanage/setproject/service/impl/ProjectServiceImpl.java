package com.crm.projectmanage.setproject.service.impl;
import com.crm.projectmanage.setproject.entity.ProjectEntity;
import com.crm.projectmanage.setproject.entity.ProjectPeopleEntity;
import com.crm.projectmanage.setproject.entity.ProjectPlanEntity;

import com.crm.projectmanage.setproject.service.ProjectServiceI;
import org.apache.tools.ant.Project;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;

import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Service("projectService")
@Transactional
public class ProjectServiceImpl extends CommonServiceImpl implements ProjectServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(ProjectEntity entity) throws Exception{
 		super.delete(entity);
 	}
	public void addMain(ProjectEntity project,
						List<ProjectPeopleEntity> projectPeopleList, List<ProjectPlanEntity> projectPlanList) throws Exception{
			//保存主信息
			this.save(project);
		
			/**保存-项目人员*/
			for(ProjectPeopleEntity projectPeople:projectPeopleList){
				//外键设置
				projectPeople.setMainTable(project.getId());
				this.save(projectPeople);
			}
			/**保存-项目计划*/
			for(ProjectPlanEntity projectPlan:projectPlanList){
				//外键设置
				projectPlan.setMainTable(project.getId());
				this.save(projectPlan);
			}
	}

	
	public void updateMain(ProjectEntity project,
	        List<ProjectPeopleEntity> projectPeopleList,List<ProjectPlanEntity> projectPlanList)  throws Exception{
		//保存主表信息
		if(StringUtil.isNotEmpty(project.getId())){
			try {
				ProjectEntity temp = findUniqueByProperty(ProjectEntity.class, "id", project.getId());
				MyBeanUtils.copyBeanNotNull2Bean(project, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(project);
		}
		//===================================================================================
		//获取参数
		Object mainTable0 = project.getId();
		Object mainTable1 = project.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-项目人员
	    String hql0 = "from ProjectPeopleEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectPeopleEntity> projectPeopleOldList = this.findHql(hql0,mainTable0);
		//2.筛选更新明细数据-项目人员
		if(projectPeopleList!=null&&projectPeopleList.size()>0){
		for(ProjectPeopleEntity oldE:projectPeopleOldList){
			boolean isUpdate = false;
				for(ProjectPeopleEntity sendE:projectPeopleList){
					//需要更新的明细数据-项目人员
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-项目人员
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-项目人员
			for(ProjectPeopleEntity projectPeople:projectPeopleList){
				if(oConvertUtils.isEmpty(projectPeople.getId())){
					//外键设置
					projectPeople.setMainTable(project.getId());
					this.save(projectPeople);
				}
			}
		}
		//===================================================================================
		//1.查询出数据库的明细数据-项目计划
	    String hql1 = "from ProjectPlanEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectPlanEntity> projectPlanOldList = this.findHql(hql1,mainTable1);
		//2.筛选更新明细数据-项目计划
		if(projectPlanList!=null&&projectPlanList.size()>0){
		for(ProjectPlanEntity oldE:projectPlanOldList){
			boolean isUpdate = false;
				for(ProjectPlanEntity sendE:projectPlanList){
					//需要更新的明细数据-项目计划
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-项目计划
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-项目计划
			for(ProjectPlanEntity projectPlan:projectPlanList){
				if(oConvertUtils.isEmpty(projectPlan.getId())){
					//外键设置
					projectPlan.setMainTable(project.getId());
					this.save(projectPlan);
				}
			}
		}
	}

	
	public void delMain(ProjectEntity project)  throws Exception{
		//删除主表信息
		this.delete(project);
		//===================================================================================
		//获取参数
		Object mainTable0 = project.getId();
		Object mainTable1 = project.getId();
		//===================================================================================
		//删除-项目人员
	    String hql0 = "from ProjectPeopleEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectPeopleEntity> projectPeopleOldList = this.findHql(hql0,mainTable0);
		this.deleteAllEntitie(projectPeopleOldList);
		//===================================================================================
		//删除-项目计划
	    String hql1 = "from ProjectPlanEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectPlanEntity> projectPlanOldList = this.findHql(hql1,mainTable1);
		this.deleteAllEntitie(projectPlanOldList);
	}
	
 	
}