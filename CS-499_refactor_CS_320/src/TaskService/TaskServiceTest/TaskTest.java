package TaskService.TaskServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import TaskService.Task;

//This is a test class which relies on JUNIT 5 for testing. 
class TaskTest {
	Task TaskTest = new Task("12345678", "Andrew", "Description");
	//test to check ID length
		@Test
		@DisplayName("TEST: Task should be created without error")
		public void TestTaskIDCreation() {
			
			Exception exception = assertThrows(IllegalArgumentException.class, ()->{
				Task TaskTest = new Task(null, null, null);

			});
			assertEquals("Invalid Task ID, can neither be 0 nor greater than 10",exception.getMessage());
		}
	//test 
	
	//test to check ID length
	@Test
	@DisplayName("TEST: Task ID length (must not be more than 10)")
	public void TestTaskIDLength() {
		
		 Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			Task TaskTest = new Task("12345678910", "Andrew", "Description");
		});
		 assertEquals("Invalid Task ID, can neither be 0 nor greater than 10",exception.getMessage());
	}
	
	//test to check name length
	@Test
	@DisplayName("TEST: Task Name length (must not be more than 20)")
	public void TestTaskNameLength() {
			
			Exception exception = assertThrows(IllegalArgumentException.class,()-> {
				Task TaskTest= new Task("12345", "1234567891011121314151617181920", "Description");
			});
	}
	//test to ensure name is never null
	@Test
	@DisplayName("TEST: Task Name length (checks for null)")
	public void TestTaskNameLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			Task TaskTest = new Task("1234567890", null , "description");
			assertNotNull(TaskTest.getTaskName(), "Task name must not be null");
		});
			 assertEquals("Invalid taskName length, can neither be 0 nor greater than 20 characters." ,exception.getMessage());	
		}
		
	//test to ensure description length is less than 50
	@Test
	@DisplayName("TEST: Description length Test (must be <50)")
	public void testTaskDescription() {
		 
		 Exception exception = assertThrows(IllegalArgumentException.class, ()->{
			 @SuppressWarnings("unused")
			 Task TaskTest = new Task("12345", "Andrew", "This description will be over 50 characters for the purpose of this test..................................................................................");

		 });
		 assertEquals("Invalid Task Description length, can neither be 0 nor greater than 50 characters." ,exception.getMessage());	
	}
	//test to ensure description is never null
	@Test
	@DisplayName("TEST: Task Description length (checks for null)")
	public void TestTaskDescriptionLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, ()->{
		Task TaskTest = new Task("1234567890", "", null);
		assertNotNull(TaskTest.getTaskDescription(), "Task Description must not be null");
	});
		 assertEquals("Invalid Task Description length, can neither be 0 nor greater than 50 characters." ,exception.getMessage());	
	}
	
}
