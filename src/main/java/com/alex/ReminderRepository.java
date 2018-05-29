package com.alex;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.alex.Reminder.Status;

public interface ReminderRepository extends CrudRepository<Reminder, Integer> {
	
	List<Reminder> findAllByStatus(Status status);

	List<Reminder> findAllByStatusAndDueDateBetween(Status status, Date from, Date to);

	List<Reminder> findAllByDueDateBetween(Date from, Date to);
	
	List<Reminder> findAllByDueDateLessThanEqual(Date dueDate);
}
