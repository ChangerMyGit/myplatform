package com.oecp.myplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oecp.myplatform.common.dao.BaseHibernateDao;
import com.oecp.myplatform.common.service.BaseService;
import com.oecp.myplatform.dao.UserDao;
import com.oecp.myplatform.model.User;

@Service
public class UserService extends BaseService{
	
	@Autowired
	private UserDao userDao;
	
    public void testTx(User u1 , User u2){
    	userDao.create(u1);
    	if(true)throw new RuntimeException("测试事务异常");
    	userDao.create(u2);
    }

	@Override
	public BaseHibernateDao getDao() {
		return userDao;
	}

	@Override
	public Class getEntityClass() {
		return User.class;
	}
}
