package br.com.criandoapi.projeto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message = "O nome é obrigatório")
	@Length(min = 4, max = 200, message = "O nome precisa ter no mínimo 4 caracteres")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@NotBlank(message = "O email é obrigatório")
	@Email(message = "Email Inválido")
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	
	@NotBlank(message = "O senha é obrigatório")
	@Column(name = "senha", columnDefinition = "TEXT", nullable = false)
	private String senha;
	
	@NotBlank(message = "O telefone é obrigatório")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
