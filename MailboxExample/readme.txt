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
 
 
 
 
 stmp支持的属性
 mail.stmp.host
String
SMTP服务器地址，如smtp.sina.com.cn
mail.stmp.port
int
SMTP服务器端口号，默认为25
mail.stmp.auth
boolean
SMTP服务器是否需要用户认证，默认为false
mail.stmp.user
String
SMTP默认的登陆用户名
mail.stmp.from
String
默认的邮件发送源地址
mail.stmp.socketFactory.class
String
socket工厂类类名，通过设置该属性可以覆盖提供者默认的实现，必须实现javax.NET.SocketFactory接口
mail.stmp.socketFactory.port
int
指定socket工厂类所用的端口号，如果没有规定，则使用默认的端口号
mail.smtp.socketFactory.fallback
boolean
设置为true时，当使用指定的socket类创建socket失败后，将使用Java.net.Socket创建socket，默认为true
mail.stmp.timeout
int
I/O连接超时时间，单位为毫秒，默认为永不超时