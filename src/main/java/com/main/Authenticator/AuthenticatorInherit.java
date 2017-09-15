package com.main.Authenticator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AuthenticatorInherit extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication passwordAuthentication=new PasswordAuthentication(UserInfoUitl.getUserName(), UserInfoUitl.getpassword());
		return passwordAuthentication;
	}

}
