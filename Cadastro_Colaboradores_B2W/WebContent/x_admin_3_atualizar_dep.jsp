<%@page import="br.com.fiap.tds.bo.LoginBo"%>
<%@page import="br.com.fiap.tds.bean.Login"%>
<%@page import="br.com.fiap.tds.bo.ColaboradorBo"%>
<%@page import="br.com.fiap.tds.bean.Colaborador"%>
<%@page import="br.com.fiap.tds.bo.DependenteBo"%>
<%@page import="br.com.fiap.tds.bean.Dependente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="shortcut icon" href="img/xcave.png">

<title>xcave Solution - Atualizar Dependente</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/style_upload.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<%
		DependenteBo depBo = new DependenteBo();
	int matricula = Integer.parseInt(request.getParameter("matricula"));
	int codigo = Integer.parseInt(request.getParameter("codigo"));
	Dependente d = depBo.pesquisar(matricula, codigo);
	%>

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion toggled"
			id="accordionSidebar">
			
			<!-- Sidebar - Brand -->
			<a style="pointer-events: none;"
				class="sidebar-brand d-flex align-items-center justify-content-center">
				xcave </a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Tables -->

			<!-- Atualizar Dependente -->
			<li class="nav-item active my-4"><a id="botaoAtualizarDep"
				style="cursor: pointer" class="nav-link"><i
					style="font-size: 30px;" class="fas fa-save"></i><span
					style="font-size: 15px;">Salvar</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Baixar Documento -->
			<li class="nav-item active my-4"><a id="botaoBaixarDocDep"
				style="cursor: pointer" class="nav-link"><i
					style="font-size: 30px;" class="fas fa-download"></i><span
					style="font-size: 15px;">Baixar Documento</span></a></li>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Voltar -->
			<form method="get" id="formVoltar" action="x_admin_2_dependentes.jsp">

				<li class="nav-item active my-4"
					onClick="document.getElementById('formVoltar').submit();"><a
					style="cursor: pointer" class="nav-link"><input
						style="visibility: hidden; width: 0;" type="text"
						value="<%=matricula%>" name="matricula"><i
						style="font-size: 30px;" class="fas fa-arrow-circle-left"></i><span
						style="font-size: 16px;">Voltar</span></a></li>
			</form>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="container">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					
		<!-- Mensagem de erro -->			
<p class="text-danger ml-4 mt-3">${erro}</p>

					<!-- Sidebar Toggle (Topbar) -->
					<form class="form-inline">
						<button id="sidebarToggleTop"
							class="btn btn-link d-md-none rounded-circle mr-3">
							<i class="fa fa-bars"></i>
						</button>
					</form>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Procurar por..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Olá,
									ADMIN</span> <img class="img-profile rounded-circle"
								src="img/b2w-logo.png">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Perfil
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Configurações
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i> Log de
									atividades
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Sair
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Modal Dados do Dependente -->
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLongTitle">Dados do
									dependente</h5>
							</div>

							<div class="modal-body">
								<form id="form_dependente" method="post"
									action="atualizarDependente">
									<div class="form-group">
										<div class="row">
											<!-- Tipo de Dependente -->
											<div class="col-12 col-md-4 mt-4">
												<label for="tipo">Tipo de dependente:</label> <input
													type="text" class="form-control"
													placeholder="<%=d.getTipo()%>" disabled="disabled" />
											</div>
											<!-- Nome Completo -->
											<div class="col-12 col-md-4 mt-4">
												<label for="nome">Nome completo:</label> <input
													class="form-control" type="text" id="nome" name="nome"
													placeholder="<%=d.getNome()%>">
											</div>
											<!-- CPF -->
											<div class="col-12 col-md-4 mt-4">
												<label for="cpf">CPF:</label> <input class="form-control"
													type="text" id="cpf" name="cpf"
													placeholder="<%=d.mascaraCpf(d.getCpf())%>"
													onkeypress="$(this).mask('000.000.000-00')">
											</div>
											<!-- Sexo -->
											<div class="col-12 col-md-4 mt-4">
												<label for="sexo">Sexo:</label> <select class="form-control"
													id="sexo" name="sexo">
													<option value="0" disabled selected><%=d.getSexo()%></option>
													<option value="1">FEMININO</option>
													<option value="2">MASCULINO</option>
												</select>
											</div>
											<!-- Data de Nascimento -->
											<div class="col-12 col-md-4 mt-4">
												<label for="data">Data de nascimento:</label> <input
													class="form-control" id="data" name="data" type="text"
													placeholder="<%=d.getDataNascimento()%>"
													onkeypress="$(this).mask('00/00/0000')">
											</div>
											<!-- Documento -->
											<div class="col-12 mt-4">
												<label for="documento"><b>Documento</b></label> <input
													type="file" class="form-control-file mb-3" id="documento"
													name="documento">
											</div>
											<input style="visibility: hidden; width: 0;" type="text"
												id="matricula" value="<%=matricula%>" name="matricula">
											<input style="visibility: hidden; width: 0;" type="text"
												id="codigo" value="<%=codigo%>" name="codigo">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; xcave Solution | B2W Challenge</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pronto para
						sair?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Selecione "sair" abaixo se você deseja
					encerrar esta sessão.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancelar</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="js/demo/datatables-demo.js"></script>

	<!-- Máscaras Jquery -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>

	<!-- SweetAlert2 -->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
	<!-- Optional: include a polyfill for ES6 Promises for IE11 -->
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>

	<!-- JS Local -->
	<script src="js/baixarDocDep.js"></script>
	<script src="js/atualizarDep.js"></script>

</body>

</html>