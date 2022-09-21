package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() throws SQLException {
		SingleConnection.getConnection();

		Userposjava userposjava = new Userposjava();
		UserPosDAO userposdao = new UserPosDAO();

		userposjava.setNome("Luis Aguiar");
		userposjava.setEmail("luis.aguiar@gmail.com");

		userposdao.Inserir(userposjava);
	}

	@Test
	public void initListaUsuarios() throws SQLException {
		SingleConnection.getConnection();
		UserPosDAO userposdao = new UserPosDAO();

		List<Userposjava> lista = userposdao.listarUsuarios();

		for (Userposjava userposjava : lista) {
			System.out.println("ID: " + userposjava.getId());
			System.out.println("Nome: " + userposjava.getNome());
			System.out.println("E-mail: " + userposjava.getEmail());
			System.out.println("_____________________");
		}
	}

	@Test
	public void initAtualizarUsuario() throws SQLException {
		SingleConnection.getConnection();
		UserPosDAO userposdao = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		userposjava.setNome("João Mathias");
		userposdao.atualizarUsuario(userposjava, 1L);

	}
	
	@Test
	public void inserirTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(51) 3288-1930");
		telefone.setTipo("Residencial");
		telefone.setUsuario(3L);
		
		UserPosDAO userposdao = new UserPosDAO();
		userposdao.salvarTelefone(telefone);
	}

	@Test
	public void initDeletarUsuario() {
		try {
			SingleConnection.getConnection();
			UserPosDAO userposdao = new UserPosDAO();
			userposdao.deletarUsuario(5L);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void listarContatosUsuario() throws SQLException {
		SingleConnection.getConnection();
		
		UserPosDAO userposdao = new UserPosDAO();
		List<BeanUserFone> listaDeUsuarios = userposdao.listarContatosUsuario(3L);
		
		for(BeanUserFone bUserFone : listaDeUsuarios) {
			System.out.println("Nome: " + bUserFone.getNome());
			System.out.println("Numero: " + bUserFone.getNumero());
			System.out.println("E-mail: " + bUserFone.getEmail());
			System.out.println("_______________________");
		}
	}
	
	

}
