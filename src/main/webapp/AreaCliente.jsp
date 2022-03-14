<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/webapp/cadastroView/mostraCadastroView" var="linkCadastroPedido" />
<c:url value="/webapp/login/desloga" var="linkDesloga" />
<c:url value="/webapp/Pedido/deleta/" var="linkDelete" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link href="<%= request.getContextPath()%>/stylesheet/AreaCliente.css" rel="stylesheet">
	<meta charset="UTF-8">

	<title>Area Do Cliente</title>
</head>
<body class="custom text-white">

<div class="px-4 pt-4">
	<h2>Bem Vindo ${username}</h2>
</div>

<div class="position-static pp-3 pt-2 px-2 rounded-5 overflow-hidden">

	<div class="card custom text-white">

		<div class="card-header"><h2>Seus Pedidos:</h2></div>
		<div class="cardBody">
			<div >
				<table class="table customLeve align-middle text-white bdr" >
					<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col" class="text-center">Produtos</th>
						<th scope="col" class="text-center">Endereço</th>
						<th scope="col" class="text-center">Valor do Pedido</th>
						<th scope="col" class="text-center">Valor do Frete</th>
						<th scope="col" class="text-center">Prazo De Entrega</th>
						<th scope="col">Ações</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="p" items="${pedidos}">
						<tr>
							<th scope="row">${p.id}</th>
							<td>
								<c:forEach var="prod" items="${p.produtos}">
									<li>${prod.nome}</li>
								</c:forEach>
							</td>
							<td>${p.endereco}</td>
							<td class="text-center"><label type="number" min="0" step="0.01"></label>R$ ${p.valorPedido}</td>
							<td class="text-center">R$ ${p.valorFrete}</td>
							<td class="text-center">${p.prazoEntrega} dias</td>
							<td colspan="3">
								<form method="get" action="${linkDelete}${p.id}" id="formDelete${p.id}">
									<button type="submit" class="btn btn-danger inner" id="button${p.id}" form="formDelete${p.id}">Deletar</button>
								</form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="input-container ic1 ">
		<a class="input-container" href="${linkDesloga}">
			<button type="text" class="submit inner">Deslogar</button>
		</a>
		<a class="input-container" href="${linkCadastroPedido}">
				<button type="text" class="submit inner">Fazer Um Novo Pedido</button>
		</a>
	</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>