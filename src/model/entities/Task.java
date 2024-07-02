package model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Marking;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sq")
	@SequenceGenerator(name = "task_sq", sequenceName = "sq_task", initialValue = 1, allocationSize = 1)
	protected Long id;
	
	@Temporal(TemporalType.DATE)
	protected Date date;
	
	@Column(name = "name", length = 50, nullable = false)
	protected String name;
	
	@Column(name = "note", length = 250, nullable = true)
	protected String note;
	
	@Column(name = "mark", length = 25, nullable = false)
	protected Marking mark;
	
	@ManyToOne
	@JoinColumn(name = "list_id")
	protected TaskList taskList;
	
	public Task() {
	}

	public Task(Long id, Date date, String name, String note, Marking mark) {
		this.id = id;
		this.date = date;
		this.name = name;
		this.note = note;
		this.mark = mark;
	}
	
	public Task(String name, Date date, String note, Marking mark) {
		this.name = name;
		this.date = date;
		this.note = note;
		this.mark = mark;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public Marking getMark() {
		return mark;
	}
	
	public void setMark(Marking mark) {
		this.mark = mark;
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

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", mark=" + mark + "]";
	}

}
