package com.wellscosta.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
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

import com.wellscosta.model.enums.Marking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "tb_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sq")
	@SequenceGenerator(name = "task_sq", sequenceName = "sq_task", initialValue = 1, allocationSize = 1)
	protected Long id;
	
	@Column(name = "name", length = 50, nullable = false)
	protected String name;
	
	@Column(name = "note", length = 250, nullable = true)
	protected String note;
	
	@Temporal(TemporalType.DATE)
	protected Date date;
	
	@Enumerated(EnumType.STRING)
	protected Marking mark;
	
	@ManyToOne
	@JoinColumn(name = "list_id", 
	foreignKey = @ForeignKey(name = "fk_TaskList_Task"),
	referencedColumnName = "id", nullable = false)
	protected TaskList taskList;
	
	public Task(String name, Date date, String note, Marking mark) {
		this.name = name;
		this.date = date;
		this.note = note;
		this.mark = mark;
	}
}
