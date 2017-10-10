package com.main.Authenticator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 用户名和密码的包装类
 * @author Mr-hang
 *
 */
public class AuthenticatorInherit extends Authenticator{

	private String user; 
	private String password;
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication passwordAuthentication=new PasswordAuthentication(user, password);
		return passwordAuthentication;
	}
	public AuthenticatorInherit(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

}
