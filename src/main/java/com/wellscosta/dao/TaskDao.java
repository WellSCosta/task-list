package com.wellscosta.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.wellscosta.config.JpaUtil;
import com.wellscosta.model.entities.Event;
import com.wellscosta.model.entities.Task;

public class TaskDao implements ITaskDao{
	
	EntityManagerFactory emf;
	EntityManager em;

	@Override
	public void save(Task task) {
		openConnection();
		
		em.persist(task);
		em.getTransaction().commit();
		
		closeConnection();
	}

	@Override
	public Task update(Task task) {
		openConnection();
		
		var taskUpdate = em.merge(task);
		em.getTransaction().commit();
		
		closeConnection();

		return taskUpdate;
	}

	@Override
	public Task search(Long id) {
		openConnection();
		
		Task task = em.find(Task.class, id);
		em.getTransaction().commit();
		
		closeConnection();
		return task;
	}
	
	@Override
	public void delete(Task task) {
		openConnection();
		
		task = em.merge(task);
		em.remove(task);
		em.getTransaction().commit();
		
		closeConnection();
	}

	public EntityManager openConnection() {
		emf = JpaUtil.getEntityManagerFactory();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		return em;
	}
	
	private void closeConnection() {
		em.close();
		emf.close();
	}

	@Override
	public List<Task> searchAllTask() {
		openConnection();
		
		List<Task> tasks = em.createQuery("SELECT t FROM Task t WHERE TYPE(t) = Task", Task.class).getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return tasks;
	}

	@Override
	public List<Event> searchAllEvent() {
		openConnection();
		
		List<Event> events = em.createQuery("SELECT e FROM Event e WHERE TYPE(e) = Event", Event.class).getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return events;
	}
	
	@Override
	public List<Task> searchAll() {
		openConnection();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		
		Root<Task> root = cq.from(Task.class);
		cq.select(root);
		
		TypedQuery<Task> tq = em.createQuery(cq);
		
		List<Task> tasks = tq.getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return tasks;
	}

	@Override
	public List<Task> searchByName(String name) {
		openConnection();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Task> cq = cb.createQuery(Task.class);
		
		Root<Task> root = cq.from(Task.class);
		
		cq.select(root);
		cq.where(cb.equal(root.get("name"), name));
		
		TypedQuery<Task> tq = em.createQuery(cq);
		List<Task> tasks = tq.getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return tasks;
	}
}
