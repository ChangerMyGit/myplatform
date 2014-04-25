package com.oecp.myplatform.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.MappedSuperclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Dao 父类提供基本的操作方法
 * 
 * @author Changer
 *
 */
@MappedSuperclass
public abstract class BaseHibernateDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getHibernateSession() {
		return sessionFactory.getCurrentSession();
	}

	public void clear() {
		getHibernateSession().clear();
	}

	public <T> void create(T entity) {
		getHibernateSession().save(entity);
	}

	public <T> void createBatch(List<T> entitys) {
		for (T entity : entitys) {
			create(entity);
		}
	}

	public <T> void update(T entity) {
		getHibernateSession().merge(entity);
	}

	public <T> void delete(Class<T> entityClass,Serializable entityid) {
		getHibernateSession().delete(getHibernateSession().get(entityClass, entityid));
	}

	public <T> void delete(Class<T> entityClass,Serializable[] entityids) {
		for (Serializable id : entityids) {
			delete(entityClass, id);
		}
	}
	
	public <T> T find(Class<T> entityClass, Serializable entityId) {
		return (T) getHibernateSession().get(entityClass, entityId);
	}

}
