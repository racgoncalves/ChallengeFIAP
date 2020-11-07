package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.DependenteBo;
import br.com.fiap.tds.bo.DocumentoBo;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/cadastrarDependenteAdmin")
public class CadastrarDependenteAdmin extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classes Bean
		Login login = new Login();
		Documento doc = new Documento();
		Dependente dep = new Dependente();

		// Classes Bo
		DependenteBo depBo = new DependenteBo();
		DocumentoBo docBo = new DocumentoBo();
		LoginBo loginBo = new LoginBo();

		// Atributos
		int matricula = Integer.parseInt(request.getParameter("matriculaDep"));
		String codigoTipo = request.getParameter("tipoDependente");
		String nome = request.getParameter("nomeDependente").toUpperCase();
		String cpf = request.getParameter("cpfDependente");
		String codigoSexo = request.getParameter("sexoDependente");
		String data = request.getParameter("dataDependente");
		String nomeArquivo = request.getParameter("docDependente");

		// Arruma o CPF
		cpf = dep.getNumeroCpfOrPis(cpf);

		// Seleciona os selects
		String tipo = dep.selecionarTipo(codigoTipo);
		String nomeDocumento = dep.selecionarNomeDocumento(codigoTipo);
		String sexo = dep.selecionarSexo(codigoSexo);

		try {

			// Pega os dados de login
			login = loginBo.pesquisar(matricula);

			// Envia os dados para a classe bean e cadastra o dependente
			doc = new Documento(nomeDocumento, nomeArquivo, docBo.importarArquivo(nomeArquivo));
			dep = new Dependente(login, tipo, nome, cpf, sexo, data, doc);
			depBo.cadastrar(dep);

			// Envia a matrícula
			request.setAttribute("matricula", matricula);

			// Atualiza a página de dependentes
			request.getRequestDispatcher("x_admin_2_dependentes.jsp").forward(request, response);

		} catch (Exception e) {

			// Envia a matrícula
			request.setAttribute("matricula", matricula);

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_2_dependentes.jsp").forward(request, response);
		}

	}

}
