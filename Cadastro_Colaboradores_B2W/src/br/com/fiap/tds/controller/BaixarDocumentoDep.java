package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bo.DependenteBo;

@WebServlet("/baixarDocumentoDep")
public class BaixarDocumentoDep extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DependenteBo depBo = new DependenteBo();

		int matricula = Integer.parseInt(request.getParameter("matricula"));
		int codigo = Integer.parseInt(request.getParameter("codigo"));

		try {

			depBo.baixarArquivo(matricula, codigo);

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_3_atualizar_dep.jsp").forward(request, response);
		}
	}
}
