<%--
  Created by IntelliJ IDEA.
  User: Andre
  Date: 04/03/2022
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/login/" var="linkResource" />
<!DOCTYPE html>
<html>
<head>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/stylesheet/loginForm.css">
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<div class="login">
    <form action="${linkResource}" method="post" id="form2">
        <div class="title">Faça Login!</div>
        <div class="input-container ic1">
            <input id="username" name="username" class="input" type="text" placeholder=" " autocomplete="off"/>
            <div class="cut"></div>
            <label for="username" class="placeholder">Usuario</label>
        </div>
        <div class="input-container ic2">
            <input id="password" name="password" class="input" type="password" placeholder=" " autocomplete="off"/>
            <div class="cut"></div>
            <label for="password" class="placeholder">Senha</label>
        </div>
        <div class="input-container icSubmit">
            <button type="submit" class="submitLogin" form="form2">Fazer Login</button>
        </div>
    </form>
</div>
</body>
</html>