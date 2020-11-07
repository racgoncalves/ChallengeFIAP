package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.bo.ColaboradorBo;
import br.com.fiap.tds.bo.LoginBo;

@WebServlet("/cadastrarColaborador")
public class CadastrarColaborador extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classe Bean
		Login login = new Login();
		Colaborador colab = new Colaborador();

		// Classe Bo
		ColaboradorBo colabBo = new ColaboradorBo();
		LoginBo loginBo = new LoginBo();

		// Atributos
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		String nome = request.getParameter("nome").toUpperCase();
		String cpf = request.getParameter("cpf");
		String pis = request.getParameter("pis");
		String codigoSexo = request.getParameter("sexo");
		String dataNascimento = request.getParameter("data");
		String nacionalidade = request.getParameter("nacionalidade").toUpperCase();
		String naturalidade = request.getParameter("naturalidade").toUpperCase();
		int codigoEstadoCivil = Integer.parseInt(request.getParameter("estadoCivil"));
		int filhos = Integer.parseInt(request.getParameter("filhos"));
		int codigoEtnia = Integer.parseInt(request.getParameter("etnia"));
		String agencia = request.getParameter("agencia");
		String digitoAgencia = request.getParameter("digitoAgencia");
		String conta = request.getParameter("conta");
		String digitoConta = request.getParameter("digitoConta");
		int codigoCamiseta = (Integer.parseInt(request.getParameter("camiseta")));
		String orientacao = request.getParameter("orientacao").toUpperCase();
		String religiao = request.getParameter("religiao").toUpperCase();

		// Arruma o CPF e o PIS
		cpf = colab.getNumeroCpfOrPis(cpf);
		pis = colab.getNumeroCpfOrPis(pis);

		// Seleciona os select
		String sexo = colab.selecionarSexo(codigoSexo);
		String estadoCivil = colab.selecionarEstadoCivil(codigoEstadoCivil);
		String etnia = colab.selecionarEtnia(codigoEtnia);
		String camiseta = colab.selecionarCamiseta(codigoCamiseta);

		try {

			// Pega os dados de login
			login = loginBo.pesquisar(matricula);

			// Envia os dados para a classe bean
			colab = new Colaborador(login, nome, cpf, pis, sexo, nacionalidade, naturalidade, dataNascimento,
					estadoCivil, filhos, etnia, camiseta, agencia, digitoAgencia, conta, digitoConta, orientacao,
					religiao);

			// Cadastra no banco de dados
			colabBo.cadastrar(colab);

			// Leva para a página de documentos
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());
			request.getRequestDispatcher("x_colab_3_documentos.jsp").forward(request, response);

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("apelido", login.getApelido());
			request.setAttribute("matricula", login.getMatricula());
			request.setAttribute("erro", "Ação não concluída! "+e.getMessage());
			request.getRequestDispatcher("x_colab_2_dados.jsp").forward(request, response);
		}

	}
}
