package dao;

import java.util.List;

import model.entities.TaskList;

public interface ITaskListDao {
	
	public TaskList create(TaskList list);
	
	public TaskList update(TaskList list);
	
	public void delete(TaskList list);
	
	public TaskList search(Long id);
	
	public List<TaskList> searchAll();
}
