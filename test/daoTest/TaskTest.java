package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.ITaskDao;
import dao.ITaskListDao;
import dao.TaskDao;
import dao.TaskListDao;
import model.entities.Event;
import model.entities.Task;
import model.entities.TaskList;
import model.enums.Marking;

public class TaskTest {
	
	private ITaskDao taskDao;
	private ITaskListDao taskListDao;
	
	private TaskList list;
	
	//Constructor instantiating the DAO
	public TaskTest() {
		taskDao = new TaskDao();
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
		Task task = createTask();
		taskDao.save(task);
		assertNotNull(task);
		assertNotNull(task.getId());
	}
	
	@Test
	public void updateTask() {
		//saveTask
		Task task = createTask();
		taskDao.save(task);
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
		taskDao.save(task);
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
		taskDao.save(task);
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
	
	@Test
	public void searchAllTaskTest() {
		//saveTask 1
		Task task1 = createTask();
		taskDao.save(task1);
		Long id1 = task1.getId();
		assertNotNull(task1);
		assertNotNull(id1);
		assertEquals("TaskTest", task1.getName());
		
		//saveTask 2
		Task task2 = createTask();
		taskDao.save(task2);
		Long id2 = task2.getId();
		assertNotNull(task2);
		assertNotNull(id2);
		assertEquals("TaskTest", task2.getName());
		
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id3 = event.getId();
		assertNotNull(event);
		assertNotNull(id3);
		assertEquals("EventTest", event.getName());
		
		//searchAll
		List<Task> tasks = taskDao.searchAllTask();
		assertEquals(2, tasks.size());
	}
	
	@Test
	public void searchAllTest() {
		//saveTask
		Task task1 = createTask();
		taskDao.save(task1);
		Long id1 = task1.getId();
		assertNotNull(task1);
		assertNotNull(id1);
		assertEquals("TaskTest", task1.getName());
		
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id2 = event.getId();
		assertNotNull(event);
		assertNotNull(id2);
		assertEquals("EventTest", event.getName());
		
		//searchAll
		List<Task> tasks = taskDao.searchAll();
		assertEquals(2, tasks.size());
	}
	
	@Test
	public void searchByNameTest() {
		//saveTask 1
		Task task1 = createTask();
		taskDao.save(task1);
		Long id1 = task1.getId();
		assertNotNull(task1);
		assertNotNull(id1);
		assertEquals("TaskTest", task1.getName());
		
		//saveTask 2
		Task task2 = createTask();
		taskDao.save(task2);
		Long id2 = task2.getId();
		assertNotNull(task2);
		assertNotNull(id2);
		assertEquals("TaskTest", task2.getName());
		
		//saveTask 3
		Task task3 = new Task();
		task3.setName("Test");
		task3.setNote("AnnotationTask");
		task3.setDate(new Date());
		task3.setMark(Marking.NEXTDAY);
		task3.setTaskList(list);
		taskDao.save(task3);
		Long id3 = task3.getId();
		assertNotNull(task3);
		assertNotNull(id3);
		assertEquals("Test", task3.getName());
		
		List<Task> tasks = taskDao.searchByName("TaskTest");
		assertEquals(2, tasks.size());
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
	
	public Event createEvent() {
		Event event = new Event();
		event.setName("EventTest");
		event.setNote("AnnotationEvent");
		event.setDate(new Date());
		event.setMark(Marking.NEXTDAY);
		event.setHour(new Date());
		event.setTaskList(list);
		
		return event;
	}
	
	public TaskList saveTaskList() {
		TaskList list = new TaskList();
		list.setDate(new Date());
		
		return taskListDao.create(list);
	}
}
