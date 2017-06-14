/*    
 * Copyright (c) 2011 CoolCloudz, Inc.
 * All right reserved.
 *
 * 文件名：      SearchBean.java
 * 作者:     Jacky Wang
 * 创建日期： 2011-1-30 下午03:22:57
 * 版本：           
 *
 */
package com.gavin.study.mysqlcluster.common.base.dao.page;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用来保存自定义查询的相关数据。
 * <p>
 */
public class SearchBean
{

	/**
	 * 字段名称
	 */
	private String	name;
	/**
	 * 字段值
	 */
	private String	value;
	/**
	 * 条件关系，包含：=, <, >, <=, >=, like, in, isNull, isNotNull, !=
	 */
	private String	relation;

	/**
	 * @param name
	 * @param realtion
	 * @param type
	 * @param value
	 */
	public SearchBean(String name, String value, String relation)
	{
		this.name = name;
		this.relation = relation;
		this.value = value;
	}

	public String toString()
	{
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE).append("name", name).append("value", value).append("realtion", relation)
				.toString();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getRelation()
	{
		return relation;
	}

	public void setRelation(String relation)
	{
		this.relation = relation;
	}
}