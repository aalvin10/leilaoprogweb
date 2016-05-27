package controllers;

import play.mvc.Controller;
import play.mvc.With;

public class Admin extends Controller {
	public static void admin() {
		System.out.println("entri");
		if (session.contains("username") && session.get("username").equals("the__alvaro@hotmail.com")){
			 Application.admin();
		 }
		 else {
			 Application.forbidden("Acesso permitido somente para administradores!");
		 }
	}
}
