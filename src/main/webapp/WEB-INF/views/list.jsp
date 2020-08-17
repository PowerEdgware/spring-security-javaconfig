<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>楞严经学习</title>
    
</head>
<body>
    <div id="main" style="margin: 5% 10%;" align="center">
    	<p style="font-weight: bold;">楞严经学习录音笔记</p>
    	
    	<c:forEach items="${items }" var="item">
    		<a href="list_month/${item }">${item }</a><br>
    	</c:forEach>
    </div>
</body>
</html>
