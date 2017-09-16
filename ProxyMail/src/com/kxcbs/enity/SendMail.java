package com.kxcbs.enity;

import java.util.List;

/**
 * 
 * 发送邮件实体类
 * @author renwei
 *
 */
public class SendMail {
	/**
	 * 发件人邮箱地址
	 */
	private String address;
	/**
	 * 发件人邮箱密码
	 */
	private String password;
	/**
	 * 邮件主题
	 */
	private String subject;
	/**
	 * 邮件文本内容
	 */
	private String content;
	/**
	 * 邮件收件人邮箱地址列表
	 */
	private List<String> toAddress;
	/**
	 * 邮件抄送人邮箱地址列表
	 */
	private List<String> ccAddress;
	/**
	 * 邮件密送人邮箱地址列表
	 */
	private List<String> bccAddress;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getToAddress() {
		return toAddress;
	}
	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}
	public List<String> getCcAddress() {
		return ccAddress;
	}
	public void setCcAddress(List<String> ccAddress) {
		this.ccAddress = ccAddress;
	}
	public List<String> getBccAddress() {
		return bccAddress;
	}
	public void setBccAddress(List<String> bccAddress) {
		this.bccAddress = bccAddress;
	}
	
}
