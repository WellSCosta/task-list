package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import dao.ITaskDao;
import dao.TaskDao;
import model.entities.Task;
import model.enums.Marking;

public class TaskTest {
	
	private ITaskDao taskDao;
	
	//Constructor instantiating the DAO
	public TaskTest() {
		taskDao = new TaskDao();
	}
	
	//Method to run after all others
	@After
	public void end() {
		
	}
	
	@Test
	public void saveTask() {
		Task task = createTask();
		taskDao.cadastrar(task);
		assertNotNull(task);
		assertNotNull(task.getId());
	}
	
	@Test
	public void updateTask() {
		//saveTask
		Task task = createTask();
		taskDao.cadastrar(task);
		Long id = task.getId();
		assertNotNull(task);
		assertNotNull(id);
		assertEquals("TaskTest", task.getName());
		
		//update
		task.setName("TaskTestUpdate");
		taskDao.update(task);
		assertNotNull(task);
		assertNotNull(task.getId());
		assertEquals(id, task.getId());
		assertEquals("TaskTestUpdate", task.getName());
	}
	
	@Test
	public void searchTest() {
		//saveTask
		Task task = createTask();
		taskDao.cadastrar(task);
		Long id = task.getId();
		assertNotNull(task);
		assertNotNull(id);
		assertEquals("TaskTest", task.getName());
		
		//search
		Task taskSearch = taskDao.search(id);
		assertNotNull(taskSearch);
		assertEquals("TaskTest", taskSearch.getName());
	}
	
	@Test
	public void deleteTest() {
		//saveTask
		Task task = createTask();
		taskDao.cadastrar(task);
		Long id = task.getId();
		assertNotNull(task);
		assertNotNull(id);
		assertEquals("TaskTest", task.getName());
		
		//search
		Task taskSearch = taskDao.search(id);
		assertNotNull(taskSearch);
		assertEquals("TaskTest", taskSearch.getName());
		
		//delete
		taskDao.delete(taskSearch);
		
		//search
		Task taskSearch1 = taskDao.search(id);
		assertNull(taskSearch1);
	}
	
	public Task createTask() {
		Task task = new Task();
		task.setName("TaskTest");
		task.setNote("AnnotationTask");
		task.setDate(new Date());
		task.setMark(Marking.NEXTDAY);
		
		return task;
	}
}
