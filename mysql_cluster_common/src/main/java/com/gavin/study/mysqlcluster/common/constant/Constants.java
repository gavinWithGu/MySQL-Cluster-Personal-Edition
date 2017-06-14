package com.gavin.study.mysqlcluster.common.constant;

/**
 * @ClassName Constants
 * @Description 常量定义类
 * @author Alvin
 * @date Nov 7, 2011 1:16:09 PM
 * @version V 1.0
 */

public interface Constants {
	/**
	 * 表示','字符.
	 */
	char					CHAR_COMMA				= ',';
	/**
	 * 表示'.'字符.
	 */
	char					CHAR_DOT				= '.';
	/**
	 * 表示'/'字符.
	 */
	char					CHAR_FORWARDSLASH		= '/';
	/**
	 * 表示'\\'字符.
	 */
	char					CHAR_BACKSLASH			= '\\';
	/**
	 * 表示'\'字符.
	 */
	char					CHAR_POUND				= '#';
	/**
	 * 表示'-'字符.
	 */
	char					CHAR_DASH				= '-';
	/**
	 * 表示'_'字符.
	 */
	char					CHAR_UNDERLINE			= '_';
	/**
	 * 表示'&'字符.
	 */
	char					CHAR_AND				= '&';
	/**
	 * 表示'*'字符.
	 */
	char					CHAR_STAR				= '*';
	/**
	 * 表示'@'字符.
	 */
	char					CHAR_AT					= '@';
	/**
	 * 表示'$'字符.
	 */
	char					CHAR_DOLLAR				= '$';
	/**
	 * 表示"$"字符.
	 */
	char					CHAR_PERCENT			= '%';
	/**
	 * 表示","字符串.
	 */
	String					STRING_COMMA			= ",";
	/**
	 * 表示"."字符串.
	 */
	String					STRING_DOT				= ".";
	/**
	 * 表示'/'字符.
	 */
	String					STRING_FORWARDSLASH		= "/";
	/**
	 * 表示"\"字符.
	 */
	String					STRING_BACKSLASH		= "\\";
	/**
	 * 表示"#"字符.
	 */
	String					STRING_POUND1			= "#";
	String					STRING_POUND2			= "##";
	/**
	 * 表示"999999999999"字符.
	 */
	String					STRING_ROOTTYPE			= "999999999999";
	/**
	 * 表示"-"字符.
	 */
	String					STRING_DASH				= "-";
	/**
	 * 表示"_"字符.
	 */
	String					STRING_UNDERLINE		= "_";
	/**
	 * 表示"&"字符.
	 */
	String					STRING_AND				= "&";
	/**
	 * 表示"*"字符.
	 */
	String					STRING_STAR				= "*";
	/**
	 * 表示"@"字符.
	 */
	String					STRING_AT				= "@";
	/**
	 * 表示"$"字符.
	 */
	String					STRING_DOLLAR			= "$";
	/**
	 * 表示"$"字符.
	 */
	String					STRING_PERCENT			= "%";
	/**
	 * 表示"/"字符.
	 */
	String					STRING_FORWARD_SLASH	= "/";
	/**
	 * 表示"\"字符.
	 */
	String					STRING_BACK_SLASH		= "\\";
	/**
	 * 表示"--"字符.
	 */
	String					STRING_DOUBLE_DASH		= "--";
	/**
	 * 表示";"字符.
	 */
	String					STRING_SEMICOLON		= ";";

	/**
	 * ACTION类的跳转参数
	 */
	String					STRING_ACTION			= "action";
	/**
	 * 进入查询列表页面参数
	 */
	String					STRING_LIST				= "list";
	/**
	 * 进入查询列表页面参数,不分页方法
	 */
	String					STRING_LIST_ALL			= "list_all";
	/**
	 * 进入查询主页面参数
	 */
	String					STRING_CENTER			= "center";
	/**
	 * 执行查询的参数
	 */
	String					STRING_QUERY			= "query";

	/**
	 * 执行登录的参数
	 */
	String					STRING_LOGIN			= "login";

	/**
	 * 执行左页面的参数
	 */
	String					STRING_LEFT				= "left";
	/**
	 * 进入增加页面的参数
	 */
	String					STRING_ADD				= "add";
	/**
	 * 执行增加的参数
	 */
	String					STRING_ADD_DO			= "add_do";
	/**
	 * 进入修改页面的参数
	 */
	String					STRING_MODI				= "modi";
	/**
	 * 进入索引页面的参数
	 */
	String					STRING_INDEX			= "index";
	/**
	 * 执行修改的参数
	 */
	String					STRING_MODI_DO			= "modi_do";

	/**
	 * 进入详细信息查看页面的参数
	 */
	String					STRING_DETAIL			= "detail";

	/**
	 * 删除参数
	 */
	String					STRING_DEL				= "del";

	/**
	 * 得到参数
	 */
	String					STRING_PARAM			= "param";
	/**
	 * 得到表列
	 */
	String					STRING_TABLE			= "table";
	/**
	 * 删除参数
	 */

	String					STRING_FALSE			= "false";
	/**
	 * 
	 */
	String					STRING_WAY				= "goWayFlag";

	String					SUCCESS					= "success";

	String					FAILURE					= "failure";

	/**
	 * 每页显示条数常量
	 */
	String					STRING_INTPAGE			= "intPage";

	/**
	 * 用户认证信息
	 */
	String					STRING_AUTH				= "authorization";

	/**
	 * 失败页面跳转
	 */
	String					STRING_FAILURE			= "failure";
	String					STRING_FAILSE			= "false";
	String					STRING_GOWAYFLAG		= "goWayFlag";
	String					STRING_PAGEINFO			= "pageInfo";
	String					STRING_PAGELIST			= "pageList";
	/**
	 * 编码方式常量。
	 */
	String					ENCODING_GBK			= "GBK";
	String					ENCODING_UTF8			= "UTF-8";
	/**
	 * 数据库类型常量。
	 */
	String					DATABASE_DB2			= "DB2";
	String					DATABASE_MSSQL			= "MSSQL";
	String					DATABASE_SYBASE			= "SYBASE";
	String					DATABASE_ORACLE			= "ORACLE";
	String					DATABASE_INFORMIX		= "INFORMIX";
	String					DATABASE_HIBERNATE		= "HIBERNATE";
	String					DATABASE_DERBY			= "DERBY";

	/**
	 * Flash图标色彩数组
	 */
	public static String[]	CHART_COLORS			= { "AFD8F8", "F6BD0F", "8BBA00", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6",
			"9D080D", "A186BE", "1EBE38"			};

	String SESSION_KEY_LOGIN_USER = "loginUser";
	
	String SESSION_KEY_LOGIN_TIME = "loginTime";
	
	String SESSION_KEY_LOGIN_USER_NAME = "loginUserName";
	
	String SESSION_KEY_LOGIN_USER_TYPE = "loginUserType";
	
	String SESSION_KEY_SKIN = "skin";
	
	String USER_TYPE_EC = "ec";
	
	String USER_TYPE_SYS = "sysc";
	
	String SYS_DEFAULT_LOGIN_PWD = "111111";
}
