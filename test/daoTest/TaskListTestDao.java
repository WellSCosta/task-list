package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.ITaskDao;
import dao.ITaskListDao;
import dao.TaskDao;
import dao.TaskListDao;
import model.entities.Task;
import model.entities.TaskList;
import model.enums.Marking;

public class TaskListTestDao {
	
	private ITaskListDao taskListDao;
	private ITaskDao taskDao;
	
	Calendar calendar = Calendar.getInstance();
	
	public TaskListTestDao() {
		taskListDao = new TaskListDao();
		taskDao = new TaskDao();
	}
	
	@After
	public void end() {
		List<TaskList> list = taskListDao.searchAll();
		for (TaskList tl : list) {
			taskListDao.delete(tl);
		}
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
	
	@Test
	public void deleteTest() {
		//create task
		Task task = createTask();
		Date date;
		TaskList list = new TaskList();
		
		//save list
		date = new Date();
		list.setDate(date);
		list.addTask(task);
		taskListDao.create(list);
		date = list.getDate();

		assertNotNull(list);
		assertNotNull(list.getId());
		
		//save task
		saveTask(task, list);
		
		//search list
		TaskList listSearch = taskListDao.search(list.getId());
		
		assertNotNull(listSearch);
		assertNotNull(listSearch.getId());
		
		//remove list
		taskListDao.delete(list);
		
		//search list
		TaskList listSearch2 = taskListDao.search(list.getId());
		
		assertNull(listSearch2);
	}
	
	@Test
	public void searchTest() {
		//create task
		Task task = createTask();
		Date date;
		TaskList list = new TaskList();
		
		//save list
		date = new Date();
		list.setDate(date);
		list.addTask(task);
		taskListDao.create(list);
		date = list.getDate();

		assertNotNull(list);
		assertNotNull(list.getId());
		
		//save task
		saveTask(task, list);
		
		//search list
		TaskList listSearch = taskListDao.search(list.getId());
		
		assertNotNull(listSearch);
		assertNotNull(listSearch.getId());
		assertEquals(list.getId(), listSearch.getId());
		
		assertEquals(task.getTaskList().getId(), listSearch.getId());
	}
	
	@Test
	public void searchAllTest() {
		//create Tasklist1
		Task task = createTask();
		Task task2 = createTask();
		
		TaskList list = new TaskList();

		list.setDate(new Date());
		list.addTask(task);
		list.addTask(task2);
		
		taskListDao.create(list);
		assertNotNull(list);
		assertNotNull(list.getId());

		saveTask(task, list);
		saveTask(task2, list);
		
		//create Tasklist2
		Task task3 = createTask();
		Task task4 = createTask();

		TaskList list2 = new TaskList();

		list2.setDate(new Date());
		list2.addTask(task3);
		list2.addTask(task4);
		
		taskListDao.create(list2);
		assertNotNull(list2);
		assertNotNull(list2.getId());

		saveTask(task3, list2);
		saveTask(task4, list2);
		
		//searchAll
		List<TaskList> tlList = taskListDao.searchAll();
		assertEquals(2, tlList.size());
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
