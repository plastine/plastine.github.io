package ContactService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class ContactQueries {

    private final JDBC jdbc;

    // Constructor: inject JDBC dependency
    public ContactQueries(JDBC jdbc) {
        if (jdbc == null) {
            throw new IllegalArgumentException("JDBC cannot be null");
        }
        this.jdbc = jdbc;
    }

    /**
     * Retrieve all contacts from the database.
     *
     * @return a list of Contact objects; empty list if none found
     */
    public List<Contact> listContacts() {
        List<Contact> contacts = new ArrayList<>();
        String query = "SELECT * FROM contact_info";

        // Use try-with-resources for automatic closing
        try (Connection con = jdbc.DBresource();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String id = rs.getString("contactID");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phone = rs.getString("phone");
                String address = rs.getString("address");

                contacts.add(new Contact(id, firstName, lastName, phone, address));
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Optional: replace with logging
        }

        return contacts;
    }

    /**
     * Retrieve the first name of a contact by its unique ID.
     *
     * @param contactID the contact's unique ID
     * @return the first name if found; null if not found
     */
    public String getContactNameById(String contactID) {
        if (contactID == null || contactID.isEmpty()) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty");
        }

        String firstName = null;
        String query = "SELECT firstName FROM contact_info WHERE contactID = ?";

        try (Connection con = jdbc.DBresource();
             PreparedStatement pStmt = con.prepareStatement(query)) {

            pStmt.setString(1, contactID);

            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    firstName = rs.getString("firstName");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Optional: replace with logging
        }

        return firstName;
    }
}
