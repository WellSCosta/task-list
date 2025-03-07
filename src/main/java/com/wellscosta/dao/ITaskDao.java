package com.wellscosta.dao;

import java.util.List;

import com.wellscosta.model.entities.Event;
import com.wellscosta.model.entities.Task;

public interface ITaskDao {

	void save(Task task);

	void update(Task task);
	
	Task search(Long id);
	
	void delete(Task task);
	
	List<Task> searchAllTask();
	
	List<Event> searchAllEvent();
	
	List<Task> searchAll();
	
	List<Task> searchByName(String name);

}
