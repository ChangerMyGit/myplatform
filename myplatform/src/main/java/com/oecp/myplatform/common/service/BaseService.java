package com.oecp.myplatform.common.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.MappedSuperclass;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oecp.myplatform.common.dao.BaseHibernateDao;

@Transactional(propagation=Propagation.REQUIRED)
@MappedSuperclass
public abstract class BaseService {
    
	public abstract BaseHibernateDao getDao();
	public abstract Class getEntityClass();
	
	public <T> void create(T entity){
		getDao().create(entity);
	} 
	
	public <T> void createBatch(List<T> entitys) {
		for (T entity : entitys) {
			create(entity);
		}
	}

	public <T> void update(T entity) {
		getDao().update(entity);
	}

	public <T> void delete(Serializable entityid) {
		getDao().delete(getEntityClass(),entityid);
	}

	public <T> void delete(Serializable[] entityids) {
		for (Serializable id : entityids) {
			delete(id);
		}
	}
	
	public <T> T find(Serializable entityId) {
		return (T) getDao().find(getEntityClass(), entityId);
	}
}
