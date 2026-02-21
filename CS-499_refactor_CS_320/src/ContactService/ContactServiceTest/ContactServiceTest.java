package ContactService.ContactServiceTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ContactService.Contact;
import ContactService.ContactService;


public class ContactServiceTest {
	String ContactID = "12313";
ContactService ContactServiceTester = new ContactService();
Contact ContactTester = new Contact("12313", "Andrew", "Allen", "5553335353", "Test Ave");
//each test uses the same parameters for initial tests.
//All tests will look for both the Contact ID as well as the specified component to ensure the correct data object is being tested
	
	
//test for creating a new Contact. This is done through the Contact ID, as this is unique and unchanging.
@Test
@DisplayName("TEST: creating a new Contact")
public void testNewContact() {
    ContactServiceTester.newContact("123456789", "Andrew", "Allen", "5553335353", "Test Ave");
    ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
    assertNotNull(ContactServiceTester.getContactMapID(ContactID));
    assertTrue(ContactServiceTester.contactMap.containsKey(ContactTester.getContactID()));
    
    Exception exception = assertThrows(IllegalArgumentException.class, ()->{
    	ContactServiceTester.newContact(null, null, null,null,null);
    });
    assertEquals("Invalid contact ID",exception.getMessage());
}
//test for checking for duplicated IDs within the application
@Test
@DisplayName("TEST: duplicates")
public void testForDuplicateContacts() {
	
   
    ContactServiceTester.newContact("2345734", "George", "Washington", "1111111111", "Test Ave");
    Exception exceptionForService =  assertThrows(IllegalArgumentException.class, ()->{
         ContactServiceTester.newContact("2345734", "George", "Washington", "1111111111", "Test Ave");
    });
    assertEquals("Must be a Unique ID for contact, this ID already exists",exceptionForService.getMessage());
    ContactServiceTester.newContact("12313", "Barack", "Obama", "9999999999", " Defect Lane");
    
    Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
    	ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
    });
    assertEquals("Must be a Unique ID for contact, this ID already exists",exception.getMessage());
    
    
}

@Test
@DisplayName("TEST: multiple additions")
public void test3NewContacts() {
	
    assertEquals(0, ContactServiceTester.contactMap.size());
    
    ContactServiceTester.newContact("2345734", "George", "Washington", "1111111111", "Test Ave");
    ContactServiceTester.newContact("12313", "Barack", "Obama", "9999999999", " Defect Lane");
    ContactServiceTester.newContact("123234", "Thomas", "Jefferson", "3333333333", "Error Street");
    
    assertEquals(3,ContactServiceTester.contactMap.size());
    
}

//This test checks for the possibility that the deleteContact would be used to delete a null value, which would cause unintended errors    
@Test
@DisplayName("TEST: Attempting to remove NullID")
public void testRemoveContactIDNull( ) {
	
	Exception exception = assertThrows(IllegalArgumentException.class, ()->{
		ContactTester = new Contact(null,null,null,null,null);
		ContactServiceTester.removeContact(ContactTester.getContactID());
	});
		
	assertEquals("Invalid contact ID",exception.getMessage());
}

//This test checks the remove Contact method. This method removes the Contact through the Contact ID for the same reason as the prior method for testing
@Test
@DisplayName("TEST: Removing a Contact")
public void testRemoveContact() {
	
	
	ContactServiceTester.newContact("2345734", "George", "Washington", "1111111111", "Test Ave");
	ContactServiceTester.newContact("12314", "Barack", "Obama", "9999999999", " Defect Lane");
	
	ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
	
	assertEquals(3,ContactServiceTester.contactMap.size());
	
    ContactServiceTester.removeContact("12313");
    Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
        ContactServiceTester.removeContact(null);
        });
        assertEquals("ID is not found, or cannot be removed",exception.getMessage());
    
    assertEquals(2,ContactServiceTester.contactMap.size());
    
    assertNull(ContactServiceTester.getContactMapID("12313"));
    
    assertFalse(ContactServiceTester.contactMap.containsKey("12313"));
}
//this test is for ensuring the update Contact name method works appropriately
@Test
@DisplayName("TEST: changing first Name")
public void testUpdateFirstName() {
	ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
    ContactServiceTester.updateContactFirstName("12313", "Tommy");
    
    assertEquals("Tommy", ContactServiceTester.getContactMapID("12313").getFirstName(), "Name was not changed or updated");
    
    Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
    	ContactServiceTester.updateContactFirstName("12313", "Tommyyyyyyyyyyyyyyy");
    });
    assertEquals("Updated Length too long or null",exception.getMessage());
}
//this test is for ensuring the update Contact name method works appropriately
@Test
@DisplayName("TEST: changing last Name")
public void testUpdateLastName() {

  ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
  ContactServiceTester.updateContactLastName("12313", "Tommyson");
  
  assertEquals("Tommyson", ContactServiceTester.getContactMapID("12313").getLastName(), "Name was not changed or updated");
  
  Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
	  ContactServiceTester.updateContactLastName("12313", "Tommyyyyyyyyyyyyyyyson");
  });
  	assertEquals("Updated Length too long or null",exception.getMessage());
}
//this test ensures that the update Contact description method works appropriately
@Test
@DisplayName("TEST: changing Contact phone number")
public void testUpdateContactPhoneNumber() {
	ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
    ContactServiceTester.updateContactPhoneNumber("12313", "3335557777");
    
    assertEquals("3335557777", ContactServiceTester.getContactMapID("12313").getPhoneNumber(), "Description was not changed or updated");
    
    Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
    	ContactServiceTester.updateContactPhoneNumber("12313", "333555777798");
    });
        assertEquals("Updated Length too long or null",exception.getMessage());
}

//this test ensures that the update Contact description method works appropriately
@Test
@DisplayName("TEST: changing Contact home address")
public void testUpdateHomeAddress() {
	ContactServiceTester.newContact(ContactTester.getContactID(), ContactTester.getFirstName(), ContactTester.getLastName(), ContactTester.getPhoneNumber(), ContactTester.getHomeAddress());
  ContactServiceTester.updateContactHomeAddress("12313", "Bug Valley");
  
  assertEquals("Bug Valley", ContactServiceTester.getContactMapID("12313").getHomeAddress(), "Description was not changed or updated");
  
  Exception exception =  assertThrows(IllegalArgumentException.class, ()->{
	  ContactServiceTester.updateContactHomeAddress("12313", "BrokenSyntAxVALLLLEeeeey1");
  });
  	assertEquals("Updated Length too long or null",exception.getMessage());
}
}