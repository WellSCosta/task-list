package com.wellscosta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wellscosta.config.JpaUtil;
import com.wellscosta.model.entities.TaskList;

public class TaskListDao implements ITaskListDao{
	
	EntityManagerFactory emf;
	EntityManager em;

	@Override
	public TaskList create(TaskList list) {
		openConnection();
		
		em.persist(list);
		em.getTransaction().commit();
		
		closeConnection();
		
		return list;
	}

	@Override
	public TaskList update(TaskList list) {
		openConnection();
		
		list = em.merge(list);
		em.getTransaction().commit();
		
		closeConnection();
		
		return list;
	}

	@Override
	public void delete(TaskList list) {
		openConnection();
		
		list = em.merge(list);
		em.remove(list);
		em.getTransaction().commit();
		
		closeConnection();
	}

	@Override
	public TaskList search(Long id) {
		openConnection();
		
		TaskList list = em.find(TaskList.class, id);
		em.getTransaction().commit();
		
		closeConnection();
		return list;
	}

	@Override
	public List<TaskList> searchAll() {
		openConnection();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<TaskList> cq = cb.createQuery(TaskList.class);
		
		Root<TaskList> root = cq.from(TaskList.class);
		
		cq.select(root);
		
		TypedQuery<TaskList> tq = em.createQuery(cq);
		
		List<TaskList> list = tq.getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return list;
	}
	
	public void openConnection() {
		emf = JpaUtil.getEntityManagerFactory();
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	private void closeConnection() {
		em.close();
		emf.close();
	}
}
