package model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TaskList")
public class TaskList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskList_sq")
	@SequenceGenerator(name = "taskList_sq", sequenceName = "sq_taskList", allocationSize = 1, initialValue = 1)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	private List<Task> tasks = new ArrayList<>();
	
	public TaskList() {
	}

	public TaskList(Long id, Date date) {
		this.id = id;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
}
