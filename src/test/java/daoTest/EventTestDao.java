package daoTest;

import java.util.Date;
import java.util.List;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

import com.wellscosta.dao.ITaskDao;
import com.wellscosta.dao.ITaskListDao;
import com.wellscosta.dao.TaskDao;
import com.wellscosta.dao.TaskListDao;
import com.wellscosta.model.entities.Event;
import com.wellscosta.model.entities.Task;
import com.wellscosta.model.entities.TaskList;
import com.wellscosta.model.enums.Marking;

public class EventTestDao {
	private ITaskDao taskDao;
	private ITaskListDao taskListDao;
	
	private TaskList list;
	
	//Constructor instantiating the DAO
	public EventTestDao() {
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
	public void saveEvent() {
		Event event = createEvent();
		taskDao.save(event);
		assertNotNull(event);
		assertNotNull(event.getId());
	}
	
	@Test
	public void updateEvent() {
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id = event.getId();
		assertNotNull(event);
		assertNotNull(id);
		assertEquals("EventTest", event.getName());
		
		//update
		event.setName("EventTestUpdate");
		taskDao.update(event);
		assertNotNull(event);
		assertNotNull(event.getId());
		assertEquals(id, event.getId());
		assertEquals("EventTestUpdate", event.getName());
	}
	
	@Test
	public void searchEvent() {
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id = event.getId();
		assertNotNull(event);
		assertNotNull(id);
		assertEquals("EventTest", event.getName());
		
		//search
		Event taskSearch = (Event) taskDao.search(id);
		assertNotNull(taskSearch);
		assertEquals("EventTest", taskSearch.getName());
	}
	
	@Test
	public void deleteEvent() {
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id = event.getId();
		assertNotNull(event);
		assertNotNull(id);
		assertEquals("EventTest", event.getName());
		
		//search
		Event eventSearch = (Event) taskDao.search(id);
		assertNotNull(eventSearch);
		assertEquals("EventTest", eventSearch.getName());
		
		//delete
		taskDao.delete(eventSearch);
		
		//search
		Event eventSearch1 = (Event) taskDao.search(id);
		assertNull(eventSearch1);
	}
	
	@Test
	public void searchAllEvent() {
		//saveEvent
		Event event = createEvent();
		taskDao.save(event);
		Long id = event.getId();
		assertNotNull(event);
		assertNotNull(id);
		assertEquals("EventTest", event.getName());
		
		//saveEvent
		Event event1 = createEvent();
		taskDao.save(event1);
		Long id1 = event1.getId();
		assertNotNull(event1);
		assertNotNull(id1);
		assertEquals("EventTest", event1.getName());
		
		//saveTask
		Task task1 = createTask();
		taskDao.save(task1);
		Long id3 = task1.getId();
		assertNotNull(task1);
		assertNotNull(id3);
		assertEquals("TaskTest", task1.getName());
		
		//searchAll
		List<Event> events = taskDao.searchAllEvent();
		assertEquals(2, events.size());
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
