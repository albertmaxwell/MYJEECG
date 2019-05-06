package com.crm.projectmanage.taskcenter.controller;
import com.crm.projectmanage.taskcenter.entity.TaskCenterEntity;
import com.crm.projectmanage.taskcenter.service.TaskCenterServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;

import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
import org.apache.commons.lang3.StringUtils;

import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Title: Controller
 * @Description: 任务中心
 * @author onlineGenerator
 * @date 2019-05-01 13:26:01
 * @version V1.0
 *
 */
@Api(value="TaskCenter",description="任务中心",tags="taskCenterController")
@Controller
@RequestMapping("/taskCenterController")
public class TaskCenterController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TaskCenterController.class);

	@Autowired
	private TaskCenterServiceI taskCenterService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;



	/**
	 * 任务中心列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/crm/projectmanage/taskcenter/taskCenterList");
	}

	/**
	 * 任务中心列表 页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "myTaskList")
	public ModelAndView myTaskList(HttpServletRequest request) {
		return new ModelAndView("com/crm/projectmanage/taskcenter/taskCenterList");
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
	public void datagrid(TaskCenterEntity taskCenter, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TaskCenterEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, taskCenter, request.getParameterMap());
		cq.add();
		this.taskCenterService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除任务中心
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TaskCenterEntity taskCenter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		taskCenter = systemService.getEntity(TaskCenterEntity.class, taskCenter.getId());
		message = "任务中心删除成功";
		try{
			taskCenterService.delete(taskCenter);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "任务中心删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除任务中心
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "任务中心删除成功";
		try{
			for(String id:ids.split(",")){
				TaskCenterEntity taskCenter = systemService.getEntity(TaskCenterEntity.class,
				id
				);
				taskCenterService.delete(taskCenter);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "任务中心删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加任务中心
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TaskCenterEntity taskCenter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "任务中心添加成功";
		try{
			taskCenterService.save(taskCenter);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "任务中心添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新任务中心
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TaskCenterEntity taskCenter, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "任务中心更新成功";
		TaskCenterEntity t = taskCenterService.get(TaskCenterEntity.class, taskCenter.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(taskCenter, t);
			taskCenterService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "任务中心更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","taskCenterController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TaskCenterEntity taskCenter,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TaskCenterEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, taskCenter, request.getParameterMap());
		List<TaskCenterEntity> taskCenters = this.taskCenterService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"任务中心");
		modelMap.put(NormalExcelConstants.CLASS,TaskCenterEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("任务中心列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,taskCenters);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TaskCenterEntity taskCenter,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"任务中心");
    	modelMap.put(NormalExcelConstants.CLASS,TaskCenterEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("任务中心列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
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
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<TaskCenterEntity> listTaskCenterEntitys = ExcelImportUtil.importExcel(file.getInputStream(),TaskCenterEntity.class,params);
				for (TaskCenterEntity taskCenter : listTaskCenterEntitys) {
					taskCenterService.save(taskCenter);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
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


	@RequestMapping(value="/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="任务中心列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<TaskCenterEntity>> list(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize, HttpServletRequest request) {
		if(pageSize > Globals.MAX_PAGESIZE){
			return Result.error("每页请求不能超过" + Globals.MAX_PAGESIZE + "条");
		}
		CriteriaQuery query = new CriteriaQuery(TaskCenterEntity.class);
		query.setCurPage(pageNo<=0?1:pageNo);
		query.setPageSize(pageSize<1?1:pageSize);
		List<TaskCenterEntity> listTaskCenters = this.taskCenterService.getListByCriteriaQuery(query,true);
		return Result.success(listTaskCenters);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取任务中心信息",notes="根据ID获取任务中心信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		TaskCenterEntity task = taskCenterService.get(TaskCenterEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取任务中心信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建任务中心")
	public ResponseMessage<?> create(@ApiParam(name="任务中心对象")@RequestBody TaskCenterEntity taskCenter, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TaskCenterEntity>> failures = validator.validate(taskCenter);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			taskCenterService.save(taskCenter);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("任务中心信息保存失败");
		}
		return Result.success(taskCenter);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新任务中心",notes="更新任务中心")
	public ResponseMessage<?> update(@ApiParam(name="任务中心对象")@RequestBody TaskCenterEntity taskCenter) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TaskCenterEntity>> failures = validator.validate(taskCenter);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			taskCenterService.saveOrUpdate(taskCenter);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新任务中心信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新任务中心信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除任务中心")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" , id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			taskCenterService.deleteEntityById(TaskCenterEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("任务中心删除失败");
		}

		return Result.success();
	}

}
