package com.kxcbs.enums;

/**
 * ��������ö��
 * @author renwei
 *
 */
public enum MailEnum {
	SEND_SUCCESS(1,"�����ʼ��ɹ�"),
	RECEIVE_SUCCESS(1,"�����ʼ��ɹ�"),
	PARSE_SUCCESS(1,"�����ʼ��ɹ�"),
	SEND_FAILED(0,"�����ʼ�ʧ��"),
	SAVE_FAILED(0,"�����ʼ����ѷ�����ʧ��"),
	MONITOR_FAILED(0,"����ռ���ʧ��"),
	INNER_ERROR(0,"ϵͳ����");
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
