package com.mail.Example;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMultipart;

import com.main.Authenticator.AuthenticatorInherit;
import com.main.Authenticator.UserInfoUitl;


public class copier {
	public static void main(String[] args) throws MessagingException {
		getMailFileList();
	    }
	/**
	 * 移动收件箱的邮件到指定的文件夹里
	 * TODO ：未通过测试
	 */
	public static void getMailFileList(){
			boolean debug = true;	
			try {
				//利用imap协议的新浪邮箱地址构造出url
			    URLName url = new URLName("imap://imap.163.com");
			    //不知道这是什么。。。权且记录
			    Properties props=System.getProperties();
			    props.setProperty("mail.imap.partialfetch", "false");
			    props.setProperty("mail.imap.auth", "true");
			    //根据系统属性集获取对话session。。TODO 不怎么理解为什么要用系统的属性集合来获取，我打印了一下，里面也没什么有用的属性啊
			    Session session = Session.getInstance(props);
			     //设置程序运行的日志输出，如果设置为false，则不会有任何日志输出，否则会输出debug信息
			    session.setDebug(debug);  
			    //根据url获取Store对象
			    Store store = session.getStore(url);
			    //根据用户名和密码连接到邮箱
			    System.out.println("正在连接IMG......");
			    store.connect(UserInfoUitl.getUserName(), UserInfoUitl.getpassword());
			    System.out.println("成功连接");
			    //获取邮箱里面的文件夹实例
			    Folder sourceFolder=store.getFolder("INBOX");
			    //以只读方式打开该文件夹
			    sourceFolder.open(Folder.READ_ONLY);
			    //获取文件夹里面的信息个数
			    int count=  sourceFolder.getMessageCount();
			    if(count==0){
			    	System.out.println("你选择的源文件夹一个文件也木有哦");
			    	sourceFolder.close(false);
			    	store.close();
			    }
			    Folder destFolder=store.getFolder("（づ￣3￣）づ╭❤～");
			   if(!(destFolder.exists())){
				   //这种创建的文件夹可以包含其他子文件夹
				   if(!destFolder.create(Folder.HOLDS_MESSAGES)){
					   System.out.println("创建文件夹失败");
				   }
			   }
			   //从源文件取得信息序号1到1的邮件信息集合。
			   Message[] msgs=sourceFolder.getMessages(1, 1);
			   //将该集合的邮件集合从源文件夹复制到目的文件夹
			   sourceFolder.copyMessages(msgs, destFolder);
			   sourceFolder.close(false);
		    	store.close();
		    	System.out.println("成功完成，关闭资源。。。。。。");
			    /*Message  message=folder.getMessage(1);
			    //注：如果该条信息里面的格式时text/plan格式的话，获取的该对象就是一个String对象，否则，就是一个MimeMultipart对象
			    //解析起来草麻烦，不过，这次的目标仅仅是移动文件
			    MimeMultipart mimeMultipart=(MimeMultipart)message.getContent();
			    System.out.println(mimeMultipart.getBodyPart(1).getContent());*/
			} catch (Exception e) {
			    e.printStackTrace();
			}
			System.exit(0);
	}
}
