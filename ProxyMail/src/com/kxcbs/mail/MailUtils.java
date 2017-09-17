package com.kxcbs.mail;
import java.util.ArrayList;
import java.util.List;

public class MailUtils {

    public static void main(String[] args) {
    	//多收件人
    	List<String> send_list = new ArrayList<String>();
    	send_list.add("982077829@qq.com");
    	send_list.add("2536964814@qq.com");
    	//多抄送人
    	List<String> cc_list = new ArrayList<String>();
    	cc_list.add("1527105310@qq.com");
    	cc_list.add("1445082062@qq.com");
    	//多密件抄送人
    	List<String> bcc_list = new ArrayList<String>();
    	
        SendMail cn = new SendMail();
        // 设置发件人地址、收件人地址和邮件标题
        cn.setAddress("邮箱名",send_list,cc_list,bcc_list,"测试发送失败","测试正文内容");
        // 设置要发送附件的位置和标题
        cn.setAttachMent("G:\\mailtest\\teacher测试结果.xls", "teacher测试结果.xls");
        
        /**
         * 设置smtp服务器以及邮箱的帐号和密码
         * 用QQ 邮箱作为发生者不好使 （原因不明）
         * 163 邮箱可以，但是必须开启  POP3/SMTP服务 和 IMAP/SMTP服务
         * 因为程序属于第三方登录，所以登录密码必须使用163的授权码  
         */
        // 注意： [授权码和你平时登录的密码是不一样的]
        cn.send("邮箱名", "密码");
        
        
        
        //接受邮件测试
        //ReceiveMailsTest gmt = new ReceiveMailsTest();
        //gmt.getMails("dingkewen@mail.sciencep.com", "cspm1qazxsw2");
        
        //监控收件箱测试
        //MonitorInbox mi = new MonitorInbox();
        //mi.monitorInbox("邮箱名", "密码", 10*1000);
        
        

    }
}