<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>模拟业务平台</title>
</head>
<body class="easyui-layout">
	<div region="north"
		style="overflow: hidden; height: 30px; background: #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
		<span style="float: right; padding-right: 20px;" class="head">
			<a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;">平台地址 https://github.com/ChangerMyGit/myplatform</span>
	</div>
	<!--这种方式会有黄色警告，不如下面的方式好-->
	<div data-options="region:'center'">
		<div class="easyui-tabs" data-options="fit:true" id="tabs">
			<!-- 每一个div 就是一个选项卡 
			<div data-options="title:'选项卡一',iconCls:'icon-ok',closable:true"">内容一</div>
			<div data-options="title:'选项卡二',closable:true">内容二</div>
			<div data-options="title:'选项卡三',closable:true">内容三</div>
			-->
		</div>
	</div>
	<div data-options="region:'west',title:'导航'" style="width: 200px;">
		<!-- 树形菜单 -->
        <ul id="tree"></ul>
	</div>
</body>
<script type="text/javascript">
	//实例化树形菜单
	$("#tree").tree({
		url:"${pageContext.request.contextPath}/function/getMenu",
		method:"get",
		lines : true,
		onClick : function(node) {
			if (node.attributes) {
				Open(node.text, node.attributes.url);
			}
		}
	});
	
	//在右边center区域打开菜单，新增tab
    function Open(text, url) {
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
        	url = "${pageContext.request.contextPath}" + url; 
        	var content = '<iframe scrolling="no" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';  
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
                content : content 
            });
        }
    }
</script>
</html>