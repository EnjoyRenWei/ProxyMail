package com.kxcbs.mail;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.kxcbs.enums.MailEnum;
import com.kxcbs.exception.MailException;


/**
 * 发送邮件成功后，保存邮件到已发送箱
 * 
 *收件箱INBOX,Sent Items发件箱,Drafts草稿箱
 * @author renwei
 *
 */
public class SaveMail {
	public static void saveMail(Session session,Message msg,String user,String pwd){
		/*
	     * Save a copy of the message, if requested.
	     */
		// Get a Store object
		try{
			Store store = null;
		    URLName urln = new URLName("imap",Constant.RECEIVE_HOST_KEY,143,null,user,pwd);
		    store = session.getStore(urln);
		    store.connect();

			// Get record Folder.  Create if it does not exist.
			Folder folder = store.getFolder("Sent Items");
			if (folder == null) {
			    System.err.println("Can't get record folder.");
			    throw new MailException("Sent Items邮箱不存在");
			}
			if (!folder.exists())
			    folder.create(Folder.HOLDS_MESSAGES);

			Message[] msgs = new Message[1];
			msgs[0] = msg;
			//设置为已读
			msgs[0].setFlag(Flags.Flag.SEEN, true);
			folder.appendMessages(msgs);
			
			System.err.println("保存到已发送成功!");
		}catch (MailException e){
			throw new MailException(e.getMessage());
		}catch (Exception e){
			throw new MailException(MailEnum.SAVE_FAILED);
		}
		
	}
}
