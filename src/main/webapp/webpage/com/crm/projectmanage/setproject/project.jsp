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
		<el-form label-width="100px"  size="30">
			<el-row>
				<el-col :span="6">
					<el-form-item label="编号" prop="code">
						<el-input v-model="form.code" name="code" auto-complete="off" placeholder="请输入编号"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目名称" prop="name">
						<el-input v-model="form.name" name="name" auto-complete="off" placeholder="请输入项目名称"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目负责部门" prop="department">
						<el-input v-model="form.department" name="department" auto-complete="off" placeholder="请输入项目负责部门"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="负责人名称" prop="headName">
						<el-input v-model="form.headName" name="headName" auto-complete="off" placeholder="请输入负责人名称"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目成立时间">
						<el-date-picker type="date" name="setTime" placeholder="选择项目成立时间" v-model="form.setTime"></el-date-picker>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="开始时间">
						<el-date-picker type="date" name="startTime" placeholder="选择项目开始时间" v-model="form.startTime"></el-date-picker>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="预计结束时间">
						<el-date-picker type="date" name="expectEndtime" placeholder="选择项目预计结束时间" v-model="form.expectEndtime"></el-date-picker>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="实际结束时间">
						<el-date-picker type="date" name="actualEndtime" placeholder="选择实际项目结束时间" v-model="form.actualEndtime"></el-date-picker>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目状态">
						<el-select v-model="form.status"  name="status" placeholder="请选择项目状态">
							<el-option :label="option.typename" :value="option.typecode" v-for="option in proStatusOptions"></el-option>
						</el-select>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目类型" prop="type">
						<el-input v-model="form.type" name="type" auto-complete="off" placeholder="请输入项目类型"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="项目客户名称" prop="customerName">
						<el-input v-model="form.customerName" name="customerName" auto-complete="off" placeholder="请输入项目客户名称"></el-input>
					</el-form-item>
				</el-col>
				<el-col :span="6">
					<el-form-item label="联系电话" prop="phone">
						<el-input v-model="form.phone" name="phone" auto-complete="off" placeholder="请输入联系电话"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="6">
					<el-form-item label="备注">
						<el-input style="width: 1200px" type="textarea" name="note" v-model="form.note"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>
				<el-col :span="6">
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

				<el-col :span="6">
					<el-form-item label="项目背景" prop="background">
						<el-upload
								:action="url.upload"
								:data="{isup:'1'}"
								:on-success="handleMainBackGroundUploadFile"
								:on-remove="handleMainBackGroundRemoveFile"
								:file-list="formFile.main_background">
							<el-button size="small" type="primary">点击上传</el-button>
						</el-upload>
					</el-form-item>
				</el-col>
			</el-row>
		</el-form>
			
			<el-tabs type="card">
				<el-tab-pane label="项目人员">
					<el-table size="mini" :data="projectPeopleList" class="tb-edit" highlight-current-row style="width: 100%;">
				  		<el-table-column prop="mainTable" label="主表ID" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="mainTable" v-model="scope.row.mainTable" placeholder="主表ID"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="code" label="编号" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="code" v-model="scope.row.code" placeholder="编号"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="personName" label="项目人员名称" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="personName" v-model="scope.row.personName" placeholder="项目人员名称"></el-input>
			                </template>
				  		</el-table-column>
				  		
				  		<el-table-column label="操作" width="50">
			                <template scope="scope">
								<a @click="handleRowDelete('projectPeopleList',scope.$index, scope.row)"><i class="el-icon-minus"></i></a>
								<a @click="handleProjectPeopleAdd('projectPeopleList',scope.$index, scope.row)"><i class="el-icon-plus"></i></a>
			                </template>
			            </el-table-column>
				  	</el-table>
				</el-tab-pane>
				<el-tab-pane label="项目计划">
					<el-table size="mini" :data="projectPlanList" class="tb-edit" highlight-current-row style="width: 100%;">
				  		<el-table-column prop="mainTable" label="主表ID" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="mainTable" v-model="scope.row.mainTable" placeholder="主表ID"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="code" label="编号" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="code" v-model="scope.row.code" placeholder="编号"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="planTitle" label="计划标题" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="planTitle" v-model="scope.row.planTitle" placeholder="计划标题"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="detail" label="计划详情" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="detail" v-model="scope.row.detail" placeholder="计划详情"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="startTime" label="开始时间" min-width="120">
				  			<template scope="scope">
								<el-date-picker  size="mini" name="startTime" type="date" placeholder="选择开始时间" v-model="scope.row.startTime"></el-date-picker>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="endTime" label="结束时间" min-width="120">
				  			<template scope="scope">
								<el-date-picker  size="mini" name="endTime" type="date" placeholder="选择结束时间" v-model="scope.row.endTime"></el-date-picker>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="status" label="状态" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="status" v-model="scope.row.status" placeholder="状态"></el-input>
			                </template>
				  		</el-table-column>
				  		<el-table-column prop="note" label="状态说明" min-width="120">
				  			<template scope="scope">
			                    <el-input size="mini" name="note" v-model="scope.row.note" placeholder="状态说明"></el-input>
			                </template>
				  		</el-table-column>
				  		
				  		<el-table-column label="操作" width="50">
			                <template scope="scope">
								<a @click="handleRowDelete('projectPlanList',scope.$index, scope.row)"><i class="el-icon-minus"></i></a>
								<a @click="handleProjectPlanAdd('projectPlanList',scope.$index, scope.row)"><i class="el-icon-plus"></i></a>
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
<script type="text/javascript"  charset="utf-8" src="${webRoot}/plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript"  charset="utf-8" src="${webRoot}/plug-in/ueditor/ueditor.all.min.js"></script>
<script>
	var valid=null;
	var note_editor = null;
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
					projectPeopleList:'${webRoot}/projectController.do?projectPeopleList',
					projectPlanList:'${webRoot}/projectController.do?projectPlanList',
				},

				//新增界面数据
				form: {
				}, 
				formFile: {
					main_background:[],
					main_detail:[],
				},
				//子表数据对象
				projectPeopleList:[{
				}],
				//子表数据字典对象
				//子表数据对象
				projectPlanList:[{
				}],
				//子表数据字典对象
                proStatusOptions:[],
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

            handleMainBackGroundUploadFile: function(response, file, fileList){
                file.url=response.obj;
                this.form.background=response.obj;
                if(fileList.length>1){
                    this.handleMainBackGroundRemoveFile(fileList.splice(0,1)[0],fileList);
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
            handleMainBackGroundRemoveFile: function(file, fileList){
                if(fileList.length==0){
                    this.form.background="";
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
			handleProjectPeopleAdd(rowsName,index, row) {
	        	this[rowsName].push({
	        	});
	        	this.addValidType();
	        },
	      	//获取子表数据
			getProjectPeopleList(id){
				this.projectPeopleList=[{
				}];
				if(!id){
					return;
				}
				this.$http.get(this.url.projectPeopleList,{
					params: {
						id:id
					}
				}).then((res) => {
					if(res.data.length>0){
						for (var i = 0; i < res.data.length; i++) {
							var data = res.data[i];
						}
						this.projectPeopleList = res.data;
					}
				});
			},
			handleProjectPlanAdd(rowsName,index, row) {
	        	this[rowsName].push({
	        	});
	        	this.addValidType();
	        },
	      	//获取子表数据
			getProjectPlanList(id){
				this.projectPlanList=[{
				}];
				if(!id){
					return;
				}
				this.$http.get(this.url.projectPlanList,{
					params: {
						id:id
					}
				}).then((res) => {
					if(res.data.length>0){
						for (var i = 0; i < res.data.length; i++) {
							var data = res.data[i];
						}
						this.projectPlanList = res.data;
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
					var background=[];
					if(!!this.form.background){
						background=[{
							name:this.form.background.substring(this.form.background.lastIndexOf('\\')+1),
							url:this.form.background
						}]
					}
					var detail=[];
					if(!!this.form.detail){
						detail=[{
							name:this.form.detail.substring(this.form.detail.lastIndexOf('\\')+1),
							url:this.form.detail
						}]
					}
					this.formFile={
							main_background:background,
							main_detail:detail,
					};
					//加载子表列表
					this.getProjectPeopleList(this.form.id);
					this.getProjectPlanList(this.form.id);
				}
				var _this=this;
				note_editor = UE.getEditor('note');
				note_editor.ready(function(){
					note_editor.setContent(!!row?_this.form.note:"");
				});
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
	 				$("input[name='code']").each(function(){
	 					if(!$(this).attr("datatype")){
	 						$(this).attr("datatype","*").attr("validType","project_people,code,id");
	 					}
	 				});
				$("input[name='personName']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","*").attr("nullmsg","请填写【项目人员】-[项目人员名称]");
 					}
 				});
				$("input[name='code']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","*").attr("nullmsg","请填写【项目计划】-[编号]");
 					}
 				});
				$("input[name='planTitle']").each(function(){
 					if(!$(this).attr("datatype")){
 						$(this).attr("datatype","*").attr("nullmsg","请填写【项目计划】-[计划标题]");
 					}
 				});
	    	 	},1000);
	        },
			//初始化数据字典
			initDictsData:function(){
	        	var _this = this;
                _this.initDictByCode('proStatus',_this,'proStatusOptions');
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
					
					para.note=note_editor.getContent();
					
					for (var i = 0; i < _this.projectPeopleList.length; i++) {
						_this.projectPeopleList[i].test4=!_this.projectPeopleList[i].test4 ? '' : utilFormatDate(new Date(_this.manyOneList[i].test4), 'yyyy-MM-dd');
					}
					para.projectPeopleListStr=JSON.stringify(_this.projectPeopleList);
					for (var i = 0; i < _this.projectPlanList.length; i++) {
						_this.projectPlanList[i].test4=!_this.projectPlanList[i].test4 ? '' : utilFormatDate(new Date(_this.manyOneList[i].test4), 'yyyy-MM-dd');
				  		_this.projectPlanList[i].startTime=!_this.projectPlanList[i].startTime ? '' : utilFormatDate(new Date(_this.projectPlanList[i].startTime), 'yyyy-MM-dd');
				  		_this.projectPlanList[i].endTime=!_this.projectPlanList[i].endTime ? '' : utilFormatDate(new Date(_this.projectPlanList[i].endTime), 'yyyy-MM-dd');
					}
					para.projectPlanListStr=JSON.stringify(_this.projectPlanList);
					
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