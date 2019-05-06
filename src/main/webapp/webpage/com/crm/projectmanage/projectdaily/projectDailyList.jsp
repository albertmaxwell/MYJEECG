<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>项目日报</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name=viewportcontent="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no,minimal-ui">
	<link rel="stylesheet" href="${webRoot}/plug-in/element-ui/css/index.css">
	<link rel="stylesheet" href="${webRoot}/plug-in/element-ui/css/elementui-ext.css">
	<script src="${webRoot}/plug-in/vue/vue.js"></script>
	<script src="${webRoot}/plug-in/vue/vue-resource.js"></script>
	<script src="${webRoot}/plug-in/element-ui/index.js"></script>
	<!-- Jquery组件引用 -->
	<script src="${webRoot}/plug-in/jquery/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/jquery-plugs/i18n/jquery.i18n.properties.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/mutiLang/zh-cn.js"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/lhgDialog/lhgdialog.min.js?skin=metrole"></script>
	<script type="text/javascript" src="${webRoot}/plug-in/tools/curdtools.js"></script>
	<style>
	.toolbar {
	    padding: 10px;
	    margin: 10px 0;
	}
	.toolbar .el-form-item {
	    margin-bottom: 10px;
	}
	.el-table__header tr th{
		padding:3px 0px;
	}
	[v-cloak] { display: none }
	</style>
</head>
<body style="background-color: #FFFFFF;">
	<div id="projectDailyList" v-cloak>
		<!--工具条-->
		<el-row style="margin-top: 15px;">
			<el-form :inline="true" :model="filters" size="mini" ref="filters">
				<el-form-item style="margin-bottom: 8px;" prop="title">
					<el-input v-model="filters.title" auto-complete="off" placeholder="请输入日报标题"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="priority">
					<el-input v-model="filters.priority" auto-complete="off" placeholder="请输入优先级"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="dailyDate">
					<el-date-picker type="date" placeholder="选择日报日期" v-model="filters.dailyDate"></el-date-picker>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="writePerson">
					<el-input v-model="filters.writePerson" auto-complete="off" placeholder="请输入填报人"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="department">
					<el-input v-model="filters.department" auto-complete="off" placeholder="请输入部门"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="post">
					<el-input v-model="filters.post" auto-complete="off" placeholder="请输入岗位"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="project">
					<el-input v-model="filters.project" auto-complete="off" placeholder="请输入隶属项目"></el-input>
				</el-form-item>
				<el-form-item>
			    	<el-button type="primary" icon="el-icon-search" v-on:click="getProjectDailys">查询</el-button>
			    </el-form-item>
			    <el-form-item>
			    	<el-button icon="el-icon-refresh" @click="resetForm('filters')">重置</el-button>
			    </el-form-item>
			    <el-form-item>
			    	<el-button type="primary" icon="el-icon-edit" @click="handleAdd">新增</el-button>
			    </el-form-item>
			    <el-form-item>
			    	<el-button type="primary" icon="el-icon-edit" @click="ExportXls">导出</el-button>
			    </el-form-item>
			    <el-form-item>
			    	<el-button type="primary" icon="el-icon-edit" @click="ImportXls">导入</el-button>
			    </el-form-item>
			</el-form>
		</el-row>
		
		<!--列表-->
		<el-table :data="projectDailys" border stripe size="mini" highlight-current-row v-loading="listLoading" @sort-change="handleSortChange"  @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55"></el-table-column>
			<el-table-column type="index" width="60"></el-table-column>
			<el-table-column prop="title" label="日报标题" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="todayPlan" label="今日实际完成" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="dailyDate" label="日报日期" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
			<el-table-column prop="writePersonName" label="填报人" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="department" label="部门" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="post" label="岗位" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="writeTime" label="填写日报时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDateTime"			></el-table-column>
			<el-table-column prop="projectName" label="隶属项目" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="mini" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		
		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" size="mini" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			 <el-pagination small background @current-change="handleCurrentChange" @size-change="handleSizeChange" :page-sizes="[10, 20, 50, 100]"
      			:page-size="pageSize" :total="total" layout="sizes, prev, pager, next"  style="float:right;"></el-pagination>
		</el-col>
		
		<!--新增界面-->
		<el-dialog :title="formTitle" fullscreen z-index="800" :visible.sync="formVisible" :close-on-click-modal="false">
			<el-form :model="form" label-width="100px" :rules="formRules" ref="form" size="30">
				<el-row>
				<el-col :span="8">
				<el-form-item label="日报标题" prop="title">
						<el-input v-model="form.title" auto-complete="off" placeholder="请输入日报标题"></el-input>
					</el-form-item>
				</el-col>

						<el-col :span="8">
					<el-form-item label="今日实际完成" prop="todayPlan">
						<el-input v-model="form.todayPlan" auto-complete="off" placeholder="请输入今日实际完成"></el-input>
					</el-form-item>
						</el-col>
					</el-row>
					<el-row>
							<el-col :span="8">
					<el-form-item label="优先级" prop="priority">
						<el-input v-model="form.priority" auto-complete="off" placeholder="请输入优先级"></el-input>
					</el-form-item>
							</el-col>
								<el-col :span="8">
					<el-form-item label="日报日期">
						<el-date-picker style="width: 325.86px" type="date" placeholder="选择日报日期" v-model="form.dailyDate"></el-date-picker>
					</el-form-item>
								</el-col>
									<el-col :span="8">
					<el-form-item label="填报人" prop="writePersonName">
						<el-input v-model="form.writePersonName" auto-complete="off" placeholder="请输入填报人"></el-input>
					</el-form-item>
									</el-col>
						</el-row>
						<el-row>
										<el-col :span="8">
					<el-form-item label="部门" prop="department">
						<el-input v-model="form.department" auto-complete="off" placeholder="请输入部门"></el-input>
					</el-form-item>
										</el-col>
											<el-col :span="8">
					<el-form-item label="岗位" prop="post">
						<el-input v-model="form.post" auto-complete="off" placeholder="请输入岗位"></el-input>
					</el-form-item>
											</el-col>
												<el-col :span="8">
					<el-form-item label="填写日报时间">
						 <el-date-picker style="width: 325.86px" type="datetime" placeholder="选择填写日报时间" v-model="form.writeTime"></el-date-picker>
					</el-form-item>
												</el-col>
							</el-row>
							<el-row>
													<el-col :span="8">
					<el-form-item label="隶属项目" prop="projectName">
						<el-input v-model="form.projectName" auto-complete="off" placeholder="请输入隶属项目"></el-input>
					</el-form-item>
													</el-col>

								</el-row>

			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="formVisible = false">取消</el-button>
				<el-button type="primary" @click.native="formSubmit" :loading="formLoading">提交</el-button>
			</div>
		</el-dialog>
	</div>
