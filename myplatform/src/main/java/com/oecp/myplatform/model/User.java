package com.oecp.myplatform.model;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.oecp.myplatform.common.model.BaseEO;

@Entity
@Table(name = "OECP_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class User extends BaseEO {
	private static final long serialVersionUID = -3715537577598691466L;
	
	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
