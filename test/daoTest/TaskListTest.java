package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
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
	
	Calendar calendar = Calendar.getInstance();
	
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
	
	@Test
	public void updateTest() {
		//create task
		Task task = createTask();
		Date firstDate;
		Date secondDate;
		TaskList list = new TaskList();
		
		//save list
		firstDate = new Date();
		list.setDate(firstDate);
		list.addTask(task);
		taskListDao.create(list);
		firstDate = list.getDate();
		Long id1 = list.getId();

		assertNotNull(list);
		assertNotNull(list.getId());
		
		//save task
		saveTask(task, list);
		
		//update list's date (day + 1)
		calendar.setTime(firstDate);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		
		
		secondDate = calendar.getTime();;
		list.setDate(secondDate);
		taskListDao.update(list);
		Long id2 = list.getId();
		
		assertNotNull(list);
		assertNotEquals(firstDate, list.getDate());
		assertEquals(id1, id2);
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
