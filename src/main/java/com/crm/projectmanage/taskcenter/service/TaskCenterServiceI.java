package com.crm.projectmanage.taskcenter.service;
import com.crm.projectmanage.taskcenter.entity.TaskCenterEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TaskCenterServiceI extends CommonService{
	
 	public void delete(TaskCenterEntity entity) throws Exception;
 	
 	public Serializable save(TaskCenterEntity entity) throws Exception;
 	
 	public void saveOrUpdate(TaskCenterEntity entity) throws Exception;

}
