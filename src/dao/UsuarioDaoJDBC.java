package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import model.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

	@Override
	public void salvar(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, email, idade) VALUES (?, ?, ?)";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setInt(3, usuario.getIdade());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<Usuario> listar() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * from usuario";

		try (Connection conn = ConnectionFactory.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				u.setIdade(rs.getInt("idade"));

				usuarios.add(u);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}

		return usuarios;

	}

	@Override
	public Usuario buscarPorId(int Id) {
		String sql = "SELECT * FROM usuario WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, Id);

			try (ResultSet rs = stmt.executeQuery()) {

				if (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setIdade(rs.getInt("idade"));

					return usuario;
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar usuário por ID", e);
		}

		return null;
	}

	@Override
	public void atualizar(Usuario usuario) {
		String sql = "UPDATE usuario SET nome = ?, email = ?, idade = ? WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setInt(3, usuario.getIdade());
			stmt.setLong(4, usuario.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar usuário", e);
		}
	}

	@Override
	public void deletar(int id) {
		String sql = "DELETE FROM usuario WHERE id = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setLong(1, id);
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar usuário", e);
		}
	}

	@Override
	public boolean emailExiste(String email) {
		String sql = "SELECT * FROM usuario WHERE email = ?";

		try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao verificar email", e);
		}
	}
}