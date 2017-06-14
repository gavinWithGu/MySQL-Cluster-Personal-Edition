package com.gavin.study.mysqlcluster.core.zookeeper.wrapper;

import com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.IDynamicConfigFileListener;
import com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.impl.DynamicConfigFileListenerImpl;

public class DynamicConfigFileListenerWrapper {
	private static IDynamicConfigFileListener listener;

	private static final DynamicConfigFileListenerWrapper instance = new DynamicConfigFileListenerWrapper();

	static {
		listener = new DynamicConfigFileListenerImpl();
	}

	private DynamicConfigFileListenerWrapper() {
	}

	public static DynamicConfigFileListenerWrapper getInstance() {
		return instance;
	}

	public IDynamicConfigFileListener getListener() {
		return listener;
	}

}
