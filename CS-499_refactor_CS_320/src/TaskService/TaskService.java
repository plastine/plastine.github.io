package TaskService;
import java.util.HashMap;

/*this class is for the data structure and functionality of said data structure. This class is related to the Task class. 
requirements as stated:
The task service shall be able to add tasks with a unique ID.
The task service shall be able to delete tasks per taskId.
The task service shall be able to update task fields per taskId. The following fields are updatable:
name
description
*/
public class TaskService {
// the Tasks will be stored in a hash map data structure
public HashMap <String, Task> taskMap; 

//constructor for the taskMap hash-map
public TaskService() {
	taskMap = new HashMap<>();
}

//this method creates a new object inside of the hash-map
public void newTask( String taskID, String taskName, String taskDescription ){
	if(!(taskMap.containsKey(taskID)==false)) {
		throw new IllegalArgumentException("Must be a Unique ID for task, this ID already exists");
	}
	taskMap.put(taskID, new Task(taskID, taskName, taskDescription));
}

//this method removes Tasks from the hash-map 
public void removeTask(String TaskID) {
	if(!(taskMap.containsKey(TaskID)==false) && TaskID != null) {

		taskMap.remove(TaskID);
	}
	else throw new IllegalArgumentException("ID is not found, or cannot be removed");
}
//this is used for Task ID retrieval. This is needed as the Task ID is what each Task will rely on through testing and functionality.
public Task getTaskMapID(String TaskID) {
	return taskMap.get(TaskID);
}
//each method from this point forward will automatically set each portion of the Task that are not the ID if they are empty with what is presented


//this method is for updating the Task name (NOT ID). It cannot be longer than 20 characters (or null)
public void updateTaskName(String TaskID, String taskName) {
	Task updateTask = taskMap.get(TaskID);
	if (updateTask !=null && (taskName.length() <= 20&& taskName != null)) {
		updateTask.setTaskName(taskName);
	} 
	else throw new IllegalArgumentException("task name is too long or null");
	}
//this method is for updating the task description. It cannot be larger than 50 characters (or null)
public void updateTaskDescription(String TaskID, String taskDescriptionUpdate) {
	Task updateTask = taskMap.get(TaskID);
	if (updateTask !=null && (taskDescriptionUpdate.length() <= 50&& taskDescriptionUpdate != null)) {
		updateTask.setTaskDescription(taskDescriptionUpdate);
	}
	else throw new IllegalArgumentException("task description is too long or null");
}

}

