package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.After;
import org.junit.Test;

import dao.ITaskDao;
import dao.TaskDao;
import model.entities.Event;
import model.enums.Marking;

public class EventTest {
	private ITaskDao taskDao;
	
	//Constructor instantiating the DAO
	public EventTest() {
		taskDao = new TaskDao();
	}
	
	//Method to run after all others
	@After
	public void deleteAll() {
		
	}
	
	@Test
	public void saveEvent() {
		Event event = createEvent();
		taskDao.cadastrar(event);
		assertNotNull(event);
		assertNotNull(event.getId());
	}
	
	@Test
	public void updateEvent() {
		//saveEvent
		Event event = createEvent();
		taskDao.cadastrar(event);
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
}
