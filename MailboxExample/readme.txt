/**
 * ��ʾ��������ʾ��ν������ļ��е�һ���ļ��ƶ�����һ���ļ�
 * 
 * 
 * 
 * QQ����:����-->�˻�-->����IMAP/SMTP����
    SMTP��������imap.qq.com
    IMAP��������smtp.qq.com
    INBOX
    �����ļ���
    Sent Messages
    Drafts
    Deleted Messages
    Junk
    QQ�ʼ�����


126����:����-->����POP3/SMTP/IMAP->����IMAP����
    SMTP��������smtp.126.com
    IMAP��������imap.126.com
    INBOX
    �ݸ���
    �ѷ���
    ��ɾ��
    �����ʼ�
    �����ʼ�
    ����ʼ�
    �����ʼ�


sina���䣺������-->����IMAP4����/SMTP����
    SMTP��������smtp.sina.com
    IMAP��������imap.sina.com
    INBOX
    �ݸ��
    �ѷ���
    ��ɾ��
    �����ʼ�
    �����ʼ�
    �Ǳ��ʼ�
    ��Ѷ��Ϣ
    ��վ֪ͨ
    �����ʼ�
Hotmail����֧��IMAPЭ�顣


Gmail���������ò�ͬ����������
    props.setProperty("mail.imap.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.imap.socketFactory.fallback", "false");
    props.setProperty("mail.imap.port", "993");
    props.setProperty("mail.imap.socketFactory.port", "993");
    props.setProperty("mail.imap.host", "imap.gmail.com");
    Drafts
    INBOX
    Notes
    [Gmail]
    �����ʼ�
    ��ɾ���ʼ�
    �Ѽ��Ǳ�
    �ѷ��ʼ�
    �����ʼ�
    �ݸ�
    ��Ҫ
    �����ʼ�
    �վ�
    �������
    ˽���ʼ�


    ע����ȡ���������������Ĭ�ϵ�folder��Ҫʹ��: folder.list("*") ;
 * 
 * 
 * 
 * urlname��imap://john:password@mailstore.com
 * PS:����imapЭ�飬�����𷢣�ֻ������
 * @author Mr-hang
 *
 */
 
 
 
 
 stmp֧�ֵ�����
 mail.stmp.host
String
SMTP��������ַ����smtp.sina.com.cn
mail.stmp.port
int
SMTP�������˿ںţ�Ĭ��Ϊ25
mail.stmp.auth
boolean
SMTP�������Ƿ���Ҫ�û���֤��Ĭ��Ϊfalse
mail.stmp.user
String
SMTPĬ�ϵĵ�½�û���
mail.stmp.from
String
Ĭ�ϵ��ʼ�����Դ��ַ
mail.stmp.socketFactory.class
String
socket������������ͨ�����ø����Կ��Ը����ṩ��Ĭ�ϵ�ʵ�֣�����ʵ��javax.NET.SocketFactory�ӿ�
mail.stmp.socketFactory.port
int
ָ��socket���������õĶ˿ںţ����û�й涨����ʹ��Ĭ�ϵĶ˿ں�
mail.smtp.socketFactory.fallback
boolean
����Ϊtrueʱ����ʹ��ָ����socket�ഴ��socketʧ�ܺ󣬽�ʹ��Java.net.Socket����socket��Ĭ��Ϊtrue
mail.stmp.timeout
int
I/O���ӳ�ʱʱ�䣬��λΪ���룬Ĭ��Ϊ������ʱ