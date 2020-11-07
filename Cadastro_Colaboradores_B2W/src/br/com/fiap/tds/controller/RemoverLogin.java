package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/removerLogin")
public class RemoverLogin extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginBo loginBo = new LoginBo();

		int matricula = Integer.parseInt(request.getParameter("matricula"));

		try {

			loginBo.remover(matricula);


		} catch (Exception e) {
			// Colocando mensagem de erro na página
			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}
}