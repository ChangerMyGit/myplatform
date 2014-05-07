<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	<div class="easyui-layout" fit="true"
		style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
		<div data-options="region:'center',split:false,border:false"
			style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
			<table id="tt"></table>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('#tt').datagrid({
		method : "GET",
		url : ctx + "/user/list",
		fit : true,
		pagination : true,//底部分页
		pagePosition : 'bottom',//'top','bottom','both'.
		fitColumns : false,//自适应列宽
		striped : true,//显示条纹
		pageSize : 20,//每页记录数
		singleSelect : false,//单选模式
		rownumbers : true,//显示行数
		columns : [ [ {
			field : 'id',
			title : 'ID'
		}, {
			field : 'userName',
			title : '用户名',
			width : 100,
			align : 'center'
		}, {
			field : 'password',
			title : '密码'
		}, {
			field : 'created',
			title : '创建时间',
			width : 100,
			align : 'center'
		}, {
			field : 'creater',
			title : '创建人',
			width : 100,
			align : 'center'
		} , {
			field : 'updater',
			title : '更新人',
			width : 100,
			align : 'center'
		}, {
			field : 'updated',
			title : '更新时间',
			width : 100,
			align : 'center'
		}] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openDialog("add_dialog", "add");
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				openDialog("add_dialog", "edit");
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				delAppInfo();
			}
		} ]
	});
</script>
</html>