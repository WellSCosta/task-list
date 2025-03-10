package serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import com.wellscosta.dao.ITaskDao;
import com.wellscosta.dao.ITaskListDao;
import com.wellscosta.dao.TaskDao;
import com.wellscosta.dao.TaskListDao;
import com.wellscosta.model.entities.Task;
import com.wellscosta.model.entities.TaskList;
import com.wellscosta.model.enums.Marking;
import com.wellscosta.service.ITaskService;
import com.wellscosta.service.TaskService;

public class TaskTestService {

    private ITaskDao taskDao;
	private ITaskListDao taskListDao;
    private ITaskService service;
	
	private TaskList list;
	
	//Constructor instantiating the DAO
	public TaskTestService() {
		taskDao = new TaskDao();
        service = new TaskService(taskDao);
		taskListDao = new TaskListDao();
		list = saveTaskList();
	}
	
	//Method to run after all others
	@After
	public void end() {
		List<Task> tasks = taskDao.searchAll();
		
		for (Task task : tasks) {
			taskDao.delete(task);
		}
		
		List<TaskList> tls = taskListDao.searchAll();
		
		for (TaskList list : tls) {
			taskListDao.delete(list);
		}
	}

    @Test
	public void saveTask() {
        var task = createTask();
		service.saveTask(task);

		assertNotNull(task.getId());

		List<Task> tasks = taskDao.searchByName("TaskTest");
		assertTrue(!tasks.isEmpty());
		assertEquals(1, tasks.size());
    }

	@Test(expected = RuntimeException.class)
	public void doubleInsertionSaveTask() {
        var task = createTask();
		var task1 = createTask();

		service.saveTask(task);
		assertNotNull(task.getId());

		List<Task> tasks = taskDao.searchByName("TaskTest");
		assertTrue(!tasks.isEmpty());
		assertEquals(1, tasks.size());

		service.saveTask(task1);
    }

	@Test
	public void updateTask() {
        Task task = createTask();
		service.saveTask(task);
		Long id = task.getId();
		assertNotNull(task);
		assertNotNull(id);
		assertEquals("TaskTest", task.getName());
		
		//update
		task.setName("TaskTestUpdate");
		service.updateTask(task);
		assertNotNull(task);
		assertNotNull(task.getId());
		assertEquals(id, task.getId());
		assertEquals("TaskTestUpdate", task.getName());
    }

	@Test(expected = RuntimeException.class)
	public void updateErro() {
        Task task = createTask();

		task.setName("TaskTestUpdate");
		service.updateTask(task);
    }
	
    public Task createTask() {
		Task task = new Task();
		task.setName("TaskTest");
		task.setNote("AnnotationTask");
		task.setDate(new Date());
		task.setMark(Marking.NEXTDAY);
		task.setTaskList(list);
		
		return task;
	}
	
	public TaskList saveTaskList() {
		TaskList list = new TaskList();
		list.setDate(new Date());
		
		return taskListDao.create(list);
	}
}
