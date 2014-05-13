<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-layout" fit="true"
		style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
		<div data-options="region:'center',split:false,border:false"
			style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
			<!-- <table id="function_treegrid" /> -->

			<table id="function_treegrid" class="easyui-treegrid"
				data-options="
				url: ctx + '/function/getMenu',
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'text',
				toolbar: [{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						showDialog();
					}
					},{
						text:'编辑',
						iconCls:'icon-save',
						handler:function(){
							edit();
						}
					},{
						text:'删除',
						iconCls : 'icon-remove',
						handler : function() {
							deleteFunction();
						}
					}]">
				<thead>
					<tr>
						<th data-options="field:'text',editor:'text'" width="220">功能名称</th>
						<th data-options="field:'url'" width="220">访问路径</th>
						<th data-options="field:'seqNo'" width="120">序号</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
<!-- 	
   <div id="tb" style="padding: 5px; height: 22px">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add"  onclick="showDialog();" plain="true">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit();" plain="true">修改</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="deleteFunction();" plain="true">删除</a>
	</div>
-->
	<div id="functionDlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#functionDlg-buttons">
		<div>功能信息</div>
		<form id="functionFm" method="post">
			<div class="fitem">
				<label>上级资源:</label> 
				<input id="parentId" name="parentId"
					class="easyui-combotree"
					data-options="url: ctx + '/function/getMenu',method: 'get' ,valueField:'id',textField:'text'" />
			</div>
			<div class="fitem">
				<label>功能名称:</label> 
				<input id="text" name="text" type="text" > 
				<input id="functionId" name="id" hidden="true">
				<input name="parentId" hidden="true">
			</div>
			<div class="fitem">
				<label>访问路径:</label> 
				<input id="url"  type="text" name="url">
			</div>
			<div class="fitem">
				<label>序号:</label> 
				<input id="seqNo" name="seqNo" class="easyui-numberbox">
			</div>
		</form>
	</div>
	<div id="functionDlg-buttons">
		<a href="#" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="save()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#functionDlg').dialog('close')">Cancel</a>
	</div>
<style type="text/css">
		#functionFm {
			margin: 0;
			padding: 10px 30px;
		}
		
		.ftitle {
			font-size: 14px;
			font-weight: bold;
			padding: 5px 0;
			margin-bottom: 10px;
			border-bottom: 1px solid #ccc;
		}
		
		.fitem {
			margin-bottom: 5px;
		}
		
		.fitem label {
			display: inline-block;
			width: 80px;
		}
</style>
<script type="text/javascript">
	function save() {
		params = {};
		params.parentid = $('#parentId').combobox("getValue");
		params.text = $('#text').val();
		params.url = $('#url').val();
		params.id = $('#functionId').val();
		params.seqNo = $('#seqNo').val();
  		if(params.text == ''){
			$.messager.show({
				title : '提示',
				msg : "请输入功能名称！"
			});
			return;
		}  
		$.ajax({
			url : ctx + "/function/save",
			type : "post",
			data : params,
			dataType : "json",
			success : function(result) {
				if (result.success) {
					$('#functionDlg').dialog('close');
					$('#function_treegrid').treegrid('reload');
					$('#parentId').combotree("reload");
					$("#tree").tree("reload");
				}
			}
		});
		
	}

	//编辑
	function edit(row) {
	    if (row == undefined) {
	        row = $('#function_treegrid').treegrid('getSelected');
	    }
	    if (row != undefined) {
	        showDialog(row);
	    } else {
			$.messager.show({
				title : 'Error',
				msg : "选择要操作的对象！"
			});
	    }
	}
	
	function showDialog(row) {
		$('#functionDlg').show();
        $('#functionFm').form('clear');
		if(row){
			$('#functionDlg').dialog('open').dialog('setTitle', "修改功能");
			$('#functionFm').form('load', row);
			if(row.parentId)
			  $('#parentId').combotree("setValue",row.parentId);
		} else {
			$('#functionDlg').dialog('open').dialog('setTitle', "新增功能");
		}
	}
	
	function deleteFunction(){
		var rows = $('#function_treegrid').treegrid('getSelected');
		if (!rows || rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : "请选择一条记录！"
			});
			return;
		}
		// alert(rows.id);
		if (rows || rows.length != 0) {
			$.messager.confirm('Confirm', '确认删除该功能，会删除功能树下所有的功能节点?', function(r) {
				if (r) {
					$.ajax({
						url : ctx + "/function/deleteFunction",
						type : "post",
						data : {
							entityid : rows.id
						},
						dataType : "json",
						success : function(result) {
							if (result.success) {
								// reload the function data
								$('#function_treegrid').treegrid('reload');
								$('#parentId').combotree("reload");
								$("#tree").tree("reload");
								$.messager.show({
									title : '提示',
									msg : "删除成功！"
								});
							}
						},
						error : function(){
							$.messager.show({
								title : '提示',
								msg : "操作失败！"
							});
						}
					});
				}
			});
		}
	}
	
</script>
</body>
</html>