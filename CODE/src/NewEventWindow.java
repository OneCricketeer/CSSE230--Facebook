import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 12, 2013.
 */
public class NewEventWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewEventWindow frame = new NewEventWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewEventWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 165, 304, 86);
		contentPane.add(textField_2);
		
		final JComboBox startMoCombo = new JComboBox();
		startMoCombo.setBounds(10, 44, 67, 20);
		contentPane.add(startMoCombo);
		
		final JComboBox endMoCombo = new JComboBox();
		endMoCombo.setBounds(10, 109, 67, 20);
		contentPane.add(endMoCombo);
		
		final JComboBox startDayCombo = new JComboBox();
		startDayCombo.setBounds(87, 44, 54, 20);
		contentPane.add(startDayCombo);
		
		JComboBox startYrCombo = new JComboBox();
		startYrCombo.setBounds(151, 44, 67, 20);
		contentPane.add(startYrCombo);
		
		JComboBox endDayCombo = new JComboBox();
		endDayCombo.setBounds(87, 109, 54, 20);
		contentPane.add(endDayCombo);
		
		JComboBox endYrCombo = new JComboBox();
		endYrCombo.setBounds(151, 109, 67, 20);
		contentPane.add(endYrCombo);

		for (String month : CalendarPanel.MonthList) {
			startMoCombo.addItem(month.substring(0, 3));
			endMoCombo.addItem(month.substring(0, 3));
		}
		
		startMoCombo.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				startDayCombo.removeAll();
				int monthIndex = startMoCombo.getSelectedIndex();
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH,monthIndex);
				int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				for (int day =1 ; day <= maxDay;day++){
					startDayCombo.addItem(day);
				}
				
				
				
			}
		});
		
		
	}
}
