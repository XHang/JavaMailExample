package com.mail.Application;

import org.junit.Test;
import com.main.Authenticator.UserInfoUitl;

public class CopierTest {
	
	@Test
	public void testCopyFile() throws Exception{
		String srcFolderName = "INBOX";
		String descfolderName = "DESC";
		String host = "imap://imap.163.com";
		String user = UserInfoUitl.getUserName();
		String password = UserInfoUitl.getpassword();
		MailApplication.copyFile(srcFolderName, descfolderName, host, user, password, 1, 1);
	}
}
