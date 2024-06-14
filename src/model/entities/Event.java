package model.entities;

import java.util.Date;

import model.enums.Marking;

public class Event extends Task{
	
	private Date intant;
	
	public Event(Long id, String name, String note, Marking mark, Date intant) {
		super(id, name, note, mark);
		this.intant = intant;
	}

	public Date getIntant() {
		return intant;
	}

	public void setIntant(Date intant) {
		this.intant = intant;
	}
	
}
