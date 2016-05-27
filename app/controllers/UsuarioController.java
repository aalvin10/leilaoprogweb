package controllers;

import play.libs.Mail;
import play.mvc.Scope.Session;
import play.mvc.With; 
import play.mvc.Mailer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.util.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mysql.jdbc.Statement;
import com.ning.http.client.FilePart;

import models.Administrador;
import models.CadastrarCliente;
import models.CadastrarProduto;
import models.CadastrarSocio;
import play.*;
import play.db.DB;
import play.db.jpa.*;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.*;
import play.mvc.results.Forbidden;
import play.mvc.results.RenderHtml;
import play.mvc.results.Result;
import sun.net.www.http.HttpClient;

public class UsuarioController extends Controller{

	public static Long cliente;
	
	public static void cadastrarCliente(String nome, String endereco, String cpf, String cep, String cidade, String telefone, String email,
		String senha) throws EmailException{
		if (CadastrarCliente.find("byEmail", email).first() == null){
			new CadastrarCliente(nome,endereco, cpf,cep,cidade,telefone,email,senha,"5").save();
		} else {
			System.out.println("Já existe");
		}
		new Mails(email);
		Application.index();
	}
	
	public static void listarCliente(){
		CadastrarCliente c = CadastrarCliente.find("byEmail", session.get("username")).first();
    	renderJSON(c);
    }
	
	public static void findAllUsers(){
		List<CadastrarCliente> produto = new ArrayList<CadastrarCliente>();
    	produto = CadastrarCliente.findAll();  
    	renderJSON(produto);
	}
	
	public static void deletarCliente(){
		CadastrarCliente c = CadastrarCliente.find("byEmail", session.get("username")).first();
		c.delete();
		Application.index();
	}
	
	public static void deletarClienteComCodigo(Long id){
		CadastrarCliente c = CadastrarCliente.findById(id);
		c.delete();
		String url = flash.get("url");
        if(url == null) {
            url = "/admin";
        }
		redirect(url);
	}
	
	public static void atualizarCliente(String nome, String endereco, String cpf, 
			String cep, String cidade, String telefone, String email,String senha, Long id){
		System.out.println(id);
		CadastrarCliente c = CadastrarCliente.findById(id);
		System.out.println(c);
		c.nome = nome;
		c.endereco = endereco;
		c.cpf = cpf;
		c.cep = cep;
		c.cidade  = cidade;
		c.telefone = telefone;
		c.email = email;
		c.senha = senha;
		c.save();
        String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
	public static void situacaoCliente(){
		renderJSON(cliente);
	}
	
	public static void gerouPendencia(){
		CadastrarCliente c = CadastrarCliente.findById(cliente);
		c.pendencia = true;
		c.save();
	}
	
	public static void quitouPendencia(){
		CadastrarCliente c = CadastrarCliente.findById(cliente);
		c.pendencia = false;
		c.save();
	}
	
	public static void possuiPendencia(){
		CadastrarCliente c = CadastrarCliente.findById(cliente);
		renderJSON(c.pendencia);
	}
	
	 public static boolean isConnected() {
          return session.contains("username");
     }
	 
	//retorna o nome do usuario conectado
	 public static void connected() {
		CadastrarCliente c = CadastrarCliente.find("byEmail", session.get("username")).first();
		renderJSON(c);
     }
	 
	 public static void comprarLances(String id, String nomeCartao, String numeroCartao, String lances) {
		 Long idUsuario = Long.parseLong(id);
		 CadastrarCliente c = CadastrarCliente.findById(idUsuario);
		 int lanceAnterior = Integer.parseInt(c.lanceDisponivel);
		 int lanceAtual = Integer.parseInt(lances);
		 lanceAnterior = lanceAnterior + lanceAtual;
		 c.lanceDisponivel = String.valueOf(lanceAnterior);
		 c.save();
		 String url = flash.get("url");
	        if(url == null) {
	            url = "/#home";
	        }
		redirect(url);
	 }
	
}
