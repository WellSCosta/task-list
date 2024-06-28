package dao;

import model.entities.Task;

public interface ITaskDao {

	void cadastrar(Task task);

	void update(Task task);
	
	Task search(Long id);
	
	void delete(Task task);

}
