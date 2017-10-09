package com.mail.Uitl;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

/**
 * 邮件的工具类
 * @author Mr-hang
 *
 */
public class JavaMailUItl {
	
	/**
	 * 获取连接对象
	 * @param user 用户名
	 * @param password 密码
	 * @param serverHost 邮箱服务器
	 * @param debug 是否为本次连接启动debug模式
	 * @return
	 * @throws MessagingException 
	 */
	public static Store getConnection(String user,String password,String serverHost,boolean debug) throws MessagingException{
		//利用imap协议的新浪邮箱地址构造出url
	    URLName url = new URLName(serverHost);
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
	    store.connect(user, password);
	    return store;
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
			store = getConnection(user, password, host, false);
			folder= store.getFolder(FolderName);
			folder.open(Folder.READ_WRITE);
			folder.setFlags(startIndex, endIndex,new Flags(Flag.DELETED) , true);
			//folder.expunge();  该语句可在邮箱连接状态时，删掉含有删除标记的文件。由于关闭文件夹也可以删除，故注释
		}finally{
			closeFolder(folder, true);
			closeConnection(store);
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
			store = getConnection(user, password, host, false);
			folder= store.getFolder(FolderName);
			folder.open(Folder.READ_WRITE);
			folder.setFlags(firstFileIndex, folder.getMessageCount(),new Flags(Flag.DELETED) , true);
			//folder.expunge();  该语句可在邮箱连接状态时，删掉含有删除标记的文件。由于关闭文件夹也可以删除，故注释
		}finally{
			closeFolder(folder, true);
			closeConnection(store);
		}
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
