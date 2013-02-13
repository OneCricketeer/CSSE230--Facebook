import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

import classes.Event;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 6, 2013.
 */
public class CalendarPanel extends JPanel {
	private EventPanel[][] events = new EventPanel[5][7];
	private int Month;
	private int Year;
	private boolean eventViewerActive  = false;
	private static final String[] DayList = { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thursday", "Friday", "Saturday" };
	private static final String[] MonthList = { "January", "February", "March",
			"April", "May", "June", "July", "August", "September", "October",
			"November", "December" };

	public CalendarPanel() {
		setLayout(new GridLayout(6, 7, 0, 0));
		for (String day : DayList) {
			JPanel dayPanel = new JPanel() {
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D) g;
					
					if (!isOpaque()) {
						super.paintComponent(g);
						return;
					}
					int w = getWidth( );
					int h = getHeight( );
					 
					// Paint a gradient from top to bottom
					GradientPaint gp = new GradientPaint(
					    0, h, new Color(150, 0, 0),
					    0, 0, new Color(234, 20, 20));

					g2d.setPaint( gp );
					g2d.fillRect( 0, 0, w, h );

					
					
					setOpaque(false);
					super.paintComponent(g);
					setOpaque(true);
				}
			};
			JLabel dayLabel = new JLabel("<html><br />" + day + "</html>");
			dayLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
			dayLabel.setForeground(Color.WHITE);
			dayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			dayPanel.add(dayLabel);
			dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
			dayLabel.setVerticalAlignment(SwingConstants.CENTER);
			add(dayPanel);
		}

		initializeEvents();
		Calendar dCal = Calendar.getInstance();
		this.Month = dCal.get(Calendar.MONTH);
		this.Year = dCal.get(Calendar.YEAR);
		setDays();

	}

	/**
	 * TODO Put here a description of what this method does.
	 * 
	 */
	private void initializeEvents() {
		for (int week = 0; week < 5; week++)
			for (int day = 0; day < 7; day++) {				
				events[week][day] = createEventPanel();
			}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	private EventPanel createEventPanel() {
		final EventPanel evPanel = new EventPanel(1, new ArrayList<Event>());
		evPanel.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				CalendarPanel.this.eventViewerActive = true;
				final Component[] componenets = CalendarPanel.this.getComponents();
				removeAll();
				setLayout(new GridLayout(1, 1, 0, 0));
				repaint();
				final JPanel eventViewer = new JPanel(){
					@Override
					protected void paintComponent(Graphics g) {
						Graphics2D g2d = (Graphics2D) g;
						
						if (!isOpaque()) {
							super.paintComponent(g);
							return;
						}
						int w = getWidth( );
						int h = getHeight( );
						 
						// Paint a gradient from top to bottom
						GradientPaint gp = new GradientPaint(
						    0, h, new Color(150, 0, 0),
						    0, 0, new Color(234, 20, 20));

						g2d.setPaint( gp );
						g2d.fillRect( 0, 0, w, h );						
						
						setOpaque(false);
						super.paintComponent(g);
						setOpaque(true);
					}
				};
				eventViewer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				eventViewer.setLayout(new BorderLayout());
				JLabel eventText = new JLabel();
				eventText.setHorizontalAlignment(SwingConstants.CENTER);
				eventText.setFont(new Font("Tahoma", Font.BOLD, 20));
				eventText.setText(evPanel.getDescription());
				eventViewer.setVisible(true);
				JButton button = new JButton("Exit");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						CalendarPanel.this.eventViewerActive = false;
						remove(eventViewer);
						setLayout(new GridLayout(6, 7, 0, 0));
						for (Component c : componenets){
							c.setVisible(true);
							add(c);
						}
						repaint();
					}
				});
				eventViewer.add(eventText, BorderLayout.CENTER);
				eventViewer.add(button, BorderLayout.SOUTH);
				add(eventViewer);
				eventViewer.grabFocus();				
			}
		});
		return evPanel;
	}

	public void setDays() {
		while (this.Month > 11) {
			this.Year++;
			this.Month -= 12;
		}
		while (this.Month < 0) {
			this.Year--;
			this.Month += 12;
		}
		Calendar dCal = Calendar.getInstance();
		Date date = new Date(this.Year, this.Month, 1);

		// Get last month values
		setCalendar(dCal, -1, 0);
		int prev_maxDay = dCal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// Get this month values
		setCalendar(dCal, 0, 0);
		int firstDay = dCal.get(Calendar.DAY_OF_WEEK);
		int maxDay = dCal.getActualMaximum(Calendar.DAY_OF_MONTH);

		// Get next month values
		setCalendar(dCal, 1, 0);
		int next_firstDay = dCal.get(Calendar.DAY_OF_WEEK);
		int next_index = 1;

		// Initialize the start day values
		int dayStart = prev_maxDay - (firstDay - 2);
		boolean firstDayFound = false;
		int dayIndex = 1;

		for (int week = 0; week < 5; week++)
			for (int day = 0; day < 7; day++) {
				if ((day + 1) == firstDay && !firstDayFound)
					firstDayFound = true;
				EventPanel panel = this.events[week][day];
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if (firstDayFound && dayIndex <= maxDay) {
					panel.setDay((dayIndex++));
				} else if (!firstDayFound) {
					panel.setDay((dayStart++), Color.LIGHT_GRAY);
				} else if (dayIndex > maxDay) {
					panel.setDay((next_index++),Color.LIGHT_GRAY);
				}
				panel.setNewSize(this.getWidth()/7,this.getHeight()/6);
				Event e = new Event();
				e.setDesc("Test"+(week+1));
				panel.addEvent(e);
				add(panel);
			}
	}

	private void setCalendar(Calendar cal, int monthDiff, int yearDiff) {
		if (Month + monthDiff > 11) {
			yearDiff++;
			monthDiff -= 12;
		} else if (Month + monthDiff < 0) {
			yearDiff--;
			monthDiff += 12;
		}
		cal.set(this.Year + yearDiff, this.Month + monthDiff, 1);
	}

	public int getMonth() {
		return this.Month;
	}

	public int getYear() {
		return this.Year;
	}

	public int nextMonth() {
		if (this.eventViewerActive) return getMonth();
		if (++this.Month > 11) {
			this.Month -= 12;
			this.Year++;
		}
		setDays();
		return getMonth();
	}

	public int previousMonth() {
		if (this.eventViewerActive) return getMonth();
		if (--this.Month < 0) {
			this.Month += 12;
			this.Year--;
		}
		setDays();
		return getMonth();
	}

	public String getMonthString() {
		return MonthList[getMonth()];
	}

}
