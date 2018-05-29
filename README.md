# Reminder Demo
This is a sample project that demonstrates how to create rest service that can persists data 

## Getting started
Please run Application.java as Java command line app, this will start a web-server
you can access it by hitting url: 
http://localhost:8080/reminders

### Eplorer 
```
get all reminders:
http://localhost:8080/reminders
```
```
get by status:
http://localhost:8080/reminders?status=Done
```
```
get by date:
http://localhost:8080/reminders?fromDate=05012018&toDate=09012018
```
```
get by date & status:
http://localhost:8080/reminders?status=Done&fromDate=05012018&toDate=09012018
```
Use PUT, POST, GET methods to update, create and retrieve by id
http://localhost:8080/reminder/0

### Tests
Please see ReminderRepositoryTests to see unit-tests for the app

## Author
Alex Vershinin