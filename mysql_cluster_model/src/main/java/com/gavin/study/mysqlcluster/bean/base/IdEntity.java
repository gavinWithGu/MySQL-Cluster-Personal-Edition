package com.gavin.study.mysqlcluster.bean.base;

import java.io.Serializable;

/**
 * 
 * @author alvin
 */
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
    
	public Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
