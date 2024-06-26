package model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Marking;

@Entity
@Table(name = "Event")
public class Event extends Task{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_sq")
	@SequenceGenerator(name = "event_sq", sequenceName = "sq_event", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private Date hour;
	
	public Event(String name, Date date, String note, Marking mark, Date hour) {
		super(name, date, note, mark);
		this.hour = hour;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}
	
}
