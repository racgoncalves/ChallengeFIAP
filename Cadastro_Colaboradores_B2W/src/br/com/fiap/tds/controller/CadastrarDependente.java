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

@WebServlet("/cadastrarDependente")
public class CadastrarDependente extends javax.servlet.http.HttpServlet {
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
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		String codigoTipo = request.getParameter("tipo");
		String nome = request.getParameter("nome").toUpperCase();
		String cpf = request.getParameter("cpf");
		String codigoSexo = request.getParameter("sexo");
		String data = request.getParameter("data");
		String nomeArquivo = request.getParameter("doc");

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

			// Leva para a página de dependentes
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());
			request.setAttribute("mensagem", "O dependente foi cadastrado com sucesso!");
			request.getRequestDispatcher("x_colab_4_dependentes.jsp").forward(request, response);

		} catch (Exception e) {
			
			// colocando mensagem de erro na página
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());
			request.setAttribute("erro", "Ação não concluída! "+e.getMessage());
			request.getRequestDispatcher("x_colab_4_dependentes.jsp").forward(request, response);

		}

	}

}
