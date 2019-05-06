package com.crm.projectmanage.projectdaily.service.impl;
import com.crm.projectmanage.projectdaily.entity.ProjectDailyEntity;

import com.crm.projectmanage.projectdaily.service.ProjectDailyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


@Service("projectDailyService")
@Transactional
public class ProjectDailyServiceImpl extends CommonServiceImpl implements ProjectDailyServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
 	public void delete(ProjectDailyEntity entity) throws Exception{
 		super.delete(entity);
 	}
 	
 	public Serializable save(ProjectDailyEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ProjectDailyEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 	}
 	
}