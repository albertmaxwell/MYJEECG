<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>项目立项主表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name=viewportcontent="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
	<link rel="stylesheet" href="${webRoot}/plug-in/element-ui/css/index.css">
	<link href="${webRoot}/plug-in/themes/bootstrap-ext/css/validform-ext.css" rel="stylesheet" />
	<style>
	[v-cloak] { display: none }
	</style>
</head>
<body style="background-color: #FFFFFF;">
	<div id="projectForm" v-cloak>
	<form class="form-horizontal" role="form" id="form" action="" method="POST">
		<button type="button" id="btn_sub" class="btn_sub" @click="addSubmit()" style="display:none"></button>
		<input type="hidden" id="id" :value="form.id">
			<!--新增界面-->
			<el-form label-width="80px" size="mini">
				<el-row>
				  	<el-col :span="8">
					<el-form-item label="编号" prop="code">
						<el-input v-model="form.code" name="code" auto-complete="off" placeholder="请输入编号"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目背景" prop="background">
						<el-input v-model="form.background" name="background" auto-complete="off" placeholder="请输入项目背景"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目名称" prop="name">
						<el-input v-model="form.name" name="name" auto-complete="off" placeholder="请输入项目名称"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目详细说明" prop="detail">
						<el-upload
						  :action="url.upload"
						  :data="{isup:'1'}"
						  :on-success="handleMainDetailUploadFile"
						  :on-remove="handleMainDetailRemoveFile"
						  :file-list="formFile.main_detail">
						  <el-button size="small" type="primary">点击上传</el-button>
						</el-upload>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目负责部门" prop="department">
						<el-input v-model="form.department" name="department" auto-complete="off" placeholder="请输入项目负责部门"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="负责人" prop="head">
						<el-input v-model="form.head" name="head" auto-complete="off" placeholder="请输入负责人"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目成立时间">
						<el-date-picker type="date" name="setTime" placeholder="选择项目成立时间" v-model="form.setTime"></el-date-picker>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目开始时间">
						<el-date-picker type="date" name="startTime" placeholder="选择项目开始时间" v-model="form.startTime"></el-date-picker>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目预计结束时间">
						<el-date-picker type="date" name="expectEndtime" placeholder="选择项目预计结束时间" v-model="form.expectEndtime"></el-date-picker>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="实际项目结束时间">
						<el-date-picker type="date" name="actualEndtime" placeholder="选择实际项目结束时间" v-model="form.actualEndtime"></el-date-picker>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目状态" prop="status">
						<el-input v-model="form.status" name="status" auto-complete="off" placeholder="请输入项目状态"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目类型" prop="type">
						<el-input v-model="form.type" name="type" auto-complete="off" placeholder="请输入项目类型"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目客户编号" prop="customerCode">
						<el-input v-model="form.customerCode" name="customerCode" auto-complete="off" placeholder="请输入项目客户编号"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="项目客户名称" prop="customerName">
						<el-input v-model="form.customerName" name="customerName" auto-complete="off" placeholder="请输入项目客户名称"></el-input>
					</el-form-item>
					</el-col>
				  	<el-col :span="8">
					<el-form-item label="联系电话" prop="phone">
						<el-input v-model="form.phone" name="phone" auto-complete="off" placeholder="请输入联系电话"></el-input>
					</el-form-item>
					</el-col>
				<el-col :span="8">
					<el-form-item label="备注">
						<el-input type="textarea" name="note" v-model="form.note"></el-input>
					</el-form-item>
				</el-col>
				</el-row>
			</el-form>
			
			<el-tabs type="card">
				<el-tab-pane label="项目立项">
					<el-table size="mini" :data="projectModuleList" class="tb-edit" highlight-current-row style="width: 100%;">
				  		<el-table-column prop="code" label="编号" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="code" v-model="scope.row.code" placeholder="编号"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="mainTable" label="主表外键" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="mainTable" v-model="scope.row.mainTable" placeholder="主表外键"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="moduleName" label="模块名称" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="moduleName" v-model="scope.row.moduleName" placeholder="模块名称"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="moduleDetail" label="模块详情" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="moduleDetail" v-model="scope.row.moduleDetail" placeholder="模块详情"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="moduleHead" label="负责人" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="moduleHead" v-model="scope.row.moduleHead" placeholder="负责人"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="department" label="部门" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="department" v-model="scope.row.department" placeholder="部门"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="status" label="状态" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="status" v-model="scope.row.status" placeholder="状态"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="setTime" label="设立时间" min-width="120">
				  			<template scope="scope">
								<el-date-picker  size="mini" name="setTime" type="date" placeholder="选择设立时间" v-model="scope.row.setTime"></el-date-picker>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="expectEndtime" label="预计完成时间" min-width="120">
				  			<template scope="scope">
								<el-date-picker  size="mini" name="expectEndtime" type="date" placeholder="选择预计完成时间" v-model="scope.row.expectEndtime"></el-date-picker>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="phone" label="联系电话" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="phone" v-model="scope.row.phone" placeholder="联系电话"></el-input>
			                </template>
				  		</el-table-column>
				  		
				  		<el-table-column label="操作" width="50">
			                <template scope="scope">
								<a @click="handleRowDelete('projectModuleList',scope.$index, scope.row)"><i class="el-icon-minus"></i></a>
								<a @click="handleProjectModuleAdd('projectModuleList',scope.$index, scope.row)"><i class="el-icon-plus"></i></a>
			                </template>
			            </el-table-column>
				  	</el-table>
				</el-tab-pane>
			</el-tabs>
	</form>
	</div>
