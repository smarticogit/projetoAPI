package br.com.criandoapi.projeto.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.repository.IUsuario;

@Service
public class UsuarioService {
	
	private PasswordEncoder password;
	private IUsuario repository;
	
	public UsuarioService(IUsuario dao, IUsuario repository ) {
		super();
		this.password = new BCryptPasswordEncoder();
		this.repository = repository;
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}
	
	public Usuario salvarUsuario (Usuario usuario) {
		String encoder = this.password.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	
	public Usuario editarUsuario (Usuario usuario) {
		String encoder = this.password.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	
	public Boolean validarSenha (Usuario usuario) {
		String senha = repository.getById(usuario.getId()).getSenha();
		boolean valid = password.matches(usuario.getSenha(),senha );
		return valid;
	}
	
	public Boolean verificarEmail (Usuario usuario) {
		Usuario res = repository.findByEmailIs(usuario.getEmail());
		if (res != null) {
			return false;
		}
		return true;
	}
	
	
	public Boolean excluirUsuario (Integer id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new Exception (e.getMessage());
		}
		return true;
	}
 }



