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
	public void create(TaskList list) {
		openConnection();
		
		em.persist(list);
		em.getTransaction().commit();
		
		closeConnection();
	}

	@Override
	public void update(TaskList list) {
		openConnection();
		
		em.merge(list);
		em.getTransaction().commit();
		
		closeConnection();
	}

	@Override
	public void delete(TaskList list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TaskList search(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskList> searchAll() {
		// TODO Auto-generated method stub
		return null;
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
