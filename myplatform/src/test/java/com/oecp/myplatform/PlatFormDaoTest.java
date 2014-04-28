package com.oecp.myplatform;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oecp.myplatform.dao.UserDao;
import com.oecp.myplatform.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/root-context*.xml","/servlet-context.xml"})
public class PlatFormDaoTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	protected ApplicationContext ctx;
	
	
    @Test
	public void saveTest(){
    	UserDao userDao = (UserDao) ctx.getBean("userDao");
    	User user = new User();
    	user.setName("changer");
    	user.setCreater("changer");
    	user.setUpdater("changer");
    	userDao.create(user);
    }
}
