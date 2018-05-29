package com.alex;

import java.util.Date;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alex.Reminder.Status;

@SpringBootApplication
@SuppressWarnings("deprecation")
public class Application {
	
	@Autowired
	private ReminderRepository repository;
	
	@PostConstruct
	public void populateData() {
		for(int i = 0; i < 10; i++) {
			Reminder r = new Reminder();
			r.setName("Reminder " + i);
			r.setDescription("Description " + i);
			r.setStatus(i % 2 == 0? Status.Done : Status.NotDone);
			r.setDueDate(new Date(118, i, 1));
			
			repository.save(r);
		}
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
