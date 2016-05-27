package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import play.db.jpa.Model;

@Entity
public class CadastrarCliente extends Model {
	public String nome;
	public String endereco;
	public String cpf;
	public String cep;
	public String cidade;
	public String telefone;
	public String email;
	public String senha;
	public String lanceDisponivel;
	public boolean pendencia;
	public String tipo;
	public boolean status;
	public CadastrarCliente(String nome, String endereco, String cpf, String cep, String cidade, String telefone,
			String email, String senha, String lanceDisponivel){
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf = cpf;
		this.cep = cep;
		this.cidade = cidade;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
		this.lanceDisponivel =lanceDisponivel;
		pendencia = false;
		tipo = "cliente";
		status = false;
	}
	
}
