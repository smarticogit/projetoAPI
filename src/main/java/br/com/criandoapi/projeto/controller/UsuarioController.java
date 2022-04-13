package br.com.criandoapi.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.criandoapi.projeto.model.Usuario;
import br.com.criandoapi.projeto.service.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios () {
		List<Usuario> lista = usuarioService.listarUsuario();
		return ResponseEntity.status(200).body(lista);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> criarUsuario (@Valid @RequestBody Usuario usuario) {
		Usuario usuarioNovo = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.status(201).body(usuarioNovo);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> editarUsuario (@Valid @RequestBody Usuario usuario) {
		Usuario usuarioNovo = usuarioService.editarUsuario(usuario);
		return ResponseEntity.status(201).body(usuarioNovo);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirUsuario (@PathVariable Integer id) {
		try {
			usuarioService.excluirUsuario(id);			
		} catch (Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		return ResponseEntity.status(204).build();
	}
	
	@GetMapping("/logar")
	public ResponseEntity<?> validarSenha (@Valid @RequestBody Usuario usuario) {
		Boolean valid = usuarioService.validarSenha(usuario);
		if (!valid) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.status(200).build();
	}
}
