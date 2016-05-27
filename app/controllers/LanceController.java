package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.CadastrarCliente;
import models.CadastrarProduto;
import models.Lance;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class LanceController extends Controller {
	public static int num;
	public static void calcularLance(String valor, String produto,String usuario) {

		Date dataFormatada = new Date();
		dataFormatada.getTime();
		Long idProduto = Long.parseLong(produto);
		Long idUsuario = Long.parseLong(usuario);
		
		CadastrarCliente c = CadastrarCliente.findById(idUsuario);
		CadastrarProduto cp = CadastrarProduto.findById(idProduto);
		
		Lance lance = Lance.find("byProduto", produto).first();
		
		if (c.lanceDisponivel.equals("0")){
	        String url = flash.get("url");
	        if(url == null) {
	            url = "/#comprarLance";
	        }
			redirect(url);
		}
		
		if (lance == null){
			if (!c.lanceDisponivel.equals("0")){
				num = Integer.parseInt(c.lanceDisponivel);
				num--;
				c.lanceDisponivel = String.valueOf(num);
				new Lance(valor,produto,dataFormatada,usuario).save();
				cp.arrematadoPor = c.nome;
				c.save();
				cp.save();
				ProdutoController.atualizarProdutoValor(idProduto, valor);
			}
		} else {
			if (!c.lanceDisponivel.equals("0")){
				if (Double.parseDouble(lance.valor) < Double.parseDouble(valor)){
					lance.produto = produto;
					lance.hora = dataFormatada;
					lance.valor = valor;
					lance.usuario = usuario;
					
					num = Integer.parseInt(c.lanceDisponivel);
					num--;
					c.lanceDisponivel = String.valueOf(num);
					cp.arrematadoPor = c.nome;
					
					lance.save();
					c.save();
					cp.save();
					
					ProdutoController.atualizarProdutoValor(idProduto, valor);
				} else {
					 String url = flash.get("url");
			        if(url == null) {
			            url = "/#home";
			        }
					redirect(url);
				}
			}
		}

        String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	public static void listarLance(){
    	List<Lance> lance = new ArrayList<Lance>();
    	lance = Lance.findAll();  
    	renderJSON(lance);
	}
}
