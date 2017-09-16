package com.kxcbs.mail;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.kxcbs.enums.MailEnum;
import com.kxcbs.exception.MailException;


public class SendMail {
	private String from = ""; // 发件人地址
    private List<String> receivers;// 收件人地址
    private List<String> ccs;// 邮件的抄送者，可以有多个
    private List<String> bccs;// 邮件的密件抄送者，可以有多个
    private String attachPath = ""; // 附件地址
    private String attachName = ""; // 附件名称
    private String subject = ""; // 邮件标题
    private String contentText = ""; //正文内容
    
    public SendMail(){
    	
    }

    public void setAddress(String from, List<String> receivers,List<String> ccs, List<String> bccs,String subject,String contentText) {
        this.from = from;
        this.receivers = receivers;
        this.ccs = ccs;
        this.bccs = bccs;
        this.subject = subject;
        this.contentText = contentText;
    }

    public void setAttachMent(String attachPath, String attachName) {
        this.attachPath = attachPath;
        this.attachName = attachName;
    }

    /**
     * @param user
     * @param pwd
     */
    public void send( String user, String pwd) {
        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（中科院的smtp服务器）
        props.put(Constant.SEND_HOST_NAME, Constant.SEND_HOST_KEY);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put(Constant.SEND_AUTH, "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(from));
            // 加载收件人地址
            if(receivers!=null&&receivers.size()>0){
	            @SuppressWarnings("static-access")
				InternetAddress[] internetAddressTo = new InternetAddress().parse(receivers.toString().replace("[", "").replace("]", ""));
	            message.addRecipients(Message.RecipientType.TO, internetAddressTo);
            }
            // 加载抄送人地址
            if(ccs!=null&&ccs.size()>0){
	            @SuppressWarnings("static-access")
				InternetAddress[] internetAddressCC = new InternetAddress().parse(ccs.toString().replace("[", "").replace("]", ""));
	            message.addRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            // 加载密件抄送人地址
            if(bccs!=null&&bccs.size()>0){
	            @SuppressWarnings("static-access")
				InternetAddress[] internetAddressCC = new InternetAddress().parse(bccs.toString().replace("[", "").replace("]", ""));
	            message.addRecipients(Message.RecipientType.BCC, internetAddressCC);
            }
            // 加载标题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(contentText);
            multipart.addBodyPart(contentPart);
            
            // 添加附件
            if(attachPath!=null&&attachName!=null){
	            BodyPart messageBodyPart = new MimeBodyPart();
	            // 添加附件的标题
	            // Base64编码解决中文乱码
	            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
	            messageBodyPart.setFileName("=?GBK?B?"
	                    + enc.encode(attachName.getBytes()) + "?=");
	            multipart.addBodyPart(messageBodyPart);
	            DataSource source = new FileDataSource(attachPath);
	            // 添加附件的内容
	            messageBodyPart.setDataHandler(new DataHandler(source));
            }
            

            // 将multipart对象放到message中
            message.setContent(multipart,"text/html; charset=utf-8");
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(Constant.SEND_HOST_KEY, user, pwd);
            System.err.println("开始发送......");
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.err.println(MailEnum.SEND_SUCCESS);
            
            //保存邮件到发件箱
            SaveMail.saveMail(session, message, user, pwd);

        } catch (MailException e) {
            throw new MailException(e.getMessage());
        } catch (Exception e){
        	throw new MailException(MailEnum.SEND_FAILED);
        }
    }
}
