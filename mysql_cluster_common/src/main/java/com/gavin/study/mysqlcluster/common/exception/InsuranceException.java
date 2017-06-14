package com.gavin.study.mysqlcluster.common.exception;

import com.gavin.study.mysqlcluster.common.messagecode.IMsgCode;


public class InsuranceException extends Exception {
	private static final long serialVersionUID = 5534682856432097803L;

	/**
	 * 错误码
	 */
	private int errorCode;
	private String errorMsg;

	private Object[] args;

	public InsuranceException() {
		super();
		this.errorCode = IMsgCode.BASE_MSGCODE_ERROR;
	}

	public InsuranceException(String message) {
		super();
		this.errorMsg = message;
	}
	
	public InsuranceException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public InsuranceException(int errorCode, Object[] args) {
		super();
		this.errorCode = errorCode;
		this.args = args;
	}

	/**
	 * <默认构造函数>
	 * 
	 * @param errorCode
	 *            错误码
	 * @param th
	 *            Throwable
	 */
	public InsuranceException(int errorCode, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}

	public InsuranceException(int errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorMsg = message;
		this.errorCode = errorCode;
	}

	public InsuranceException(int errorCode, String message) {
		super(message);
		this.errorMsg = message;
		this.errorCode = errorCode;
	}

	public InsuranceException(int errorCode, Object[] args, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public InsuranceException(int errorCode, Object[] args, String message,
			Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.args = args;
	}

	public InsuranceException(int errorCode, Object[] args, String message) {
		super(message);
		this.errorCode = errorCode;
		this.args = args;
	}

	/**
	 * errorCode
	 * 
	 * @return the errorCode
	 */

	public int getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
