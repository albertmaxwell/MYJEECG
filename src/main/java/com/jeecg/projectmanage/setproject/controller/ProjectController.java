package com.jeecg.projectmanage.setproject.controller;
import com.jeecg.projectmanage.setproject.entity.ProjectEntity;
import com.jeecg.projectmanage.setproject.service.ProjectServiceI;
import com.jeecg.projectmanage.setproject.page.ProjectPage;
import com.jeecg.projectmanage.setproject.entity.ProjectModuleEntity;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
import java.util.HashMap;
/**   
 * @Title: Controller
 * @Description: 项目立项主表
 * @author onlineGenerator
 * @date 2019-04-30 17:12:28
 * @version V1.0   
 *
 */
@Api(value="Project",description="项目立项主表",tags="projectController")
@Controller
@RequestMapping("/projectController")
public class ProjectController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectServiceI projectService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;

	/**
	 * 项目立项主表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/projectmanage.setproject/projectList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */
	@RequestMapping(params = "datagrid")
	public void datagrid(ProjectEntity project,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ProjectEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, project, request.getParameterMap());
		cq.add();
		this.projectService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除项目立项主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ProjectEntity project, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		project = systemService.getEntity(ProjectEntity.class, project.getId());
		String message = "项目立项主表删除成功";
		try{
			projectService.delMain(project);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目立项主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除项目立项主表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "项目立项主表删除成功";
		try{
			for(String id:ids.split(",")){
				ProjectEntity project = systemService.getEntity(ProjectEntity.class,
				id
				);
				projectService.delMain(project);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目立项主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加项目立项主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ProjectEntity project,ProjectPage projectPage, HttpServletRequest request) {
		String projectModuleListStr=request.getParameter("projectModuleListStr");
		List<ProjectModuleEntity> projectModuleList = JSON.parseArray(projectModuleListStr, ProjectModuleEntity.class);
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			projectService.addMain(project, projectModuleList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目立项主表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(project);
		return j;
	}
	/**
	 * 更新项目立项主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ProjectEntity project,ProjectPage projectPage, HttpServletRequest request) {
		String projectModuleListStr=request.getParameter("projectModuleListStr");
		List<ProjectModuleEntity> projectModuleList = JSON.parseArray(projectModuleListStr, ProjectModuleEntity.class);
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			projectService.updateMain(project, projectModuleList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新项目立项主表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 项目立项主表表单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goForm")
	public ModelAndView goForm(ProjectEntity project, HttpServletRequest req) {
		return new ModelAndView("com/jeecg/projectmanage.setproject/project");
	}

	/**
	 * 加载明细列表[项目立项]
	 * 
	 * @return
	 */
	@RequestMapping(params = "projectModuleList")
	@ResponseBody
	public List<ProjectModuleEntity> projectModuleList(ProjectEntity project, HttpServletRequest req) {
		List<ProjectModuleEntity> projectModuleEntityList =new ArrayList<ProjectModuleEntity>();
		//===================================================================================
		//获取参数
		Object mainTable0 = project.getMainTable();
		//===================================================================================
		//查询-项目立项
	    String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
	    try{
	    	projectModuleEntityList = systemService.findHql(hql0,mainTable0);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
	    return projectModuleEntityList;
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ProjectEntity project,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ProjectEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, project);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ProjectEntity> list=this.projectService.getListByCriteriaQuery(cq, false);
    	List<ProjectPage> pageList=new ArrayList<ProjectPage>();
        if(list!=null&&list.size()>0){
        	for(ProjectEntity entity:list){
        		try{
        		ProjectPage page=new ProjectPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
           		    Object mainTable0 = entity.getMainTable();
				    String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
        	        List<ProjectModuleEntity> projectModuleEntityList = systemService.findHql(hql0,mainTable0);
            		page.setProjectModuleList(projectModuleEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"项目立项主表");
        map.put(NormalExcelConstants.CLASS,ProjectPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("项目立项主表列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<ProjectPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ProjectPage.class, params);
				ProjectEntity entity1=null;
				for (ProjectPage page : list) {
					entity1=new ProjectEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            projectService.addMain(entity1, page.getProjectModuleList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			}
			return j;
	}
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"项目立项主表");
		map.put(NormalExcelConstants.CLASS,ProjectPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("项目立项主表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "projectController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(value="/list/{pageNo}/{pageSize}",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="项目立项主表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ProjectPage>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize>Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(ProjectEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<ProjectEntity> list = this.projectService.getListByCriteriaQuery(query,true);
    	List<ProjectPage> pageList=new ArrayList<ProjectPage>();
        if(list!=null&&list.size()>0){
        	for(ProjectEntity entity:list){
        		try{
        			ProjectPage page=new ProjectPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object mainTable0 = entity.getMainTable();
				     String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
	    			List<ProjectModuleEntity> projectModuleOldList = this.projectService.findHql(hql0,mainTable0);
            		page.setProjectModuleList(projectModuleOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取项目立项主表信息",notes="根据ID获取项目立项主表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ProjectEntity task = projectService.get(ProjectEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取项目立项主表信息为空");
		}
		ProjectPage page = new ProjectPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object mainTable0 = task.getMainTable();
		    String hql0 = "from ProjectModuleEntity where 1 = 1 AND mainTable = ? ";
			List<ProjectModuleEntity> projectModuleOldList = this.projectService.findHql(hql0,mainTable0);
    		page.setProjectModuleList(projectModuleOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建项目立项主表")
	public ResponseMessage<?> create(@ApiParam(name="项目立项主表对象")@RequestBody ProjectPage projectPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProjectPage>> failures = validator.validate(projectPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ProjectModuleEntity> projectModuleList =  projectPage.getProjectModuleList();
		
		ProjectEntity project = new ProjectEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(projectPage,project);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存项目立项主表失败");
        }
		projectService.addMain(project, projectModuleList);

		return Result.success(project);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新项目立项主表",notes="更新项目立项主表")
	public ResponseMessage<?> update(@RequestBody ProjectPage projectPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ProjectPage>> failures = validator.validate(projectPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ProjectModuleEntity> projectModuleList =  projectPage.getProjectModuleList();
		
		ProjectEntity project = new ProjectEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(projectPage,project);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("项目立项主表更新失败");
        }
		projectService.updateMain(project, projectModuleList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除项目立项主表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ProjectEntity project = projectService.get(ProjectEntity.class, id);
			projectService.delMain(project);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目立项主表删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 获取文件附件信息
	 * 
	 * @param id project主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
}
