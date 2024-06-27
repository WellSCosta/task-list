package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.entities.Task;

public class TaskDao implements ITaskDao{

	@Override
	public void cadastrar(Task task) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TaskListJPA");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(task);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
	}
	
}
