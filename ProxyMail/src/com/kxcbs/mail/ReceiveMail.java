package com.kxcbs.mail;


import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
/**
 * �����ʼ�
 * @author renwei
 *
 */
public class ReceiveMail {

	public void getMails(String user,String password){

	    Properties prop = System.getProperties();  
	    //���ý����ʼ�Э�飬��ַ
	    prop.put(Constant.RECEIVE_PROTOCOL_NAME,Constant.RECEIVE_PROTOCOL_KEY);  
	    prop.put(Constant.RECEIVE_HOST_NAME, Constant.RECEIVE_HOST_KEY);  
	    //��ȡ�Ự
	    Session session = Session.getInstance(prop);  
	    int total = 0;  
	    IMAPStore store;
		try {
			store = (IMAPStore) session.getStore("imap");
			store.connect(user, password);  
		    IMAPFolder folder = (IMAPFolder) store.getFolder("INBOX"); // �ռ���  
		    folder.open(Folder.READ_WRITE);  
		    // ��ȡ���ʼ���  
		    total = folder.getMessageCount();  
		    System.out.println("-----------------�����ʼ���" + total  
		            + " ��--------------");  
		    // �õ��ռ����ļ�����Ϣ����ȡ�ʼ��б�  
		    System.out.println("δ���ʼ�����" + folder.getUnreadMessageCount());  
		    Message[] messages = folder.getMessages(); 
		    StringBuffer sb = new StringBuffer(30);
		    for (Message message : messages) {  
		         
		        Flags flags = message.getFlags();  
		        if (!flags.contains(Flags.Flag.SEEN)){
		        	//�����ʼ�
		        	ParseMail pm = new ParseMail(message);
		        	System.out.println("����һ��δ���ʼ�");
		        	System.out.println("����ʱ�䣺" + pm.getSentDate());  
		        	System.out.println("�����ˣ�"+pm.getFrom());
			        System.out.println("���⣺" + pm.getSubject());  
			        //�������� 
			        pm.getMailTextContent(message,sb);
			        System.out.println("���ݣ�" + sb);
			        boolean isAttach = pm.isContainAttach(message);
			        System.out.println("�Ƿ����������"+isAttach);
			        if(isAttach){
			        	pm.setAttachPath("G:\\mailtest\\");
			        	pm.saveAttachMent(message,pm.getAttachPath());
			        }
			        
			        //����Ϊ�Ѷ�
			        //message.setFlag(Flags.Flag.SEEN, true);
		        }
		    }  
		    // �ͷ���Դ  
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