package TaskService.TaskServiceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import TaskService.Task;
import TaskService.TaskService;


public class TaskServiceTest {
	String taskID = "123456"; 
TaskService TaskServiceTester = new TaskService();
Task taskTester = new Task("12313", "fighting bears", "good luck");
//each test uses the same parameters for initial tests.
//All tests will look for both the Task ID as well as the specified component to ensure the correct data object is being tested
	
	
////test for creating a new Task. This is done through the Task ID, as this is unique and unchanging.
@Test
@DisplayName("TEST: creating a new Task")
public void testNewTask() {
	Task taskTester = new Task(taskID,"Andrew","Allen");
    TaskServiceTester.newTask(taskID, "Andrew", "Allen");
    assertNotNull(TaskServiceTester.getTaskMapID(taskID));
    assertTrue(TaskServiceTester.taskMap.containsKey(taskTester.getTaskID()));
    Exception exception = assertThrows(IllegalArgumentException.class, ()->{
    	TaskServiceTester.newTask(null, null, null);
    });
    assertEquals("Invalid Task ID, can neither be 0 nor greater than 10",exception.getMessage());
}
//tests for multiple new tasks being added, as well as a duplicate
@Test
@DisplayName("TEST: multiple additions/duplicates")
public void test3NewTasks() {
	
    assertEquals(0, TaskServiceTester.taskMap.size());
    TaskServiceTester.newTask("2345734", "cutting wood", "cutting wood involves an axe");
    TaskServiceTester.newTask("12313", "fighting bears", "good luck");
    TaskServiceTester.newTask("123234", "picking berries", "please use the guide to not die");
    assertEquals(3,TaskServiceTester.taskMap.size());
    Exception exception = assertThrows(IllegalArgumentException.class, ()->{
    	TaskServiceTester.newTask("12313", "fighting bears", "good luck");
    });
    assertEquals("Must be a Unique ID for task, this ID already exists",exception.getMessage());
}

//This test checks for the possibility that the deleteTask would be used to delete a null value, which would cause unintended errors    
@Test
@DisplayName("TEST: Attempting to remove NullID")
public void testRemoveTaskIDNull( ) {
	
	Exception exception = assertThrows(IllegalArgumentException.class, ()->{
		taskTester = new Task(null,null,null);
		TaskServiceTester.removeTask(taskTester.getTaskID());
	});
	assertEquals("Invalid Task ID, can neither be 0 nor greater than 10",exception.getMessage());
	
}

//This test checks the remove Task method. This method removes the Task through the Task ID for the same reason as the prior method for testing
@Test
@DisplayName("TEST: Removing a Task")
public void testRemoveTask() {
	
	
	TaskServiceTester.newTask("2345734", "cutting wood", "cutting wood involves an axe");
	TaskServiceTester.newTask("123234", "picking berries", "please use the guide to not die");
	TaskServiceTester.newTask(taskTester.getTaskID(), taskTester.getTaskName(), taskTester.getTaskDescription());
	
	assertEquals(3,TaskServiceTester.taskMap.size());
	
    TaskServiceTester.removeTask("12313");
    
    assertEquals(2,TaskServiceTester.taskMap.size());
    
    assertNull(TaskServiceTester.getTaskMapID("12313"));
    
    assertFalse(TaskServiceTester.taskMap.containsKey("12313"));
    

}
//this test is for ensuring the update task name method works appropriately
@Test
@DisplayName("TEST: changing task Name")
public void testUpdateTaskName() {
	TaskServiceTester.newTask(taskTester.getTaskID(), taskTester.getTaskName(), taskTester.getTaskDescription());
	
    TaskServiceTester.updateTaskName("12313", "generic Task name 2");
    assertEquals("generic Task name 2", TaskServiceTester.getTaskMapID("12313").getTaskName(), "Name was not changed or updated");
    Exception exception = assertThrows(IllegalArgumentException.class, ()->{
    	TaskServiceTester.updateTaskName("12313","generic Task nameeeeeeeeeee");
    	
    });
    assertEquals("task name is too long or null",exception.getMessage());
    
}
//this test ensures that the update task description method works appropriately
@Test
@DisplayName("TEST: changing task Description")
public void testUpdateTaskDescription() {
	TaskServiceTester.newTask(taskTester.getTaskID(), taskTester.getTaskName(), taskTester.getTaskDescription());
	
    TaskServiceTester.updateTaskDescription("12313", "generic Task description 2");
    assertEquals("generic Task description 2", TaskServiceTester.getTaskMapID("12313").getTaskDescription(), "Description was not changed or updated");
    Exception exception = assertThrows(IllegalArgumentException.class, ()->{
    	TaskServiceTester.updateTaskDescription("12313", "generic Task description 25555555555555555555555555555555");
});
assertEquals("task description is too long or null",exception.getMessage());
}
}