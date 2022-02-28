package classmapping;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Mailling {
	public static void send(String from,String pwd,String to,String etat){
		//Propriétés
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		//Session
		Session s = Session.getDefaultInstance(p,
		  new javax.mail.Authenticator() {
			  protected PasswordAuthentication getPasswordAuthentication() {
			     return new PasswordAuthentication(from, pwd);
			  }
		});
		//composer le message
		try {
			MimeMessage m = new MimeMessage(s);
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			m.setSubject("Retour de votre signalement");
			m.setText("Bonjour, \n Des modifications ont été faite après votre signalement.Le problème est "+etat+". Vous pouvez les consulter dans votre application.\n Merci de votre contribution,à bientôt.");
			//envoyer le message
			Transport.send(m);
			System.out.println("Message envoyé avec succès");
		    } catch (MessagingException e) {
		    	e.printStackTrace();
		    }
	  	}
		 public static void main(String[] args) {
		   //from, password, to, subject, message
		   Mailling.send(
		    "ampitaomada@gmail.com",
			"MaNaMi2022",
			"mickaya@outlook.fr","en construstion"
		  );
		 }
		}
