import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import classes.Event;
import classes.SixDegrees;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 12, 2013.
 */
public class NewEventWindow{

	private JPanel contentPane;
	private JTextArea descriptionText;
	private Event ev;
	private User friend = null;

	/**
	 * Create the frame.
	 */
	public NewEventWindow() {
		final JFrame  eventWindow =new JFrame();
		eventWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		eventWindow.setTitle("Add Event");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		eventWindow.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		eventWindow.setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 140, 86, 14);
		contentPane.add(lblDescription);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(10, 75, 86, 14);
		contentPane.add(lblEndDate);

		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(10, 17, 86, 14);
		contentPane.add(lblStartDate);

		final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

		descriptionText = new JTextArea();
		descriptionText.setColumns(10);
		descriptionText.setRows(1);
		descriptionText.setLineWrap(true);
		descriptionText.setWrapStyleWord(true);
		descriptionText.setBounds(10, 165, 304, 86);
		JScrollPane descripScroll = new JScrollPane();
		descripScroll.setBounds(10, 165, 304, 86);
		descripScroll.setViewportView(descriptionText);
		contentPane.add(descripScroll);

		final JComboBox startMoCombo = new JComboBox();
		startMoCombo.setBounds(10, 44, 67, 20);
		contentPane.add(startMoCombo);

		final JComboBox endMoCombo = new JComboBox();
		endMoCombo.setBounds(10, 109, 67, 20);
		contentPane.add(endMoCombo);

		final JComboBox startDayCombo = new JComboBox();
		startDayCombo.setBounds(87, 44, 54, 20);
		contentPane.add(startDayCombo);

		final JComboBox startYrCombo = new JComboBox();
		startYrCombo.setBounds(151, 44, 67, 20);
		contentPane.add(startYrCombo);

		final JComboBox endDayCombo = new JComboBox();
		endDayCombo.setBounds(87, 109, 54, 20);
		contentPane.add(endDayCombo);

		final JComboBox endYrCombo = new JComboBox();
		endYrCombo.setBounds(151, 109, 67, 20);
		contentPane.add(endYrCombo);

		for (String month : CalendarPanel.MonthList) {
			startMoCombo.addItem(month.substring(0, 3));
			endMoCombo.addItem(month.substring(0, 3));
		}

		Calendar.getInstance();
		for (int year = Calendar.getInstance().get(Calendar.YEAR); year < Calendar
				.getInstance().get(Calendar.YEAR) + 5; year++) {
			startYrCombo.addItem(year);
			endYrCombo.addItem(year);
		}

		startMoCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				startDayCombo.removeAllItems();
				int monthIndex = startMoCombo.getSelectedIndex();
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH, monthIndex);
				int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int day = 1; day <= maxDay; day++) {
					startDayCombo.addItem(day);
				}

			}
		});
		endMoCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				endDayCombo.removeAllItems();
				int monthIndex = endMoCombo.getSelectedIndex();
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH, monthIndex);
				int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int day = 1; day <= maxDay; day++) {
					endDayCombo.addItem(day);
				}

			}
		});
		endMoCombo.setSelectedIndex(1);
		endMoCombo.setSelectedIndex(Calendar.MONTH - 1);
		startMoCombo.setSelectedIndex(1);
		startMoCombo.setSelectedIndex(Calendar.MONTH - 1);

		JButton btnNewButton = new JButton("Add Event");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String startDateStr = (startMoCombo.getSelectedIndex() + 1)
						+ "/"
						+ (startDayCombo.getSelectedIndex() + 1)
						+ "/"
						+ (startYrCombo.getSelectedIndex() + Calendar
								.getInstance().get(Calendar.YEAR));
				Date startDate = null;
				try {
					startDate = df.parse(startDateStr);
				} catch (ParseException exception1) {
					exception1.printStackTrace();
				}

				String endDateStr = (endMoCombo.getSelectedIndex() + 1)
						+ "/"
						+ (endDayCombo.getSelectedIndex() + 1)
						+ "/"
						+ (endYrCombo.getSelectedIndex() + Calendar
								.getInstance().get(Calendar.YEAR));
				Date endDate = null;
				try {
					endDate = df.parse(startDateStr);
				} catch (ParseException exception) {
					exception.printStackTrace();
				}

				ev = new Event(startDate, endDate);
				ev.setDesc(descriptionText.getText());
				SixDegrees.getCurrentUser().getCalendar().addEvent(ev);
				if(friend!=null){
					friend.getCalendar().addEvent(ev);
				}
				SixDegreesViewer.calendarControl.setDays();
				eventWindow.dispose();
			}
		});
		btnNewButton.setBounds(324, 215, 100, 36);
		contentPane.add(btnNewButton);
		eventWindow.setVisible(true);
	}

	public NewEventWindow(User friend) {
		this();
		this.friend = friend;
		
	}
}
