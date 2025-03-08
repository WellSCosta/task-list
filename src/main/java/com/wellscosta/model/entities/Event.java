package com.wellscosta.model.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.wellscosta.model.enums.Marking;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event extends Task{

	@Temporal(TemporalType.TIME)
	private Date hour;
	
	public Event(String name, Date date, String note, Marking mark, Date hour) {
		super(name, date, note, mark);
		this.hour = hour;
	}
}
