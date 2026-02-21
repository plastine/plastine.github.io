package ContactService.ContactServiceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ContactService.Contact;

//This is a test class which relies on JUNIT 5 for testing. 
class ContactTest {
	Contact ContactTest = new Contact("12345678", "Andrew", "Allen", "5553335353", "Test Ave");

	// test to check ID length
	@Test
	@DisplayName("TEST: Contact should be created without error")
	public void TestContactIDCreation() {
		assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact(null , null, null, null, null);

		});
		// assertEquals("")
	}

	// test to check ID length
	@Test
	@DisplayName("TEST: Contact ID length (must not be more than 10)")
	public void TestContactIDLength() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("12345678910", "Andrew", "Allen", "5553335353", "Test Ave");
		});
		assertEquals("Invalid contact ID", exception.getMessage());
	}

	// test to check first name length
	@Test
	@DisplayName("TEST: Contact first Name length (must not be more than 10)")
	public void TestFirstNameLength() {

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("12345678", "1234567891011121314151617181920", "Allen", "5553335353",
					"Test Ave");
		});
		assertEquals("Invalid first name", exception.getMessage());
	}

	// test to check last name length
	@Test
	@DisplayName("TEST: Contact first Name length (must not be more than 10)")
	public void TestLastNameLength() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("12345678", "Andrew", "Allen2222222222222222222", "5553335353",
					"Test Ave");
		});
		assertEquals("Invalid last name", exception.getMessage());
	}

	// test to ensure first name is never null
	@Test
	@DisplayName("TEST: first Name length (checks for null)")
	public void TestFirstNameLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("1234567890", null, "Allen", "5553335353", "description");
			assertNotNull(ContactTest.getFirstName(), "Contact name must not be null");
		});
		assertEquals("Invalid first name", exception.getMessage());

	}

	// test to ensure last name is never null
	@Test
	@DisplayName("TEST: Last Name length (checks for null)")
	public void TestLastNameLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("1234567890", "Andrew", null, "5553335353", "description");
		});
		assertEquals("Invalid last name", exception.getMessage());

	}

	// test to ensure phone number length is EXACTLY 10
	@Test
	@DisplayName("TEST: phone Number length Test (must be Exactly 10)")
	public void testPhoneNumberLength() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Contact ContactTest = new Contact("12345", "Andrew", "Allen", "12345678910", "test Ave");
		});
		assertEquals("Invalid phone number", exception.getMessage());
	}

	// test to ensure phone number is never null
	@Test
	@DisplayName("TEST: Contact phone number length (checks for null)")
	public void TestPhoneNumberLengthForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("123456789", "Andrew", "Allen", null, "test ave");
		});
		assertEquals("Invalid phone number", exception.getMessage());
	}

	// test to ensure home address length is no more than 30
	@Test
	@DisplayName("TEST: address length Test(no more than 30) )")
	public void testHomeAddressLength() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			@SuppressWarnings("unused")
			Contact ContactTest = new Contact("12345", "Andrew", "Allen", "1234567891",
					"test Ave must be more than 30 characters for error");
		});
		assertEquals("Invalid home address", exception.getMessage());
	}

	// test to ensure phone number is never null
	@Test
	@DisplayName("TEST: Adress number length (checks for null)")
	public void TestHomeAddressForNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			Contact ContactTest = new Contact("123456789", "Andrew", "Allen", "1234567891", null);
		});
		assertEquals("Invalid home address", exception.getMessage());
	}
}
