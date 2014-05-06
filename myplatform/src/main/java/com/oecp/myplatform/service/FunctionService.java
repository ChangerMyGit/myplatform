package com.oecp.myplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oecp.myplatform.common.dao.BaseHibernateDao;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.dao.FunctionDao;
import com.oecp.myplatform.model.FunctionNode;

@Service
public class FunctionService extends BaseService {

	@Autowired
	private FunctionDao functionDao;
	
	@Override
	public BaseHibernateDao getDao() {
		return functionDao;
	}

	@Override
	public Class getEntityClass() {
		return FunctionNode.class;
	}

}
