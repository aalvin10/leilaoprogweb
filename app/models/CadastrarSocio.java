package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class CadastrarSocio extends Model {
	public String nome;
	public String cnpj;
	public String email;
	public String endereco;
	public String razaoSocial;
	public String telefone;
	public String cep;
	public String cidade;
	public String senha;
	public String tipo;
	public boolean pendencia;
	
	public CadastrarSocio(String nome, String cnpj, String email,
			String endereco, String razaoSocial, String telefone, String cep,
			String cidade, String senha) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.endereco = endereco;
		this.razaoSocial = razaoSocial;
		this.telefone = telefone;
		this.cep = cep;
		this.cidade = cidade;
		this.senha = senha;
		pendencia = false;
		tipo = "socio";
	}
	
} 
