//Andrew Allen
//CS-320 Module 4
package TaskService;

import java.util.Date;

public class Task {

private final String TaskID;
private String taskDescription;
private String taskName;


/*constructor for the Task Class
illegal arguments as per requirements:
The task object shall have a required unique task ID String that cannot be longer than 10 characters. The task ID shall not be null and shall not be updatable.
The task object shall have a required name String field that cannot be longer than 20 characters. The name field shall not be null.
The task object shall have a required description String field that cannot be longer than 50 characters. The description field shall not be null.
*/
public Task(String TaskID, String taskName, String taskDescription){

if ( TaskID == null || TaskID.length() >= 11) { 
	throw new IllegalArgumentException("Invalid Task ID, can neither be 0 nor greater than 10");
}
this.TaskID = TaskID;
if (taskName == null || taskName.length() > 20) {
	throw new IllegalArgumentException("Invalid taskName length, can neither be 0 nor greater than 20 characters.");
}
this.taskName = taskName;

if (taskDescription == null || taskDescription.length() > 50) {
	throw new IllegalArgumentException("Invalid Task Description length, can neither be 0 nor greater than 50 characters.");
}
this.taskDescription = taskDescription;
}


//getter methods for each object
public String getTaskID() {
	return TaskID;
}

public String getTaskDescription() {
	return taskDescription;
}
public String getTaskName() {
	return taskName;
}

//setter methods for each object in task class
public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	

public void setTaskName(String taskName) {

		this.taskName = taskName;
	}
}