import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classes.Event;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 8, 2013.
 */
public class EventPanel extends JPanel {
	JLabel lblDate;
	JLabel lblEvent;
	ArrayList<Event> events;

	/**
	 * Create the panel.
	 * 
	 * @param Day
	 * @param events
	 */
	public EventPanel(int Day, ArrayList<Event> evs) {
		setLayout(null);
		events = evs;
		this.lblDate = new JLabel(Day + "");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		// lblDate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(lblDate);

		setEvents();

	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	private void setEvents() {
		String eventString = "<html>";
		if (events != null) {
			if (events.size() > 1) {
				eventString += "<p>" + (this.events.size())
						+ " events (click to view)</p>";
			} else if (events.size() == 1) {
				eventString += "<p>" + this.events.get(0).getDesc() + "</p>";
			}
		}
		eventString += "</html>";
		if (lblEvent != null)
			lblEvent.setText(eventString);
		else
			lblEvent = new JLabel(eventString);
		lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// lblEvent.setHorizontalAlignment(SwingConstants.CENTER);

		add(lblEvent);

	}

	public void setDay(int day) {
		this.lblDate.setText(day + "");
	}

	public void setDay(int day, Color color) {
		this.lblDate.setText(day + "");
		this.lblDate.setForeground(color);
	}

	public void setNewSize(int width, int height) {
		this.setSize(width, height);
		lblDate.setSize(width / 5, height / 3);
		lblEvent.setSize(width, (2 * height) / 3);
		lblEvent.setLocation(0, height / 3);

	}

	public void addEvent(Event ev) {
		this.events.add(ev);
		setEvents();
	}
	
	public void removeAllEvents() {
		this.events = new ArrayList<Event>();
	}

	public String getDescription() {
		String eventString = "<html>";
		if (events != null) {
			for (Event ev : this.events)
				eventString += "<p>" + ev.getDesc() + "</p>";
		} else {
			eventString += "<p>No events today</p>";
		}
		eventString += "</html>";
		return eventString;

	}
	
	public boolean hasEvent(Event e) {
		if(events.contains(e)){
			return true;
		}
		return false;
	}

}
