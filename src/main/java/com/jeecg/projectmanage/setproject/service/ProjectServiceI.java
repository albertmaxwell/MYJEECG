package com.jeecg.projectmanage.setproject.service;
import com.jeecg.projectmanage.setproject.entity.ProjectEntity;
import com.jeecg.projectmanage.setproject.entity.ProjectModuleEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ProjectServiceI extends CommonService{
 	public void delete(ProjectEntity entity) throws Exception;
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ProjectEntity project,
	        List<ProjectModuleEntity> projectModuleList) throws Exception;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ProjectEntity project,
	        List<ProjectModuleEntity> projectModuleList)throws Exception;
	        
	/**
	 * 删除一对多
	 * 
	 */
	public void delMain (ProjectEntity project) throws Exception;
}
