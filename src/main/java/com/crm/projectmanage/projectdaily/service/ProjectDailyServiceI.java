package com.crm.projectmanage.projectdaily.service;
import com.crm.projectmanage.projectdaily.entity.ProjectDailyEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ProjectDailyServiceI extends CommonService{
	
 	public void delete(ProjectDailyEntity entity) throws Exception;
 	
 	public Serializable save(ProjectDailyEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ProjectDailyEntity entity) throws Exception;
 	
}
