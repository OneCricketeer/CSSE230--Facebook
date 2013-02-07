package classes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Event {
	private ArrayList<Integer> attendeeIDs;
	private Calendar cal;
	private Date startTime, endTime;
	private String desc;

	public Event() {
		this.attendeeIDs = new ArrayList<Integer>();
		cal = Calendar.getInstance();
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
	
	public Date getStartTime(){
		return this.startTime;
	}
	
	public Date getEndTime(){
		return this.endTime;
	}
	
	public String getDesc(){
		return this.desc;
	}
	
	public void setDesc(String Desc){
		this.desc = Desc;	
	}

	@Override
	public String toString() {
		System.out.printf("Event: %s\n", this.desc);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
		System.out.printf("Start: %s\nEnd: %s\n", dateFormat.format(startTime), dateFormat.format(endTime));
		return "";
	}
}
