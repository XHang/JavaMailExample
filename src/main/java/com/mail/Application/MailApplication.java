package com.mail.Application;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import org.junit.Test;
import com.mail.Uitl.JavaMailUItl;
import com.main.Authenticator.UserInfoUitl;

/**
 * 目标：复制邮箱文件夹里面的文件
 * @author Mr-hang
 *
 */
public class MailApplication {
	/**
	 * 从一个源文件夹拷贝文件到另一个文件夹
	 * @param srcFolderName  
	 * @param descfolderName
	 * @param host  邮件服务器地址
	 * @param user
	 * @param password
	 * @param srcFileStartIndex 源文件夹中文件的开始索引，从1开始
	 * @param srcFileEndIndex 源文件夹中文件的结束索引，从1开始
	 * 注：如果只要拷贝第一个文件的话，srcFileStartIndex和srcFileEndIndex都取1即可
	 * @throws Exception 
	 */ 
	public static void copyFile(String srcFolderName,String descfolderName,String host,String user,String password,int srcFileStartIndex,int srcFileEndIndex) throws Exception{
		Store store = null;
		Folder sourceFolder=null;
		Folder destFolder = null;
			try {
				boolean debug = false;
				 store = JavaMailUItl.getConnection(user,password,host,debug);
			    System.out.println("成功连接");
			    //获取邮箱里面的文件夹实例
			    sourceFolder=store.getFolder(srcFolderName);
			    //以只读方式打开该文件夹
			    sourceFolder.open(Folder.READ_ONLY);
			    //获取文件夹里面的信息个数
			    int count=  sourceFolder.getMessageCount();
			    if(count==0){
			    	throw new RuntimeException("源文件夹没有文件");
			    }
			    destFolder=store.getFolder(descfolderName);
			    if(!(destFolder.exists())){
				   //这种创建的文件夹可以包含其他子文件夹
				   if(!destFolder.create(Folder.HOLDS_MESSAGES)){
					   throw new RuntimeException("创建文件夹失败");
				   }
			   }
			   destFolder.open(Folder.READ_WRITE);
			   //从源文件取得信息序号1到1的邮件信息集合。
			   Message[] msgs=sourceFolder.getMessages(srcFileStartIndex, srcFileEndIndex);
			   //将该集合的邮件集合从源文件夹复制到目的文件夹
			   sourceFolder.copyMessages(msgs, destFolder);
			   //删除邮件后关闭文件夹时是否要和服务器同步（服务器也删除文件）
		       System.out.println("成功完成，关闭资源。。。。。。");
			} catch (Exception e) {
			    throw e;
			}finally{
				JavaMailUItl.closeFolder(sourceFolder, false);
				JavaMailUItl.closeFolder(destFolder, false);
				JavaMailUItl.closeConnection(store);
			}
	}
	
	//public static void sendMail(String host,String user,String )
	
	
	
	
}
