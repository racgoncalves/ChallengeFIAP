package br.com.fiap.tds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe responsável por acessar o banco de dados e realizar as operações
 * básicas (CRUD) de login
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class LoginDao {

	// CADASTRAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra um login no banco de dados
	 * 
	 * @param colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Login login) throws ClassNotFoundException, SQLException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_LOGIN" + " (CD_MATRICULA, NM_EMAIL, NM_SENHA, NM_APELIDO, ST_CADASTRO)"
						+ " VALUES (SQ_XCAVE_MATRICULA.NEXTVAL, ?, ?, ?, 0)");

		// Coloca os valores na query
		stmt.setString(1, login.getEmail());
		stmt.setString(2, login.getSenha());
		stmt.setString(3, login.getApelido());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// PESQUISAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera o login do colaborador
	 * 
	 * @param email E-mail do colaborador
	 * @param senha Senha do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DadoInvalidoException
	 */
	public Login pesquisar(String email, String senha)
			throws ClassNotFoundException, SQLException, DadoInvalidoException {

		Login login = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM T_XCAVE_LOGIN WHERE NM_EMAIL = ? AND NM_SENHA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setString(2, senha);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			login = parse(resultado);

		}

		// Valida se encontrou o login
		if (login == null)
			throw new DadoInvalidoException("\nO e-mail ou senha estão incorretos!");

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return login;
	}

	/**
	 * Recupera um login pela matricula
	 * 
	 * @param matricula Matrícula do colaborador
	 * @return login Objeto com os dados de login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Login pesquisar(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Login login = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_LOGIN WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			login = parse(resultado);

		}

		// Valida se encontrou o colaborador
		if (login == null)
			throw new ItemNaoEncontradoException("\nO login não foi encontrado!");

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return login;
	}

	/**
	 * Recupera um login pelo email
	 * 
	 * @param email E-mail de login
	 * @return login Objeto com os dados de login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public Login pesquisar(String email) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		Login login = null;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM T_XCAVE_LOGIN WHERE NM_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			login = parse(resultado);

		}

		// Valida se encontrou o colaborador
		if (login == null)
			throw new ItemNaoEncontradoException("\nO login não foi encontrado!");

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return login;
	}

	/**
	 * Recupera todos os logins cadastrados
	 * 
	 * @return List<Login> Lista com todos os logins cadastrados
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Login> listar() throws ClassNotFoundException, SQLException {

		List<Login> lista = new ArrayList<Login>();

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o objeto para executar um comando SQL
		Statement stmt = conexao.createStatement();

		// Realizar a pesquisa de todos os funcionários
		ResultSet resultado = stmt.executeQuery("SELECT * FROM T_XCAVE_LOGIN ORDER BY NM_APELIDO");

		while (resultado.next()) {

			Login login = parse(resultado);

			lista.add(login);
		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return lista;
	}

	/**
	 * Verifica se o email já foi cadastrado
	 * 
	 * @param email Email de login
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isEmailCadastrado(String email) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_LOGIN WHERE NM_EMAIL = ?");

		// Coloca os valores na query
		stmt.setString(1, email);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Verifica se o email é o mesmo para atualizar
	 * 
	 * @param email Email do login
	 * @param matricula Matrícula do login
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isMesmoEmail(String email, int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_LOGIN WHERE NM_EMAIL = ? AND CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, email);
		stmt.setInt(2, matricula);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conexão
		stmt.close();
		conexao.close();

		return x;
	}

	// ATUALIZAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza um login no banco de dados
	 * 
	 * @param login Objeto contendo os dados de um login
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Login login) throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"UPDATE T_XCAVE_LOGIN SET NM_EMAIL = ?, NM_SENHA = ?, NM_APELIDO = ? WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setString(1, login.getEmail());
		stmt.setString(2, login.getSenha());
		stmt.setString(3, login.getApelido());
		stmt.setInt(4, login.getMatricula());

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// REMOVER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Remove um login e todos os dados do colaborador
	 * 
	 * @param matricula Matrícula do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula) throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		// Conexão com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement("DELETE FROM T_XCAVE_LOGIN WHERE CD_MATRICULA = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se removeu alguma linha no banco de dados
		if (qtd == 0)
			throw new ItemNaoEncontradoException("\nO login não foi encontrado!");

		// Fechar a conexão
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean do login
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return login Objeto com o login
	 * @throws SQLException
	 */
	private Login parse(ResultSet resultado) throws SQLException {

		Login login = new Login();

		login.setMatricula(resultado.getInt("CD_MATRICULA"));
		login.setEmail(resultado.getString("NM_EMAIL"));
		login.setSenha(resultado.getString("NM_SENHA"));
		login.setApelido(resultado.getString("NM_APELIDO"));
		login.setFinalizado(resultado.getBoolean("ST_CADASTRO"));

		return login;

	}

}// Classe
