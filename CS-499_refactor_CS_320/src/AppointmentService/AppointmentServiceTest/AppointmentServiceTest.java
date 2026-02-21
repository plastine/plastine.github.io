package AppointmentService.AppointmentServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import AppointmentService.Appointment;
import AppointmentService.AppointmentService;

import java.util.Date;

public class AppointmentServiceTest {
	// For initialization
	Date currentDate = new Date();
	Appointment AppointmentTest = new Appointment("12345678", "Description", currentDate);
	AppointmentService AppointmentServiceTester = new AppointmentService();
//each test uses the same parameters for initial tests.
//All tests will look for both the Appointment ID as well as the specified component to ensure the correct data object is being tested

////test for creating a new Appointment. This is done through the Appointment ID, as this is unique and unchanging.
	@Test
	@DisplayName("TEST: creating a new Appointment")
	public void testNewAppointment() {

		AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
				AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());
		// exception in the case that a null appointment is attempted to be created
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			AppointmentServiceTester.NewAppointment(null, null, null);
		});
		assertEquals("Invalid Appointment ID", exception.getMessage());

	}

//This test checks the remove Appointment method. This method removes the Appointment through the Appointment ID for the same reason as the prior method for testing
	@Test
	@DisplayName("TEST: Removing an Appointment")
	public void testRemoveAppointment() {

		AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
				AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());
		AppointmentServiceTester.NewAppointment("12345", "Description", new Date());
		assertEquals(2, AppointmentServiceTester.AppointmentMap.size());
		AppointmentServiceTester.DeleteAppointment("12345678");
		assertEquals(1, AppointmentServiceTester.AppointmentMap.size());
		assertNull(AppointmentServiceTester.getAppointmentMapID("12345678"));
	}

//This test checks for duplicates of the same ID. This should not happen as per the requirements. This also tests multiple additions to the hash-map
	@Test
	@DisplayName("TEST: multiple additions/duplicates")
	public void test3NewAppointments() {

		assertEquals(0, AppointmentServiceTester.AppointmentMap.size());
		AppointmentServiceTester.NewAppointment("2345734", "removing mole", currentDate);
		AppointmentServiceTester.NewAppointment("12313", "trimming beard", currentDate);
		AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
				AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());

		assertEquals(3, AppointmentServiceTester.AppointmentMap.size());

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
					AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());
		});
		assertEquals("Must be a Unique ID for Appointment, this ID already exists", exception.getMessage());
	}

//This test checks for the possibility that the deleteAppointment would be used to delete a null value, which would cause unintended errors    
	@Test
	@DisplayName("TEST: Attempting to add/remove NullID")
	public void testRemoveAppointmentIDNull() {

		String delete = null;

		AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
				AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			AppointmentServiceTester.NewAppointment(delete, null, new Date());
		});
		assertEquals("Invalid Appointment ID", exception.getMessage());

		Exception exceptionTwo = assertThrowsExactly(IllegalArgumentException.class, () -> {
			AppointmentServiceTester.DeleteAppointment(null);
		});
		assertEquals("ID is not found, or cannot be removed", exceptionTwo.getMessage());

	}

//this test is for ensuring that a valid appointment date must be picked. An appointment cannot be scheduled in the past as that logically does not make sense.
	@Test
	@DisplayName("TEST: Inputting Valid date")
	public void testAppointmentValidation() {

		AppointmentServiceTester.NewAppointment(AppointmentTest.getAppointmentID(),
				AppointmentTest.getAppointmentDescription(), AppointmentTest.getAppointmentDate());
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Date appointmentDate = new Date(System.currentTimeMillis() * 60 * 60 * 240);
			AppointmentServiceTester.NewAppointment("12345", "description", appointmentDate);

		});
		assertEquals("Invalid Appointment Date provided or no date is provided. Please pick a day after today",
				exception.getMessage());
	}

}