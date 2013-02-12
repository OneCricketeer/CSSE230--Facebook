package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.PriorityQueue;

public class MyCalendar implements Serializable, Runnable {
	private PriorityQueue<Event> events = new PriorityQueue<Event>();
	private Calendar cal = Calendar.getInstance();

	public MyCalendar() {
		this.events = new PriorityQueue<Event>();
//		Event now = new Event();
//		Date start, end;
//		Calendar s = Calendar.getInstance();
//		start = s.getTime();
//		s.set(2013, 1, 11, 3, 01, 0);
//		end = s.getTime();
//		Event later = new Event(start, end);
//		addEvent(now);
//		events.add(later);
//		Thread t = new Thread(this);
//		t.start();
	}

	public void addEvent(Event e) {
		this.events.add(e);
	}

	public Event removeNextEvent() {
		return this.events.poll();
	}

	public void removeEvent(Event e) {
		if (events.contains(e) && e != null)
			this.events.remove(e);
	}

	public static void main(String[] args) {
		MyCalendar myCal = new MyCalendar();

		// while (myCal.events.size() != 0) {
		// System.out.print(myCal.events + "\n");
		// }
		//
		//
		// System.out.println(myCal.events.size());
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (events.size() > 0) {
			System.out.println(events.size() + " " + events.peek().getEndTime() + " " + (events.peek().getEndTime().getTime() - System.currentTimeMillis()));
			if (events.peek().getEndTime().before(Calendar.getInstance().getTime())); {
				System.out.println("Removed event ending at "
						+ removeNextEvent().getEndTime());
			}
//			System.out.println("t: "
//					+ (events.peek().compareTo(cal.getTime()) < 0));
		}

	}
	
	/**
	 * To be called for testing purposes ONLY
	 * @return
	 */
	public ArrayList<Event> getEvents_List() {
		return new ArrayList<Event>(this.events);
	}
	
	public PriorityQueue<Event> getEvents_Queue() {
		return this.events;
	}

}
