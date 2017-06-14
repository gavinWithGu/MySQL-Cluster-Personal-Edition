package com.gavin.study.mysqlcluster.common.messagecode;

public interface IMsgCode {

	public static final int MSGCODE = 0;

	/** ******************** success ********************** */
	public static final int BASE_MSGCODE_SUCCESS = MSGCODE + 1000;

	public static final int MSGCODE_SUCCESS_ADD = BASE_MSGCODE_SUCCESS + 1;

	public static final int MSGCODE_SUCCESS_DELETE = BASE_MSGCODE_SUCCESS + 2;

	public static final int MSGCODE_SUCCESS_UPDATE = BASE_MSGCODE_SUCCESS + 3;

	public static final int MSGCODE_SUCCESS_QUERY = BASE_MSGCODE_SUCCESS + 4;

	/** ******************** error ********************** */

	/** ******************** common **************** */
	public static final int BASE_MSGCODE_ERROR = MSGCODE + 20000;

	/** ******************** Job manager error ************* */
	public static final int JOB_MANAGER_ERROR = BASE_MSGCODE_ERROR + 100;
	public static final int SEND_SUBMIT_SPARK_COMMAND_ERROR = JOB_MANAGER_ERROR + 1; // 
	
	public static final int PAGE_FIND_JOBINFO_ERROR = JOB_MANAGER_ERROR + 2; // 
}
