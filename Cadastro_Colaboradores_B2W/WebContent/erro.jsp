<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página de erro</title>
<!-- Favicons-->
<link rel="shortcut icon" href="img/xcave.png">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
</head>
<body>

	<div class="container mt-5">
		<p>Erro: ${erro}</p>
		<a href="javascript:history.back()" type="button"
			class="btn btn-primary">Voltar</a>
	</div>

</body>
</html>