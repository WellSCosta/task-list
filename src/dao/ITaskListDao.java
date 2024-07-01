package dao;

import java.util.List;

import model.entities.TaskList;

public interface ITaskListDao {
	
	public void create(TaskList list);
	
	public void update(TaskList list);
	
	public void delete(TaskList list);
	
	public TaskList search(Long id);
	
	public List<TaskList> searchAll();
}
