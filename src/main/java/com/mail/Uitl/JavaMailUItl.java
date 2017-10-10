package com.mail.Uitl;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.main.Authenticator.AuthenticatorInherit;

/**
 * 邮件的工具类
 * @author Mr-hang
 *
 */
public class JavaMailUItl {
	
	/**
	 * 获取Imap协议连接对象
	 * @param user 用户名
	 * @param password 密码
	 * @param serverHost 邮箱服务器
	 * @param debug 是否为本次连接启动debug模式
	 * @return
	 * @throws MessagingException 
	 */
	public static Store getIMAPConnection(String user,String password,String serverHost,boolean debug) throws Exception{
		//TODO 理论上,直接new一个Properties对象进行配置的设置也是可以的，未测试，待定
		Properties properties=System.getProperties();
		properties.setProperty("mail.imap.partialfetch", "false");
		properties.setProperty("mail.imap.auth", "true");
	  //利用imap协议的新浪邮箱地址构造出url
	    URLName url = new URLName(serverHost);
	    //根据预先在properties设置的配置获取Session对象
	    Session session = Session.getInstance(properties);
	     //设置程序运行的日志输出，如果设置为false，则不会有任何日志输出，否则会输出debug信息
	    session.setDebug(debug);  
	    //根据url获取Store对象
	    Store store = session.getStore(url);
	    //根据用户名和密码连接到邮箱
	    System.out.println("正在连接IMG......");
	    store.connect(user, password);
	    return store;
	}
	
	public static Session getSTMPConnection(String user,String password,String serverHost,boolean debug)throws Exception{
		  Properties props = new Properties();                    // 参数配置
	      props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	      props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
	      props.setProperty("mail.host", serverHost);
	      Session session = Session.getDefaultInstance(props, new AuthenticatorInherit(user,password));
	      return session;
	}
	
	
	
	
	/**
	 * 关闭文件夹资源
	 * @param folder
	 * @param falg 指定关闭文件夹时是不是要把文件夹里面的，标有删除标记的文件，全部删掉
	 */
	public static void closeFolder(Folder folder,boolean falg){
		try{
			if(folder!=null){
				folder.close(falg);
			}
		}catch(MessagingException e){
			throw new RuntimeException("关闭文件夹失败",e);
		}
	}
	public static void closeConnection(Store store){
		try{
			if(store!=null){
				store.close();
			}
		}catch(MessagingException e){
			throw new RuntimeException("关闭邮箱失败",e);
		}
	}
	
}
