package classes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Event implements Serializable, Comparable<Event> {
	ArrayList<Integer> attendeeIDs;
	java.util.Calendar cal = Calendar.getInstance();
	Date startTime, endTime;
	String desc;

	public Event() {
		this.attendeeIDs = new ArrayList<Integer>();
		this.desc = "<Event description>";

		// Set the default event length to one hour
		// this.startTime = cal.getTime();
		// cal.roll(Calendar.HOUR, 1);
		// this.endTime = cal.getTime();
		// cal.roll(Calendar.HOUR, -1); // rollback

		// Or... a few minutes... for testing purposes :)
		this.startTime = cal.getTime();
		cal.roll(Calendar.MINUTE, 1);
		this.endTime = cal.getTime();
		cal.roll(Calendar.MINUTE, -1); // rollback
	}

	public Event(Date start, Date end) {
		this();
		if (start.compareTo(end) > 0)
			throw new UnsupportedOperationException(start + " is after " + end);
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

	public Date getStartTime() {
		return this.startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public String getStartTime_String() {
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		return fmt.format(getStartTime());
	}

	public String getEndTime_String() {
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		return fmt.format(getEndTime());
	}

	public Date getLength() {
		return new Date(endTime.getTime() - startTime.getTime());
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		String s = "";
		s += String.format("Event: %s\n", getDesc());
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		s += String.format("Start: %s\nEnd: %s\n",
				dateFormat.format(startTime), dateFormat.format(endTime));
		return s;
	}

	@Override
	public int compareTo(Event e) {
		if (!(e instanceof Event))
			return 0;
		else {
			if (this.getStartTime().equals(e.getStartTime()))
				return this.endTime.compareTo(e.getEndTime());
			if (this.getStartTime().equals(e.getStartTime())
					&& this.getEndTime().equals(e.getEndTime()))
				return 0;
			if (this.getStartTime().after(e.getEndTime()))
				return 1;
			if (this.getEndTime().before(e.getStartTime())
					|| this.startTime.before(e.getStartTime()))
				return -1;

			return 0;
		}
	}

	public int compareTo(Date time) {
		return this.getStartTime().compareTo(time);
	}
}
