## 说明：该项目演示邮箱的连接手法。
包含：  
1. 用java发送普通文本，html代码的邮件
2. 用java接收邮件信息
### 第一步：开启邮箱的各种被封禁的技能以及各大邮箱的服务器地址

 QQ邮箱:设置-->账户-->开启IMAP/SMTP服务   
    SMTP服务器：imap.qq.com  
    IMAP服务器：smtp.qq.com  


126邮箱:设置-->设置POP3/SMTP/IMAP->开启IMAP服务  
    SMTP服务器：smtp.126.com  
    IMAP服务器：imap.126.com  


sina邮箱：设置区-->开启IMAP4服务/SMTP服务
    SMTP服务器：smtp.sina.com  
    IMAP服务器：imap.sina.com  
Hotmail：不支持IMAP协议。  

 注：获取邮箱服务器上所有默认的folder需要使用: folder.list("*") ;

### 第二步，编写代码
请参阅项目内的原代码  
几个点解释下
1.  stmp协议设置的系统环境变量有以下几种  
 mail.stmp.host：SMTP服务器地址，如smtp.sina.com.cn  
 mail.stmp.port: SMTP服务器端口号，默认为25  
 mail.stmp.auth:SMTP服务器是否需要用户认证，默认为false  
 mail.stmp.user:SMTP默认的登陆用户名  
 mail.stmp.from:  默认的邮件发送源地址
 mail.stmp.socketFactory.class:socket工厂类类名，通过设置该属性可以覆盖提供者默认的实现，必须实现javax.NET.SocketFactory接口  
 mail.stmp.socketFactory.port:  指定socket工厂类所用的端口号，如果没有规定，则使用默认的端口号  
 mail.smtp.socketFactory.fallback:  设置为true时，当使用指定的socket类创建socket失败后，将使用Java.net.Socket创建socket，默认为true  
 mail.stmp.timeout:I/O连接超时时间，单位为毫秒，默认为永不超时  
2. 邮箱文件夹名称   
 关于stmp协议代码中的`Folder folder = store.getFolder("INBOX");`
 其中的inbox是文件夹名，不同的邮箱有不同的文件夹名称 
+ QQ邮箱  ：  
	INBOX    
    其他文件夹    
    Sent Messages    
    Drafts    
    Deleted Messages  
    Junk  
    QQ邮件订阅  
+  126邮箱
 	INBOX  
    草稿箱  
    已发送  
    已删除  
    垃圾邮件  
    病毒邮件  
    广告邮件  
    订阅邮件  
+ sina邮箱
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

### BUG
1. 其实也不是BUG，163邮箱的IMAP密码居然不是登录密码，而是需要自己设置一个  
2 . 163邮箱想要搞事情还挺麻烦的，要开通第三方IMAP协议使用·权