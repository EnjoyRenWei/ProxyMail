package com.kxcbs.enity;

import java.util.List;

/**
 * 
 * �����ʼ�ʵ����
 * @author renwei
 *
 */
public class SendMail {
	/**
	 * �����������ַ
	 */
	private String address;
	/**
	 * ��������������
	 */
	private String password;
	/**
	 * �ʼ�����
	 */
	private String subject;
	/**
	 * �ʼ��ı�����
	 */
	private String content;
	/**
	 * �ʼ��ռ��������ַ�б�
	 */
	private List<String> toAddress;
	/**
	 * �ʼ������������ַ�б�
	 */
	private List<String> ccAddress;
	/**
	 * �ʼ������������ַ�б�
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
