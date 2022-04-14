package br.com.criandoapi.projeto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
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
	
	public UsuarioController (UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> listaUsuarios () {
		return ResponseEntity.status(200).body(usuarioService.listarUsuario());
	}
	
	@PostMapping
	public ResponseEntity<?> criarUsuario (@Valid @RequestBody Usuario usuario) {
		Boolean res = usuarioService.verificarEmail(usuario);
		if (!res) {
			return ResponseEntity.status(400).body("Email já existente");
		}
		return ResponseEntity.status(201).body(usuarioService.salvarUsuario(usuario));
	}
	
	@PutMapping
	public ResponseEntity<?> editarUsuario (@Valid @RequestBody Usuario usuario) {
		Boolean res = usuarioService.verificarEmail(usuario);
		if (!res) {
			return ResponseEntity.status(400).body("Email já existente");
		}
		return ResponseEntity.status(201).body(usuarioService.editarUsuario(usuario));
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
	
	@PostMapping("/login")
	public ResponseEntity<Usuario> validarSenha (@Valid @RequestBody Usuario usuario) {
		Boolean valid = usuarioService.validarSenha(usuario);
		if (!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(200).build();
	}
}
