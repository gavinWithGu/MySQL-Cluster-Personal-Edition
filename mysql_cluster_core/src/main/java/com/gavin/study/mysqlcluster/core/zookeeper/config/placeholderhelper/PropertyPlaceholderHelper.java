package com.gavin.study.mysqlcluster.core.zookeeper.config.placeholderhelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class PropertyPlaceholderHelper {

	public Properties parseFromString(String value) {
		Properties props = new Properties();
		if (!StringUtils.isEmpty(value))
			try {
				props.load(new StringReader(value));
			} catch (IOException e) {
				e.printStackTrace();
			}
		return props;
	}
}
