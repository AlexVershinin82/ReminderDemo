package com.alex;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "Reminders")
public class Reminder {

	public enum Status {
		Done("DONE"), NotDone("NOTDONE");
		
		private final String value;
		
		Status(String value) {
			this.value = value;
		}
		
		@Override
	    public String toString() {
	        return value;
	    }
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    @Column(name="due_date")
    private Date dueDate;
    private Status status;

    public Reminder() {
    }
    public Reminder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("Reminder id = %d, name = '%s'\n description = '%s'", this.id, this.name, description);
	}

}
