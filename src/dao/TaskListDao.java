package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.TaskList;

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
		
		List<TaskList> list = em.createQuery("SELECT obj FROM TaskList obj", TaskList.class).getResultList();
		em.getTransaction().commit();
		
		closeConnection();
		
		return list;
	}
	
	public void openConnection() {
		emf = Persistence.createEntityManagerFactory("TaskListJPA");
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}
	
	private void closeConnection() {
		em.close();
		emf.close();
	}
}
