<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<div>
	<form id="fm" method="post" novalidate>
		<div class="fitem">
			<label>上级资源:</label> <input id="parentId" name="parentId"
				class="easyui-combotree" />
		</div>
		<div class="fitem">
			<label>功能名称:</label> <input name="userName"
				class="easyui-validatebox" required="true"> <input name="id"
				hidden="true">
		</div>
		<div class="fitem">
			<label>上级机构:</label> <input id="_parentId" name="_parentId" />
		</div>
		<div class="fitem">
			<label>访问路径:</label> <input name="url">
		</div>
	</form>
</div>