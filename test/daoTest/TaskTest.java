package daoTest;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import dao.ITaskDao;
import dao.TaskDao;
import model.entities.Task;
import model.enums.Marking;

public class TaskTest {
	
	private ITaskDao taskDao;
	
	public TaskTest() {
		taskDao = new TaskDao();
	}
	
	@Test
	public void saveTask() {
		Task task = new Task("NameTest", new Date(), null, Marking.PENDING);
		taskDao.cadastrar(task);
		assertNotNull(task);
		assertNotNull(task.getId());
	}
}
