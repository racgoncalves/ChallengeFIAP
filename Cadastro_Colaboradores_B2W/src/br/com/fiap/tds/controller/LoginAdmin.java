package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/logarAdmin")
public class LoginAdmin extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Classe Bean
		Login login = new Login();

		// Classe Bo
		LoginBo loginBo = new LoginBo();

		// Atributos
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		try {

			// Testa o login
			login = loginBo.pesquisar(email, senha);

			if (login.getApelido().equals("ADMIN")) {

				// Envia o apelido para a página do administrador
				request.setAttribute("apelido", login.getApelido());

				// Leva para a página do administrador
				request.getRequestDispatcher("x_admin_2_colaboradores.jsp").forward(request, response);

			}

			else {
				
				// Colocando mensagem de erro na página
				request.setAttribute("erro", "Usuário não é administrador!");
				request.getRequestDispatcher("x_admin_1_login.jsp").forward(request, response);
			}

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_1_login.jsp").forward(request, response);

		}
	}
}
