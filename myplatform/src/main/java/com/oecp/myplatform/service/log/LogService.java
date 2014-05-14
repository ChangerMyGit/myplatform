package com.oecp.myplatform.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oecp.myplatform.common.dao.BaseHibernateDao;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.dao.log.LogDao;
import com.oecp.myplatform.model.log.SysLog;

@Service
public class LogService extends BaseService {

	@Autowired
	private LogDao logdao;

	public BaseHibernateDao getDao() {
		return logdao;
	}

	public Class getEntityClass() {
		return SysLog.class;
	}

}
