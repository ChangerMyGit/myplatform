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
			<table id="tt" class="easyui-datagrid"
				data-options=" method : 'GET',url : ctx + '/user/list',
			       fit : true,pagination : true, pagePosition : 'bottom', 
			       fitColumns : false, striped : true, 
			       pageSize : 20, singleSelect : false, selectOnCheck : true,
			       checkOnSelect : true, rownumbers : true,
			       toolbar: [{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						addUser();
					}
					},{
						text:'编辑',
						iconCls:'icon-save',
						handler:function(){
							editUser();
						}
					},{
						text:'删除',
						iconCls : 'icon-remove',
						handler : function() {
							deleteUser();
						}
					}]">
				<thead>
					<tr>
						<th data-options="field : 'ck',checkbox : true">功能名称</th>
						<th data-options="field : 'id',hidden : true">ID</th>
						<th data-options="field : 'userName',align : 'center'" width="100">用户名</th>
						<th data-options="field : 'password',hidden : true">密码</th>
						<th data-options="field : 'email'">邮箱</th>
						<th data-options="field : 'created',width : 100,align : 'center'">创建时间</th>
						<th data-options="field : 'creater',width : 100,align : 'center'">创建人</th>
						<th data-options="field : 'updated',width : 100,align : 'center'">更新时间</th>
						<th data-options="field : 'updater',width : 100,align : 'center'">更新人</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px"  closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>用户名:</label> <input name="userName"
					class="easyui-validatebox" required="true">
					<input name="id" hidden = "true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input name="password" type="password"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>邮箱:</label> <input name="email" class="easyui-validatebox"
					validType="email">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
<style type="text/css">
		#fm {
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
/* 	$('#tt').datagrid({
		method : "GET",
		url : ctx + "/user/list",
		fit : true,
		pagination : true,//底部分页
		pagePosition : 'bottom',//'top','bottom','both'.
		fitColumns : false,//自适应列宽
		striped : true,//显示条纹
		pageSize : 20,//每页记录数
		singleSelect : false,//单选模式
		selectOnCheck : true,
		checkOnSelect : true,
		rownumbers : true,//显示行数
		columns : [ [ {
			field : "ck",
			checkbox : true
		}, {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'userName',
			title : '用户名',
			width : 100,
			align : 'center'
		}, {
			field : 'password',
			title : '密码',
			hidden : true
		}, {
			field : 'email',
			title : '邮箱',
			width : 180,
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
		}, {
			field : 'updater',
			title : '更新人',
			width : 100,
			align : 'center'
		}, {
			field : 'updated',
			title : '更新时间',
			width : 100,
			align : 'center'
		} ] ],
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				addUser();
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				editUser();
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				deleteUser();
			}
		} ]
	}); */
	var url;
	function addUser() {
		$('#dlg').dialog('open').dialog('setTitle', "新增用户");
		$('#fm').form('clear');
		url = ctx + "/user/save";
	}


	function editUser() {
		var rows = $('#tt').datagrid('getSelections');
		// alert(row.id + "/" + row.userName);
		if (rows && rows.length == 1) {
			$('#fm').form('load', rows[0]);
			$('#dlg').dialog('open').dialog('setTitle', '修改用户');
			url = ctx + "/user/save";
		} else {
			$.messager.show({
				title : '提示',
				msg : "请选择一条记录！"
			});
			return;
		}
	}

	function saveUser() {
		$('#fm').form('submit', {
			url : url,
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success) {
					$('#dlg').dialog('close'); // close the dialog
					$('#tt').datagrid('reload'); // reload the user data
				} else {
					$.messager.show({
						title : 'Error',
						msg : result.errorMsg
					});
				}
			}
		});
	}

	function deleteUser() {
		var rows = $('#tt').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : "请选择一条记录！"
			});
			return;
		}
		if (rows || rows.length != 0) {
			$.messager.confirm('Confirm', '确认删除该用户?', function(r) {
				if (r) {
					var params = new Array();
					for (var i = 0; i < rows.length; i++) {
						params.push(rows[i].id);
					}

					$.ajax({
						url : ctx + "/user/deletes",
						type : "post",
						data : {
							entityids : params
						},
						dataType : "json",
						success : function(result) {
							if (result.success) {
								// reload the user data
								$('#tt').datagrid('reload');
							}
						}
					});
				}
			});
		}
	}
</script>
</body>
</html>