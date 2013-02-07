import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
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
	private static final JLabel[] days = { new JLabel("Sunday"),
			new JLabel("Monday"), new JLabel("Tuesday"),
			new JLabel("Wednesday"), new JLabel("Thursday"),
			new JLabel("Friday"), new JLabel("Saturday") };
	private int Month;
	private int Year;

	@SuppressWarnings("deprecation")
	public CalendarPanel() {
		setLayout(new GridLayout(6, 7, 0, 0));
		for (JLabel day : days) {
			day.setHorizontalAlignment(SwingConstants.CENTER);
			day.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add(day);
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
				events[week][day] = new JPanel() ;
			}

	}
	
	public void setDays(){
			while (this.Month > 11){
				this.Year++;
				this.Month -= 12;
			}
			while (this.Month < 0){
				this.Year--;
				this.Month += 12;
			}		
		   Calendar dCal = Calendar.getInstance();
		   Date date = new Date(this.Year,this.Month,1);
		   dCal.set(this.Year,this.Month,1);
		   int firstDay = dCal.get(Calendar.DAY_OF_WEEK);
		   int maxDay = dCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		   
		   boolean firstDayFound = false;
		   int dayIndex=1;
		for (int week = 0; week < 5; week++)
			for (int day = 0; day < 7; day++) {
				if ((day+1) == firstDay && !firstDayFound)
					firstDayFound = true;
				JPanel panel = this.events[week][day];
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				panel.removeAll();
				if (firstDayFound && dayIndex <= maxDay)panel.add(new JLabel((dayIndex++) + ""));					
				add(panel);
			}
		myRepaint();
	}
	
	public void myRepaint() {
		super.repaint();
		for (int week = 0; week < 5; week++)
			for (int day = 0; day < 7; day++) {
				this.events[week][day].repaint();
				//System.out.println(((JLabel) this.events[week][day].getComponent()).getText());
			}
	}
	
	public int getMonth(){
		return this.Month;
	}
	
	public int getYear(){
		return this.Year;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int nextMonth() {
		return ++this.Month;
	}

}
