package AppointmentService;
import java.util.Date;
public class Appointment {
private final String appointmentID;
private String appointmentDescription;
private Date appointmentDate;


/*
 * Constructor for the Appointment Class Illegal Argument exceptions appear here
 * for requirements as stated:
 * 
 * The appointment object shall have a required unique appointment ID String
 * that cannot be longer than 10 characters. The appointment ID shall not be
 * null and shall not be updatable. The appointment object shall have a required
 * appointment Date field. The appointmentDate field cannot be in the past. The
 * appointmentDate field shall not be null. Note: Use java.util.Date for the
 * appointmentDate field and use before(new Date()) to check if the date is in
 * the past. The appointment object shall have a required description String
 * field that cannot be longer than 50 characters. The description field shall
 * not be null.
 */
public Appointment(String appointmentID, String appointmentDescription, Date appointmentDate){

	if ( appointmentID == null || appointmentID.length() > 10) { 
		throw new IllegalArgumentException("Invalid Appointment ID");
	}
	this.appointmentID = appointmentID;

	if (appointmentDescription == null || appointmentDescription.length() > 50) {
		throw new IllegalArgumentException("Invalid Appointment Description length, can neither be 0 nor greater than 50 characters");
	}
	this.appointmentDescription = appointmentDescription;

	if (appointmentDate == null || (!appointmentDate.before(new Date((System.currentTimeMillis() * 60 * 60 * 240))))) {
		throw new IllegalArgumentException("Invalid Appointment Date provided or no date is provided. Please pick a day after today");
	}
	this.appointmentDate = appointmentDate;
}


//getter methods for each object
public String getAppointmentID() {
	return appointmentID;
}

public String getAppointmentDescription() {
	return appointmentDescription;
}
public Date getAppointmentDate() {
	return appointmentDate;
}
}
