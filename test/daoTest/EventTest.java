package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import dao.ITaskDao;
import dao.TaskDao;
import model.entities.Event;
import model.entities.Task;
import model.enums.Marking;

public class EventTest {
	private ITaskDao taskDao;
	
	//Constructor instantiating the DAO
	public EventTest() {
		taskDao = new TaskDao();
	}
	
	//Method to run after all others
	@After
	public void end() {
		List<Task> events = taskDao.searchAllTask();
		
		for (Task event : events) {
			taskDao.delete(event);
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
	
	public Event createEvent() {
		Event event = new Event();
		event.setName("EventTest");
		event.setNote("AnnotationEvent");
		event.setDate(new Date());
		event.setMark(Marking.NEXTDAY);
		event.setHour(new Date());
		
		return event;
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
		
		//searchAll
		List<Event> events = taskDao.searchAllEvent();
		assertEquals(2, events.size());
	}
}
