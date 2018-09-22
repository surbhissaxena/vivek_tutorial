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

public class ConformMailController {

	public static void mailMethod(UserDTO dto){
		final String username = "solankinirmal24@gmail.com";
		final String password = "Janendra.9179";
		String Recipient =dto.getEmailId();
		String link=dto.getLink();
		System.out.println(Recipient);
		System.out.println(link);
		System.out.println(" yes mail in coform.... ");
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

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
		dto.setFirstName("nirmal");
		dto.setLastName("singh");
		mailMethod(dto);
	}*/

}
