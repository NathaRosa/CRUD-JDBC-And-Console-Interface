package service;

import dao.UsuarioDao;
import model.Usuario;

public class UsuarioService {

	UsuarioDao usuarioDao;

	public UsuarioService(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	 public void cadastrar(Usuario usuario) {

	        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
	            throw new IllegalArgumentException("Nome obrigatório");
	        }

	        if (!usuario.emailValido()) {
	            throw new IllegalArgumentException("Email inválido");
	        }

	        if (usuarioDao.emailExiste(usuario.getEmail())) {
	            throw new IllegalArgumentException("Email já cadastrado");
	        }

	        usuarioDao.salvar(usuario);
	    }
}