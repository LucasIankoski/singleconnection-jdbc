package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
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
	public void initDeletarUsuario() {
		try {
			SingleConnection.getConnection();
			UserPosDAO userposdao = new UserPosDAO();
			userposdao.deletarUsuario(1L);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
