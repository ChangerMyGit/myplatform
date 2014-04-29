package com.oecp.myplatform.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

/**
 * 业务实体bean父类，包括了业务数据标中的必须字段
 * 
 * @author Changer
 * 
 */
@MappedSuperclass
public class StringPKEO implements Serializable {
	private static final long serialVersionUID = 7866017096562639786L;

	private String id;// 业务实体的主键，采用hibernate的UUID的生成策略
	
	@Id
	@Column(name = "pk", length = 200)
	@GeneratedValue(generator = "string16")
	@GenericGenerator(strategy = "org.hibernate.id.UUIDHexGenerator", name = "string16")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if (StringUtils.isEmpty(id)) {
			id = null;
		}
		this.id = id;
	}

}
