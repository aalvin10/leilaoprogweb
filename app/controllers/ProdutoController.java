package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import models.CadastrarProduto;
import models.CadastrarSocio;
import play.db.jpa.Blob;
import play.db.jpa.GenericModel;
import play.db.jpa.JPA;
import play.libs.MimeTypes;
import play.mvc.Controller;

public class ProdutoController extends Controller {
	public static String CNPJ;
	public static void listarProduto() throws ParseException{
    	List<CadastrarProduto> produto = new ArrayList<CadastrarProduto>();
    	produto = CadastrarProduto.findAll();
    	/*
    	List<CadastrarProduto> produtoAtivo = new ArrayList<CadastrarProduto>();
    	DateFormat formatter = new SimpleDateFormat("yy-MM-dd");  
		Date date;
		
		Date dataFormatada = new Date();
		dataFormatada.getTime();
		
		System.out.println("Data Formatado: " + dataFormatada);
    	for (int i=0;i<produto.size();i++){
    		date = (Date)formatter.parse(produto.get(i).dataFim);
    		System.out.println(date);
    		if (date.getDay() < dataFormatada.getDay()){
    			System.out.println("Remover" + produto.get(i).dataFim);
    			produto.remove(i);
    		} else {
    			produtoAtivo.add(produto.get(i));
    		}
    	}
    	*/
    	renderJSON(produto);
    }
	
	public static void listarTodosProdutos() throws ParseException{
    	List<CadastrarProduto> produto = new ArrayList<CadastrarProduto>();
    	produto = CadastrarProduto.findAll();
    	renderJSON(produto);
    }
	
	/*
	public static void listarProdutoDoSocio(){
		Query query = JPA.em().createQuery("select cnpj from CadastrarSocio where email='" + session.get("username") + " '");
		List<CadastrarProduto> produto = query.getResultList();
		System.out.println(query);

		List<CadastrarProduto> p = new ArrayList<CadastrarProduto>();
		p = CadastrarProduto.findAll();
		for (int i=0;i<p.size();i++){
			if (!p.get(i).empresaCNPJ.equals(produto.get(i).empresaCNPJ)){
				p.remove(i);
			}
		}
    	renderJSON(produto);
    }
    */
	
	@play.db.jpa.Transactional
	public static void cadastrarProduto(String nome, String img, String item, 
			String descricao, String empresaCNPJ,String dataFim) throws FileNotFoundException{
		CNPJ = empresaCNPJ;
		CadastrarProduto produto = new CadastrarProduto(nome,img,item, descricao, empresaCNPJ,dataFim).save();
		//produto.imgNome = img.getName();
	    produto.save();
        String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
	
	@play.db.jpa.Transactional
	public static void deletarProduto(Long id){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.delete();
		 String url = flash.get("url");
	        if(url == null) {
	            url = "/#home";
	        }
	        redirect(url);
	}
	
	@play.db.jpa.Transactional
	public static void deletarProdutoComCodigo(Long id){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.delete();
		 String url = flash.get("url");
	        if(url == null) {
	            url = "/admin";
	        }
	        redirect(url);
	}
	
	@play.db.jpa.Transactional
	public static void atualizarProduto(Long id, String nome, String img, String item, 
			String descricao, String empresaCNPJ){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.nome = nome;
		p.img = img;
		p.item = item;
		p.descricao = descricao;
		p.empresaCNPJ = empresaCNPJ;
		p.save();
		String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	public static void atualizarProdutoValor(Long id, String valor){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.valor = valor;
		p.save();
		String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
	public static void confirmacaoSocio(Long id){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.confSocio = true;
		p.save();
	}
	
	public static void confirmacaoAtendente(Long id){
		CadastrarProduto p = CadastrarProduto.findById(id);
		p.confAtendente = true;
		p.save();
	}
	
	public static void Upload(CadastrarProduto img) {
		img.save();
        String url = flash.get("url");
        if(url == null) {
            url = "/#home";
        }
		redirect(url);
	}
	
}
