<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xcave Solution - Documentos</title>
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
						<!-- Início Documentos -->
						<div class="accordion-item-body">
							<h4>Parte 2 de 3</h4>
							<br>
							<h2>Olá, ${apelido}! Você está na página de envio de
								documentos.</h2>
							<br>
							<p class="lead">Use os campos abaixo para fazer o upload dos
								seus arquivos. Caso algum arquivo não seja aceito, você receberá
								uma mensagem de erro.</p>
							<div class="form-group">
								<div class="row">
									<form id="form_documentos" method="post"
										action="cadastrarDocumentos">
										<!-- RG -->
										<div class="col-12 mt-4">
											<label for="rg"><b>RG</b></label> <input type="file"
												class="form-control-file" id="rg" name="rg">
										</div>
										<!-- Carteira de Trabalho -->
										<div class="col-12 mt-4">
											<label for="trabalho"><b>Carteira de trabalho</b></label> <input
												type="file" class="form-control-file" id="trabalho"
												name="trabalho">
										</div>
										<!-- Comprovante de Residência -->
										<div class="col-12 mt-4">
											<label for="residencia"><b>Comprovante de
													residência</b></label> <input type="file" class="form-control-file"
												id="residencia" name="residencia">
										</div>
										<!-- Título de Eleitor -->
										<div class="col-12 mt-4">
											<label for="eleitor"><b>Título de eleitor</b></label> <input
												type="file" class="form-control-file" id="eleitor"
												name="eleitor">
										</div>
										<!-- Comprovante de Escolaridade -->
										<div class="col-12 mt-4">
											<label for="escolaridade"><b>Comprovante de
													escolaridade</b></label> <input type="file" class="form-control-file"
												id="escolaridade" name="escolaridade">
										</div>
										<!-- Certificado de Reservista -->
										<div class="col-12 mt-4">
											<label for="reservista"><b>Certificado de
													reservista (para homens)</b></label> <input type="file"
												class="form-control-file" id="reservista" name="reservista">
											<button class="btn botao mt-5 mb-5" id="botaoAdicionarDoc"
												type="button">Enviar</button>
											<input style="visibility: hidden;" type="text" id="matricula"
												value="${matricula}" name="matricula">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
					<!-- Fim Documentos -->
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
	<script src="js/cadastrarDoc.js"></script>
</body>

</html>