package br.com.criandoapi.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto.dao.IUsuario;
import br.com.criandoapi.projeto.model.Usuario;

@Service
public class UsuarioService {
	
	private PasswordEncoder password;
	private IUsuario dao;
	
	@Autowired
	public UsuarioService(PasswordEncoder password, IUsuario dao) {
		super();
		this.password = new BCryptPasswordEncoder();
		this.dao = dao;
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = dao.findAll();
		return lista;
	}
	
	public Usuario salvarUsuario (Usuario usuario) {
		String encoder = this.password.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;
	}
 }
