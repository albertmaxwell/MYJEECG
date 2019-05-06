<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>项目立项主表</title>
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
	<script src="${webRoot}/plug-in/layer/layer.js"></script>
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
	[v-cloak] { display: none }
	</style>
</head>
<body style="background-color: #FFFFFF;">
	<div id="projectList" v-cloak>
		<!--工具条-->
		<el-row>
			<el-form :inline="true" :model="filters" size="mini" ref="filters">
				<el-form-item style="margin-bottom: 8px;" prop="code">
					<el-input v-model="filters.code" auto-complete="off" placeholder="请输入编号"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="name">
					<el-input v-model="filters.name" auto-complete="off" placeholder="请输入项目名称"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="headName">
					<el-input v-model="filters.headName" auto-complete="off" placeholder="请输入负责人"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="expectEndtime">
					<el-date-picker type="date" placeholder="选择项目预计结束时间" v-model="filters.expectEndtime"></el-date-picker>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="status">
					<el-input v-model="filters.status" auto-complete="off" placeholder="请输入项目状态"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="customerName">
					<el-input v-model="filters.customerName" auto-complete="off" placeholder="请输入项目客户名称"></el-input>
				</el-form-item>
				<el-form-item style="margin-bottom: 8px;" prop="note">
					<el-input v-model="filters.note" auto-complete="off" placeholder="请输入备注"></el-input>
				</el-form-item>
				<el-form-item>
			    	<el-button type="primary" icon="el-icon-search" v-on:click="getProjects" >查询</el-button>
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
		<el-table :data="projects" border stripe highlight-current-row size="mini" v-loading="listLoading" @sort-change="handleSortChange" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55"></el-table-column>
			<el-table-column type="index" width="60"></el-table-column>
			<el-table-column prop="code" label="编号" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="background" label="项目背景" min-width="120" sortable="custom" show-overflow-tooltip>
				<template slot-scope="scope" v-if="scope.row.background">
					<el-button size="mini" type="primary" @click="handleDownFile('1',scope.row.background)">文件下载</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="name" label="项目名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="detail" label="项目详细说明" min-width="120" sortable="custom" show-overflow-tooltip>
				<template slot-scope="scope" v-if="scope.row.detail">
					<el-button size="mini" type="primary" @click="handleDownFile('1',scope.row.detail)">文件下载</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="department" label="项目负责部门" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="headName" label="负责人" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="setTime" label="项目成立时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
			<el-table-column prop="startTime" label="项目开始时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
			<el-table-column prop="expectEndtime" label="项目预计结束时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
			<el-table-column prop="actualEndtime" label="实际项目结束时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
			<el-table-column prop="status" label="项目状态" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="type" label="项目类型" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="customerName" label="项目客户名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="phone" label="联系电话" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
			<el-table-column prop="note" label="备注" min-width="500" sortable="custom" show-overflow-tooltip></el-table-column>
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
		
	</div>
</body>
<script>
	var vue = new Vue({			
		el:"#projectList",
		data() {
			return {
				filters: {
					code:'', 
					name:'', 
					headName:'',
					expectEndtime:'',
					status:'', 
					customerName:'', 
					note:'', 
				},
				url:{
					list:'${webRoot}/projectController.do?datagrid',
					del:'${webRoot}/projectController.do?doDel',
					batchDel:'${webRoot}/projectController.do?doBatchDel',
					goEdit:'${webRoot}/projectController.do?goForm',
					queryDict:'${webRoot}/systemController.do?typeListJson',
					downFile:'${webRoot}/img/server/',
					exportXls:'${webRoot}/projectController.do?exportXls&id=',
					ImportXls:'${webRoot}/projectController.do?upload'
				},
				projects: [],
				total: 0,
				page: 1,
				pageSize:10,
				sort:{
					sort:'id',
					order:'desc'
				},
				listLoading: false,
				sels: [],//列表选中列
				
				//数据字典 
			}
		},
		methods: {
			handleSortChange(sort){
				this.sort={
					sort:sort.prop,
					order:sort.order=='ascending'?'asc':'desc'
				};
				this.getProjects();
			},
			formatDate: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd'):'';
			},
			formatDateTime: function(row,column,cellValue, index){
				return !!cellValue?utilFormatDate(new Date(cellValue), 'yyyy-MM-dd hh:mm:ss'):'';
			},
			handleCurrentChange(val) {
				this.page = val;
				this.getProjects();
			},
			handleSizeChange(val) {
				this.pageSize = val;
				this.page = 1;
				this.getProjects();
			},
			handleDownFile(type,filePath){
				var downUrl=this.url.downFile+ filePath +"?down=true";
				window.open(downUrl);
			},
			resetForm(formName) {
		        this.$refs[formName].resetFields();
		        this.getProjects();
		    },
			//获取列表
			getProjects() {
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
				fields.push('code');
				fields.push('background');
				fields.push('name');
				fields.push('detail');
				fields.push('department');
				fields.push('headId');
				fields.push('headName');
				fields.push('setTime');
				fields.push('startTime');
				fields.push('expectEndtime');
				fields.push('actualEndtime');
				fields.push('status');
				fields.push('type');
				fields.push('customerId');
				fields.push('customerName');
				fields.push('phone');
				fields.push('note');
				let para = {
					params: {
						page: this.page,
						rows: this.pageSize,
						//排序
						sort:this.sort.sort,
						order:this.sort.order,
					 	code:this.filters.code,
					 	name:this.filters.name,
					 	head:this.filters.headName,
						expectEndtime: !this.filters.expectEndtime ? '' : utilFormatDate(new Date(this.filters.expectEndtime ), 'yyyy-MM-dd'),
					 	status:this.filters.status,
					 	customerName:this.filters.customerName,
					 	note:this.filters.note,
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
					this.projects = datas;
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
						this.getProjects();
					});
				}).catch(() => {

				});
			},
			//显示编辑界面
			handleEdit: function (index, row) {
				var index = layer.open({
					type : 2,
					title : "编辑页面",
					area : [ '80%', '80%' ],
					shade : 0.3,
					maxmin : true,
					content : this.url.goEdit,
					btn : [ '确定', '关闭' ],
					yes : function(index, layero) {
				        var iframeWin = window[layero.find('iframe')[0]['name']];
				        //iframeWin.vue.addSubmit();
				        $('#btn_sub', iframeWin.document).click();
					},
					btn2 : function(index, layero) {
						layer.closeAll();
					},
					zIndex : layer.zIndex,
					success : function(layero,index) {
						var iframeWin = window[layero.find('iframe')[0]['name']];
				        iframeWin.vue.initForm(Object.assign({}, row));
						layer.setTop(layero);
						layer.full(index);
					}
				});
			},
			//显示新增界面
			handleAdd: function () {
				var index = layer.open({
					type : 2,
					title : "新增页面",
					area : [ '80%', '80%' ],
					shade : 0.3,
					maxmin : true,
					content : this.url.goEdit,
					btn : [ '确定', '关闭' ],
					yes : function(index, layero) {
				        var iframeWin = window[layero.find('iframe')[0]['name']];
				        //iframeWin.vue.addSubmit();
				        $('#btn_sub', iframeWin.document).click();
					},
					btn2 : function(index, layero) {
						layer.closeAll();
					},
					zIndex : layer.zIndex,
					success : function(layero,index) {
						var iframeWin = window[layero.find('iframe')[0]['name']];
				        iframeWin.vue.initForm();
						layer.setTop(layero);
						layer.full(index);
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
						this.getProjects();
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
				openuploadwin('Excel导入',this.url.ImportXls, "projectList");
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
			this.getProjects();
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