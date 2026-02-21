package ContactService.ContactServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ContactService.Contact;
import ContactService.ContactQueries;
import ContactService.JDBC;

class ContactQueriesTest {
	//set of mock objects used for Mockito in testing interactions with a database
    @Mock
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

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Inject mocked JDBC into ContactQueries
        queries = new ContactQueries(mockJdbc);

        // Mock JDBC connection
        when(mockJdbc.DBresource()).thenReturn(mockCon);

        // Mock PreparedStatement for getContactNameById()
        when(mockCon.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeQuery()).thenReturn(mockRsSingle);

        // Mock Statement for listContacts()
        when(mockCon.createStatement()).thenReturn(mockStmt);
        when(mockStmt.executeQuery(anyString())).thenReturn(mockRsList);
    }

    @Test
    @DisplayName("listContacts() returns a non-empty list with correct data")
    void testListContacts() throws Exception {
        // Simulate two rows dynamically for listContacts()
        final List<Contact> rows = new ArrayList<>();
        rows.add(new Contact("123", "Andrew", "Allen", "1234567890", "123 Main St"));
        rows.add(new Contact("456", "John", "Doe", "0987654321", "456 Elm St"));

        when(mockRsList.next()).thenAnswer(invocation -> !rows.isEmpty() && rows.remove(0) != null);
        when(mockRsList.getString("contactID")).thenReturn("123", "456");
        when(mockRsList.getString("firstName")).thenReturn("Andrew", "John");
        when(mockRsList.getString("lastName")).thenReturn("Allen", "Doe");
        when(mockRsList.getString("phone")).thenReturn("1234567890", "0987654321");
        when(mockRsList.getString("address")).thenReturn("123 Main St", "456 Elm St");

        List<Contact> contacts = queries.listContacts();

        assertNotNull(contacts);
        assertEquals(2, contacts.size());

        assertEquals("Andrew", contacts.get(0).getFirstName());
        assertEquals("John", contacts.get(1).getFirstName());
    }

    @Test
    @DisplayName("getContactNameById() returns first name for valid ID")
    void testGetContactNameByValidId() throws Exception {
        final String[] called = {null};

        when(mockRsSingle.next()).thenAnswer(invocation -> {
            if (called[0] == null) {
                called[0] = "called";
                return true; // first call -> row exists
            }
            return false; // second call -> no more rows
        });
        when(mockRsSingle.getString("firstName")).thenReturn("Andrew");

        String firstName = queries.getContactNameById("12313");

        assertNotNull(firstName);
        assertEquals("Andrew", firstName);

        verify(mockPs).setString(1, "12313");
    }

    @Test
    @DisplayName("getContactNameById() returns null for invalid ID")
    void testGetContactNameByInvalidId() throws Exception {
        when(mockRsSingle.next()).thenReturn(false);

        String firstName = queries.getContactNameById("99999");

        assertNull(firstName);

        verify(mockPs).setString(1, "99999");
    }

    @Test
    @DisplayName("listContacts() returns contacts with valid field lengths")
    void testListContactsContent() throws Exception {
        final List<Contact> rows = new ArrayList<>();
        rows.add(new Contact("123", "Andrew", "Allen", "1234567890", "123 Main St"));
        rows.add(new Contact("456", "John", "Doe", "0987654321", "456 Elm St"));

        when(mockRsList.next()).thenAnswer(invocation -> !rows.isEmpty() && rows.remove(0) != null);
        when(mockRsList.getString("contactID")).thenReturn("123", "456");
        when(mockRsList.getString("firstName")).thenReturn("Andrew", "John");
        when(mockRsList.getString("lastName")).thenReturn("Allen", "Doe");
        when(mockRsList.getString("phone")).thenReturn("1234567890", "0987654321");
        when(mockRsList.getString("address")).thenReturn("123 Main St", "456 Elm St");

        List<Contact> contacts = queries.listContacts();

        for (Contact c : contacts) {
            assertTrue(c.getContactID().length() <= 10);
            assertTrue(c.getFirstName().length() <= 10);
            assertTrue(c.getLastName().length() <= 10);
            assertEquals(10, c.getPhoneNumber().length());
            assertTrue(c.getHomeAddress().length() <= 30);
        }
    }
}
