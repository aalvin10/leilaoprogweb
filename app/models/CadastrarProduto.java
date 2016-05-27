package models;

import play.*;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.*;

import javax.persistence.*;

import java.io.File;
import java.util.*;

@Entity
public class CadastrarProduto extends Model {

	public String nome;
	public String img;
	public String item;
	public String descricao;
	public String tipo;
	public String empresaCNPJ;
	public String arrematadoPor;
	public String valor;
	public String dataFim;

	public boolean confSocio;
	public boolean confAtendente;
	public CadastrarProduto(String nome, String img, String item, String descricao, String empresaCNPJ, String dataFim) {
		super();
		this.nome = nome;
		this.img = img;
		this. item = item;
		this.descricao = descricao;
		this.empresaCNPJ = empresaCNPJ;
		this.dataFim = dataFim;
		confSocio = true;
		confAtendente = false;
		tipo = "objeto";
		valor = "0";

	}
}