package com.mail.Application;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.MimeMessage;
import javax.mail.Flags.Flag;

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
				 store = JavaMailUItl.getIMAPConnection(user,password,host,debug);
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
	/**
	 * 发送邮件到其他邮箱
	 * TODO 未测试
	 * @param host  发送方的邮箱服务器
	 * @param user 发送方用户名
	 * @param password  发送方密码
	 * @param descMailAddress 接受方邮件地址
	 * @param text 邮件正文
	 * @param title 标题（不允许包含任何换行符，有的话会被去掉）
	 * @throws Exception 
	 */
	public static void sendMail(String host,String user,String password,String descMailAddress,String text,String title) throws Exception{
			System.out.println("正在准备发送邮件");
			title = removeCRLF(title);
			Session session = null;
			session = JavaMailUItl.getSTMPConnection(user, password, host, false);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(user);
			//指定收件人类型和地址
			message.setRecipients(Message.RecipientType.TO, descMailAddress);
			message.setSubject(title, "utf-8");
			message.setText(text, "utf-8");
			System.out.println("邮件创建成功，正在发送");
			Transport.send(message);
			System.out.println("邮件发送成功");
	}
	/**
	 * 为字符串去除换行符
	 * @param title
	 * @return
	 */
	private static String removeCRLF(String title){
		title = title.replaceAll("\r", "");
		return title.replaceAll("\n", "");
	}
	
	/**
	 * 获取邮箱内文件夹的的文件数量
	 * @param store 连接对象
	 * @param folderName  文件夹名称
	 * @return 
	 * @throws
	 */
	public static int  getMailFolderSize(Store store,String folderName) throws Exception{
		Folder sourceFolder=store.getFolder(folderName);
	    //以只读方式打开该文件夹
	    sourceFolder.open(Folder.READ_ONLY);
	    //获取文件夹里面的信息个数
	    int count=  sourceFolder.getMessageCount();
	    System.out.println("当前选择的文件夹里面的文件数为"+count);
	    return count;
	}
	
	/**
	 * 查看文件夹是否是空
	 * @param store 连接对象
	 * @param folderName  文件夹名称
	 * @return 
	 * @throws Exception
	 */
	public static boolean isEmptyFolder(Store store,String folderName) throws Exception{
		int count = getMailFolderSize(store,folderName);
		return count==0;
	}
	
	/**
	 * 删除邮件
	 * @param FolderName
	 * @param host
	 * @param user
	 * @param password
	 * @param startIndex 要删除的文件开始索引，从1开始
	 * @param endIndex 要删除的文件结束
	 * 注：如果只要删除第一个文件的话，startIndex和endIndex都取1即可
	 * @throws Exception 
	 */
	public static  void removeFiles(String FolderName,String host,String user,String password,int startIndex,int endIndex) throws Exception{
		Store store = null;
		Folder folder = null;
		try{
			store = JavaMailUItl.getIMAPConnection(user, password, host, false);
			folder= store.getFolder(FolderName);
			folder.open(Folder.READ_WRITE);
			folder.setFlags(startIndex, endIndex,new Flags(Flag.DELETED) , true);
			//folder.expunge();  该语句可在邮箱连接状态时，删掉含有删除标记的文件。由于关闭文件夹也可以删除，故注释
		}finally{
			JavaMailUItl.closeFolder(folder, true);
			JavaMailUItl.closeConnection(store);
		}
	}
	/**
	 * 删除单个文件
	 * @param FolderName
	 * @param host
	 * @param user
	 * @param password
	 * @param index  所要删除文件的索引
	 * @throws Exception 
	 */
	public static void removeSingleFile(String FolderName,String host,String user,String password,int index) throws Exception{
		removeFiles(FolderName, host, user, password, index, index);
	}
	
	
	public static void removeAllFile(String FolderName,String host,String user,String password) throws Exception{
		Store store = null;
		Folder folder = null;
		int firstFileIndex = 1;
		try{
			store = JavaMailUItl.getIMAPConnection(user, password, host, false);
			folder= store.getFolder(FolderName);
			folder.open(Folder.READ_WRITE);
			folder.setFlags(firstFileIndex, folder.getMessageCount(),new Flags(Flag.DELETED) , true);
			//folder.expunge();  该语句可在邮箱连接状态时，删掉含有删除标记的文件。由于关闭文件夹也可以删除，故注释
		}finally{
			JavaMailUItl.closeFolder(folder, true);
			JavaMailUItl.closeConnection(store);
		}
	}
	
	
	
}
