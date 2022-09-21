package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void Inserir(Userposjava userposjava) throws SQLException {

		try {
			String sql = "";
			sql = " INSERT INTO USERPOSJAVA (nome, email) VALUES (?, ?) ";
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, userposjava.getNome());
			pstm.setString(2, userposjava.getEmail());
			pstm.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public List<Userposjava> listarUsuarios() throws SQLException {
		List<Userposjava> lista = new ArrayList<Userposjava>();

		String sql = " SELECT * FROM USERPOSJAVA ";

		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet resultSet = pstm.executeQuery();

		while (resultSet.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultSet.getLong("id"));
			userposjava.setNome(resultSet.getString("nome"));
			userposjava.setEmail(resultSet.getString("email"));

			lista.add(userposjava);
		}

		return lista;
	}

	public void atualizarUsuario(Userposjava userposjava, Long id) throws SQLException {
		String sql = " UPDATE USERPOSJAVA SET NOME = ? WHERE ID = " + id;
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, userposjava.getNome());
		pstm.execute();
		connection.commit();

	}

	public void deletarUsuario(Long id) throws SQLException {
		try {
			String sql = " DELETE FROM USERPOSJAVA WHERE ID = ? ";
			PreparedStatement pstm = connection.prepareStatement(sql);

			pstm.setLong(1, id);
			pstm.execute();
			connection.commit();
		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

	}

	public void salvarTelefone(Telefone telefone) {

		try {

			String sql = " INSERT INTO TELEFONE_USER (NUMERO, TIPO, USUARIO) VALUES (?, ?, ?) ";
			PreparedStatement pstm = connection.prepareStatement(sql);

			pstm.setString(1, telefone.getNumero());
			pstm.setString(2, telefone.getTipo());
			pstm.setLong(3, telefone.getUsuario());

			pstm.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public List<BeanUserFone> listarContatosUsuario(Long id) throws SQLException {		
			List<BeanUserFone> lista = new ArrayList<BeanUserFone>();

			String sql = " SELECT nome, numero, email FROM userposjava as userp " + " INNER JOIN " + " telefone_user as fone "
					+ " ON userp.id = fone.usuario " + " WHERE userp.id = " + id;

			PreparedStatement pstm = connection.prepareStatement(sql);			
			ResultSet resultSet = pstm.executeQuery();
			
			while(resultSet.next()) {
				BeanUserFone bUserFone = new BeanUserFone();
				bUserFone.setNome(resultSet.getString("nome"));
				bUserFone.setNumero(resultSet.getString("numero"));
				bUserFone.setEmail(resultSet.getString("email"));
				
				lista.add(bUserFone);
			}		

		return lista;

	}

}
