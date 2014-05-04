package com.oecp.myplatform.common.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class BaseEO extends StringPKEO {
	private static final long serialVersionUID = 6692445484291756337L;

	private String creater;
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date created;
	private String updater;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date updated;

	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}

	/**
	 * @param creater
	 *            the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}

	/**
	 * @return the updater
	 */
	public String getUpdater() {
		return updater;
	}

	/**
	 * @param updater
	 *            the updater to set
	 */
	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
