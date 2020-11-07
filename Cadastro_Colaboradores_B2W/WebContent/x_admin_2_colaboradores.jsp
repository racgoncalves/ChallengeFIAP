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

<title>xcave Solution - Colaboradores</title>

<!-- Custom fonts for this template -->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark toggled nav-wrapper" 
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a style="pointer-events: none;"
				class="sidebar-brand d-flex align-items-center justify-content-center">
				xcave </a>

			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Tables -->

			<!-- Ir para Logins -->
			<form method="get" id="formLogins" action="x_admin_2_logins.jsp">
				<li class="nav-item active my-4"><a
					onClick="document.getElementById('formLogins').submit();"
					style="cursor: pointer" class="nav-link"><i
						style="font-size: 30px;" class="fas fa-user-circle"></i><span
						style="font-size: 15px;">Logins</span></a></li>
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

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">Colaboradores</h1>
					<p class="mb-4">Aqui você pode visualizar todos os
						colaboradores cadastrados.</p>

					<!-- Dados Principais dos Colaboradores -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered display" id="dataTable"
									cellspacing="0">
									<thead>
										<tr>
											<th>Matrícula</th>
											<th>Nome</th>
											<th>CPF</th>
											<th>Data de Nascimento</th>
											<th>Visualizar</th>
											<th>Documentos</th>
											<th>Dependentes</th>
											<th>Remover</th>
										</tr>
									</thead>
									<tbody>
										<%
											ColaboradorBo colabBo = new ColaboradorBo();
										List<Colaborador> lista = colabBo.listar();
										for (Colaborador c : lista) {
										%>
										<tr>

											<td><%=c.getLogin().getMatricula()%></td>
											<td><%=c.getNome()%></td>
											<td><%=c.mascaraCpf(c.getCpf())%></td>
											<td><%=c.getDataNascimento()%></td>

											<td><form method="GET"
													action="x_admin_3_atualizar_colab.jsp">
													<input style="visibility: hidden; width: 0;" type="text"
														value="<%=c.getLogin().getMatricula()%>" name="matricula">
													<button type="submit" class="btn btn-success ml-1">
														<i class="fas fa-search"></i>
													</button>
												</form></td>
											<td><form method="GET" action="x_admin_2_documentos.jsp">
													<input style="visibility: hidden; width: 0;" type="text"
														value="<%=c.getLogin().getMatricula()%>" name="matricula">
													<button type="submit" class="btn btn-warning ml-4">
														<i class="fas fa-file-alt"></i>
													</button>
												</form></td>
											<td><form method="GET"
													action="x_admin_2_dependentes.jsp">
													<input style="visibility: hidden; width: 0;" type="text"
														value="<%=c.getLogin().getMatricula()%>" name="matricula">
													<button type="submit" class="btn btn-info ml-4">
														<i class="fas fa-users"></i>
													</button>
												</form></td>
											<td><form method="GET"
													action="x_admin_4_remover_colab.jsp">
													<input style="visibility: hidden; width: 0;" type="text"
														value="<%=c.getLogin().getMatricula()%>" name="matricula">
													<input style="visibility: hidden; width: 0;" type="text"
														value="<%=c.getNome()%>" name="nome">
													<button type="submit" class="btn btn-danger">
														<i class="fas fa-trash"></i>
													</button>
												</form></td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white mt-4">
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
				</div>
				<div class="modal-body my-4">Selecione "SAIR" abaixo se você
					deseja encerrar esta sessão.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">CANCELAR</button>
					<a class="btn btn-primary" href="x_6_login_admin.html">SAIR</a>
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
	<script src="sweetalert2.all.min.js"></script>
	<!-- Optional: include a polyfill for ES6 Promises for IE11 -->
	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>

	<!-- Data Table -->
	<script
		src="https://cdn.datatables.net/buttons/1.6.4/js/dataTables.buttons.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.flash.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.html5.min.js"></script>
	<script
		src="https://cdn.datatables.net/buttons/1.6.4/js/buttons.print.min.js"></script>
	<script src="js/tabela-colab.js"></script>
</body>

</html>