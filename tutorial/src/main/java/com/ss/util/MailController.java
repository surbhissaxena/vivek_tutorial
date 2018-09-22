package com.ss.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ss.dto.UserDTO;

public class MailController {

	final static String username = " project2017fall@gmail.com";
	final static String password = "password1993";
	
	
	private static  Properties getProperties(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		return props;
	}
	

	public static void mailMethod( UserDTO dto){
		final String username = "project2017fall@gmail.com";
		final String password = "password1993";
		String Recipient =dto.getEmailId();
        String pass=dto.getUserPassword();
        String userName=dto.getUserName();
		System.out.println(" yes mail in process.... ");
		
		Properties props = getProperties();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(Recipient));
				
			message.setSubject("Registration"+"\n"+"welcome");
			
	      		message.setText("<h1>Hi<h1>"+"<h1>Thanks for Registration...,<h1>");
             //........................................         
	      		
	            message.setContent("<html>\n" +
//	                     " <h1>Your User Name :"+userName+"</h1>"+
	                      " <h1>and Password :"+pass+" for login</h1>"+
	                    "<a href=\"http://localhost:8080/tutorial/\">\n"
	                   , "text/html");
	      		//.....................................
			Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	
	public static void sendMailForFogotPassword(UserDTO dto){
		
		String Recipient =dto.getEmailId();
		String link=dto.getLink();
		Properties props = getProperties();

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("solankinirmal24@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(Recipient));
				
			message.setSubject("Testing Subject"+"\n"+"welcome");
			
	      		message.setText("<h1>Hi<h1>"+"<h1> conformation for reset password ...<h1>");
             //........................................         
	      		message.setSubject("Test verification....");
	      		
	            message.setContent("<html>"
	                 + "<body>"+"<h2>please click here for your conformation..</h2>"
	            	 + "<a href='"+link+"'\">click here</a>"
	            	 + "</body>"
	            	 + "</html>","text/html");
	      		//..................................... 
// String content="<a href=\"www.abc.com/activation?hash="+i+"\">click here</a>";
	            Transport.send(message);
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	
	/*public static void main(String[] args) {
		UserDTO dto = new UserDTO();
		dto.setEmailId("solankinirmal24@gmail.com");
		dto.setUserName("martand");
		dto.setUserPassword("1234");
		mailMethod(dto);
	}*/

}
