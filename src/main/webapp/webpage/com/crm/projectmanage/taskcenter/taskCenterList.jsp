<%@page import="org.jeecgframework.core.util.ResourceUtil"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>任务中心</title>
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
	<script type="text/javascript"  charset="utf-8" src="${webRoot}/plug-in/ueditor/ueditor.config.js"></script>
	<script type="text/javascript"  charset="utf-8" src="${webRoot}/plug-in/ueditor/ueditor.all.min.js"></script>
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
<div id="taskCenterList" v-cloak>
	<!--工具条-->
	<el-row style="margin-top: 15px;">
		<el-form :inline="true" :model="filters" size="mini" ref="filters">
			<el-form-item style="margin-bottom: 8px;" prop="taskCode">
				<el-input v-model="filters.taskCode" auto-complete="off" placeholder="请输入任务编号"></el-input>
			</el-form-item>
			<el-form-item style="margin-bottom: 8px;" prop="projectName">
				<el-input v-model="filters.projectName" auto-complete="off" placeholder="请输入项目名称"></el-input>
			</el-form-item>
			<el-form-item style="margin-bottom: 8px;" prop="taskName">
				<el-input v-model="filters.taskName" auto-complete="off" placeholder="请输入任务名称"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" v-on:click="getTaskCenters">查询</el-button>
			</el-form-item>
			<el-form-item>
				<el-button icon="el-icon-refresh" @click="resetForm('filters')">重置</el-button>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-edit" @click="handleAdd">派发任务</el-button>
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
	<el-table :data="taskCenters" border stripe size="mini" highlight-current-row v-loading="listLoading" @sort-change="handleSortChange"  @selection-change="selsChange" style="width: 100%;">
		<el-table-column type="selection" width="55"></el-table-column>
		<el-table-column type="index" width="60"></el-table-column>
		<el-table-column prop="taskCode" label="任务编号" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="projectName" label="项目名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="headId" label="任务处理人员ID" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="headName" label="任务处理人员名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="taskName" label="任务名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="taskContent" label="内容详情" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="setTime" label="规定完成时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
		<el-table-column prop="status" label="状态" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formattaskStatusDict"></el-table-column>
		<el-table-column prop="phone" label="联系电话" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="actualComptime" label="实际完成时间" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatDate"></el-table-column>
		<el-table-column prop="handoverPersonName" label="任务派发人名称" min-width="120" sortable="custom" show-overflow-tooltip></el-table-column>
		<el-table-column prop="priority" label="优先级" min-width="120" sortable="custom" show-overflow-tooltip :formatter="formatlevelDict" ></el-table-column>
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
					<el-form-item label="任务编号" prop="taskCode">
						<el-input v-model="form.taskCode" auto-complete="off" placeholder="请输入任务编号"></el-input>
					</el-form-item>
				</el-col>

				<%--<el-col :span="8">
					<el-form-item label="项目ID" prop="projectId">
						<el-input v-model="form.projectId" auto-complete="off" placeholder="请输入项目ID"></el-input>
					</el-form-item>
				</el-col>--%>

				<el-col :span="8">
					<el-form-item label="项目名称" prop="projectName">
						<el-input v-model="form.projectName" @click.native="selPersen(this)" name="projectId" auto-complete="off"  placeholder="项目名称" readonly="readonly"></el-input>
						<el-input v-model="form.projectId"  name="projectName" style="display:none;" auto-complete="off" placeholder="项目名称" readonly="readonly"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>

				<%--	<el-col :span="8">
						<el-form-item label="任务处理人员ID" prop="headId">
							<el-input v-model="form.headId" auto-complete="off" placeholder="请输入任务处理人员ID"></el-input>
						</el-form-item>
					</el-col>--%>

				<el-col :span="8">
					<el-form-item label="处理人员名称" prop="taskPersonName">
						<el-input v-model="form.taskPersonName" auto-complete="off" placeholder="请输入任务处理人员名称"></el-input>
					</el-form-item>
				</el-col>

				<el-col :span="8">
					<el-form-item label="任务名称" prop="taskName">
						<el-input v-model="form.taskName" auto-complete="off" placeholder="请输入任务名称"></el-input>
					</el-form-item>
				</el-col>

				<el-col :span="8">
					<el-form-item label="内容详情" prop="taskContent">
						<el-input v-model="form.taskContent" auto-complete="off" placeholder="请输入内容详情"></el-input>
					</el-form-item>
			</el-row>
			<el-row>
				</el-col>

				<el-col :span="8">
					<el-form-item label="规定完成时间">
						<el-date-picker style="width: 325.86px"  type="date" placeholder="选择规定完成时间" v-model="form.setTime"></el-date-picker>
					</el-form-item>
				</el-col>


				<el-col :span="8">
					<el-form-item label="状态">
						<el-select     v-model="form.status" placeholder="请选择状态">
							<el-option  :label="option.typename" :value="option.typecode"
										name="status"  v-for="option in taskStatusOptions"></el-option>
						</el-select>
					</el-form-item>
				</el-col>

				<el-col :span="8">
					<el-form-item label="联系电话" prop="phone">
						<el-input v-model="form.phone" auto-complete="off" placeholder="请输入联系电话"></el-input>
					</el-form-item>
				</el-col>
			</el-row>
			<el-row>

				<el-col :span="8">
					<el-form-item label="实际完成时间">
						<el-date-picker style="width: 325.86px" type="date" placeholder="选择实际完成时间"
										v-model="form.actualComptime"></el-date-picker>
					</el-form-item>
				</el-col>

				<el-col :span="8">
					<el-form-item label="任务派发人名称" prop="handPerson">
						<el-input v-model="form.handoverPersonName" auto-complete="off" placeholder="请输入任务派发人名称"></el-input>
					</el-form-item>
				</el-col>

			</el-row>
			<el-row>

				<el-col :span="8">
					<el-form-item label="优先级">
						<el-select     v-model="form.priority" placeholder="请选择优先级">
							<el-option  :label="option.typename" :value="option.typecode"
										name="priority"  v-for="option in levelOptions"></el-option>
						</el-select>
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
    var userId = "<%=ResourceUtil.getSessionUser().getId()%>";
    var vue = new Vue({
        el:"#taskCenterList",
        data() {
            return {
                filters: {
                    taskCode:'',
                    projectName:'',
                    taskName:'',
                },
                url:{
                    list:'${webRoot}/taskCenterController.do?datagrid',
                    del:'${webRoot}/taskCenterController.do?doDel',
                    batchDel:'${webRoot}/taskCenterController.do?doBatchDel',
                    queryDict:'${webRoot}/systemController.do?typeListJson',
                    save:'${webRoot}/taskCenterController.do?doAdd',
                    edit:'${webRoot}/taskCenterController.do?doUpdate',
                    upload:'${webRoot}/systemController/filedeal.do',
                    downFile:'${webRoot}/img/server/',
                    exportXls:'${webRoot}/taskCenterController.do?exportXls&id=',
                    ImportXls:'${webRoot}/taskCenterController.do?upload'
                },
                taskCenters: [],
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
                taskStatusOptions:[],
                levelOptions:[],
            }
        },
        methods: {

            selPersen: function(obj) {
                var params = {"head": userId};
                params = JSON.stringify(params);
                popupClickParam(obj, 'name', 'a', 'project', params, this.choseProject);
            },
            choseProject: function(result) {
                this.$set(this.form, 'projectId', result.selectedRow[0].id);
                this.$set(this.form, 'projectName', result.selectedRow[0].name);
            },


            handleSortChange(sort){
                this.sort={
                    sort:sort.prop,
                    order:sort.order=='ascending'?'asc':'desc'
                };
                this.getTaskCenters();
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
            formattaskStatusDict: function(row,column,cellValue, index){
                var names="";
                var values=cellValue;
                if(!Array.isArray(cellValue))values=cellValue.split(',');
                for (var i = 0; i < values.length; i++) {
                    var value = values[i];
                    if(i>0)names+=",";
                    for(var j in this.taskStatusOptions){
                        var option=this.taskStatusOptions[j];
                        if(value==option.typecode){
                            names+=option.typename;
                        }
                    }
                }
                return names;
            },
			formatlevelDict: function(row,column,cellValue, index){
                var names="";
                var values=cellValue;
                if(!Array.isArray(cellValue))values=cellValue.split(',');
                for (var i = 0; i < values.length; i++) {
                    var value = values[i];
                    if(i>0)names+=",";
                    for(var j in this.levelOptions){
                        var option=this.levelOptions[j];
                        if(value==option.typecode){
                            names+=option.typename;
                        }
                    }
                }
                return names;
            },
            handleCurrentChange(val) {
                this.page = val;
                this.getTaskCenters();
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.page = 1;
                this.getTaskCenters();
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
                this.getTaskCenters();
            },
            //获取用户列表
            getTaskCenters() {
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
                fields.push('taskCode');
                fields.push('projectId');
                fields.push('projectName');
                fields.push('taskPersonId');
                fields.push('taskPersonName');
                fields.push('taskName');
                fields.push('taskContent');
                fields.push('setTime');
                fields.push('status');
                fields.push('phone');
                fields.push('actualComptime');
                fields.push('handPersonId');
                fields.push('handoverPersonName');
                fields.push('priority');
                let para = {
                    params: {
                        page: this.page,
                        rows: this.pageSize,
                        //排序
                        sort:this.sort.sort,
                        order:this.sort.order,
                        taskCode:this.filters.taskCode,
                        projectName:this.filters.projectName,
                        taskName:this.filters.taskName,
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
                this.taskCenters = datas;
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
                this.getTaskCenters();
            });
            }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                this.formTitle='编辑';
                this.formVisible = true;
                this.form = Object.assign({}, row);
                var _this=this;

            },
            //显示新增界面
            handleAdd: function () {
                this.formTitle='新增';
                this.formVisible = true;
                this.form = {
                    taskCode:'',
                    projectId:'',
                    projectName:'',
                    headId:'',
                    headName:'',
                    taskName:'',
                    taskContent:'',
                    setTime:'',
                    status:'',
                    phone:'',
                    actualComptime:'',
                    handPerson:'',
                    handoverPerson:'',
                    priority:'',
                    taskChange:'',
                    questionStatus:'',
                };

            },
            //新增
            formSubmit: function () {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            this.formLoading = true;
                        let para = Object.assign({}, this.form);

                        para.setTime = !para.setTime ? '' : utilFormatDate(new Date(para.setTime), 'yyyy-MM-dd');
                        para.actualComptime = !para.actualComptime ? '' : utilFormatDate(new Date(para.actualComptime), 'yyyy-MM-dd');

                        this.$http.post(!!para.id?this.url.edit:this.url.save,para,{emulateJSON: true}).then((res) => {
                            this.formLoading = false;
                        this.$message({
                            message: '提交成功',
                            type: 'success',
                            duration:1500
                        });
                        this.$refs['form'].resetFields();
                        this.formVisible = false;
                        this.getTaskCenters();
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
                this.getTaskCenters();
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
                openuploadwin('Excel导入',this.url.ImportXls, "taskCenterList");
            },
            //初始化数据字典
            initDictsData:function(){
                var _this = this;
                _this.initDictByCode('taskStatus',_this,'taskStatusOptions');
                _this.initDictByCode('level',_this,'levelOptions');
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
            this.getTaskCenters();
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