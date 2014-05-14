package com.oecp.myplatform.model.log;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.oecp.myplatform.common.enums.LogType;
import com.oecp.myplatform.common.model.BaseEO;

/**
 * 系统日志
 * 
 * @author Changer
 *
 */
@Table(name = "sys_log")
@Entity
public class SysLog extends BaseEO {
	private static final long serialVersionUID = 1130768567358545545L;
	private String remoteAddr;
	private String requertUrl;
	private String method;
	private String params;
	private String exceptionMessage;
	private String userAgent;
    private LogType type;
    @Transient
    private String typeValue;
	
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getRequertUrl() {
		return requertUrl;
	}

	public void setRequertUrl(String requertUrl) {
		this.requertUrl = requertUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	@Enumerated(EnumType.ORDINAL)
	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public String getTypeValue() {
		if (getType().equals(LogType.TYPE_ACCESS))
			return "操作日志";
		else
			return "异常日志";
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

}
