package com.crm.projectmanage.setproject.service;
import com.crm.projectmanage.setproject.entity.ProjectEntity;
import com.crm.projectmanage.setproject.entity.ProjectPeopleEntity;
import com.crm.projectmanage.setproject.entity.ProjectPlanEntity;


import java.util.List;
import org.jeecgframework.core.common.service.CommonService;


public interface ProjectServiceI extends CommonService{
 	public void delete(ProjectEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ProjectEntity project,
						List<ProjectPeopleEntity> projectPeopleList, List<ProjectPlanEntity> projectPlanList) throws Exception;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ProjectEntity project,
						   List<ProjectPeopleEntity> projectPeopleList, List<ProjectPlanEntity> projectPlanList)throws Exception;
	        
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain(ProjectEntity project) throws Exception;
}