</body>
<script src="${webRoot}/plug-in/vue/vue.js"></script>
<script src="${webRoot}/plug-in/vue/vue-resource.js"></script>
<script src="${webRoot}/plug-in/element-ui/index.js"></script>
<!-- Jquery组件引用 -->
<script src="${webRoot}/plug-in/jquery/jquery-1.9.1.js"></script>
<script src="${webRoot}/plug-in/layer/layer.js"></script>
<!-- Validform组件引用 -->
<script src="${webRoot}/plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js"></script>
<script src="${webRoot}/plug-in/Validform/js/Validform_Datatype_zh-cn.js"></script>
<script src="${webRoot}/plug-in/Validform/js/datatype_zh-cn.js"></script>
<script src="${webRoot}/plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js"></script>
<!-- 验证提示 -->
<script src="${webRoot}/plug-in/themes/bootstrap-ext/js/common.js"></script>
<script>
	var valid=null;
	$(function(){
		valid=$("#form").Validform({
			tiptype:function(msg,o,cssctl){
				if(o.type==3){
					var oopanel = $(o.obj).closest(".el-tab-pane");
					var a = 0;
					if(oopanel.length>0){
						var panelID = oopanel.attr("id");
						if(!!panelID){
							var waitActive = $("#tab-"+panelID.substring(panelID.indexOf("-")+1));
							if(!waitActive.attr(".aria-selected")){
								waitActive.click();
								a = 1;
							}
						}
					}
					if(a==1){
						setTimeout(function(){validationMessage(o.obj,msg);},500);
					}else{
						validationMessage(o.obj,msg);
					}
				}else{
					removeMessage(o.obj);
				}
			},
			btnSubmit : "#btn_sub",
			ajaxPost : true,
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 18,
					trigger : function(obj, error) {
						if (error) {
							obj.parent().next().find(".Validform_checktip").show();
							obj.find(".passwordStrength").hide();
						} else {
							$(".passwordStrength").show();
							obj.parent().next().find(".Validform_checktip").hide();
						}
					}
				}
			}
	    });
	});
	var vue = new Vue({ 			
		el:"#projectForm",
		data() {
			return {
				url:{
					save:'${webRoot}/projectController.do?doAdd',
					edit:'${webRoot}/projectController.do?doUpdate',
					queryDict:'${webRoot}/systemController.do?typeListJson',
					upload:'${webRoot}/systemController/filedeal.do',
					projectModuleList:'${webRoot}/projectController.do?projectModuleList',
				},

				//新增界面数据
				form: {
				}, 
				formFile: {
					main_detail:[],
				},
				//子表数据对象
				projectModuleList:[{
				}],
				//子表数据字典对象
			}
		},
		methods: {
			handleMainDetailUploadFile: function(response, file, fileList){
				file.url=response.obj;
				this.form.detail=response.obj;
				if(fileList.length>1){
					this.handleMainDetailRemoveFile(fileList.splice(0,1)[0],fileList);
				}
			},
			handleMainDetailRemoveFile: function(file, fileList){
				if(fileList.length==0){
					this.form.detail="";
				}
				this.$http.get(this.url.upload,{
					params:{
						isdel:'1',
						path:file.url
					}
				}).then((res) => {
				});
			},
			formatDate: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd'):'';
			},
			formatDateTime: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd hh:mm:ss'):'';
			},
			handleProjectModuleAdd(rowsName,index, row) {
	        	this[rowsName].push({
	        	});
	        	this.addValidType();
	        },
	      	//获取子表数据
			getProjectModuleList(id){
				this.projectModuleList=[{
				}];
				if(!id){
					return;
				}
				this.$http.get(this.url.projectModuleList,{
					params: {
						id:id
					}
				}).then((res) => {
					if(res.data.length>0){
						for (var i = 0; i < res.data.length; i++) {
							var data = res.data[i];
						}
						this.projectModuleList = res.data;
					}
				});
			},
	        handleRowDelete(rowsName,index, row) {
	        	this[rowsName].splice(index, 1);
	        },
			//显示编辑界面
			initForm: function (row) {
				if(!!row){
					this.form = Object.assign({}, row);
					var detail=[];
					if(!!this.form.detail){
						detail=[{
							name:this.form.detail.substring(this.form.detail.lastIndexOf('\\')+1),
							url:this.form.detail
						}]
					}
					this.formFile={
							main_detail:detail,
					};
					//加载子表列表
					this.getProjectModuleList(this.form.id);
				}
				this.initDictsData();
				this.addValidType();
			},
			//初始化校验
			addValidType:function(){
	 				$("input[name='code']").each(function(){
	 					if(!$(this).attr("datatype")){
	 						$(this).attr("datatype","*").attr("validType","project,code,id");
	 					}
	 				});
				$("input[name='background']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","*").attr("nullmsg","请填写【项目立项主表】-[项目背景]");
 					}
 				});
				$("input[name='phone']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","m").attr("ignore","ignore");
 					}
 				});
	    	 	setTimeout(function(){
	 				$("input[name='moduleName']").each(function(){
	 					if(!$(this).attr("datatype")){
	 						$(this).attr("datatype","*").attr("validType","project_module,module_name,id");
	 					}
	 				});
				$("input[name='moduleDetail']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","*").attr("nullmsg","请填写【项目立项】-[模块详情]");
 					}
 				});
	    	 	},1000);
	        },
			//初始化数据字典
			initDictsData:function(){
	        	var _this = this;
	        },
	        initDictByCode:function(code,_this,dictOptionsName){
	        	if(!code || !_this[dictOptionsName] || _this[dictOptionsName].length>0)
	        		return;
	        	this.$http.get(this.url.queryDict,{params: {typeGroupName:code}}).then((res) => {
	        		var data=res.data;
					if(data.success){
					  _this[dictOptionsName] = data.obj;
					  _this[dictOptionsName].splice(0, 1);//去掉请选择
					}
				});
	        },
			//新增
			addSubmit: function () {
				if(!valid.check(true)){
					return false;
				}
				var _this=this;
				_this.$confirm('确认提交吗？', '提示', {}).then(() => {
					let para = Object.assign({}, _this.form);
					para.setTime = !para.setTime ? '' : utilFormatDate(new Date(para.setTime), 'yyyy-MM-dd');
					para.startTime = !para.startTime ? '' : utilFormatDate(new Date(para.startTime), 'yyyy-MM-dd');
					para.expectEndtime = !para.expectEndtime ? '' : utilFormatDate(new Date(para.expectEndtime), 'yyyy-MM-dd');
					para.actualEndtime = !para.actualEndtime ? '' : utilFormatDate(new Date(para.actualEndtime), 'yyyy-MM-dd');
					
					
					for (var i = 0; i < _this.projectModuleList.length; i++) {
						_this.projectModuleList[i].test4=!_this.projectModuleList[i].test4 ? '' : utilFormatDate(new Date(_this.manyOneList[i].test4), 'yyyy-MM-dd');
				  		_this.projectModuleList[i].setTime=!_this.projectModuleList[i].setTime ? '' : utilFormatDate(new Date(_this.projectModuleList[i].setTime), 'yyyy-MM-dd');
				  		_this.projectModuleList[i].expectEndtime=!_this.projectModuleList[i].expectEndtime ? '' : utilFormatDate(new Date(_this.projectModuleList[i].expectEndtime), 'yyyy-MM-dd');
					}
					para.projectModuleListStr=JSON.stringify(_this.projectModuleList);
					
					_this.$http.post(!!para.id?_this.url.edit:_this.url.save,para,{emulateJSON: true}).then((res) => {
						var win = window.parent;
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index); 
						win.vue.getProjects();
						var data=res.data;
						if (data.success == true) {
							win.vue.$message({
								message: '提交成功',
								type: 'success',
								duration:1500
							});
						} else {
							win.vue.$message({
								message: '提交失败',
								type: 'error',
								duration:1500
							});
						}
					});
				});
			},
		}
	});
	
	function utilFormatDate(date, pattern) {
        pattern = pattern || "yyyy-MM-dd";
        return pattern.replace(/([yMdhsm])(\1*)/g, function ($0) {
            switch ($0.charAt(0)) {
                case 'y': return padding(date.getFullYear(), $0.length);
                case 'M': return padding(date.getMonth() + 1, $0.length);
                case 'd': return padding(date.getDate(), $0.length);
                case 'w': return date.getDay() + 1;
                case 'h': return padding(date.getHours(), $0.length);
                case 'm': return padding(date.getMinutes(), $0.length);
                case 's': return padding(date.getSeconds(), $0.length);
            }
        });
    };
	function padding(s, len) {
	    var len = len - (s + '').length;
	    for (var i = 0; i < len; i++) { s = '0' + s; }
	    return s;
	};
</script>
</html>