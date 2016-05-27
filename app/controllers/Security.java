package controllers;

import play.mvc.Controller;
import models.Administrador;
import models.CadastrarCliente;
import models.CadastrarSocio;

public class Security extends Secure.Security {
	
	public static String chave;

	 static boolean authenticate(String email, String senha) {
	      CadastrarCliente user = CadastrarCliente.find("byEmail", email).first();
	      CadastrarSocio user2 = CadastrarSocio.find("byEmail", email).first();
	        if (user != null && user.senha.equals(senha) || user2 != null && user2.senha.equals(senha) ){
	        	chave = email; 
	        	return true;
	        }
	        return false;
	 }
	 
	 // chamado automaticamente quando o usuario conecta
	 static void onAuthenticated() {
		 
	 }
	 
	 // retorna um booleano para saber se o usuario está conectado
	 static boolean isConnected() {
         return session.contains("username");
     }
	 
	 //retorna o nome do usuario conectado
	 static String connected() {
         return session.get("username");
     }
	 
	 //função utilizada quando o usuario desconecta
	 static void onDisconnected() {
		 Application.index();
     }
	 
	 public static void admin() {
		 if (isConnected() && session.get("username").equals("the__alvaro@hotmail.com")){
			 Application.admin();
		 }
		 else {
			 Application.forbidden("Acesso permitido somente para administradores!");
		 }
	 }
	 
	 
	 
}
