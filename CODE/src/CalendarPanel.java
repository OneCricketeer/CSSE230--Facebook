import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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

	public CalendarPanel() {
		setLayout(new GridLayout(6, 7, 0, 0));
		for (JLabel day : days) {
			day.setHorizontalAlignment(SwingConstants.CENTER);
			day.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add(day);
		}

		initializeEvents();

		for (int week = 0; week < 5; week++)
			for (int day = 0; day < 7; day++) {
				JPanel panel = this.events[week][day];
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(panel);
			}
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

}
