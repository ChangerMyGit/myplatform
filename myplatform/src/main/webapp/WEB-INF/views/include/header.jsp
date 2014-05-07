<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%application.setAttribute("path", request.getContextPath());%>
<script type="text/javascript">
   ctx="<%=request.getContextPath()%>"; 
</script>
<link rel="stylesheet" type="text/css" href="${path}/resources/scripts/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/scripts/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/css/demo.css">
<script type="text/javascript" src="${path}/resources/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyloader.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/locale/easyui-lang-zh_CN.js"></script>