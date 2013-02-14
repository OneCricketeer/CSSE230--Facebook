import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.JButton;

import org.omg.CORBA.Current;

import classes.SixDegrees;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewUserWindow {

	private JFrame registerFrame;
	private JTextField FNameTextField;
	/**
	 * @wbp.nonvisual location=282,59
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField uNameTextField;
	private JTextField emailTextField;
	private JTextField LNameTextField;
	private JTextField birthdayTextField;
	private JTextField workTextField;
	private JTextField dormTextField;
	private JTextField PhoneTextField;
	private JTextField addressTextField;
	private JTextField homeTextField;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// NewUserWindow window = new NewUserWindow();
	// window.registerFrame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public NewUserWindow() {
		initialize();
	}

	public void setVisible(boolean b) {
		this.registerFrame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {
		registerFrame = new JFrame();
		registerFrame.setTitle("6 Degrees Registration");
		registerFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				NewUserWindow.class.getResource("/DefaultUserMale.gif")));
		//registerFrame.setType(Type.POPUP);
		registerFrame.setResizable(false);
		registerFrame.setAlwaysOnTop(true);
		registerFrame.setBounds(100, 100, 250, 430);
		registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("First Name: ");
		lblName.setBounds(21, 73, 65, 14);
		registerFrame.getContentPane().add(lblName);

		FNameTextField = new JTextField();
		FNameTextField.setBounds(88, 70, 122, 20);
		registerFrame.getContentPane().add(FNameTextField);
		FNameTextField.setColumns(10);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(21, 326, 46, 14);
		registerFrame.getContentPane().add(lblGender);

		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(88, 322, 55, 23);
		registerFrame.getContentPane().add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(145, 322, 65, 23);
		registerFrame.getContentPane().add(rdbtnFemale);

		buttonGroup.add(rdbtnMale);
		buttonGroup.add(rdbtnFemale);

		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(21, 18, 55, 14);
		registerFrame.getContentPane().add(lblUsername);

		uNameTextField = new JTextField();
		uNameTextField.setBounds(88, 15, 122, 20);
		registerFrame.getContentPane().add(uNameTextField);
		uNameTextField.setColumns(10);

		JLabel emailLabel = new JLabel("Email: ");
		emailLabel.setBounds(21, 46, 55, 14);
		registerFrame.getContentPane().add(emailLabel);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(88, 43, 122, 20);
		registerFrame.getContentPane().add(emailTextField);

		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SixDegrees.load();
				Integer uID = SixDegrees.getMaxUserID()+1;

				User user = new User();
				user.setUID(uID);
				user.setUserName(uNameTextField.getText());
				user.setFname(FNameTextField.getText());
				user.setLname(LNameTextField.getText());
				user.setWork(workTextField.getText());
				user.setDorm(dormTextField.getText());
				user.setEmail(emailTextField.getText());
				user.setAddress(addressTextField.getText());
				user.setHometown(homeTextField.getText());
				if(rdbtnMale.isSelected()){
					user.setGender(true);
				}

				SimpleDateFormat fmt = new SimpleDateFormat("MMMMM dd yyyy");
				DecimalFormat phoneFmt = new DecimalFormat("####,###,###");

				try {
					user.setBirthday(fmt.parse(birthdayTextField.getText()));
					user.setPhone(phoneFmt.parse(PhoneTextField.getText()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				SixDegrees.addUser(user);
				SixDegrees.save();
				SixDegreesViewer.setDisplayedUser(user);
				setVisible(false);
			}
		});
		btnCreateUser.setBounds(70, 352, 107, 23);
		registerFrame.getContentPane().add(btnCreateUser);

		JLabel lblLastName = new JLabel("Last Name: ");
		lblLastName.setBounds(21, 102, 65, 14);
		registerFrame.getContentPane().add(lblLastName);

		LNameTextField = new JTextField();
		LNameTextField.setColumns(10);
		LNameTextField.setBounds(88, 99, 122, 20);
		registerFrame.getContentPane().add(LNameTextField);

		birthdayTextField = new JTextField();
		birthdayTextField.setColumns(10);
		birthdayTextField.setBounds(88, 127, 122, 20);
		registerFrame.getContentPane().add(birthdayTextField);

		JLabel lblBirthday = new JLabel("Birthday: ");
		lblBirthday.setBounds(21, 130, 65, 14);
		registerFrame.getContentPane().add(lblBirthday);

		workTextField = new JTextField();
		workTextField.setColumns(10);
		workTextField.setBounds(88, 158, 122, 20);
		registerFrame.getContentPane().add(workTextField);

		JLabel lblWork = new JLabel("Work: ");
		lblWork.setBounds(21, 161, 65, 14);
		registerFrame.getContentPane().add(lblWork);

		dormTextField = new JTextField();
		dormTextField.setColumns(10);
		dormTextField.setBounds(88, 189, 122, 20);
		registerFrame.getContentPane().add(dormTextField);

		JLabel lblDorm = new JLabel("Dorm: ");
		lblDorm.setBounds(21, 192, 65, 14);
		registerFrame.getContentPane().add(lblDorm);

		PhoneTextField = new JTextField();
		PhoneTextField.setColumns(10);
		PhoneTextField.setBounds(88, 220, 122, 20);
		registerFrame.getContentPane().add(PhoneTextField);

		JLabel lblPhone = new JLabel("Phone: ");
		lblPhone.setBounds(21, 223, 65, 14);
		registerFrame.getContentPane().add(lblPhone);

		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(88, 251, 122, 20);
		registerFrame.getContentPane().add(addressTextField);

		JLabel lblAddress = new JLabel("Address: ");
		lblAddress.setBounds(21, 254, 65, 14);
		registerFrame.getContentPane().add(lblAddress);

		homeTextField = new JTextField();
		homeTextField.setColumns(10);
		homeTextField.setBounds(88, 282, 122, 20);
		registerFrame.getContentPane().add(homeTextField);

		JLabel lblHometown = new JLabel("Hometown: ");
		lblHometown.setBounds(21, 285, 65, 14);
		registerFrame.getContentPane().add(lblHometown);
	}
}