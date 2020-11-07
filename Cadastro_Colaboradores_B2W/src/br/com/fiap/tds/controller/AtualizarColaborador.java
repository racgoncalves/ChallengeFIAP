package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Colaborador;
import br.com.fiap.tds.bo.ColaboradorBo;

@WebServlet("/atualizarColaborador")
public class AtualizarColaborador extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classe Bean
		Colaborador colab = new Colaborador();

		// Classe Bo
		ColaboradorBo colabBo = new ColaboradorBo();

		try {

			// Atributos
			int matricula = Integer.parseInt(request.getParameter("matricula"));

			// Carrega o colaborador
			colab = colabBo.pesquisar(matricula);

			String nome = request.getParameter("nome");
			if (nome != null && !nome.isEmpty()) {
				nome = nome.toUpperCase();
				colab.setNome(nome);
			}

			String cpf = request.getParameter("cpf");
			if (cpf != null && !cpf.isEmpty()) {
				cpf = colab.getNumeroCpfOrPis(cpf);
				colab.setCpf(cpf);
			}

			String pis = request.getParameter("pis");
			if (pis != null && !pis.isEmpty()) {
				pis = colab.getNumeroCpfOrPis(pis);
				colab.setPis(pis);
			}

			String codigoSexo = request.getParameter("sexo");
			if (codigoSexo != null && !codigoSexo.isEmpty()) {
				String sexo = colab.selecionarSexo(codigoSexo);
				colab.setSexo(sexo);
			}

			String dataNascimento = request.getParameter("data");
			if (dataNascimento != null && !dataNascimento.isEmpty()) {
				colab.setDataNascimento(dataNascimento);
			}

			String nacionalidade = request.getParameter("nacionalidade");
			if (nacionalidade != null && !nacionalidade.isEmpty()) {
				nacionalidade = nacionalidade.toUpperCase();
				colab.setNacionalidade(nacionalidade);
			}

			String naturalidade = request.getParameter("naturalidade");
			if (naturalidade != null && !naturalidade.isEmpty()) {
				naturalidade = naturalidade.toUpperCase();
				colab.setNaturalidade(naturalidade);
			}

			String codigoEstadoCivil = request.getParameter("estadoCivil");
			if (codigoEstadoCivil != null && !codigoEstadoCivil.isEmpty()) {
				int codEstadoCivil = Integer.parseInt(codigoEstadoCivil);
				String estadoCivil = colab.selecionarEstadoCivil(codEstadoCivil);
				colab.setEstadoCivil(estadoCivil);
			}

			String filhos = request.getParameter("filhos");
			if (filhos != null && !filhos.isEmpty()) {
				colab.setFilhos(Integer.parseInt(filhos));
			}

			String codigoEtnia = request.getParameter("etnia");
			if (codigoEtnia != null && !codigoEtnia.isEmpty()) {
				int codEtnia = Integer.parseInt(codigoEtnia);
				String etnia = colab.selecionarEtnia(codEtnia);
				colab.setEtnia(etnia);
			}

			String agencia = request.getParameter("agencia");
			if (agencia != null && !agencia.isEmpty()) {
				colab.setAgencia(agencia);
			}

			String digitoAgencia = request.getParameter("digitoAgencia");
			if (digitoAgencia != null && !digitoAgencia.isEmpty()) {
				colab.setDigitoAgencia(digitoAgencia);
			}

			String conta = request.getParameter("conta");
			if (conta != null && !conta.isEmpty()) {
				colab.setConta(conta);
			}

			String digitoConta = request.getParameter("digitoConta");
			if (digitoConta != null && !digitoConta.isEmpty()) {
				colab.setDigitoConta(digitoConta);
			}

			String codigoCamiseta = request.getParameter("camiseta");
			if (codigoCamiseta != null && !codigoCamiseta.isEmpty()) {
				int codCamiseta = Integer.parseInt(codigoCamiseta);
				String camiseta = colab.selecionarCamiseta(codCamiseta);
				colab.setCamiseta(camiseta);
			}

			String orientacao = request.getParameter("orientacao");
			if (orientacao != null && !orientacao.isEmpty()) {
				orientacao = orientacao.toUpperCase();
				colab.setOrientacao(orientacao);
			}

			String religiao = request.getParameter("religiao");
			if (religiao != null && !religiao.isEmpty()) {
				religiao = religiao.toUpperCase();
				colab.setReligiao(religiao);
			}

			// Cadastra no banco de dados
			colabBo.atualizar(colab);

			// Atualiza a página do colaborador
			request.getRequestDispatcher("x_admin_3_atualizar_colab.jsp").forward(request, response);

		} catch (Exception e) {
			
			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! "+e.getMessage());
			request.getRequestDispatcher("x_admin_3_atualizar_colab.jsp").forward(request, response);
		}

	}
}