</body>
<script>
	var vue = new Vue({			
		el:"#projectDailyList",
		data() {
			return {
				filters: {
					title:'',
					priority:'',
					dailyDate:'',
					writePersonName:'',
					department:'',
					post:'',
					projectName:'',
				},
				url:{
					list:'${webRoot}/projectDailyController.do?datagrid',
					del:'${webRoot}/projectDailyController.do?doDel',
					batchDel:'${webRoot}/projectDailyController.do?doBatchDel',
					queryDict:'${webRoot}/systemController.do?typeListJson',
					save:'${webRoot}/projectDailyController.do?doAdd',
					edit:'${webRoot}/projectDailyController.do?doUpdate',
					upload:'${webRoot}/systemController/filedeal.do',
					downFile:'${webRoot}/img/server/',
					exportXls:'${webRoot}/projectDailyController.do?exportXls&id=',
					ImportXls:'${webRoot}/projectDailyController.do?upload'
				},
				projectDailys: [],
				total: 0,
				page: 1,
				pageSize:10,
				sort:{
					sort:'id',
					order:'desc'
				},
				listLoading: false,
				sels: [],//列表选中列
				
				formTitle:'新增',
				formVisible: false,//表单界面是否显示
				formLoading: false,
				formRules: {
				},
				//表单界面数据
				form: {},
				
				
				//数据字典 
			}
		},
		methods: {
			handleSortChange(sort){
				this.sort={
					sort:sort.prop,
					order:sort.order=='ascending'?'asc':'desc'
				};
				this.getProjectDailys();
			},
			handleDownFile(type,filePath){
				var downUrl=this.url.downFile+ filePath +"?down=true";
				window.open(downUrl);
			},
			formatDate: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd'):'';
			},
			formatDateTime: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd hh:mm:ss'):'';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getProjectDailys();
			},
			handleSizeChange(val) {
				this.pageSize = val;
				this.page = 1;
				this.getProjectDailys();
			},
			resetForm(formName) {
		        this.$refs[formName].resetFields();
		        this.getProjectDailys();
		    },
			//获取用户列表
			getProjectDailys() {
				var fields=[];
				fields.push('id');
				fields.push('id');
				fields.push('createName');
				fields.push('createBy');
				fields.push('createDate');
				fields.push('updateName');
				fields.push('updateBy');
				fields.push('updateDate');
				fields.push('sysOrgCode');
				fields.push('sysCompanyCode');
				fields.push('bpmStatus');
				fields.push('title');
				fields.push('todayPlan');
				fields.push('dailyDate');
				fields.push('writePersonId');
				fields.push('writePersonName');
				fields.push('department');
				fields.push('post');
				fields.push('writeTime');
				fields.push('projectId');
				fields.push('projectName');

				let para = {
					params: {
						page: this.page,
						rows: this.pageSize,
						//排序
						sort:this.sort.sort,
						order:this.sort.order,
					 	title:this.filters.title,
					 	priority:this.filters.priority,
						dailyDate: !this.filters.dailyDate ? '' : utilFormatDate(new Date(this.filters.dailyDate ), 'yyyy-MM-dd'),
					 	writePerson:this.filters.writePerson,
					 	department:this.filters.department,
					 	post:this.filters.post,
					 	project:this.filters.project,
						field:fields.join(',')
					}
				};
				this.listLoading = true;
				this.$http.get(this.url.list,para).then((res) => {
					this.total = res.data.total;
					var datas=res.data.rows;
					for (var i = 0; i < datas.length; i++) {
						var data = datas[i];
					}
					this.projectDailys = datas;
					this.listLoading = false;
				});
			},
			//删除
			handleDel: function (index, row) {
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					let para = { id: row.id };
					this.$http.post(this.url.del,para,{emulateJSON: true}).then((res) => {
						this.listLoading = false;
						this.$message({
							message: '删除成功',
							type: 'success',
							duration:1500
						});
						this.getProjectDailys();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				this.formTitle='编辑';
				this.formVisible = true;
				this.form = Object.assign({}, row);
			},
			//显示新增界面
			handleAdd: function () {
				this.formTitle='新增';
				this.formVisible = true;
				this.form = {
					title:'',
					todayPlan:'',
					dailyDate:'',
					writePersonName:'',
					department:'',
					post:'',
					writeTime:'',
					projectName:'',


				};
			},
			//新增
			formSubmit: function () {
				this.$refs.form.validate((valid) => {
					if (valid) {
						this.$confirm('确认提交吗？', '提示', {}).then(() => {
							this.formLoading = true;
							let para = Object.assign({}, this.form);
							
							para.dailyDate = !para.dailyDate ? '' : utilFormatDate(new Date(para.dailyDate), 'yyyy-MM-dd');
							para.writeTime = !para.writeTime ? '' : utilFormatDate(new Date(para.writeTime), 'yyyy-MM-dd hh:mm:ss');
							
							
							this.$http.post(!!para.id?this.url.edit:this.url.save,para,{emulateJSON: true}).then((res) => {
								this.formLoading = false;
								this.$message({
									message: '提交成功',
									type: 'success',
									duration:1500
								});
								this.$refs['form'].resetFields();
								this.formVisible = false;
								this.getProjectDailys();
							});
						});
					}
				});
			},
			selsChange: function (sels) {
				this.sels = sels;
			},
			//批量删除
			batchRemove: function () {
				var ids = this.sels.map(item => item.id).toString();
				this.$confirm('确认删除选中记录吗？', '提示', {
					type: 'warning'
				}).then(() => {
					this.listLoading = true;
					let para = { ids: ids };
					this.$http.post(this.url.batchDel,para,{emulateJSON: true}).then((res) => {
						this.listLoading = false;
						this.$message({
							message: '删除成功',
							type: 'success',
							duration:1500
						});
						this.getProjectDailys();
					});
				}).catch(() => {
				});
			},
			//导出
			ExportXls: function() {
					var ids = this.sels.map(item => item.id).toString();
					window.location.href = this.url.exportXls+ids;
			},
			//导入
			ImportXls: function(){
				openuploadwin('Excel导入',this.url.ImportXls, "projectDailyList");
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
	        }
		},
		mounted() {
			this.initDictsData();
			this.getProjectDailys();
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
	function reloadTable(){
		
	}
</script>
</html>