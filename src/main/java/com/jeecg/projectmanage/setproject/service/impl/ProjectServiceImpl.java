package com.jeecg.projectmanage.setproject.service.impl;
import com.jeecg.projectmanage.setproject.service.ProjectServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import com.jeecg.projectmanage.setproject.entity.ProjectEntity;
import com.jeecg.projectmanage.setproject.entity.ProjectModuleEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.minidao.util.FreemarkerParseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.jeecgframework.core.util.ResourceUtil;

@Service("projectService")
@Transactional
public class ProjectServiceImpl extends CommonServiceImpl implements ProjectServiceI {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
 	public void delete(ProjectEntity entity) throws Exception{
 		super.delete(entity);
 	}
	public void addMain(ProjectEntity project,
	        List<ProjectModuleEntity> projectModuleList)  throws Exception{
			//保存主信息
			this.save(project);
		
			/**保存-项目立项*/
			for(ProjectModuleEntity projectModule:projectModuleList){
				//外键设置
				projectModule.setMainTable(project.getMainTable());
				this.save(projectModule);
			}
	}

	
	public void updateMain(ProjectEntity project,
	        List<ProjectModuleEntity> projectModuleList)  throws Exception{
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
		Object mainTable0 = project.getMainTable();
		//===================================================================================
		//1.查询出数据库的明细数据-项目立项
	    String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectModuleEntity> projectModuleOldList = this.findHql(hql0,mainTable0);
		//2.筛选更新明细数据-项目立项
		if(projectModuleList!=null&&projectModuleList.size()>0){
		for(ProjectModuleEntity oldE:projectModuleOldList){
			boolean isUpdate = false;
				for(ProjectModuleEntity sendE:projectModuleList){
					//需要更新的明细数据-项目立项
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-项目立项
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-项目立项
			for(ProjectModuleEntity projectModule:projectModuleList){
				if(oConvertUtils.isEmpty(projectModule.getId())){
					//外键设置
					projectModule.setMainTable(project.getMainTable());
					this.save(projectModule);
				}
			}
		}
	}

	
	public void delMain(ProjectEntity project)  throws Exception{
		//删除主表信息
		this.delete(project);
		//===================================================================================
		//获取参数
		Object mainTable0 = project.getMainTable();
		//===================================================================================
		//删除-项目立项
	    String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
	    List<ProjectModuleEntity> projectModuleOldList = this.findHql(hql0,mainTable0);
		this.deleteAllEntitie(projectModuleOldList);
	}
	
 	
}