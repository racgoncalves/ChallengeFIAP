<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xcave Solution - Dados</title>
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
						<p class="text-danger ml-4">${erro}</p>
						<!-- Início Dados -->
						<div class="accordion-item-body">
							<h4>Parte 1 de 3</h4>
							<br>
							<h2>Olá, ${apelido}! Você está na página de envio de dados.</h2>
							<br>
							<h3>Dados pessoais</h3>
							<p class="lead mt-4">Use o formulário abaixo para
								disponibilizar seus dados pessoais, todos os campos são
								obrigatórios. Caso a caixa esteja verde, a informação inserida
								está correta, caso ela esteja vermelha significa que algum dado
								está incorreto e você deve corrigí-lo.</p>
							<form id="form_dados" method="post" action="cadastrarColaborador">
								<div class="form-group">
									<div class="row">
										<!-- Nome Completo -->
										<div class="col-12 col-md-4 mt-4">
											<label for="nome">Nome completo:</label> <input
												class="form-control" type="text" id="nome" name="nome">
										</div>
										<!-- CPF -->
										<div class="col-12 col-md-4 mt-4">
											<label for="cpf">Digite seu CPF:</label> <input
												class="form-control" type="text" id="cpf" name="cpf"
												onkeypress="$(this).mask('000.000.000-00')">
										</div>
										<!-- PIS -->
										<div class="col-12 col-md-4 mt-4">
											<label for="pis">Digite seu PIS:</label> <input
												class="form-control" type="text" id="pis" name="pis"
												onkeypress="$(this).mask('000.00000.00-0')">
										</div>
										<!-- Sexo -->
										<div class="col-12 col-md-3 mt-4">
											<label for="sexo">Sexo:</label> <select class="form-control"
												id="sexo" name="sexo">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">FEMININO</option>
												<option value="2">MASCULINO</option>
											</select>
										</div>
										<!-- Nacionalidade -->
										<div class="col-12 col-md-3 mt-4">
											<label for="nacionalidade">Nacionalidade:</label> <input
												class="form-control" id="nacionalidade" name="nacionalidade"
												type="text">
										</div>
										<!-- Naturalidade -->
										<div class="col-12 col-md-3 mt-4">
											<label for="naturalidade">Naturalidade:</label> <input
												class="form-control" id="naturalidade" name="naturalidade"
												type="text">
										</div>
										<!-- Data de Nascimento -->
										<div class="col-12 col-md-3 mt-4">
											<label for="data">Data de nascimento:</label> <input
												class="form-control" id="data" name="data" type="text"
												onkeypress="$(this).mask('00/00/0000')">
										</div>
										<!-- Estado Civil -->
										<div class="col-12 col-md-3 mt-4">
											<label for="civil">Estado civil:</label> <select
												class="form-control" id="estadoCivil" name="estadoCivil">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">SOLTEIRO</option>
												<option value="2">CASADO</option>
												<option value="3">DIVORCIADO</option>
												<option value="4">VIÚVO</option>
											</select>
										</div>
										<!-- Filhos -->
										<div class="col-12 col-md-3 mt-4">
											<label for="filhos">Número de filhos:</label> <input
												class="form-control" id="filhos" name="filhos" type="text">
										</div>
										<!-- Etnia -->
										<div class="col-12 col-md-3 mt-4">
											<label for="etnia">Etnia:</label> <select
												class="form-control" id="etnia" name="etnia">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">BRANCO</option>
												<option value="2">NEGRO</option>
												<option value="3">PARDO</option>
												<option value="4">INDÍGENA</option>
												<option value="5">NÃO INFORMADO</option>
											</select>
										</div>
										<!-- Tamanho Camiseta -->
										<div class="col-12 col-md-3 mt-4">
											<label for="camiseta">Tamanho da camiseta:</label> <select
												class="form-control" id="camiseta" name="camiseta">
												<option value="0" disabled selected>SELECIONE</option>
												<option value="1">PP</option>
												<option value="2">P</option>
												<option value="3">M</option>
												<option value="4">G</option>
												<option value="5">GG</option>
											</select>
										</div>
										<!-- Agência -->
										<div class="col-12 col-md-3 mt-4">
											<label for="agencia">Número da agência:</label> <input
												class="form-control" type="text" id="agencia" name="agencia"
												onkeypress="$(this).mask('0000')">
										</div>
										<!-- Dígito Agência -->
										<div class="col-12 col-md-3 mt-4">
											<label for="digitoAgencia">Dígito da agência:</label> <input
												class="form-control" type="text" id="digitoAgencia"
												name="digitoAgencia" onkeypress="$(this).mask('0')">
										</div>
										<!-- Conta -->
										<div class="col-12 col-md-3 mt-4">
											<label for="conta">Número da conta:</label> <input
												class="form-control" type="text" id="conta" name="conta"
												onkeypress="$(this).mask('0000000')">
										</div>
										<!-- Dígito Conta -->
										<div class="col-12 col-md-3 mt-4">
											<label for="digitoConta">Dígito da conta:</label> <input
												class="form-control" type="text" id="digitoConta"
												name="digitoConta" onkeypress="$(this).mask('0')">
										</div>
									</div>
								</div>
								<div class="form-group mt-4">
									<br>
									<h3>Dados sensíveis</h3>
									<p class="lead mt-4">As perguntas abaixo fazem parte de uma
										pesquisa da B2W, esses dados não serão divulgados nem
										utilizados em processos seletivos da empresa e você não é
										obrigado a informá-los.</p>
									<div class="row">
										<!-- Orientação Sexual -->
										<div class="col-12 col-md-6 mt-4">
											<label for="orientacao">Orientação sexual:</label> <input
												class="form-control" type="text" id="orientacao"
												name="orientacao">
										</div>
										<!-- Religião -->
										<div class="col-12 col-md-6 mt-4">
											<label for="religiao">Religião:</label> <input
												class="form-control" type="text" id="religiao"
												name="religiao">
										</div>
									</div>
								</div>
								<!-- Checkbox -->
								<div class="form-check mt-4 mb-2">
									<input class="form-check-input" type="checkbox" value=""
										id="check" name="check"> <label
										class="form-check-label" for="check"> Declaro que as
										informações acima prestadas são verdadeiras. </label>
									<div class="invalid-feedback">Você deve marcar este campo
										antes de continuar.</div>
								</div>
								<!-- Botão Salvar Dados -->
								<button class="btn botao mt-4 mb-5" id="botaoDados"
									type="button">Enviar</button>
								<input style="visibility: hidden;" type="text" id="matricula"
									value="${matricula}" name="matricula">
							</form>
						</div>
						<!-- Fim Dados -->
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	<!-- Optional: include a polyfill for ES6 Promises for IE11 -->
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>
	<script src="js/cadastrarColab.js"></script>
</body>

</html>