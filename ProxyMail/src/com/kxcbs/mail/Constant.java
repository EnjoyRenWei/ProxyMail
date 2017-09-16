package com.kxcbs.mail;
/**
 * 邮件发送和接收配置
 * @author renwei
 *
 */
public class Constant {
	/**
	 * smtp发送邮件服务器地址name
	 */
	public static final String SEND_HOST_NAME="mail.smtp.host";
	/**
	 * smtp发送邮件服务器地址key
	 */
	public static final String SEND_HOST_KEY="smtp.cstnet.cn";
	/**
	 * smtp发送授权，即有户名和密码的校验，这样才能通过验证
	 */
	public static final String SEND_AUTH="mail.smtp.auth";
	/**
	 * imap接受邮件服务器地址name(POP3不能判断邮件已读)
	 */
	public static final String RECEIVE_HOST_NAME="mail.imap.host";
	/**
	 * imap接受邮件服务器地址key(POP3不能判断邮件已读)
	 */
	public static final String RECEIVE_HOST_KEY="imap.cstnet.cn";
	/**
	 * imap接受邮件协议name(POP3不能判断邮件已读)
	 */
	public static final String RECEIVE_PROTOCOL_NAME="mail.store.protocol";
	/**
	 * imap接受邮件协议key(POP3不能判断邮件已读)
	 */
	public static final String RECEIVE_PROTOCOL_KEY="imap";
	
}
