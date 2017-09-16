package com.kxcbs.enums;

/**
 * 返回数据枚举
 * @author renwei
 *
 */
public enum MailEnum {
	SEND_SUCCESS(1,"发送邮件成功"),
	RECEIVE_SUCCESS(1,"接受邮件成功"),
	PARSE_SUCCESS(1,"解析邮件成功"),
	SEND_FAILED(0,"发送邮件失败"),
	SAVE_FAILED(0,"保存邮件到已发送箱失败"),
	MONITOR_FAILED(0,"监控收件箱失败"),
	INNER_ERROR(0,"系统错误");
	private int State;
	private String stateInfo;
	
	public int getState() {
		return State;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	private MailEnum(int state, String stateInfo) {
		State = state;
		this.stateInfo = stateInfo;
	}
	
}
