package com.kxcbs.mail;
import java.util.ArrayList;
import java.util.List;

public class MailUtils {

    public static void main(String[] args) {
    	//���ռ���
    	List<String> send_list = new ArrayList<String>();
    	send_list.add("982077829@qq.com");
    	send_list.add("2536964814@qq.com");
    	//�೭����
    	List<String> cc_list = new ArrayList<String>();
    	cc_list.add("1527105310@qq.com");
    	cc_list.add("1445082062@qq.com");
    	//���ܼ�������
    	List<String> bcc_list = new ArrayList<String>();
    	
        SendMail cn = new SendMail();
        // ���÷����˵�ַ���ռ��˵�ַ���ʼ�����
        cn.setAddress("������",send_list,cc_list,bcc_list,"���Է���ʧ��","������������");
        // ����Ҫ���͸�����λ�úͱ���
        cn.setAttachMent("G:\\mailtest\\teacher���Խ��.xls", "teacher���Խ��.xls");
        
        /**
         * ����smtp�������Լ�������ʺź�����
         * ��QQ ������Ϊ�����߲���ʹ ��ԭ������
         * 163 ������ԣ����Ǳ��뿪��  POP3/SMTP���� �� IMAP/SMTP����
         * ��Ϊ�������ڵ�������¼�����Ե�¼�������ʹ��163����Ȩ��  
         */
        // ע�⣺ [��Ȩ�����ƽʱ��¼�������ǲ�һ����]
        cn.send("������", "����");
        
        
        
        //�����ʼ�����
        //ReceiveMailsTest gmt = new ReceiveMailsTest();
        //gmt.getMails("dingkewen@mail.sciencep.com", "cspm1qazxsw2");
        
        //����ռ������
        //MonitorInbox mi = new MonitorInbox();
        //mi.monitorInbox("������", "����", 10*1000);
        
        

    }
}