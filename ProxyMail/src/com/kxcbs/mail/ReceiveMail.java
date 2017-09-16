package com.kxcbs.mail;


import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
/**
 * 接收邮件
 * @author renwei
 *
 */
public class ReceiveMail {

	public void getMails(String user,String password){

	    Properties prop = System.getProperties();  
	    //设置接受邮件协议，地址
	    prop.put(Constant.RECEIVE_PROTOCOL_NAME,Constant.RECEIVE_PROTOCOL_KEY);  
	    prop.put(Constant.RECEIVE_HOST_NAME, Constant.RECEIVE_HOST_KEY);  
	    //获取会话
	    Session session = Session.getInstance(prop);  
	    int total = 0;  
	    IMAPStore store;
		try {
			store = (IMAPStore) session.getStore("imap");
			store.connect(user, password);  
		    IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱  
		    folder.open(Folder.READ_WRITE);  
		    // 获取总邮件数  
		    total = folder.getMessageCount();  
		    System.out.println("-----------------共有邮件：" + total  
		            + " 封--------------");  
		    // 得到收件箱文件夹信息，获取邮件列表  
		    System.out.println("未读邮件数：" + folder.getUnreadMessageCount());  
		    Message[] messages = folder.getMessages(); 
		    StringBuffer sb = new StringBuffer(30);
		    for (Message message : messages) {  
		         
		        Flags flags = message.getFlags();  
		        if (!flags.contains(Flags.Flag.SEEN)){
		        	//解析邮件
		        	ParseMail pm = new ParseMail(message);
		        	System.out.println("这是一封未读邮件");
		        	System.out.println("发送时间：" + pm.getSentDate());  
		        	System.out.println("发件人："+pm.getFrom());
			        System.out.println("主题：" + pm.getSubject());  
			        //解析内容 
			        pm.getMailTextContent(message,sb);
			        System.out.println("内容：" + sb);
			        boolean isAttach = pm.isContainAttach(message);
			        System.out.println("是否包含附件："+isAttach);
			        if(isAttach){
			        	pm.setAttachPath("G:\\mailtest\\");
			        	pm.saveAttachMent(message,pm.getAttachPath());
			        }
			        
			        //设置为已读
			        //message.setFlag(Flags.Flag.SEEN, true);
		        }
		    }  
		    // 释放资源  
		    if (folder != null)  
		        folder.close(true);   
		    if (store != null)  
		        store.close();  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
	}
}