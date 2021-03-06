package br.com.fiap.tds.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.tds.bean.Dependente;
import br.com.fiap.tds.bean.Documento;
import br.com.fiap.tds.bean.Login;
import br.com.fiap.tds.exception.AtualizacaoNaoRealizadaException;
import br.com.fiap.tds.exception.DadoInvalidoException;
import br.com.fiap.tds.exception.ItemCadastradoException;
import br.com.fiap.tds.exception.ItemNaoEncontradoException;
import br.com.fiap.tds.factory.ConnectionFactory;

/**
 * Classe respons�vel por acessar o banco de dados e realizar as opera��es
 * b�sicas (CRUD) de um dependente
 * 
 * @author Rodrigo Chiarelli
 * @version 4.0
 */
public class DependenteDao {

	// CADASTRAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Cadastra o dependente
	 * 
	 * @param Dependente Objeto contendo um dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws DadoInvalidoException
	 * @throws ItemCadastradoException
	 * @throws ItemNaoEncontradoException
	 */
	public void cadastrar(Dependente dep) throws ClassNotFoundException, SQLException, DadoInvalidoException,
			ItemCadastradoException, ItemNaoEncontradoException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"INSERT INTO T_XCAVE_DEPENDENTE (CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, "
						+ "NR_CPF, DS_SEXO, DT_NASCIMENTO, NM_DOCUMENTO, NM_ARQUIVO, BT_ARQUIVO) "
						+ "VALUES (?,SQ_XCAVE_DEPENDENTE.NEXTVAL,?,?,?,?,?,?,?,?)");

		// Coloca os valores na query
		stmt.setInt(1, dep.getLogin().getMatricula());
		stmt.setString(2, dep.getTipo());
		stmt.setString(3, dep.getNome());
		stmt.setString(4, dep.getCpf());
		stmt.setString(5, dep.getSexo());
		stmt.setString(6, dep.getDataNascimento());
		stmt.setString(7, dep.getDocumento().getNome());
		stmt.setString(8, dep.getDocumento().getNomeArquivo());
		stmt.setBytes(9, dep.getDocumento().getArquivo());

		// Executar a query
		stmt.executeUpdate();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// PESQUISAR >>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Recupera o os dados de um dependente
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param codigo    C�digo do dependente
	 * @return dependente Objeto contendo o dados do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public Dependente pesquisar(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		Dependente dependente = new Dependente();
		int aux = 0;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT * FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, codigo);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			dependente = parse(resultado);
			aux++;
		}

		if (aux == 0)
			throw new ItemNaoEncontradoException("\nO dependente n�o foi encontrado!");

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return dependente;
	}

	/**
	 * Recupera os dados de todos os dependentes do colaborador
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return List<Dependente> Lista com todos os dependentes do colaborador
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Dependente> listar(int matricula) throws ClassNotFoundException, SQLException {

		List<Dependente> listaDependentes = new ArrayList<Dependente>();

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_MATRICULA, CD_DEPENDENTE, TP_DEPENDENTE, NM_DEPENDENTE, NR_CPF, DS_SEXO, DT_NASCIMENTO, "
						+ "NM_DOCUMENTO FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? ORDER BY CD_DEPENDENTE");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		while (resultado.next()) {

			listaDependentes.add(parseLista(resultado));

		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return listaDependentes;
	}

	/**
	 * Recupera o documento de um dependente
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param codigo    C�digo do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 * @throws IOException
	 */
	public void baixarArquivo(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException, IOException {

		DocumentoDao docDao = new DocumentoDao();
		int aux = 0;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT NM_ARQUIVO, BT_ARQUIVO FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, codigo);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			docDao.exportarArquivo(resultado.getString("NM_ARQUIVO"), resultado.getBytes("BT_ARQUIVO"));
			aux++;
		}

		if (aux == 0)
			throw new ItemNaoEncontradoException("\nO documento n�o foi encontrado!");

		// Fechar a conex�o
		stmt.close();
		conexao.close();
	}

	/**
	 * Verifica se o colaborador j� possui um c�njuge cadastrado
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean conjugeIsCadastrado(int matricula) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Select separado

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_DEPENDENTE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'C�NJUGE'");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			x = true;
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;

	}

	/**
	 * Verifica se o colaborador j� possui um genitor do mesmo sexo cadastrado
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param sexo      Sexo do dependente
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean genitorIsCadastrado(int matricula, String sexo) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT * FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'GENITOR' AND DS_SEXO = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setString(2, sexo);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			x = true;
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;

	}

	/**
	 * Verifica a quantidade de filhos dependentes
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @return filhosDep Quantidade de filhos dependentes
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public int getFilhosDependente(int matricula)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		int filhosDep = 0;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT COUNT(TP_DEPENDENTE) FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND TP_DEPENDENTE = 'FILHO'");

		// Coloca os valores na query
		stmt.setInt(1, matricula);

		// Obter o resultado da pesquisa -> ResultSet
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {
			filhosDep = resultado.getInt(1);
		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return filhosDep;

	}

	/**
	 * Verifica se o CPF j� foi cadastrado
	 * 
	 * @param cpf CPF do dependente
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isCpfCadastrado(String cpf) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao
				.prepareStatement("SELECT CD_MATRICULA FROM T_XCAVE_DEPENDENTE WHERE NR_CPF = ?");

		// Coloca os valores na query
		stmt.setString(1, cpf);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	/**
	 * Verifica se o CPF � o mesmo para atualizar
	 * 
	 * @param cpf       CPF do dependente
	 * @param matricula Matr�cula do dependente
	 * @param codigo    C�digo do dependente
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isMesmoCpf(String cpf, int matricula, int codigo) throws ClassNotFoundException, SQLException {

		boolean x = false;

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Criar o PreparedStatement
		PreparedStatement stmt = conexao.prepareStatement(
				"SELECT CD_MATRICULA FROM T_XCAVE_DEPENDENTE WHERE NR_CPF = ? AND CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setString(1, cpf);
		stmt.setInt(2, matricula);
		stmt.setInt(3, codigo);

		// Obter o resultado da pesquisa
		ResultSet resultado = stmt.executeQuery();

		// Verificar se encontrou resultado
		if (resultado.next()) {

			x = true;

		}

		// Fechar a conex�o
		stmt.close();
		conexao.close();

		return x;
	}

	// ATUALIZAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Atualiza o dependente
	 * 
	 * @param dependente Objeto contendo o dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtualizacaoNaoRealizadaException
	 */
	public void atualizar(Dependente dependente)
			throws ClassNotFoundException, SQLException, AtualizacaoNaoRealizadaException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("UPDATE T_XCAVE_DEPENDENTE SET TP_DEPENDENTE = ?, NM_DEPENDENTE = ?,"
						+ " NR_CPF = ?, DS_SEXO = ?, DT_NASCIMENTO = ?, NM_DOCUMENTO = ?,"
						+ " NM_ARQUIVO = ?, BT_ARQUIVO = ? WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setString(1, dependente.getTipo());
		stmt.setString(2, dependente.getNome());
		stmt.setString(3, dependente.getCpf());
		stmt.setString(4, dependente.getSexo());
		stmt.setString(5, dependente.getDataNascimento());
		stmt.setString(6, dependente.getDocumento().getNome());
		stmt.setString(7, dependente.getDocumento().getNomeArquivo());
		stmt.setBytes(8, dependente.getDocumento().getArquivo());
		stmt.setInt(9, dependente.getLogin().getMatricula());
		stmt.setInt(10, dependente.getCodigo());

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se atualizou o dado
		if (qtd == 0)
			throw new AtualizacaoNaoRealizadaException();

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// REMOVER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Remove um dependente
	 * 
	 * @param matricula Matr�cula do colaborador
	 * @param codigo    C�digo do dependente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ItemNaoEncontradoException
	 */
	public void remover(int matricula, int codigo)
			throws ClassNotFoundException, SQLException, ItemNaoEncontradoException {

		// Conex�o com o banco de dados
		Connection conexao = ConnectionFactory.getConnection();

		// Cria a query para executar no banco
		PreparedStatement stmt = conexao
				.prepareStatement("DELETE FROM T_XCAVE_DEPENDENTE WHERE CD_MATRICULA = ? AND CD_DEPENDENTE = ?");

		// Coloca os valores na query
		stmt.setInt(1, matricula);
		stmt.setInt(2, codigo);

		// Executar a query
		int qtd = stmt.executeUpdate();

		// Valida se removeu alguma linha no banco de dados
		if (qtd == 0)
			throw new ItemNaoEncontradoException("\nO dependente n�o foi encontrado!");

		// Fechar a conex�o
		stmt.close();
		conexao.close();

	}

	// OUTROS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Preenche a classe bean do dependente
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return dadosDependente Objeto com os dados do dependente
	 * @throws SQLException
	 */
	private Dependente parse(ResultSet resultado) throws SQLException {

		Dependente dep = new Dependente();
		Login login = new Login();
		Documento doc = new Documento();

		login.setMatricula(resultado.getInt("CD_MATRICULA"));

		doc.setNome(resultado.getString("NM_DOCUMENTO"));
		doc.setNomeArquivo(resultado.getString("NM_ARQUIVO"));
		doc.setArquivo(resultado.getBytes("BT_ARQUIVO"));

		dep.setLogin(login);
		dep.setCodigo(resultado.getInt("CD_DEPENDENTE"));
		dep.setTipo(resultado.getString("TP_DEPENDENTE"));
		dep.setNome(resultado.getString("NM_DEPENDENTE"));
		dep.setCpf(resultado.getString("NR_CPF"));
		dep.setSexo(resultado.getString("DS_SEXO"));
		dep.setDataNascimento(dep.getDataBD(resultado.getString("DT_NASCIMENTO")));
		dep.setDocumento(doc);

		return dep;
	}

	/**
	 * Preenche a classe bean do dependente da lista
	 * 
	 * @param resultado ResultSet com a pesquisa realizada
	 * @return dadosDependente Objeto com os dados do dependente
	 * @throws SQLException
	 */
	private Dependente parseLista(ResultSet resultado) throws SQLException {

		Dependente dep = new Dependente();
		Login login = new Login();
		Documento doc = new Documento();

		login.setMatricula(resultado.getInt("CD_MATRICULA"));

		doc.setNome(resultado.getString("NM_DOCUMENTO"));

		dep.setLogin(login);
		dep.setCodigo(resultado.getInt("CD_DEPENDENTE"));
		dep.setTipo(resultado.getString("TP_DEPENDENTE"));
		dep.setNome(resultado.getString("NM_DEPENDENTE"));
		dep.setCpf(resultado.getString("NR_CPF"));
		dep.setSexo(resultado.getString("DS_SEXO"));
		dep.setDataNascimento(dep.getDataBD(resultado.getString("DT_NASCIMENTO")));
		dep.setDocumento(doc);

		return dep;
	}

}// Classe
