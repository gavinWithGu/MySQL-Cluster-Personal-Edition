ʵ�ֹ��ܣ�
  1. Mysql���Ӹ��ƣ���д����(��д����Ĺ����ɳ���ָ��������mysql proxy��ʵ�ָ��Ӿ�ȷ�Ŀ���)
  2. Mysql�����ͼ�Ⱥ��(master-salve)��zookeeper���ù���
  3. �������Spring MVC+Spring+Mybatits+bootstrap

==========================================================================================
ʵ��ԭ��
1. MySQL���ݿ���Ҫ�������Ӹ��ƣ�������1�����
  Ĭ�Ϲ��������ģ�ɾ�������ǲ������⣬
  ��ѯ�����ǲ����ӿ⣬����ʵ�ֶ��ڶ���ӿ�ĸ��ؾ���

2. zookeeper���ݽṹ��
  /gavin/config/sampleConfig/mysqlclusterĿ¼���������ļ���[masterJdbc.properties, jdbc.properties]
  masterJdbc.propertiesָ����������ӵ�ַ
  jdbc.propertiesָ����Ⱥ��(master-salve)�ĵ�ַ
  jdbc.properties���ݣ�
	#��Ⱥ���ݿ�����
	db.num=3
	jdbc.url.master=10.44.55.143
	#��Ⱥ��Ϣ
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

3. jvm�����ݽṹ��
  zookeeper����(DBClusterPropertyPlaceholder.configs)��map�ṹMap<"shardNum:0", List<DBInfo>>:
    key����������ɣ�(1) shardNumΪ����չ���ܣ�Ŀǰû��ʵ�֣�ָ��mysql��Ƭ�ţ�ĿǰͳһΪshardNum (2) 0ָ��������Դ�����⻹�Ǵӿ⣬1:����,2:�ӿ�
    valueΪkey��Ӧ������Դ��ϸ����б�ÿһ��DBInfo��Ϊһ������Դ��Ϣ��װpojo
    Ѱ���㷨���Ȱ��չ���Ѱ�Ҷ�Ӧ�ķ�Ƭ(shardNum)��Ȼ�����0 or 1ȥѰ�Ҹ÷�Ƭ��������ߴӿ����Ϣ������Ǵӿ⣬�����һ�����㷨(��ѯ�����)��list����һ��datasource����
  spring����·�ɴ������ݽṹ(DynamicDataSource._targetDataSources):Map<sn,Dbcp datasource>:
     keyΪ��Ⱥ�����ݿ��Ψһ��ʾsn
     valueΪ��Ӧ��dbcp����Դ���󣬿��Ի���c3p0��ʵ��
     Ѱ���㷨��determineCurrentLookupKey������threadlocal�л�ȡmaster/slave��־(0 or 1)����DBClusterPropertyPlaceholder.configs��ȡ��Ӧ��sn��
               ���_targetDataSources�в�����sn��Ӧ��datasource�����DBClusterPropertyPlaceholder��ͬ�����е�datsource��_targetDataSources��
	       ���ظ�sn
    
==========================================================================================
����˵����
1. zookeeper_service_parent��zookeeper_stub�����������ļ���zookeeper��
2. mysql_cluster ��ͷ���̣�ʾ�����̲���������Դ����


==========================================================================================
��Ҫ����ʵ��˵����
1. com.gavin.study.mysqlcluster.portal.listener.StartupListener.java��
  web��Ŀ����ʱ����

2. com.gavin.study.mysqlcluster.core.zookeeper.config.listerner.impl.DynamicConfigFileListenerImpl.java
  StartupListener��������zookeeperͬ��jdbc.properties,masterJdbc.properties�����ļ����ݣ����Ҽ���jdbc.properties���ݱ仯

3. /mysql_cluster_portal/src/main/resources/applicationContext-default.xml:
        <!-- ====������ؿ��ƣ�ָ��datsourceΪdynamicDataSource -->
	<bean id="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dynamicDataSource" />
	</bean>
	<!-- ʹ��annotation�������� -->
	<tx:annotation-driven transaction-manager="transactionManager"
		proxy-target-class="true" />
	<!-- ����·�ɲ� -->
	<bean id="dynamicDataSource" class="com.gavin.study.mysqlcluster.core.dbcluster.datasource.DynamicDataSource">
		<!-- ͨ��key-value����ʽ����������Դ -->
		<property name="targetDataSources">
			<map>
			</map>
		</property>
		<property name="defaultTargetDataSource" ref="dataSource" />
	</bean>
	
	<!-- aop���������е�server(������չ)�������е�servic������ע��threadlocal��master-slave���Ĭ��ʵ�� -->
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
  spring aop before advice��Ĭ�Ϲ�����ɾ��Ϊ���⣬��Ϊ�ӿ⼯Ⱥ

5. com.gavin.study.mysqlcluster.core.dbcluster.datasource.DynamicDataSource.java
  spring��̬·�ɴ���ʵ�֣���zookeeperָ��������Դ�н���mysql����

==========================================================================================

����˵����
1. MySQL���ݿ���Ҫ�������Ӹ��ƣ�������1�����

2. jdbc.properties��
  1. ����datasourceʱ��snһ��ҪΨһ
  2. ɾ��datasourceʱ��snһ��Ҫ����ǰ�Ĳ�ͬ��Ҫ��Ȼ���ǻ�·�ɵ��ظ���sn��datasource��
  3. db.num��Ŀ�ͼ�Ⱥ�е����ݿ�����һ��Ҫһ��
==========================================================================================
--���Ľ���
  1. ��ѯ�㷨���Ľ���û�п���int���ֵ�����⣬û�п���Ȩ��
  2. û������mysql�ֿ�ֱ���ƣ�ÿһ��slave���ݿⶼ������ȫ������