import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;


public class CalendarControl extends JPanel {
	
	private JLabel lblCalendarMonth;
	final private CalendarPanel calPanel = new CalendarPanel();
	private JScrollPane scroller;
	/**
	 * Create the panel.
	 */
	public CalendarControl() {
		initialize();
		calPanel.setControl(scroller);
	}
	
	private void initialize() {
setLayout(null);
		
		
		JButton lastMonthButton = new JButton("<<");
		lastMonthButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lastMonthButton.setBounds(10, 11, 55, 45);
		add(lastMonthButton);
		
		JButton nextMonthButton = new JButton(">>");
		nextMonthButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nextMonthButton.setBounds(675, 11, 55, 45);
		add(nextMonthButton);
		
		lblCalendarMonth = new JLabel(calPanel.getMonthString() + " " + calPanel.getYear());
		lblCalendarMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalendarMonth.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCalendarMonth.setBounds(290, 21, 159, 20);
		add(lblCalendarMonth);
				
//		final CalendarPanel calPanel = new CalendarPanel();
		calPanel.setBounds(10, 64, 723, 396);
		calPanel.setDays();
		
		this.scroller = new JScrollPane();
		scroller.setBounds(10, 64, 723, 380);
		scroller.setViewportView(calPanel);
		add(scroller);
		
		lastMonthButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calPanel.previousMonth();
				updateMonth();
				CalendarControl.this.repaint();
			}
		});
		nextMonthButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calPanel.nextMonth();
				updateMonth();
				CalendarControl.this.repaint();
			}
		});
	}
	
	public void updateMonth() {
		lblCalendarMonth.setText(calPanel.getMonthString() + " "+ calPanel.getYear());
	}
}
