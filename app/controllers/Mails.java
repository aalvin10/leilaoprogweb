package controllers;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.libs.Mail;
import play.mvc.Mailer;

 
public class Mails extends Mailer{
	 
   public Mails (String email) throws EmailException{
	   welcome(email);	   
   }
   
   public static void welcome(String email) throws EmailException {
	   SimpleEmail email2 = new SimpleEmail();
	   email2.setFrom("casaleilao2015@gmail.com");
	   email2.addTo(email);
	   email2.setSubject("subject");
	   email2.setMsg("Message");
	   Mail.send(email2); 
   }
}
