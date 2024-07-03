package dao;

import java.util.List;

import model.entities.Event;
import model.entities.Task;

public interface ITaskDao {

	void save(Task task);

	void update(Task task);
	
	Task search(Long id);
	
	void delete(Task task);
	
	List<Task> searchAllTask();
	
	List<Event> searchAllEvent();
	
	List<Task> searchAll();

}
