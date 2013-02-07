package classes;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {
	ArrayList<Integer> attendeeIDs;
	java.util.Calendar cal;
	Date startTime, endTime;
	String desc;

	public Event() {
		this.attendeeIDs = new ArrayList<Integer>();
		this.cal = java.util.Calendar.getInstance();
		this.desc = "<Event description>";
		this.startTime = cal.getTime();
		this.endTime = cal.getTime();
	}

	public Event(Date start, Date end) {
		this();
		this.startTime = start;
		this.endTime = end;
	}

	public void addAttendee(Integer uid) {
		this.attendeeIDs.add(uid);
	}

	public void removeAttendee(Integer uid) {
		this.attendeeIDs.remove(uid);
	}
	
	public ArrayList<Integer> getAttendees() {
		return attendeeIDs;
	}
	

	@Override
	public String toString() {
		System.out.printf("Event: %s\n", this.desc);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		System.out.printf("Start: %s\nEnd: %s\n", dateFormat.format(startTime), dateFormat.format(endTime));
		return "";
	}
}
