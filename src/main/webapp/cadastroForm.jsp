<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/cadastro/cadastroNovoPedido" var="linkResource" />
<c:url value="/webapp/cadastroView/cadastroNovoPedidoView" var="linkResourceView" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%= request.getContextPath()%>/stylesheet/projetocadastroForm.css">
<meta charset="UTF-8">
<title>Carrinho</title>
</head>
<body>
<div class="form">
	<form action="${linkResource}" method="post" id="form2">
			<div class="title">Bem-Vindo ${usuarioLogado}</div>
			<div class="subtitle">Faça o Seu Pedido!</div>
			<div class="input-container ic2">
				<input id="cep" name="cep" class="input" type="text" placeholder=" " />
				<div class="cut"></div>
				<label for="cep" class="placeholder">CEP</label>
			</div>
			<div class="input-container2 ic2">
				<input id="rua" name="rua" class="input2" type="text" placeholder=" " />
				<div class="cut"></div>
				<label for="rua" class="placeholder">Lugadouro</label> <input
					id="numero" name="numero" class="input4" type="text" placeholder=" " />
				<div class="cut2"></div>
				<label for="numero" class="placeholder2">N°</label>
			</div>
			<div class="input-container ic2">
				<input id="cidade" name="cidade" class="input" type="text" placeholder=" " />
				<div class="cut "></div>
				<label for="cidade" class="placeholder">Cidade</label>
			</div>
			<div class="input-container ic2">
				<input id="cepDestino" name="cepDestino" class="input" type="text" placeholder=" " />
				<div class="cutCepDestino "></div>
				<label for="cepDestino" class="placeholder">Cep de Destino</label>
			</div>
			<div class="custom-select ic2">
				<select name="produtos[]" multiple>
					<c:forEach var="p" items="${produtos}">
						<option value="${p.id}">Nome: ${p.nome} - R$:${p.valor}</option>
					</c:forEach>
				</select>
				<span class="custom-arrom"></span>
			</div>

		<div class="input-container3 icSubmit">
			<button type="submit" class="submit" name="bt1" form="form2" formaction="${linkResourceView}">Fazer Pedido</button>
		</div>
		</form>
	</form>
	</div>
</body>
</html>