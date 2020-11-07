<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xcave Solution - Dependentes</title>
<!-- Favicons-->
<link rel="shortcut icon" href="img/xcave.png">
<!-- Web Fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:400,500,600%7cPlayfair+Display:400i"
	rel="stylesheet">
<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css"
	rel="stylesheet">
<!-- Plugins-->
<link href="css/plugins.min.css" rel="stylesheet">
<!-- Template core CSS-->
<link href="css/template.css" rel="stylesheet">
<link href="css/style_upload.css" rel="stylesheet">
</head>

<body class="bg-gray">

	<!-- Alt Services-->
	<section class="">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-12">
					<div class="container">
						<!-- Mensagem de erro -->
						<p class="text-success ml-4">${mensagem}</p>
						<p class="text-danger ml-4">${erro}</p>
						<!-- Início Dependente -->
						<div class="accordion-item-body">
							<h4>Parte 3 de 3</h4>
							<br>
							<h2>Olá, ${apelido}! Você está na página de envio de
								dependentes.</h2>
							<br>
							<p class="lead">Use os campos abaixo para inserir as
								informações de seus dependentes, caso tenha.</p>
							<form id="form_dependente" method="post"
								action="cadastrarDependente">
								<div class="form-group">
									<div class="row">
										<!-- Tipo de Dependente -->
										<div class="col-12 col-md-4 mt-4">
											<label for="tipo">Tipo de dependente:</label> <select
												class="form-control" id="tipo" name="tipo">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">CÔNJUGE (CERTIDÃO DE CASAMENTO)</option>
												<option value="2">FILHO (CERTIDÃO DE NASCIMENTO)</option>
												<option value="3">GENITOR (RG)</option>
											</select>
										</div>
										<!-- Nome Completo -->
										<div class="col-12 col-md-4 mt-4">
											<label for="nome">Nome completo:</label> <input
												class="form-control" type="text" id="nome" name="nome">
										</div>
										<!-- CPF -->
										<div class="col-12 col-md-4 mt-4">
											<label for="cpg">CPF:</label> <input class="form-control"
												type="text" id="cpf" name="cpf"
												onkeypress="$(this).mask('000.000.000-00')">
										</div>
										<!-- Sexo -->
										<div class="col-12 col-md-4 mt-4">
											<label for="sexo">Sexo:</label> <select class="form-control"
												id="sexo" name="sexo">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">FEMININO</option>
												<option value="2">MASCULINO</option>
											</select>
										</div>
										<!-- Data de Nascimento -->
										<div class="col-12 col-md-4 mt-4">
											<label for="data">Data de nascimento:</label> <input
												class="form-control" id="data" name="data" type="text"
												placeholder="Data de nascimento"
												onkeypress="$(this).mask('00/00/0000')">
										</div>
										<!-- Documento -->
										<div class="col-12 col-md-4 mt-4">
											<label for="doc"><b>Documento</b></label> <input type="file"
												class="form-control-file" id="doc" name="doc">
										</div>
										<!-- Botão Adicionar -->
										<button class="btn botao mt-4 mb-4 ml-3"
											id="botaoAdicionarDep" type="button">Adicionar</button>
										<input style="visibility: hidden;" type="text" id="matricula"
											value="${matricula}" name="matricula">
									</div>
								</div>
							</form>
						</div>
						<!-- Fim Dependente -->
						<!-- Botão Finalizar -->
						<form id="form_finalizar" method="post" action="finalizar">
							<button class="btn botao mb-5 float-right" id="botaoFinalizar"
								type="button">Finalizar</button>
							<input style="visibility: hidden;" type="text" id="matricula"
								value="${matricula}" name="matricula">
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Alt Services end-->

	<!-- Scripts-->
	<script src="js/custom/jquery.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<script src="js/custom/plugins.min.js"></script>
	<script src="js/custom/custom.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	<!-- Optional: include a polyfill for ES6 Promises for IE11 -->
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	<script src="js/cadastrarDep.js"></script>
	<script src="js/finalizarCadastro.js"></script>
</body>

</html>