package com.alex;

import static org.assertj.core.api.Assertions.assertThat;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.alex.Reminder.Status;

@RunWith(SpringRunner.class)
@DataJpaTest
@SuppressWarnings("deprecation")
public class ReminderRepositoryTests {
	
    @Autowired
    private ReminderRepository repository;

	@Before
    public void prepare() {
    	Reminder r1 = new Reminder(1);
        r1.setName("Alex 1");
        r1.setStatus(Status.Done);
        r1.setDueDate(new Date(119, 6, 1));
        repository.save(r1);
        
        Reminder r2 = new Reminder(2);
        r2.setName("Alex 2");
        r2.setStatus(Status.NotDone);
        r2.setDueDate(new Date(119, 7, 1));
        repository.save(r2);
    }
    
    @Test
    public void testFindByStatus() {

        List<Reminder> findRemindersDone = repository.findAllByStatus(Status.Done);
        assertThat(findRemindersDone).extracting(Reminder::getName).contains("Alex 1");
        
        List<Reminder> findRemindersNotDone = repository.findAllByStatus(Status.NotDone);
        assertThat(findRemindersNotDone).extracting(Reminder::getName).contains("Alex 2");

    }
    
    @Test
    public void testFindByDueDate() {
    	
    	Date d1 = new Date(119, 5, 1);
    	Date d2 = new Date(119, 6, 3);

        List<Reminder> findRemindersBetween = repository.findAllByDueDateBetween(d1, d2);
        assertThat(findRemindersBetween).extracting(Reminder::getName).contains("Alex 1");
        
        List<Reminder> findRemindersLessThan = repository.findAllByDueDateLessThanEqual(d2);
        assertThat(findRemindersLessThan).extracting(Reminder::getName).contains("Alex 1");

    }
    @Test
    public void testFindByStatusAndDueDate() {
    	
    	Date d1 = new Date(119, 5, 1);
    	Date d2 = new Date(119, 6, 3);

        List<Reminder> findRemindersDoneBetween = repository.findAllByStatusAndDueDateBetween(Status.Done, d1, d2);
        assertThat(findRemindersDoneBetween).extracting(Reminder::getName).containsOnly("Alex 1");
        
        List<Reminder> findRemindersNotDoneBetween = repository.findAllByStatusAndDueDateBetween(Status.NotDone, d1, d2);
        assertThat(findRemindersNotDoneBetween).isEmpty();

    }
    
    @Test
    public void testCRUD() {
    	Reminder r = new Reminder(3);
        r.setName("Alex");
        r.setStatus(Status.Done);
        r.setDueDate(new Date(119, 7, 1));
        r = repository.save(r);

        Optional<Reminder> reminder = repository.findById(3);
        assertThat(reminder).hasValue(r);
        
        r.setName("Alex 666");
        Reminder persisted = repository.save(r);
        assertThat(persisted).extracting(Reminder::getName).containsOnly("Alex 666");
        
    }
}
