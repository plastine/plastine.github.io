package ContactService;
import java.util.HashMap;

/*this class is for the data structure and functionality of said data structure. This class is related to the Contact class. 
requirements as stated:The contact service shall be able to add contacts with unique ID.
The contact service shall be able to delete contacts per contactId.
The contact service shall be able to update contact fields per contactId. The following fields are updatable:
firstName
lastName
PhoneNumber
Address
*/
public class ContactService {
// the contacts will be stored in a hash map data structure
public HashMap <String, Contact> contactMap; 

//constructor for the contactMap hashmap
public ContactService() {
	contactMap = new HashMap<>();
}

//this method creates a new object inside of the hash-map
public void newContact(String contactID, String firstName, String lastName,String phoneNumber, String homeAddress ){
	if(!(contactMap.containsKey(contactID)==false)) {
		throw new IllegalArgumentException("Must be a Unique ID for contact, this ID already exists");
	}
	contactMap.put(contactID, new Contact(contactID, firstName, lastName, phoneNumber, homeAddress));
}

//this method removes contacts from the hash-map 
public void removeContact(String contactID) {
	if(!(contactMap.containsKey(contactID)==false) && contactID != null) {
	contactMap.remove(contactID);
	}
	else throw new IllegalArgumentException("ID is not found, or cannot be removed");
}
//this is used for contact ID retrieval. This is needed as the contact ID is what each contact will rely on through testing and functionality.
public Contact getContactMapID(String contactID) {
	return contactMap.get(contactID);
}
//each method from this point forward will automatically set each portion of the contact that are not the ID if they are empty with what is presented

//this method is for updating the first name
public void updateContactFirstName(String contactID, String firstNameUpdate ) {
	Contact updateContact = contactMap.get(contactID);
	if (updateContact !=null && (firstNameUpdate.length() <= 10&& firstNameUpdate != null)) {
		updateContact.setFirstName(firstNameUpdate);
	} 
	else {
		throw new IllegalArgumentException("Updated Length too long or null");
	}
	}
//this method is for updating the last name
public void updateContactLastName(String contactID, String lastNameUpdate) {
	Contact updateContact = contactMap.get(contactID);
	if (updateContact !=null && (lastNameUpdate.length() <= 10&& lastNameUpdate != null)) {
		updateContact.setLastName(lastNameUpdate);
	}
	
		else {
			throw new IllegalArgumentException("Updated Length too long or null");
		}
		
	
}

//this method is for updating the phone number
public void updateContactPhoneNumber(String contactID, String phoneNumberUpdate) {
	Contact updateContact = contactMap.get(contactID);
if (updateContact !=null && (phoneNumberUpdate.length() <= 10&& phoneNumberUpdate != null)) {
	updateContact.setPhoneNumber(phoneNumberUpdate);
}
else {
	throw new IllegalArgumentException("Updated Length too long or null");
}
}
//this method is for updating the home address
public void updateContactHomeAddress(String contactID, String homeAddressUpdate) {
	Contact updateContact = contactMap.get(contactID);
if (updateContact !=null && (homeAddressUpdate.length() <= 10&& homeAddressUpdate != null)) {
	updateContact.setHomeAddress(homeAddressUpdate);
}
else {
	throw new IllegalArgumentException("Updated Length too long or null");
}
}
}