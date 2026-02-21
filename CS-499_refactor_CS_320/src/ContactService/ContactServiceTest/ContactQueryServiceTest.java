package ContactService.ContactServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ContactService.Contact;
import ContactService.ContactQueries;
import ContactService.JDBC;

class ContactQueriesServiceTest {
	//various mock objects created for testing
	
    @Mock //mock database connection
    private JDBC mockJdbc;

    @Mock
    private Connection mockCon;

    @Mock
    private PreparedStatement mockPs;

    @Mock
    private Statement mockStmt;

    @Mock 
    private ResultSet mockRsList;

    @Mock
    private ResultSet mockRsSingle;

    private ContactQueries queries;

    private final String VALID_CONTACT_ID = "12313";
    private final String INVALID_CONTACT_ID = "99999";

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Inject mocked JDBC into ContactQueries
        queries = new ContactQueries(mockJdbc);

        // Mock JDBC connection
        when(mockJdbc.DBresource()).thenReturn(mockCon);

        // Mock PreparedStatement for getContactNameById
        when(mockCon.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRsSingle);

        // Mock Statement for listContacts
        when(mockCon.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeQuery(anyString())).thenReturn(mockRsList);
    }

    @Test
    @DisplayName("TEST: listContacts returns contacts")
    void testListContacts() throws Exception {
        // Mock two rows in ResultSet for listContacts()
        when(mockRsList.next()).thenReturn(true, true, false);
        when(mockRsList.getString("contactID")).thenReturn("123", "456");
        when(mockRsList.getString("firstName")).thenReturn("Andrew", "John");
        when(mockRsList.getString("lastName")).thenReturn("Allen", "Doe");
        when(mockRsList.getString("phone")).thenReturn("1234567890", "0987654321");
        when(mockRsList.getString("address")).thenReturn("123 Main St", "456 Elm St");

        List<Contact> contacts = queries.listContacts();

        assertNotNull(contacts, "Contacts list should not be null");
        assertEquals(2, contacts.size(), "Contacts list should contain 2 items");

        assertEquals("Andrew", contacts.get(0).getFirstName());
        assertEquals("John", contacts.get(1).getFirstName());
    }

    @Test
    @DisplayName("TEST: getContactNameById returns first name for valid ID")
    void testGetContactNameByValidId() throws Exception {
        when(mockRsSingle.next()).thenReturn(true);
        when(mockRsSingle.getString("firstName")).thenReturn("Andrew");

        String firstName = queries.getContactNameById(VALID_CONTACT_ID);

        assertNotNull(firstName, "First name should not be null");
        assertEquals("Andrew", firstName, "First name should match expected value");

        verify(mockPs).setString(1, VALID_CONTACT_ID);
    }

    @Test
    @DisplayName("TEST: getContactNameById returns null for invalid ID")
    void testGetContactNameByInvalidId() throws Exception {
        when(mockRsSingle.next()).thenReturn(false);

        String firstName = queries.getContactNameById(INVALID_CONTACT_ID);

        assertNull(firstName, "First name should be null for invalid ID");

        verify(mockPs).setString(1, INVALID_CONTACT_ID);
    }

    @Test
    @DisplayName("TEST: listContacts returns valid Contact objects")
    void testListContactsContent() throws Exception {
        when(mockRsList.next()).thenReturn(true, true, false);
        when(mockRsList.getString("contactID")).thenReturn("123", "456");
        when(mockRsList.getString("firstName")).thenReturn("Andrew", "John");
        when(mockRsList.getString("lastName")).thenReturn("Allen", "Doe");
        when(mockRsList.getString("phone")).thenReturn("1234567890", "0987654321");
        when(mockRsList.getString("address")).thenReturn("123 Main St", "456 Elm St");

        List<Contact> contacts = queries.listContacts();

        for (Contact c : contacts) {
            assertTrue(c.getContactID().length() <= 10, "ContactID must be <= 10 chars");
            assertTrue(c.getFirstName().length() <= 10, "FirstName must be <= 10 chars");
            assertTrue(c.getLastName().length() <= 10, "LastName must be <= 10 chars");
            assertEquals(10, c.getPhoneNumber().length(), "Phone must be exactly 10 digits");
            assertTrue(c.getHomeAddress().length() <= 30, "Address must be <= 30 chars");
        }
    }
}
