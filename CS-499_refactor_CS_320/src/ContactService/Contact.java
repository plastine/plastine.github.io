package ContactService;

public class Contact {

	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String homeAddress;

	/*
	 * constructor for the Contact Class Illegal Argument exceptions appear here for
	 * requirements as stated: The contact object shall have a required unique
	 * contact ID String that cannot be longer than 10 characters. The contact ID
	 * shall not be null and shall not be updatable. The contact object shall have a
	 * required firstName String field that cannot be longer than 10 characters. The
	 * firstName field shall not be null. The contact object shall have a required
	 * lastName String field that cannot be longer than 10 characters. The lastName
	 * field shall not be null. The contact object shall have a required phone
	 * String field that must be exactly 10 digits. The phone field shall not be
	 * null. The contact object shall have a required address field that must be no
	 * longer than 30 characters. The address field shall not be null.
	 */
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String homeAddress) {
		if (contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Invalid contact ID");
		}
		this.contactID = contactID;
		if (firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
		if (lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
		if (phoneNumber == null || !(phoneNumber.length() == 10)) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		this.phoneNumber = phoneNumber;
		if (homeAddress == null || homeAddress.length() > 30) {
			throw new IllegalArgumentException("Invalid home address");
		}
		this.homeAddress = homeAddress;
	}

//getter methods for each object
	public String getContactID() {
		return contactID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

//setter methods for each object 

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

}
