<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>日志管理</title>
</head>
<body>
	<div class="easyui-layout" fit="true"
		style="margin: 0px; border: 0px; overflow: hidden; width: 100%; height: 100%;">
		<div data-options="region:'center',split:false,border:false"
			style="padding: 0px; height: 100%; width: 100%; overflow-y: hidden;">
			<table id="logTable" class="easyui-datagrid"
				data-options=" method : 'GET',url : ctx + '/sysLog/list',
			       fit : true,pagination : true, pagePosition : 'bottom', 
			       fitColumns : false, striped : true, 
			       pageSize : 20, singleSelect : false, selectOnCheck : true,
			       checkOnSelect : true, rownumbers : true,
			       toolbar: [{
						text:'删除',
						iconCls : 'icon-remove',
						handler : function() {
							deleteLog();
						}
					}]">
				<thead>
					<tr>
						<th data-options="field : 'ck',checkbox : true"></th>
						<th data-options="field : 'id',hidden : true">ID</th>
						<th data-options="field : 'remoteAddr',align : 'center'"width="100">请求地址</th>
						<th data-options="field : 'requertUrl',hidden : true">请求Url路径</th>
						<th data-options="field : 'method'">方法</th>
						<th data-options="field : 'params'" width="700">参数</th>
						<th data-options="field : 'typeValue',width : 100">日志类型</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">
	function deleteLog() {
		var rows = $('#logTable').datagrid('getSelections');
		if (rows.length == 0) {
			$.messager.show({
				title : '提示',
				msg : "请选择一条记录！"
			});
			return;
		}
		if (rows || rows.length != 0) {
			$.messager.confirm('Confirm', '确认删除?', function(r) {
				if (r) {
					var params = new Array();
					for (var i = 0; i < rows.length; i++) {
						params.push(rows[i].id);
					}
					
					$.ajax({
						url : ctx + "/sysLog/deletes",
						type : "post",
						data : {
							entityids : params
						},
						dataType : "json",
						success : function(result) {
							if (result.success) {
								// reload the log data
								$('#logTable').datagrid('reload');
							}
						}
					});
				}
			});
		}
	}
</script>
</html>