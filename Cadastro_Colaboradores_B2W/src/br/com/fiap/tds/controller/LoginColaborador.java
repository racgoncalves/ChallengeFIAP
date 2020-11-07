package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.ColaboradorBo;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/logar")
public class LoginColaborador extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Classe Bean
		Login login = new Login();
		Colaborador colab = null;

		// Classe Bo
		LoginBo loginBo = new LoginBo();
		ColaboradorBo colabBo = new ColaboradorBo();
		DocumentoBo docBo = new DocumentoBo();

		// Atributos
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		try {

			// Testa o login
			login = loginBo.pesquisar(email, senha);

			// Envia o apelido para a p�gina de dados
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());

			colab = colabBo.pesquisar(login.getMatricula());

			if (login.isFinalizado()) {
				// Colocando mensagem de erro na p�gina
				request.setAttribute("erro", "Cadastro j� finalizado!");
				request.getRequestDispatcher("x_colab_1_login.jsp").forward(request, response);
			}

			else if (colab == null)
				// Leva para a p�gina de dados
				request.getRequestDispatcher("x_colab_2_dados.jsp").forward(request, response);

			else if (!docBo.isTodosCadastrados(login.getMatricula())) {
				// Leva para a p�gina de documentos
				request.getRequestDispatcher("x_colab_3_documentos.jsp").forward(request, response);

			} else {
				// Leva para a p�gina de dependentes
				request.getRequestDispatcher("x_colab_4_dependentes.jsp").forward(request, response);
			}

		} catch (Exception e) {
			
			// Colocando mensagem de erro na p�gina
			request.setAttribute("erro", "A��o n�o conclu�da! " + e.getMessage());
			request.getRequestDispatcher("x_colab_1_login.jsp").forward(request, response);
		}
	}
}