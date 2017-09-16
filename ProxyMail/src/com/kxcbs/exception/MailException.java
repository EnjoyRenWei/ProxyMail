package com.kxcbs.exception;

import com.kxcbs.enums.MailEnum;

/**
 * 自定义异常
 * @author renwei
 *
 */
public class MailException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MailException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public MailException(MailEnum me) {
		super(me.getStateInfo());
		// TODO Auto-generated constructor stub
	}

}
