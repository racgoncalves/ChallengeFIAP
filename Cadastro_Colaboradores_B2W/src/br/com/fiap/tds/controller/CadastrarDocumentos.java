package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.ColaboradorBo;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;
import br.com.fiap.tds.validation.ReservistaValidation;

@WebServlet("/cadastrarDocumentos")
public class CadastrarDocumentos extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classes Bean
		Login login = new Login();
		Documento doc = new Documento();
		Colaborador colab = new Colaborador();

		// Classes Bo
		DocumentoBo docBo = new DocumentoBo();
		LoginBo loginBo = new LoginBo();
		ColaboradorBo colabBo = new ColaboradorBo();

		// Classe Validation
		ReservistaValidation reservistaVal = new ReservistaValidation();

		try {

			// Atributos
			int matricula = Integer.parseInt(request.getParameter("matricula"));
			String erros = "Ainda não foram enviados os seguintes documentos: ";

			// Pega os dados de login
			login = loginBo.pesquisar(matricula);

			// Pega os dados do colaborador
			colab = colabBo.pesquisar(matricula);

			// RG
			String rgDocumento = "RG";
			String rgArquivo = request.getParameter("rg");
			if (rgArquivo != null && !rgArquivo.isEmpty()) {
				doc = new Documento(login, rgDocumento, rgArquivo, docBo.importarArquivo(rgArquivo));
				docBo.enviar(doc);
			} else
				erros += rgDocumento + ", ";

			// CARTEIRA DE TRABALHO
			String trabalhoDocumento = "CARTEIRA DE TRABALHO";
			String trabalhoArquivo = request.getParameter("trabalho");
			if (trabalhoArquivo != null && !trabalhoArquivo.isEmpty()) {
				doc = new Documento(login, trabalhoDocumento, trabalhoArquivo, docBo.importarArquivo(trabalhoArquivo));
				docBo.enviar(doc);
			} else
				erros += trabalhoDocumento + ", ";

			// COMPROVANTE DE RESIDÊNCIA
			String residenciaDocumento = "COMPROVANTE DE RESIDÊNCIA";
			String residenciaArquivo = request.getParameter("residencia");
			if (residenciaArquivo != null && !residenciaArquivo.isEmpty()) {
				doc = new Documento(login, residenciaDocumento, residenciaArquivo,
						docBo.importarArquivo(residenciaArquivo));
				docBo.enviar(doc);
			} else
				erros += residenciaDocumento + ", ";

			// TÍTULO DE ELEITOR
			String eleitorgDocumento = "TÍTULO DE ELEITOR";
			String eleitorArquivo = request.getParameter("eleitor");
			if (eleitorArquivo != null && !eleitorArquivo.isEmpty()) {
				doc = new Documento(login, eleitorgDocumento, eleitorArquivo, docBo.importarArquivo(eleitorArquivo));
				docBo.enviar(doc);
			} else
				erros += eleitorgDocumento + ", ";

			// COMPROVANTE DE ESCOLARIDADE
			String escolaridadeDocumento = "COMPROVANTE DE ESCOLARIDADE";
			String escolaridadeArquivo = request.getParameter("escolaridade");
			if (escolaridadeArquivo != null && !escolaridadeArquivo.isEmpty()) {
				doc = new Documento(login, escolaridadeDocumento, escolaridadeArquivo,
						docBo.importarArquivo(escolaridadeArquivo));
				docBo.enviar(doc);
			} else
				erros += escolaridadeDocumento + ", ";

			// CERTIFICADO DE RESERVISTA
			if (reservistaVal.isReservista(colab.getSexo(), colab.getDataNascimento())) {
				String reservistaDocumento = "CERTIFICADO DE RESERVISTA";
				String reservistaArquivo = request.getParameter("reservista");
				if (reservistaArquivo != null && !reservistaArquivo.isEmpty()) {
					doc = new Documento(login, reservistaDocumento, reservistaArquivo,
							docBo.importarArquivo(reservistaArquivo));
					docBo.enviar(doc);
				} else
					erros += reservistaDocumento + ", ";
			}

			if (docBo.isTodosCadastrados(matricula)) {

				// Leva para a página de dependentes
				request.setAttribute("apelido", login.getApelido());
				request.setAttribute("matricula", login.getMatricula());
				request.getRequestDispatcher("x_colab_4_dependentes.jsp").forward(request, response);
			}

			else {

				erros = erros.substring(0, -2);

				// Colocando mensagem de erro na página
				request.setAttribute("apelido", login.getApelido());
				request.setAttribute("matricula", login.getMatricula());
				request.setAttribute("erro", "Atenção! "+erros);
				request.getRequestDispatcher("x_colab_3_documentos.jsp").forward(request, response);
			}
		} catch (Exception e) {
			
			// Colocando mensagem de erro na página
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());
			request.setAttribute("erro", "Ação não concluída! "+e.getMessage());
			request.getRequestDispatcher("x_colab_3_documentos.jsp").forward(request, response);
		}

	}
}
