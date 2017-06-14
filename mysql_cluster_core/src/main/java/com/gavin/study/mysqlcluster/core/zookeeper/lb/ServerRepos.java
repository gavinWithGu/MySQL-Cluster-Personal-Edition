package com.gavin.study.mysqlcluster.core.zookeeper.lb;

import java.util.ArrayList;
import java.util.List;

public class ServerRepos {
	private static final ServerRepos instance = new ServerRepos();

	private List<String> serviceRepos = new ArrayList<String>();

	private ServerRepos() {
	}

	public static ServerRepos getInstance() {
		return instance;
	}

	public void updateRepos(List<String> newServiceRepos) {
		this.serviceRepos = newServiceRepos;
	}

	public List<String> getServiceRepos() {
		return serviceRepos;
	}

}
