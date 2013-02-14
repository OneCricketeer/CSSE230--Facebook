import gui.ImagePanel;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTree;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.JTextField;
import javax.swing.JButton;

import classes.SixDegrees;
import classes.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {
	private static User current;
	private static JFrame frmSixDegrees;
	private JTextField textFriendSearch;
	private JTextField textGeneralSearch;
	private static JLabel lblStatusMessage;
	private static JLabel lblBasicInfoMessage;
	private static JLabel lblNameLabel;
	private static JLabel lblContactinfomessage;
	private static JLabel lblAbouttext;
	private static JPanel myPagePanel;
	private static JPanel LoginPanel;
	private static SixDegrees data;
	static JTabbedPane tabViewer;
	private JTextField uNameTextField;
	private JTextField emailTextField;
	private JTextField StatusTextField;
	private static JPanel searchPanel;
	private static JPanel friendsPanel;
	private static JPanel meetingsPanel;
	private JTextField phoneText;
	private JTextField addressText;
	private JTextField emailText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final boolean design = true;
		data = new SixDegrees();

		data.addUser(new User("sternetj", "Teddy", "Sterne"));
		data.addUser(new User("yadavy", "Yashi", "Yadav"));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDegreesViewer window = new SixDegreesViewer();
					if (!design) {
						User pSherman = new User("pSherman", "P.", "Sherman");
						window.setCurrentUser(pSherman);
					}

					window.frmSixDegrees.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SixDegreesViewer() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSixDegrees = new JFrame();
		frmSixDegrees.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				SixDegrees.save();
			}
		});
		frmSixDegrees.setIconImage(Toolkit.getDefaultToolkit().getImage(
				SixDegreesViewer.class.getResource("/logo.png")));
		frmSixDegrees.setResizable(false);
		frmSixDegrees.setTitle("Six Degrees");
		frmSixDegrees.setBounds(100, 100, 754, 586);
		frmSixDegrees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSixDegrees.getContentPane().setLayout(null);

		tabViewer = new JTabbedPane(JTabbedPane.TOP);
		tabViewer.setBounds(0, 0, 748, 558);
		frmSixDegrees.getContentPane().add(tabViewer);

		LoginPanel = new JPanel();
		tabViewer.addTab("Login", null, LoginPanel, null);

		LoginPanel.setLayout(null);
		addLogo(LoginPanel);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserWindow n = new NewUserWindow();
				n.setVisible(true);
				tabViewer.setSelectedIndex(0);
			}
		});
		btnCreateNewUser.setBounds(476, 247, 126, 32);
		LoginPanel.add(btnCreateNewUser);

		uNameTextField = new JTextField();
		uNameTextField.setBounds(150, 135, 175, 32);
		LoginPanel.add(uNameTextField);
		uNameTextField.setColumns(10);

		JLabel lblNewLabel = new JLabel("User Name:");
		lblNewLabel.setBounds(71, 137, 69, 29);
		LoginPanel.add(lblNewLabel);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLogin.setBounds(71, 94, 69, 29);
		LoginPanel.add(lblLogin);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(71, 191, 69, 29);
		LoginPanel.add(lblEmail);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(150, 189, 175, 32);
		LoginPanel.add(emailTextField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SixDegrees.load();
				boolean findUser = false;
				for (User u : SixDegrees.getUsers().values()) {
					if (u.getUserName().equals(uNameTextField.getText())) {
						if (u.getEmail().equals(emailTextField.getText())) {
							setCurrentUser(u);
							findUser = true;
							tabViewer.remove(0);
							tabViewer
									.addTab("My Page", null, myPagePanel, null);
							tabViewer.addTab("Meetings", null, meetingsPanel,
									null);
							tabViewer.addTab("Friends", null, friendsPanel,
									null);
							tabViewer.addTab("Search", null, searchPanel, null);
							tabViewer.setSelectedIndex(0);
						} else
							JOptionPane.showMessageDialog(null,
									"User Name mismatch with Email!",
									"Login Error", JOptionPane.ERROR_MESSAGE);
					}
				}

				if (!findUser)
					JOptionPane.showMessageDialog(null, "User doesn't exist!",
							"Login Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnLogin.setBounds(150, 247, 78, 32);
		LoginPanel.add(btnLogin);

		JLabel lblHaventRegisteredYet = new JLabel("Haven't Registered Yet?");
		lblHaventRegisteredYet.setBounds(476, 159, 159, 32);
		LoginPanel.add(lblHaventRegisteredYet);

		JLabel lblCreateNewUser = new JLabel("Create New User Here:");
		lblCreateNewUser.setBounds(476, 191, 143, 28);
		LoginPanel.add(lblCreateNewUser);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnClose.setBounds(249, 247, 78, 32);
		LoginPanel.add(btnClose);

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.setBounds(71, 11, 50, 53);
		LoginPanel.add(imagePanel);

		ImagePanel imagePanel_1 = new ImagePanel();
		imagePanel_1.setBounds(115, 348, 69, 68);
		LoginPanel.add(imagePanel_1);

		ImagePanel imagePanel_2 = new ImagePanel();
		imagePanel_2.setBounds(388, 81, 59, 53);
		LoginPanel.add(imagePanel_2);

		ImagePanel imagePanel_3 = new ImagePanel();
		imagePanel_3.setBounds(416, 291, 50, 53);
		LoginPanel.add(imagePanel_3);

		ImagePanel imagePanel_5 = new ImagePanel();
		imagePanel_5.setBounds(683, 11, 50, 53);
		LoginPanel.add(imagePanel_5);

		myPagePanel = new JPanel();
		tabViewer.addTab("My Page", null, myPagePanel, null);
		myPagePanel.setLayout(null);

		ImagePanel profilePictureFrame = new ImagePanel(
				"./src/IMAGES/DefaultUserFemale.gif", 120, 120);
		profilePictureFrame.setBounds(10, 11, 120, 120);
		myPagePanel.add(profilePictureFrame);

		lblNameLabel = new JLabel("Name");
		lblNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNameLabel.setBounds(10, 142, 214, 28);
		myPagePanel.add(lblNameLabel);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(new Color(178, 34, 34));
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStatus.setBounds(140, 14, 214, 28);
		myPagePanel.add(lblStatus);

		lblStatusMessage = new JLabel("Status Message");
		lblStatusMessage.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					lblStatusMessage.setVisible(false);
					StatusTextField.setVisible(true);
				}
			}
		});

		lblStatusMessage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStatusMessage.setBounds(140, 53, 228, 28);
		myPagePanel.add(lblStatusMessage);

		JLabel lblContactInfo = new JLabel("Contact Info:");
		lblContactInfo.setForeground(new Color(178, 34, 34));
		lblContactInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContactInfo.setBounds(10, 283, 214, 28);
		myPagePanel.add(lblContactInfo);

		JLabel lblAbout = new JLabel("About:");
		lblAbout.setForeground(new Color(178, 34, 34));
		lblAbout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAbout.setBounds(10, 181, 214, 28);
		myPagePanel.add(lblAbout);

		JLabel lblBasicInfo = new JLabel("Basic Info:");
		lblBasicInfo.setForeground(new Color(178, 34, 34));
		lblBasicInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBasicInfo.setBounds(10, 407, 214, 28);
		myPagePanel.add(lblBasicInfo);

		lblAbouttext = new JLabel("AboutText");
		lblAbouttext.setVerticalAlignment(SwingConstants.TOP);
		lblAbouttext.setForeground(new Color(0, 0, 0));
		lblAbouttext.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAbouttext.setBounds(46, 210, 403, 62);
		myPagePanel.add(lblAbouttext);

		JLabel lblContactLabel = new JLabel(
				"<html>Phone:<br />Address:<br /><br />Email:</html>");
		lblContactLabel.setVerticalAlignment(SwingConstants.TOP);
		lblContactLabel.setForeground(Color.BLACK);
		lblContactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContactLabel.setBounds(46, 315, 52, 81);
		myPagePanel.add(lblContactLabel);

		lblContactinfomessage = new JLabel("ContactInfoMessage");
		lblContactinfomessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					lblContactinfomessage.setVisible(false);
					phoneText.setVisible(true);
					addressText.setVisible(true);
					emailText.setVisible(true);
				}

			}
		});
		lblContactinfomessage.setVerticalAlignment(SwingConstants.TOP);
		lblContactinfomessage.setForeground(Color.BLACK);
		lblContactinfomessage.setFont(new Font("Times New Roman", Font.PLAIN,
				14));
		lblContactinfomessage.setBounds(110, 316, 346, 81);
		myPagePanel.add(lblContactinfomessage);

		JLabel lblBasicInfoLabel = new JLabel(
				"<html>Birthdate:<br />Sex:<br />Hometown:</html>");
		lblBasicInfoLabel.setVerticalAlignment(SwingConstants.TOP);
		lblBasicInfoLabel.setForeground(Color.BLACK);
		lblBasicInfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBasicInfoLabel.setBounds(46, 438, 70, 81);
		myPagePanel.add(lblBasicInfoLabel);

		lblBasicInfoMessage = new JLabel("BasicInfoMessage");
		lblBasicInfoMessage.setVerticalAlignment(SwingConstants.TOP);
		lblBasicInfoMessage.setForeground(Color.BLACK);
		lblBasicInfoMessage
				.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBasicInfoMessage.setBounds(114, 438, 353, 54);
		myPagePanel.add(lblBasicInfoMessage);

		addLogo(myPagePanel);

		StatusTextField = new JTextField();
		StatusTextField.setVisible(false);
		StatusTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String status = StatusTextField.getText();
					SixDegrees.getCurrentUser().setStatus(status);
					StatusTextField.setVisible(false);
					lblStatusMessage.setVisible(true);
					lblStatusMessage.setText(StatusTextField.getText());
				}
			}
		});
		StatusTextField.setText("What are you doing?");
		StatusTextField.setBounds(140, 53, 250, 28);
		myPagePanel.add(StatusTextField);
		StatusTextField.setColumns(10);

		phoneText = new JTextField();
		phoneText.setVisible(false);
		phoneText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					current = SixDegrees.getCurrentUser();
					DecimalFormat phoneFmt = new DecimalFormat("####,###,###");
					try {
						current.setPhone(phoneFmt.parse(phoneText.getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					setCurrentUser(current);
					emailText.setVisible(false);
					addressText.setVisible(false);
					phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		phoneText.setBounds(110, 315, 150, 18);
		myPagePanel.add(phoneText);
		phoneText.setColumns(10);

		addressText = new JTextField();
		addressText.setVisible(false);
		addressText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					current = SixDegrees.getCurrentUser();
					current.setAddress(addressText.getText());
					setCurrentUser(current);
					emailText.setVisible(false);
					addressText.setVisible(false);
					phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		addressText.setColumns(10);
		addressText.setBounds(110, 338, 150, 18);
		myPagePanel.add(addressText);

		emailText = new JTextField();
		emailText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					current = SixDegrees.getCurrentUser();
					current.setEmail(emailText.getText());
					setCurrentUser(current);
					emailText.setVisible(false);
					addressText.setVisible(false);
					phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		emailText.setVisible(false);
		emailText.setColumns(10);
		emailText.setBounds(110, 365, 150, 18);
		myPagePanel.add(emailText);

		// /MEETINGS/////////////////////////////////////////////////////////////

		meetingsPanel = new JPanel();
		tabViewer.addTab("Meetings", null, meetingsPanel, null);
		meetingsPanel.setLayout(null);

		CalendarControl calendarControl = new CalendarControl();
		calendarControl.setBounds(0, 0, 743, 465);
		meetingsPanel.add(calendarControl);

		addLogo(meetingsPanel);

		JButton btnNewEvent = new JButton("New Event");
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						SixDegreesViewer.this.frmSixDegrees, "Message");
				// JOptionPane.showConfirmDialog(SixDegreesViewer.this,"Message");
				// SixDegrees.getCurrentUser().addEvent();
			}
		});
		btnNewEvent.setBounds(10, 476, 106, 35);
		meetingsPanel.add(btnNewEvent);

		// /FRIENDS//////////////////////////////////////////////////////////////////

		friendsPanel = new JPanel();
		tabViewer.addTab("Friends", null, friendsPanel, null);
		friendsPanel.setLayout(null);

		addLogo(friendsPanel);

		final JScrollPane friendsScrollPane = new JScrollPane();
		friendsScrollPane.setBounds(10, 64, 723, 396);
		friendsPanel.add(friendsScrollPane);

		final JPanel friendScroller = new JPanel();
		friendsScrollPane.setViewportView(friendScroller);
		friendScroller.setLayout(new BorderLayout(0, 0));

		final JPanel columnPanel = new JPanel();
		friendScroller.add(columnPanel, BorderLayout.NORTH);
		columnPanel.setLayout(new GridLayout(0, 1, 0, 1));

		// FriendPanel panel_1 = new FriendPanel();
		// panel_1.setBounds(0, 0, 1200, 900);
		FriendPanel rowPanel = null;

		for (int i = 1; i < 3; i++) {
			rowPanel = new FriendPanel();
			rowPanel.setPreferredSize(new Dimension(120, 90));
			columnPanel.add(rowPanel);
			rowPanel.setLayout(null);

		}

		textFriendSearch = new JTextField();
		textFriendSearch.setBounds(245, 24, 168, 20);
		friendsPanel.add(textFriendSearch);
		textFriendSearch.setColumns(10);

		final JButton btnFriendSearch = new JButton("Search");

		btnFriendSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				columnPanel.removeAll();
				String query = textFriendSearch.getText().toLowerCase();
				System.out.println(query);
				for (User u : data.getUsers().values()) {
					if (u.getName().equalsIgnoreCase(query)
							|| u.getFname().equalsIgnoreCase(query)
							|| u.getLname().equalsIgnoreCase(query)
							|| u.getUserName().equalsIgnoreCase(query)) {
						FriendPanel rowPanel = new FriendPanel();
						rowPanel.setUsername(u.getName());
						rowPanel.setPreferredSize(new Dimension(120, 90));
						columnPanel.add(rowPanel);
						rowPanel.setLayout(null);
						btnFriendSearch.setFocusable(false);

					}
					btnFriendSearch.setFocusable(true);
				}

			}

		});
		btnFriendSearch.setBounds(423, 23, 89, 23);
		friendsPanel.add(btnFriendSearch);

		searchPanel = new JPanel();
		tabViewer.addTab("Search", null, searchPanel, null);
		searchPanel.setLayout(null);

		addLogo(searchPanel);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 64, 723, 396);
		searchPanel.add(scrollPane_1);

		textGeneralSearch = new JTextField();
		textGeneralSearch.setColumns(10);
		textGeneralSearch.setBounds(245, 24, 168, 20);
		searchPanel.add(textGeneralSearch);

		JButton btnGeneralSearch = new JButton("Search");
		btnGeneralSearch.setBounds(423, 23, 89, 23);
		searchPanel.add(btnGeneralSearch);

	}

	private void addLogo(JComponent comp) {
		ImagePanel image = new ImagePanel();
		image.setBounds(618, 416, 115, 103);
		comp.add(image);
	}

	public static void setCurrentUser(User u) {
		SixDegrees.setCurrentUser(u);
		lblStatusMessage.setText(u.getStatus());
		lblNameLabel.setText(u.getName());
		lblBasicInfoMessage.setText(u.getBasicInfo());
		lblContactinfomessage.setText(u.getContactInfo());
		lblAbouttext.setText(u.getAbout());
	}
}
