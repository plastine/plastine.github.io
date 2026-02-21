package AppointmentService;
import java.util.Date;
import java.util.HashMap;

/*this class is for the data structure and functionality of said data structure. This class is related to the Appointment class.
Functional requirements as stated:
The appointment service shall be able to add appointments with a unique appointmentId.
The appointment service shall be able to delete appointments per appointmentId.
*/
public class AppointmentService {
// the Appointments will be stored in a hash-map data structure
public HashMap <String, Appointment> AppointmentMap; 

//constructor for the AppointmentMap hash-map
public AppointmentService() {
	AppointmentMap = new HashMap<>();
}

//this method creates a new object inside of the hash-map
//Exception derived from requirements as stated: The appointment ID shall not be null and shall not be updatable.
public void NewAppointment( String appointmentID, String appointmentDescription, Date appointmentDate ){
	if(!(AppointmentMap.containsKey(appointmentID) == false)) {
		throw new IllegalArgumentException("Must be a Unique ID for Appointment, this ID already exists");
	}
	else {
	AppointmentMap.put(appointmentID, new Appointment(appointmentID, appointmentDescription, appointmentDate));
	}
}

//this method removes Appointments from the hash-map 
public void DeleteAppointment(String AppointmentID) {
	if(!(AppointmentMap.containsKey(AppointmentID)==false) && AppointmentID != null ) {
		AppointmentMap.remove(AppointmentID);
	}
	else throw new IllegalArgumentException("ID is not found, or cannot be removed");
}
//this is used for Appointment ID retrieval. This is needed as the Appointment ID is what each Appointment will rely on through testing and functionality.
public Appointment getAppointmentMapID(String AppointmentID) {
	return AppointmentMap.get(AppointmentID);
}
}
