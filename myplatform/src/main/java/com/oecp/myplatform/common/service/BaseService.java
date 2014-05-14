package com.oecp.myplatform.common.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oecp.myplatform.common.dao.BaseHibernateDao;

/**
 * Serverice 父类基于基本的CRUD操作
 * @author ASUS
 *
 */
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
	
	public void deleteByCondition(String whereSql,List params){
		StringBuffer sql = new StringBuffer(100);
		sql.append("delete from ").append(getEntityClass().getName()).append(" o where 1=1 ");
		sql.append(whereSql);
		Query query = getDao().getHibernateSession().createQuery(sql.toString());
		setQueryParams(params, query);
		query.executeUpdate();
	}
	
	public <T> T find(Serializable entityId) {
		return (T) getDao().find(getEntityClass(), entityId);
	}
	
	public boolean isExitedByCondition(String whereSql,List params){
		long count = getCountByCondition(whereSql,params);
		return count > 0;
	}
	
	public Long getCountByCondition(String whereSql,List params){
		StringBuffer sql = new StringBuffer(100);
		sql.append("select count(o) from ").append(getEntityClass().getName()).append(" o where 1=1 ");
		sql.append(whereSql);
		Query query = getDao().getHibernateSession().createQuery(sql.toString());
		setQueryParams(params, query);
		return (Long) query.uniqueResult();
	}
	
	// 默认使用占位符查询
	public <T> List<T> findByCondition(String whereSql,List params){
		return findByCondition(whereSql,params,-1,-1);
	}
	
	// 分页方法
	public <T> List<T> findByCondition(String whereSql, List params,
			int startRow, int rows) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("select o from ").append(getEntityClass().getName()).append(" o where 1=1 ");
		sql.append(whereSql);
		Query query = getDao().getHibernateSession().createQuery(sql.toString());
		setQueryParams(params, query);
		if (startRow >= 0)
			query.setFirstResult(startRow);
		if (rows > 0)
			query.setMaxResults(rows);
		return query.list();
	}
	
	// 参数赋值
	private void setQueryParams(Object params, Query query) {
		if (params != null) {
			if (params instanceof List) {
				List queryParams = (List) params;
				for (int i = 0; i < queryParams.size(); i++) {
					query.setParameter(i, queryParams.get(i));
				}
			} else if (params instanceof Map) {
				Map queryParams = (Map) params;
				Iterator<String> iterator = queryParams.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					query.setParameter(key, queryParams.get(key));
				}
			}
		}
	}
}
