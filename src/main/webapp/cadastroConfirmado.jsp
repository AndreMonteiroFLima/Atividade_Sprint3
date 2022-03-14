<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/cadastro/cadastroNovoPedido" var="linkResource" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath()%>/stylesheet/projetocadastroForm.css">
<meta charset="UTF-8">
<title>Carrinho</title>
</head>
<body>
	<div class="">
			<div class="title">Cadastro Realizado com Sucesso! </div>
			<div class="subtitle">Bem-Vindo(a) ${usuarioLogado}</div>
			<div class="input-container icSubmit">
				<a href="LoginForm.jsp"> <button type="text" class="submit">Ir para a tela de login</button> </a>
			</div>
	</div>
</body>
</html>