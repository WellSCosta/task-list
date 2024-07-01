package daoTest;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;

import dao.ITaskDao;
import dao.ITaskListDao;
import dao.TaskDao;
import dao.TaskListDao;
import model.entities.Task;
import model.entities.TaskList;
import model.enums.Marking;

public class TaskListTest {
	
	private ITaskListDao taskListDao;
	private ITaskDao taskDao;
	
	public TaskListTest() {
		taskListDao = new TaskListDao();
		taskDao = new TaskDao();
	}
	
	@Test
	public void saveTest() {
		//create tasks
		Task task = createTask();
		Task task2 = createTask();
		
		//create taskList
		TaskList list = new TaskList();

		list.setDate(new Date());
		list.addTask(task);
		list.addTask(task2);
		
		//save taskList
		taskListDao.create(list);
		assertNotNull(list);
		assertNotNull(list.getId());

		//save tasks
		saveTask(task, list);
		saveTask(task2, list);
	}
	
	public void saveTask(Task task, TaskList list) {
		task.setTaskList(list);
		taskDao.save(task);
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
