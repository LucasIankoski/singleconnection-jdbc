package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
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
	
	public List<Userposjava> listarUsuarios() throws SQLException{
		List<Userposjava> lista = new ArrayList<Userposjava>();
		
		String sql = " SELECT * FROM USERPOSJAVA ";
		
		PreparedStatement pstm = connection.prepareStatement(sql);
		ResultSet resultSet = pstm.executeQuery();
		
		while(resultSet.next()) {
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

}
