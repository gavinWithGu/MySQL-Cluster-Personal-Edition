实现功能：
  1. Mysql主从复制，读写分离(读写分离的功能由程序指定，而非mysql proxy，实现更加精确的控制)
  2. Mysql管理库和集群库(master-salve)由zookeeper配置管理
  3. 技术框架Spring MVC+Spring+Mybatits+bootstrap

==========================================================================================
实现原理：
1. MySQL数据库需要配置主从复制，可以是1主多从
  默认规则：增，改，删操作都是操作主库，
  查询操作是操作从库，并且实现对于多个从库的负载均衡

2. zookeeper数据结构：
  /gavin/config/sampleConfig/mysqlcluster目录下有两个文件：[masterJdbc.properties, jdbc.properties]
  masterJdbc.properties指定管理库连接地址
  jdbc.properties指定集群库(master-salve)的地址
  jdbc.properties内容：
	#集群数据库数量
	db.num=3
	jdbc.url.master=10.44.55.143
	#集群信息
	jdbc.url.0=jdbc:mysql://10.44.55.143:3306/study_mysql_shard1?useUnicode=true&amp;characterEncoding=utf-8
	jdbc.username.0=root
	jdbc.password.0=123456
	jdbc.sn.0=aaaa
	jdbc.driverClassName.0=com.mysql.jdbc.Driver

	jdbc.url.1=jdbc:mysql://10.44.55.96:3306/study_mysql_shard1?useUnicode=true&amp;characterEncoding=utf-8
	jdbc.username.1=root
	jdbc.password.1=123456
	jdbc.driverClassName.1=com.mysql.jdbc.Driver
	jdbc.sn.1=bbbb

	jdbc.url.2=jdbc:mysql://10.44.55.35:3306/study_mysql_shard1?useUnicode=true&amp;characterEncoding=utf-8
	jdbc.username.2=root
	jdbc.password.2=123456
	jdbc.driverClassName.2=com.mysql.jdbc.Driver
	jdbc.sn.2=dddd
	......

3. jvm中数据结构：
  zookeeper监听(DBClusterPropertyPlaceholder.configs)的map结构Map<"shardNum:0", List<DBInfo>>:
    key由两部分组成：(1) shardNum为待扩展功能，目前没有实现，指定mysql分片号，目前统一为shardNum (2) 0指定该数据源是主库还是从库，1:主库,2:从库
    value为key对应的数据源详细情况列表，每一个DBInfo即为一个数据源信息封装pojo
    寻找算法：先按照规则寻找对应的分片(shardNum)，然后根据0 or 1去寻找该分片上主库或者从库的信息，如果是从库，则根据一定的算法(轮询，随机)从list中找一个datasource出来
  spring数据路由代理数据结构(DynamicDataSource._targetDataSources):Map<sn,Dbcp datasource>:
     key为集群中数据库的唯一表示sn
     value为对应的dbcp数据源对象，可以换成c3p0等实现
     寻找算法：determineCurrentLookupKey方法从threadlocal中获取master/slave标志(0 or 1)，从DBClusterPropertyPlaceholder.configs获取对应的sn，
               如果_targetDataSources中不存在sn对应的datasource，则从DBClusterPropertyPlaceholder中同步所有的datsource到_targetDataSources中
	       返回该sn
    
==========================================================================================
工程说明：
1. zookeeper_service_parent，zookeeper_stub：发布配置文件到zookeeper上
2. mysql_cluster 开头工程，示例工程操作多数据源工程


==========================================================================================
主要代码实现说明：
1. com.gavin.study.mysqlcluster.portal.listener.StartupListener.java：
  web项目启动时监听

2. com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.impl.DynamicConfigFileListenerImpl.java
  StartupListener触发，向zookeeper同步jdbc.properties,masterJdbc.properties两个文件内容，并且监听jdbc.properties内容变化

3. /mysql_cluster_portal/src/main/resources/applicationContext-default.xml:
        <!-- ====事务相关控制，指定datsource为dynamicDataSource -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dynamicDataSource" />
	</bean>
	<!-- 使用annotation定义事务 -->
	<tx:annotation-driven transaction-manager="transactionManager"
		proxy-target-class="true" />
	<!-- 数据路由层 -->
	<bean id="dynamicDataSource" class="com.gavin.study.mysqlcluster.core.dbcluster.datasource.DynamicDataSource">
		<!-- 通过key-value的形式来关联数据源 -->
		<property name="targetDataSources">
			<map>
			</map>
		</property>
		<property name="defaultTargetDataSource" ref="dataSource" />
	</bean>
	
	<!-- aop，横切所有的server(可以扩展)，对所有的servic操作，注入threadlocal的master-slave库的默认实现 -->
	<bean id="serviceAutoProxyCreator" class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <property name="beanNames">
            <list>
                <value>*ServiceImpl</value>
                 <value>*Service</value>
            </list>
        </property>
        <property name="interceptorNames">
            <list>
                <value>beforeServiceAdvice</value>
            </list>
        </property>
        <property name="proxyTargetClass" value="true" />
	</bean>

4. com.gavin.study.mysqlcluster.common.spring.aop.BeforeServiceAdvice
  spring aop before advice：默认规则：增删改为主库，否为从库集群

5. com.gavin.study.mysqlcluster.core.dbcluster.datasource.DynamicDataSource.java
  spring动态路由代理实现，从zookeeper指定的数据源中进行mysql操作

==========================================================================================

配置说明：
1. MySQL数据库需要配置主从复制，可以是1主多从

2. jdbc.properties：
  1. 增加datasource时，sn一定要唯一
  2. 删除datasource时，sn一定要和以前的不同，要不然还是会路由到重复的sn的datasource上
  3. db.num数目和集群中的数据库数量一定要一致
==========================================================================================
--待改进：
  1. 轮询算法待改进：没有考虑int最大值的问题，没有考虑权重
  2. 没有引入mysql分库分表机制，每一个slave数据库都必须是全量数据