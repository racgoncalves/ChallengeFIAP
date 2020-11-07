package br.com.fiap.tds.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bo.DependenteBo;
import br.com.fiap.tds.bo.DocumentoBo;

@WebServlet("/atualizarDependente")
public class AtualizarDependente extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Arruma os caracteres
		request.setCharacterEncoding("UTF-8");

		// Classe Bean
		Dependente dep = new Dependente();
		Documento doc = new Documento();

		// Classe Bo
		DependenteBo depBo = new DependenteBo();
		DocumentoBo docBo = new DocumentoBo();

		try {

			// Atributos
			int matricula = Integer.parseInt(request.getParameter("matricula"));
			int codigo = Integer.parseInt(request.getParameter("codigo"));

			// Carrega o colaborador
			dep = depBo.pesquisar(matricula, codigo);

			String nome = request.getParameter("nome");
			if (nome != null && !nome.isEmpty()) {
				nome = nome.toUpperCase();
				dep.setNome(nome);
			}

			String cpf = request.getParameter("cpf");
			if (cpf != null && !cpf.isEmpty()) {
				cpf = dep.getNumeroCpfOrPis(cpf);
				dep.setCpf(cpf);
			}

			String codigoSexo = request.getParameter("sexo");
			if (codigoSexo != null && !codigoSexo.isEmpty()) {
				String sexo = dep.selecionarSexo(codigoSexo);
				dep.setSexo(sexo);
			}

			String dataNascimento = request.getParameter("data");
			if (dataNascimento != null && !dataNascimento.isEmpty()) {
				dep.setDataNascimento(dataNascimento);
			}

			String nomeArquivo = request.getParameter("documento");
			if (nomeArquivo != null && !nomeArquivo.isEmpty()) {
				String nomeDocumento = dep.getDocumento().getNome();
				doc = new Documento(nomeDocumento, nomeArquivo, docBo.importarArquivo(nomeArquivo));
				dep.setDocumento(doc);
			}

			// Cadastra no banco de dados
			depBo.atualizar(dep);

			// Atualiza a página de dependentes
			request.getRequestDispatcher("x_admin_3_atualizar_dep.jsp").forward(request, response);

		} catch (Exception e) {

			// Colocando mensagem de erro na página
			request.setAttribute("erro", "Ação não concluída! " + e.getMessage());
			request.getRequestDispatcher("x_admin_3_atualizar_dep.jsp").forward(request, response);
		}

	}
}
