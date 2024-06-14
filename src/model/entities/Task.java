package model.entities;

import model.enums.Marking;

public class Task {
	
	protected Long id;
	protected String name;
	protected String note;
	protected Marking mark;

	public Task(Long id, String name, String note, Marking mark) {
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", mark=" + mark + "]";
	}

}
