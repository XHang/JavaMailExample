package com.mail.Example;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMultipart;

import com.main.Authenticator.AuthenticatorInherit;

/**
 * 本示例程序演示如何将邮箱文件夹的一个文件移动到另一个文件
 * 
 * 
 * 
 * QQ邮箱:设置-->账户-->开启IMAP/SMTP服务
    SMTP服务器：imap.qq.com
    IMAP服务器：smtp.qq.com
    INBOX
    其他文件夹
    Sent Messages
    Drafts
    Deleted Messages
    Junk
    QQ邮件订阅


126邮箱:设置-->设置POP3/SMTP/IMAP->开启IMAP服务
    SMTP服务器：smtp.126.com
    IMAP服务器：imap.126.com
    INBOX
    草稿箱
    已发送
    已删除
    垃圾邮件
    病毒邮件
    广告邮件
    订阅邮件


sina邮箱：设置区-->开启IMAP4服务/SMTP服务
    SMTP服务器：smtp.sina.com
    IMAP服务器：imap.sina.com
    INBOX
    草稿夹
    已发送
    已删除
    垃圾邮件
    订阅邮件
    星标邮件
    商讯信息
    网站通知
    其它邮件
Hotmail：不支持IMAP协议。


Gmail：属性设置不同于其他邮箱
    props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.imap.socketFactory.fallback", "false");
    props.setProperty("mail.imap.port", "993");
    props.setProperty("mail.imap.socketFactory.port", "993");
    props.setProperty("mail.imap.host", "imap.gmail.com");
    Drafts
    INBOX
    Notes
    [Gmail]
    垃圾邮件
    已删除邮件
    已加星标
    已发邮件
    所有邮件
    草稿
    重要
    工作邮件
    收据
    旅行相关
    私人邮件


    注：获取邮箱服务器上所有默认的folder需要使用: folder.list("*") ;
 * 
 * 
 * 
 * urlname：imap://john:password@mailstore.com
 * PS:这是imap协议，不负责发，只负责收
 * @author Mr-hang
 *
 */
public class copier {
	public static void main(String[] args) {
		boolean debug = false;	
		try {
			//利用imap协议的邮箱地址构造出url
		    URLName url = new URLName("imap://imap.sina.com");
		    
		    //不知道这是什么。。。权且记录
		    Properties props=System.getProperties();
		    props.setProperty("mail.imap.partialfetch", "false");
		    
		    
		    
		    
		    
		    //根据系统属性集获取对话session。。TODO 不怎么理解为什么要用系统的属性集合来获取，我打印了一下，里面也没什么有用的属性啊
		    Session session = Session.getInstance(props);
		   
		     //设置程序运行的日志输出，如果设置为false，则不会有任何日志输出，否则会输出debug信息
		    session.setDebug(debug);
		    //根据url获取Store对象
		    Store store = session.getStore(url);
		    //根据用户名和密码连接到邮箱
		    store.connect("我的用户名", "我的密码。。。不告诉你");
		    //获取邮箱里面的文件夹实例
		    Folder folder=store.getFolder("已删除");
		    //以只读方式打开该文件夹
		    folder.open(Folder.READ_ONLY);
		    //获取文件夹里面的信息个数
		    int count=  folder.getMessageCount();
		    Message  message=folder.getMessage(1);
		    
		    MimeMultipart mimeMultipart=(MimeMultipart)message.getContent();
		    
		    System.out.println(mimeMultipart.getBodyPart(1).getContent());
		    
		    
		    
		    
		    
		    
		    
		    /*// Open Source Folder
		    Folder folder = store.getFolder(src);
		    store.getDefaultFolder().getFullName();
		    
		    folder.open(Folder.READ_WRITE);
		    System.out.println("Opened source...");	  

		    if (folder.getMessageCount() == 0) {
			  System.out.println("Source folder has no messages ..");
			  folder.close(false);
			  store.close();
		    }

		    // Open destination folder, create if needed 
		    Folder dfolder = store.getFolder(dest);
		    if (!dfolder.exists()) // create
			dfolder.create(Folder.HOLDS_MESSAGES);

		    Message[] msgs = folder.getMessages(start, end);
		    System.out.println("Got messages...");	  

		    // Copy messages into destination, 
		    folder.copyMessages(msgs, dfolder);
		    System.out.println("Copied messages...");	  

		    // Close the folder and store
		    folder.close(false);
		    store.close();
		    System.out.println("Closed folder and store...");*/
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}

		System.exit(0);
	    }
	}
