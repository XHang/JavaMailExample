package com.main.Authenticator;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 用户名和密码的包装类-暂且不使用
 * @author Mr-hang
 *
 */
@Deprecated
public class AuthenticatorInherit extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		PasswordAuthentication passwordAuthentication=new PasswordAuthentication(UserInfoUitl.getUserName(), UserInfoUitl.getpassword());
		return passwordAuthentication;
	}

}
