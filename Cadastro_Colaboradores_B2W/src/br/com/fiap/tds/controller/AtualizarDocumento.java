package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/atualizarDocumento")
public class AtualizarDocumento extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classes Bean
		Login login = new Login();
		Documento doc = new Documento();

		// Classes Bo
		DocumentoBo docBo = new DocumentoBo();
		LoginBo loginBo = new LoginBo();

		// Atributos
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		String codigoNome = request.getParameter("nomeDocumento");
		String nomeArquivo = request.getParameter("nomeArquivo");
		String nomeDocumento = doc.selecionarNome(codigoNome);

		try {

			// Pega os dados de login
			login = loginBo.pesquisar(matricula);

			// Monta o documento
			doc = new Documento(login, nomeDocumento, nomeArquivo, docBo.importarArquivo(nomeArquivo));

			// Envia o documento
			docBo.enviar(doc);

			// Atualiza a página de documentos
			request.getRequestDispatcher("x_admin_2_documentos.jsp").forward(request, response);

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_2_documentos.jsp").forward(request, response);
		}

	}
}
