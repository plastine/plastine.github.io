package AppointmentService.AppointmentServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import AppointmentService.Appointment;

//This is a test class which relies on JUNIT 5 for testing. 
class AppointmentTest {
	Date currentDate = new Date();
	Appointment AppointmentTest = new Appointment("12345678", "Description", new Date());

	// test to check ID creation
	@Test
	@DisplayName("TEST: Appointment should be created without error")
	public void TestAppointmentIDCreation() {

		assertTrue(AppointmentTest.getAppointmentID().equals("12345678"));

	}

	// test to check ID length
	@Test
	@DisplayName("TEST: Appointment ID length (must not be more than 10)")
	public void TestAppointmentIDLength() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Appointment AppointmentTest = new Appointment("12345678901", "Description", currentDate);
		});
		assertEquals("Invalid Appointment ID", exception.getMessage());
	}

	// test to ensure ID is never null
	@Test
	@DisplayName("TEST: AppointmentID length (checks for null)")
	public void TestAppointmentIDLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Appointment AppointmentTest = new Appointment(null, "Description", currentDate);

		});
		assertEquals("Invalid Appointment ID", exception.getMessage());
	}

	// test to ensure description length is less than 50
	@Test
	@DisplayName("TEST: Description length Test (must be < 50)")
	public void testAppointmentDescription() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Appointment AppointmentTest = new Appointment("12345",
					"This description will be over 50................................................................................ ",
					currentDate);

		});
		assertEquals("Invalid Appointment Description length, can neither be 0 nor greater than 50 characters",
				exception.getMessage());
	}

	// test to ensure description is never null
	@Test
	@DisplayName("TEST: Appointment Description length (checks for null)")
	public void TestAppointmentDescriptionLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {

			Appointment AppointmentTest = new Appointment("12345", null, currentDate);
			assertNotNull(AppointmentTest.getAppointmentDescription(), "Appointment Description must not be null");
		});
		assertEquals("Invalid Appointment Description length, can neither be 0 nor greater than 50 characters",
				exception.getMessage());
	}

	// test to ensure the date is never null
	@Test
	@DisplayName("TEST: Appointment Date (checks for null)")
	public void TestAppointmentDateForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {

			Appointment AppointmentTest = new Appointment("12345", "description", null);
			assertNotNull(AppointmentTest.getAppointmentDate(), "Appointment Date must not be null");
		});
		assertEquals("Invalid Appointment Date provided or no date is provided. Please pick a day after today",
				exception.getMessage());
	}

}
