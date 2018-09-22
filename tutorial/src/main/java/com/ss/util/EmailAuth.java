package com.ss.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuth  extends Authenticator {
	
	 protected PasswordAuthentication getPasswordAuthentication() {

	        return new PasswordAuthentication("sendersEmailAddress", "password");

	    }


}
