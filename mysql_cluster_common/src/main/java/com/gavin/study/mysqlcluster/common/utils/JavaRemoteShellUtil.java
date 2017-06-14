package com.gavin.study.mysqlcluster.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import com.gavin.study.mysqlcluster.common.utils.log.LogUtils;


import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 * 在java程序中远程调用shell脚本
 * 
 * @author Gavin
 */
public class JavaRemoteShellUtil {

	private static final String LOG_MOUDLE = "JavaRemoteShellUtil";

	private Connection conn;
	private String ipAddr;
	private String charset = Charset.defaultCharset().toString();
	private String userName;
	private String password;

	private static final int TIME_OUT = 1000 * 5 * 60;

	public JavaRemoteShellUtil(String ipAddr, String userName, String password,
			String charset) {
		this.ipAddr = ipAddr;
		this.userName = userName;
		this.password = password;
		if (charset != null) {
			this.charset = charset;
		}
	}

	public boolean login() throws IOException {
		conn = new Connection(ipAddr);
		conn.connect(); // 连接
		return conn.authenticateWithPassword(userName, password); // 认证
	}

	public int exec(String cmds) {
		InputStream stdOut = null;
		InputStream stdErr = null;
		String outStr = "";
		String outErr = "";
		int result = -1;
		try {
			if (this.login()) {
				Session session = conn.openSession(); // 打开一个会话
				session.execCommand(cmds);

				stdOut = new StreamGobbler(session.getStdout());
				outStr = processStdout(stdOut, charset);

				stdErr = new StreamGobbler(session.getStderr());
				outErr = processStdout(stdErr, charset);

				session.waitForCondition(ChannelCondition.EXIT_STATUS, TIME_OUT);

				LogUtils.getInstance().debugSystem(LOG_MOUDLE,
						"Output message", "Command", cmds, "Stdout", outStr,
						"Stderr", outErr);

				result = session.getExitStatus();

				LogUtils.getInstance().infoSystem(LOG_MOUDLE, "Exit status",
						"result", result);

				session.close();
				conn.close();
			}
		} catch (IOException e1) {
			LogUtils.getInstance().errorSystem(LOG_MOUDLE, e1);
		}
		return result;
	}

	public String processStdout(InputStream in, String charset) {

		byte[] buf = new byte[1024];
		StringBuffer sb = new StringBuffer();
		try {
			while (in.read(buf) != -1) {
				sb.append(new String(buf, charset));
			}
		} catch (IOException e) {
			LogUtils.getInstance().errorSystem(LOG_MOUDLE, e);
		}
		return sb.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JavaRemoteShellUtil tool = new JavaRemoteShellUtil("10.44.53.126",
				"root", "123456", "utf-8");

		int result = tool.exec("service mysqld restart");
		System.out.print(result);

	}
}