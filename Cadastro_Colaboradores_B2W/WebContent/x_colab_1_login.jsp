<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xcave Solution - Login</title>
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
<link href="css/style_login.css" rel="stylesheet">
</head>

<body class="bg-gray">

	<!-- Alt Services-->
	<section class="">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-5">
					<img src="img/b2w-logo.png" alt="">
				</div>
				<div class="col-lg-6 offset-lg-1">
					<!-- Mensagem de erro -->
					<p class="text-danger">${erro}</p>
					<h2>Bem-vindo ao xcave Solution!</h2>
					<p class="lead mb-4">
						Através deste sistema, você deverá fazer o upload dos documentos
						requeridos pela B2W no processo de <i>Onboarding</i> usando o
						Login e Senha fornecidos por e-mail. Preencha os formulários que
						serão disponibilizados a seguir com seus dados pessoais.
					</p>

					<div class="container">
						<div class="row">
							<div class="col-lg-10 col-md-6">
								<div class="m-b-20">
									<h6>Faça o login abaixo</h6>
								</div>
								<div class="m-b-20">
									<form method="post" action="logar">
										<div class="form-group">
											<input class="form-control" type="text" placeholder="E-mail"
												id="email" name="email">
										</div>
										<div class="form-group">
											<input class="form-control" type="password"
												placeholder="Senha" id="senha" name="senha">
										</div>
										<div class="form-group">
											<button class="btn botao btn-block" type="submit"
												id="botaoLogin">Entrar</button>
										</div>
									</form>
								</div>
								<div class="m-b-20">
									<p>
										<small>Problemas com a sua conta? <a
											href="mailto:contato@b2w.com.br?subject = Feedback&body = Message">Entre
												em contato</a>.
										</small>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Alt Services end-->

	<!-- Scripts-->
	<script src="https://code.jquery.com/jquery-3.5.0.min.js"></script>
	<script src="js/login.js"></script>

</body>

</html>