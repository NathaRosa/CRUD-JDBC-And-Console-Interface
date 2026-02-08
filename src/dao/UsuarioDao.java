package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDao {

	void salvar(Usuario usuario);

	List<Usuario> listar();

	Usuario buscarPorId(int idBuscar);

	void atualizar(Usuario usuario);

	void deletar(int idExcluir);

	boolean emailExiste(String email);
}