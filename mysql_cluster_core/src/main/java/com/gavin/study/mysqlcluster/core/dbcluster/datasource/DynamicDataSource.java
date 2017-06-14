package com.gavin.study.mysqlcluster.core.dbcluster.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.gavin.study.mysqlcluster.common.thread.DBContextHolder;
import com.gavin.study.mysqlcluster.core.zookeeper.config.placeholderhelper.DBClusterPropertyPlaceholder;
import com.gavin.study.mysqlcluster.core.zookeeper.lb.LBStrageyFactory;
import com.gavin.study.mysqlcluster.core.zookeeper.lb.LBStrategy;
import com.gavin.study.mysqlcluster.core.zookeeper.pojo.DBInfo;

public class DynamicDataSource extends AbstractRoutingDataSource {
	private Map<Object, Object> _targetDataSources;

	private static final String DB_MASTER = "1"; // mysql 集群中的主库标志
	private static final String DB_MANAGER = "datasource"; // 中央管理库标志

	/**
	 * 根据masterflag，计算得到sn:目前采用轮询的算法 TODO:待扩展
	 * 
	 * @param masterFlag
	 * @return
	 */
	public String getDataSourceSN(String masterFlag) {
		try {
			List<DBInfo> content = DBClusterPropertyPlaceholder.getInstance()
					.getContent("shardNum:" + masterFlag); //TODO:前缀目前先写固定的shardNum，后续可以根据分库分片的号码进行动态路由

			DBInfo info;
			if (DB_MASTER.equals(masterFlag)) {
				info = content.get(0);
			} else {
				info = (DBInfo) LBStrageyFactory.getInstance()
						.getLBStrategy(LBStrategy.ROUND_ROBIN, content)
						.pickup();
			}
			return info.getSn();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @describe 查询serverId对应的数据源记录
	 * @return
	 */
	public List<Map<String, String>> getDataSourceMap() {

		List<Map<String, String>> result = new ArrayList<Map<String, String>>();

		HashMap<String, String> map = null;
		try {
//			List<DBInfo> content = DBClusterPropertyPlaceholder.getInstance()
//					.getContent("shardNum:" + masterFlag);//TODO:前缀目前先写固定的shardNum，后续可以根据分库分片的号码进行动态路由

			for (String key : DBClusterPropertyPlaceholder.getInstance()
					.getAllConfigs().keySet()) {
				
				List<DBInfo> content =DBClusterPropertyPlaceholder.getInstance().getContent(key);
				
				for (DBInfo info : content) {
					//if (!this._targetDataSources.containsKey(info.getSn())) {
						map = new HashMap<String, String>();
						map.put("DBS_URL", info.getUrl());
						map.put("DBS_UserName", info.getUserName());
						map.put("DBS_Password", info.getPassword());
						map.put("DBS_SN", info.getSn());
					//}
					result.add(map);
				}

			};
		} catch (Exception e) {
		}
		return result;
	}

	//同步方法
	private void createSource(String masterFlag) {
		synchronized (this) {
			List<Map<String, String>> dataSourceMap = this
					.getDataSourceMap();
			
			this._targetDataSources.clear();
			
			for (Map<String, String> map : dataSourceMap) {

				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName("com.mysql.jdbc.Driver");
				ds.setUrl(map.get("DBS_URL"));
				ds.setUsername(map.get("DBS_UserName"));
				ds.setPassword(map.get("DBS_Password"));

				this.addTargetDataSource(map.get("DBS_SN"), ds);
			}
		}
	}

	/**
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 * @describe 数据源为空或者为0时，自动切换至默认数据源，即在配置文件中定义的dataSource数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		String masterFlag = DBContextHolder.getDBType();
		if (masterFlag == null) {
			return DB_MANAGER; // 中央管理库标志
		} else {
			String sn = getDataSourceSN(masterFlag);
			this.selectDataSource(masterFlag,sn);
			return sn;
		}
	}

	public void setTargetDataSources(Map targetDataSources) {
		this._targetDataSources = targetDataSources;
		super.setTargetDataSources(this._targetDataSources);
		afterPropertiesSet();
	}

	private void addTargetDataSource(String key, DataSource dataSource) {
		this._targetDataSources.put(key, dataSource);
		this.setTargetDataSources(this._targetDataSources);
	}

	/**
	 * @param masterFlag
	 * @describe 数据源存在时不做处理，不存在时创建新的数据源链接，并将新数据链接添加至缓存
	 */
	private void selectDataSource(String masterFlag,String sn) {
		if (DB_MANAGER.equals(masterFlag)) {
			DBContextHolder.setDBType(DB_MANAGER);
			return;
		}
		
		Object obj = this._targetDataSources.get(sn);
		if (obj != null) { // 能够在已有的_targetDataSources找到数据
			return;
		} else { // 不能够在已有的_targetDataSources找到数据，需要增量生成一份数据
			this.createSource(masterFlag);

		}
	}
}
