import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 6, 2013.
 */
public class CalendarPanel extends JPanel {
	private JPanel[][] events = new JPanel[5][7];
	private int Month;
	private int Year;
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
//			dayPanel.setBackground(new Color(178, 34, 34));
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
				events[week][day] = new JPanel();
			}

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
				JPanel panel = this.events[week][day];
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.removeAll();
				JLabel dayNumber = new JLabel();
				if (firstDayFound && dayIndex <= maxDay) {
					// dayNumber = new JLabel();
					dayNumber.setText((dayIndex++) + "");
					// panel.add(dayNumber);
				} else if (!firstDayFound) {
					// dayNumber = new JLabel();
					dayNumber.setText((dayStart++) + "");
					dayNumber.setForeground(Color.LIGHT_GRAY);
					// dayNumber.setHorizontalAlignment(SwingConstants.LEFT);
					// dayNumber.setVerticalAlignment(SwingConstants.TOP);
					// panel.add(dayNumber);
				} else if (dayIndex > maxDay) {
					// dayNumber = new JLabel();
					dayNumber.setText((next_index++) + "");
					dayNumber.setForeground(Color.LIGHT_GRAY);
					// dayNumber.setHorizontalAlignment(SwingConstants.LEFT);
					// dayNumber.setVerticalAlignment(SwingConstants.TOP);
				}

				dayNumber.setHorizontalAlignment(SwingConstants.LEFT);
				dayNumber.setVerticalAlignment(SwingConstants.TOP);
				panel.add(dayNumber);
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
		// System.out.printf("%s %d\n", MonthList[this.Month+monthDiff],
		// this.Year+yearDiff);
		cal.set(this.Year + yearDiff, this.Month + monthDiff, 1);
	}

	public int getMonth() {
		return this.Month;
	}

	public int getYear() {
		return this.Year;
	}

	public int nextMonth() {
		if (++this.Month > 11) {
			this.Month -= 12;
			this.Year++;
		}
		// myRepaint();
		setDays();
		return getMonth();
	}

	public int previousMonth() {
		if (--this.Month < 0) {
			this.Month += 12;
			this.Year--;
		}
		// myRepaint();
		setDays();
		return getMonth();
	}

	public String getMonthString() {
		return MonthList[getMonth()];
	}

}
