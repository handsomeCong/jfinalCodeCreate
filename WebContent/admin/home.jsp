<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="include.jsp" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="<c:url value="/style.css"/>"/>
    <title>Apache Shiro Quickstart</title>
</head>
<body>
欢迎你

<shiro:hasPermission name="/user/list">add</shiro:hasPermission>
</body>
</html>
