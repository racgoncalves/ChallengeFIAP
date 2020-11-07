package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/finalizar")
public class FinalizarCadastro extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Classe Bean
		Login login = new Login();

		// Classe Bo
		LoginBo loginBo = new LoginBo();

		// Atributos
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		boolean finalizado = true;

		try {

			// Pega o login
			login = loginBo.pesquisar(matricula);
			// Atualiza na bean
			login.setFinalizado(finalizado);
			// Atualiza no servidor
			loginBo.atualizar(login);

			// Leva para a página de finalizado
			request.getRequestDispatcher("x_colab_5_final.jsp").forward(request, response);

		} catch (Exception e) {
			// colocando mensagem de erro na página
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}

	}
}
