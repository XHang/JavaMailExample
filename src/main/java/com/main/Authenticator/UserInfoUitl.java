package com.main.Authenticator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;

/**
 * 为防止将邮箱密码和用户名提交到git上，特建此类
 * 将邮箱名和密码写在prpperties内，放在其他地方，不会被git版本控制，用到时读取
 * prpperties文件包含两个属性
 * @author Administrator
 *
 */
public class UserInfoUitl {
	
	/**
	 * 保存用户名密码的文件路径
	 */
	public static final String userInfoFile="D://UsetInfo.properties";
	private static Properties userInfoProperties = new Properties();
	
	//类初始化时，就读取userInfoFile路径下的properties文件，并load到类的静态属性里面
	static{
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(userInfoFile);
			userInfoProperties.load(inputStream);
		} catch (Exception e) {
			System.out.println("读取用户信息文件失败");
			e.printStackTrace();
		}finally{
			try{
				if(inputStream!=null){
					inputStream.close();
				}
			}catch(Exception e){
				System.out.println("关闭用户信息文件失败");
				e.printStackTrace();
			}
		}
	}
	
	public static String  getpassword(){
		String password =  userInfoProperties.getProperty("password");
		if(password == null){
			throw new RuntimeException("读取不到密码信息，请检查你的文件格式");
		}
		return password;
	}
	public static String getUserName(){
		String username =  userInfoProperties.getProperty("username");
		if(username == null){
			throw new RuntimeException("读取用户名失败，请check你的文件");
		}
		return username;
	}
}
