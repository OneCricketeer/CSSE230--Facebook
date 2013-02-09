package classes;
import java.io.Serializable;
import java.util.Date;
import java.util.PriorityQueue;


public class MyCalendar implements Serializable {
	PriorityQueue<Event> events = new PriorityQueue<Event>();
	
	public MyCalendar() {
		this.events = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event e) {
		this.events.add(e);
	}
	
	public Event removeEvent() {
		return this.events.poll();
	}
	
	public void removeEvent(Event e) {
		if (events.contains(e) && e != null)
			this.events.remove(e);
	}
	
}
