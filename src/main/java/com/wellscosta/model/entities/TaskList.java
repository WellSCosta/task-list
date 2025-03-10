package com.wellscosta.model.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tb_tasklist")
@Data
@AllArgsConstructor
public class TaskList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taskList_sq")
	@SequenceGenerator(name = "taskList_sq", sequenceName = "sq_taskList", allocationSize = 1, initialValue = 1)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;
	
	@OneToMany(mappedBy = "taskList")
	@Cascade(value = {CascadeType.DELETE})
	private final List<Task> tasks;
	
	public TaskList() {
		tasks = new ArrayList<>();
	}

	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}
}
