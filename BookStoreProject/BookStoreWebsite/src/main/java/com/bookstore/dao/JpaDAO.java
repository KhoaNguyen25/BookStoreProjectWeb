package com.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDAO<E> {
	protected static EntityManagerFactory entityManagerFactory;
	
	static {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
	}
	public JpaDAO() {}

	public E create(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);

		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}

	public E update(E entity) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		entity = entityManager.merge(entity);

		entityManager.getTransaction().commit();
		entityManager.close();
		return entity;
	}

	public E find(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		E entity = entityManager.find(type, id);
		if (entity != null) {
			entityManager.refresh(entity);
		}
		entityManager.close();
		return entity;
	}
	
	public void delete(Class<E> type, Object id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public List<E> findWidthNameQuery(String nameQuery){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(nameQuery);
		List<E> result = query.getResultList();
		
		entityManager.close();
		return result;
	}
	
	public List<E> findWidthNameQuery(String nameQuery,int firstResult, int maxResult){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createNamedQuery(nameQuery);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List<E> result = query.getResultList();
		
		entityManager.close();
		return result;
	}
	
	public List<E> findWidthNameQuery(String nameQuery, String paramterName, Object paramterValue){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(nameQuery);
		query.setParameter(paramterName, paramterValue);
		List<E> result = query.getResultList();
		
		entityManager.close();
		return result;
	}
	
	public List<E> findWidthNameQuery(String nameQuery, Map<String, Object> paramteres){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(nameQuery);
		Set<Entry<String, Object>> entrySet = paramteres.entrySet();
		for(Entry<String, Object> entry : entrySet) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> result = query.getResultList();
		
		entityManager.close();
		return result;
	}
	
	public long countWidthNameQuery(String nameQuery) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(nameQuery);
		long result = (long) query.getSingleResult();
		
		entityManager.close();
		
		return result;
		
	}
	public long countWidthByCategory(String nameQuery, String paramName, Object paramValue) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createNamedQuery(nameQuery);
		query.setParameter(paramName, paramValue);
		long result = (long) query.getSingleResult();
		entityManager.close();
		
		return result;
	}
	public void close() {
		if(entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

}
