package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskList {
	
	private Long id;
	private String name;
	private Date date;
	
	private List<Task> tasks = new ArrayList<>();
	
	public TaskList() {
	}

	public TaskList(Long id, String name, Date date) {
		this.id = id;
		this.name = name;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
}
