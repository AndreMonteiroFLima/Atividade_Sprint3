<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/cadastroView/mostraUsuario" var="linkMostraUsuaio" />
<c:url value="/webapp/login/mostraLogin" var="linkLogin" />
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/stylesheet/projetoIndex.css">
    <meta charset="ISO-8859-1">
    <title>Index</title>
</head>
<body>
<div class="form">
    <div class="title">Vamos começar?</div>
    <div class="input-container icSubmit">
        <a href="${linkLogin}"><button type="text" class="submit">Login!</button></a>
    </div>
    <div class="input-container icSubmit">
        <a href="${linkMostraUsuaio}"><button type="text" class="submit">Cadastro!</button></a>
    </div>
</div>
</body>
</html>