package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Lance extends Model{
	public String valor;
	public String produto;
	public Date hora;
	public String usuario;
	public Lance(String valor, String produto, Date dataFormatada, String usuario) {
		super();
		this.valor = valor;
		this.produto = produto;
		this.hora = dataFormatada;
		this.usuario = usuario;
	}
	
	
}
