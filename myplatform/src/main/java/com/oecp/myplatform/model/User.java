package com.oecp.myplatform.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import com.oecp.myplatform.common.model.StringPKEO;


@Entity
@Table(name = "OECP_USER")
public class User extends StringPKEO {
	private static final long serialVersionUID = -3715537577598691466L;
	
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
