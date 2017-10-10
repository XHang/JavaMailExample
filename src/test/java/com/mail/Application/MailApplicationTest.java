package com.mail.Application;

import org.junit.Test;
import com.main.Authenticator.UserInfoUitl;

public class MailApplicationTest {
	
	@Test
	public void testCopyFile() throws Exception{
		String srcFolderName = "INBOX";
		String descfolderName = "DESC";
		String host = "imap://imap.163.com";
		String user = UserInfoUitl.getUserName();
		String password = UserInfoUitl.getpassword();
		MailApplication.copyFile(srcFolderName, descfolderName, host, user, password, 1, 1);
	}
	
	@Test
	public void testSendMail() throws Exception{
		String host = "smtp.163.com";
		String user = UserInfoUitl.getUserName();
		String password = UserInfoUitl.getpassword();
		String descMailAddress = "1083594261@qq.com";
		String title = "first Mail";
		String text = "越过长城，拥抱世界,fuck";
		MailApplication.sendMail(host, user, password, descMailAddress, text, title);
	}
}
