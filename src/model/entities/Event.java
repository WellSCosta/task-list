package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Marking;

@Entity
public class Event extends Task{

	@Temporal(TemporalType.TIME)
	private Date hour;
	
	public Event(String name, Date date, String note, Marking mark, Date hour) {
		super(name, date, note, mark);
		this.hour = hour;
	}
	
	public Event() {
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}
	
}
