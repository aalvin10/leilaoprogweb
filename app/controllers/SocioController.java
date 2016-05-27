package controllers;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import controllers.Secure.Security;
import play.db.jpa.JPA;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Flash;
import play.mvc.With;
import models.CadastrarCliente;
import models.CadastrarProduto;
import models.CadastrarSocio;

public class SocioController extends Controller{
	
	public static Long socio;
	
	public static void CadastrarSocio(String nome, String cnpj, String email,
		String endereco, String razaoSocial, String telefone, String cep,
		String cidade, String senha) {
	    new CadastrarSocio(nome,cnpj,email,endereco,razaoSocial,telefone,cep,cidade,senha).save();
        String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
	public static void listarSocio(){
    	CadastrarSocio c = CadastrarSocio.find("byEmail", session.get("username")).first();
    	renderJSON(c);
    }
	public static void findAllSocios(){
		List<CadastrarSocio> socio = new ArrayList<CadastrarSocio>();
    	socio = CadastrarSocio.findAll();  
    	renderJSON(socio);
	}
	
	public static void deletarSocio() {
		CadastrarSocio s = CadastrarSocio.findById(socio);
		s.delete();
	}
	public static void deletarSocioComCodigo(Long id){
		CadastrarSocio s = CadastrarSocio.findById(id);
		s.delete();
		
		String url = flash.get("url");
        if(url == null) {
            url = "/admin";
        }
		redirect(url);
	}
	
	public static void atualizarSocio(long id, String nome, String cnpj, String email,
			String endereco, String razaoSocial, String telefone, String cep,
			String cidade, String senha){
		CadastrarSocio s = CadastrarSocio.findById(id);
		s.nome = nome;
		s.cnpj = cnpj;
		s.email = email;
		s.endereco = endereco;
		s.razaoSocial = razaoSocial;
		s.telefone = telefone;
		s.cep = cep;
		s.cidade = cidade;
		s.senha = senha;
		s.save();
        
		String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
	public static void loginSocio(String email, String senha){
		if (email.equals(null) || senha.equals(null)){
			System.out.println("erro!");
		} else {
			socio = (Long) JPA.em().createQuery("select id from CadastrarSocio where email='"+ email + "' and senha='" + senha + "'").getResultList().remove(0);
	        String url = flash.get("url");
	        if(url == null) {
	            url = "/#home";
	        }
			redirect(url);			
		}
    }
	
	public static void logoutSocio(){
		socio = null;
		Application.index();
	}
	
	public static void situacao(){
		renderJSON(socio);
	}
	
	public static void gerouPendencia(){
		CadastrarSocio s = CadastrarSocio.findById(socio);
		s.pendencia = true;
		s.save();
	}
	
	public static void quitouPendencia(){
		CadastrarSocio s = CadastrarSocio.findById(socio);
		s.pendencia = false;
		s.save();
	}
	
	public static void possuiPendencia(){
		CadastrarSocio s = CadastrarSocio.findById(socio);
		renderJSON(s.pendencia);
	}
}
