package com.crm.projectmanage.taskcenter.service.impl;
import com.crm.projectmanage.taskcenter.entity.TaskCenterEntity;

import com.crm.projectmanage.taskcenter.service.TaskCenterServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Service("taskCenterService")
@Transactional
public class TaskCenterServiceImpl extends CommonServiceImpl implements TaskCenterServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(TaskCenterEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(TaskCenterEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(TaskCenterEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}

}