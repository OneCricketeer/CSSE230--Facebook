import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Window.Type;
import java.awt.Component;
import java.awt.Toolkit;

public class NewUserWindow {

	private JFrame registerFrame;
	private JTextField nameTextField;
	/**
	 * @wbp.nonvisual location=282,59
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField uNameTextField;
	private JTextField textField;

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
		
		registerFrame.setResizable(false);
		registerFrame.setAlwaysOnTop(true);
		registerFrame.setBounds(100, 100, 250, 430);
		registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		registerFrame.getContentPane().setLayout(null);

		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(21, 116, 46, 14);
		registerFrame.getContentPane().add(lblName);

		nameTextField = new JTextField();
		nameTextField.setBounds(88, 113, 122, 20);
		registerFrame.getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(21, 144, 46, 14);
		registerFrame.getContentPane().add(lblGender);

		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(88, 140, 55, 23);
		registerFrame.getContentPane().add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(145, 140, 65, 23);
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

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(88, 43, 122, 20);
		registerFrame.getContentPane().add(textField);
	}
}
