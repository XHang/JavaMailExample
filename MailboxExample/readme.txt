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