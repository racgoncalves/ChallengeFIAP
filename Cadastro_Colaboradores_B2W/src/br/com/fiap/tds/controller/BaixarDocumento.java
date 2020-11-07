package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bo.DocumentoBo;

@WebServlet("/baixarDocumento")
public class BaixarDocumento extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DocumentoBo docBo = new DocumentoBo();

		Documento doc = new Documento();

		String codigoNome = request.getParameter("nomeDocumento");
		String nomeDocumento = doc.selecionarNome(codigoNome);
		int matricula = Integer.parseInt(request.getParameter("matricula"));

		try {

			docBo.baixarArquivo(matricula, nomeDocumento);

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_2_documentos.jsp").forward(request, response);
		}
	}
}
