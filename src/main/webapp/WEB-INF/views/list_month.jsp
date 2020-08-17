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
    
    <style type="text/css">
    	a{
    		padding: 10px 10px;
    	}
    	div{
    	padding-bottom: 3%;
    	}
    </style>
    
</head>
<body>
     <div id="main" style="width:90%;margin: 5% 1%; border:1px solid #999" align="center">
     	<p style="font-weight: bold;">楞严经学习${__month__ }月录音</p>
     	<p style="font-weight: bold;">点击可下载</p>
     	
    	<c:forEach items="${items }" var="item">
    		<a href="/download?fn=${item }" target="blank">${item }</a>
    		<br>
    	</c:forEach>
    </div>
</body>
</html>
