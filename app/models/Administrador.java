package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Administrador extends Model{
	public String email;
	public String senha;
	public Administrador(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

}
