package com.gavin.study.mysqlcluster.common.base.dao.page;

/**
 * 用户保存SearchBean解析完后用于建立Expression的相关信息
 * 
 * @author Jacky Wang
 */
public class ExpressionBean
{
	/**
	 * 字段名称
	 */
	private String	name;
	/**
	 * 字段值
	 */
	private Object	value;
	/**
	 * 条件关系，包含：=, <, >, <=, >=, like, in
	 */
	private String	relation;

	public ExpressionBean(String name, String relation, Object value)
	{
		super();
		this.name = name;
		this.relation = relation;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Object getValue()
	{
		return value;
	}

	public void setValue(Object value)
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
