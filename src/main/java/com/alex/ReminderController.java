package com.alex;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alex.Reminder.Status;
import com.google.common.collect.Lists;

@RestController
public class ReminderController {
	@Autowired
	private ReminderRepository repository;
	
    @RequestMapping("/reminders")
    public List<Reminder> getEntities(@RequestParam(value="status", required = false)  Status status, 
    		@RequestParam(value="fromDate", required = false)     @DateTimeFormat(pattern="MMddyyyy") Date fromDate,
    		@RequestParam(value="toDate", required = false)     @DateTimeFormat(pattern="MMddyyyy") Date toDate) {
    	
    	if (status!= null && fromDate != null && toDate != null) {
    		return Lists.newArrayList(repository.findAllByStatusAndDueDateBetween(status, fromDate, toDate));
    	} else if (fromDate != null && toDate != null) {
    		return Lists.newArrayList(repository.findAllByDueDateBetween(fromDate, toDate));
    	} else if (status!= null) {
    		return Lists.newArrayList(repository.findAllByStatus(status));
    	} else {
            return Lists.newArrayList(repository.findAll());
    	}
    }

	@RequestMapping("/reminder/{id}")
    public Optional<Reminder> getEntity(@PathVariable("id") int id) {
        return repository.findById(id);
    }
	
	@RequestMapping(value = "/reminder", method = RequestMethod.POST)
	@ResponseBody
    public Reminder createEntity(@RequestBody Reminder reminder) {
		Reminder persisted = repository.save(reminder);
		return persisted;
    }
	
	@RequestMapping(value = "/reminder/{id}", method = RequestMethod.PUT)
	@ResponseBody
    public Reminder updateEntity(@PathVariable("id") int id, @RequestBody Reminder reminder) {
		Reminder persisted = repository.save(reminder);
		return persisted;
    }
}
