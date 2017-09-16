package com.kxcbs.mail;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.FolderClosedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

import com.kxcbs.enums.MailEnum;
import com.kxcbs.exception.MailException;
import com.sun.mail.imap.IMAPFolder;

/**
 * 监控收件箱，有新邮件则提醒
 * 
 * @author renwei
 *
 */
public class MonitorInbox {
	
	public void monitorInbox( String user, String pwd, long freq) {
		Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（中科院的smtp服务器）
        props.put(Constant.SEND_HOST_NAME, Constant.SEND_HOST_KEY);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put(Constant.SEND_AUTH, "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);
		try {
			Store store = session.getStore("imap");

			// Connect
			store.connect(Constant.RECEIVE_HOST_KEY, user, pwd);

			// Open a Folder
			Folder folder = store.getFolder("INBOX");
			if (folder == null || !folder.exists()) {
				System.out.println("Invalid folder");
				throw new MailException("INBOX邮箱不存在");
			}

			folder.open(Folder.READ_WRITE);

			// Add messageCountListener to listen for new messages
			folder.addMessageCountListener(new MessageCountAdapter() {
				public void messagesAdded(MessageCountEvent ev) {
					Message[] msgs = ev.getMessages();
					System.out.println("Got " + msgs.length + " new messages");
					StringBuffer sb = new StringBuffer(30);

					// Just dump out the new messages
					for (int i = 0; i < msgs.length; i++) {
						try {
							System.out.println("-----有新邮件-----");
							//解析邮件
				        	ParseMail pm = new ParseMail(msgs[i]);
				        	System.out.println("这是一封未读邮件");
				        	System.out.println("发送时间：" + pm.getSentDate());  
				        	System.out.println("发件人："+pm.getFrom());
					        System.out.println("主题：" + pm.getSubject()); 
					        //解析内容
					        pm.getMailTextContent(msgs[i],sb);
					        System.out.println("内容：" + sb);
					        boolean isAttach = pm.isContainAttach(msgs[i]);
					        System.out.println("是否包含附件："+isAttach);
					        if(isAttach){
					        	pm.setAttachPath("g:\\mailtest\\");
					        	pm.saveAttachMent(msgs[i],pm.getAttachPath());
					        }
							
							//将邮件设置为已读
							//msgs[i].setFlag(Flags.Flag.SEEN, true);
						}  catch (Exception mex) {
							mex.printStackTrace();
						}
					}
				}
			});

			// Check mail once in "freq" MILLIseconds
			boolean supportsIdle = false;
			//try
			if (folder instanceof IMAPFolder) {
				IMAPFolder f = (IMAPFolder) folder;
				f.idle();
				supportsIdle = true;
			}
			
			for (;;) {
				if (supportsIdle && folder instanceof IMAPFolder) {
					IMAPFolder f = (IMAPFolder) folder;
					f.idle();
					System.out.println("IDLE done");
				} else {
					Thread.sleep(freq); // sleep for freq milliseconds

					// This is to force the IMAP server to send us
					// EXISTS notifications.
					folder.getMessageCount();
				}
			}

		} catch (MailException ex) {
			throw new MailException(ex.getMessage());
		}catch (Exception ex) {
			throw new MailException(MailEnum.MONITOR_FAILED);
		}
	}
}
