<%--
  Created by IntelliJ IDEA.
  User: knandani
  Date: 21-06-2022
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <style>
        .center {
            border: 5px solid #FFFF00;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="center">
    <img src="./resources/images/Exclamation_mark_red.png">

    <h3>${ErrMsg}</h3><br>
    <h3>${ErrType}</h3>
</div>
</body>
</html>
