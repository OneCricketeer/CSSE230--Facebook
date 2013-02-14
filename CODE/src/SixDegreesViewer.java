import gui.ExpandableLabel;
import gui.ImagePanel;

import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTree;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.JTextField;
import javax.swing.JButton;

//import SearchPanel.PopupListener;

import classes.Group;
import classes.SixDegrees;
import classes.User;
import classes.XMLFileIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {

	private JFrame frmSixDegrees;
	private JTextField textFriendSearch;
	private JTextField textGeneralSearch;
	private static JPanel myPagePanel;
	private static User current;
	private static JLabel lblStatusMessage;
	private static JLabel lblBasicInfoMessage;
	private static JLabel lblNameLabel;
	private static JLabel lblContactinfomessage;
	private static JLabel lblAbouttext;
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
	private static final int subHeadingHeight = 20;
	private static User displayed;
	private static ImagePanel profilePictureFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		SixDegrees.load();
		ArrayList<User> users = new ArrayList<User>();
		for(User u:SixDegrees.getUsers().values()){
			users.add(u);
		}
		 users.get(0).addFriend(users.get(1));
		 users.get(1).addFriend(users.get(2));
		 users.get(2).addFriend(users.get(3));
		 		 
		 System.out.println(((User)SixDegrees.getUsers().values().toArray()[0]).getFname());
		 System.out.println(((User)SixDegrees.getUsers().values().toArray()[1]).getFname());
		 System.out.println(((User)SixDegrees.getUsers().values().toArray()[2]).getFname());
		 System.out.println(((User)SixDegrees.getUsers().values().toArray()[3]).getFname());

		final User usr = SixDegrees.getUsers().get(1);
		usr.setDorm("Percopo");
		usr.setWork("Ventures");
		SixDegrees.setCurrentUser(usr);
		SixDegreesViewer window = new SixDegreesViewer();
		window.frmSixDegrees.setVisible(true);
		window.uNameTextField.grabFocus();
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
		this.frmSixDegrees = new JFrame();
		this.frmSixDegrees.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				SixDegrees.save();
			}
		});
		this.frmSixDegrees.setIconImage(Toolkit.getDefaultToolkit().getImage(
				SixDegreesViewer.class.getResource("/logo.png")));
		this.frmSixDegrees.setResizable(false);
		this.frmSixDegrees.setTitle("Six Degrees");
		this.frmSixDegrees.setBounds(100, 100, 754, 586);
		this.frmSixDegrees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frmSixDegrees.getContentPane().setLayout(null);

		final JTabbedPane tabViewer = new JTabbedPane(JTabbedPane.TOP);
		tabViewer.setBounds(0, 0, 748, 558);
		this.frmSixDegrees.getContentPane().add(tabViewer);

		LoginPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File(
							"./src/IMAGES/SixDegreesLogo.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, -100, -30, 1500, 558, null);
			}
		};
		tabViewer.addTab("Login", null, LoginPanel, null);
		LoginPanel.setLayout(null);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewUserWindow n = new NewUserWindow();
				n.setVisible(true);
				tabViewer.remove(0);
				tabViewer.addTab("My Page", null, myPagePanel, null);
				tabViewer.addTab("Meetings", null, meetingsPanel, null);
				tabViewer.addTab("Friends", null, friendsPanel, null);
				tabViewer.addTab("Search", null, searchPanel, null);
				tabViewer.setSelectedIndex(0);
			}
		});
		btnCreateNewUser.setBounds(476, 247, 126, 32);
		LoginPanel.add(btnCreateNewUser);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 136, 78, 29);
		LoginPanel.add(lblNewLabel);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblLogin.setBounds(60, 90, 78, 29);
		LoginPanel.add(lblLogin);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEmail.setBounds(60, 191, 69, 29);
		LoginPanel.add(lblEmail);

		final JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SixDegrees.load();
				boolean findUser = false;
				for (User u : SixDegrees.getUsers().values()) {
					if (u.getUserName().equals(
							SixDegreesViewer.this.uNameTextField.getText())) {
						if (u.getEmail().equals(
								SixDegreesViewer.this.emailTextField.getText())) {
							setDisplayedUser(u);
							findUser = true;
							tabViewer.remove(0);
							tabViewer.addTab("My Page", null, myPagePanel, null);
							tabViewer.addTab("Meetings", null, meetingsPanel, null);
							tabViewer.addTab("Friends", null, friendsPanel, null);
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
		lblHaventRegisteredYet.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblHaventRegisteredYet.setBounds(476, 150, 217, 32);
		LoginPanel.add(lblHaventRegisteredYet);

		JLabel lblCreateNewUser = new JLabel("Create New User Here:");
		lblCreateNewUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCreateNewUser.setBounds(476, 200, 192, 28);
		LoginPanel.add(lblCreateNewUser);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SixDegreesViewer.this.frmSixDegrees.dispose();
			}
		});
		btnClose.setBounds(249, 247, 78, 32);
		LoginPanel.add(btnClose);

		uNameTextField = new JTextField();
		uNameTextField.setColumns(10);
		uNameTextField.setBackground(new Color(230,230,230));
		uNameTextField.setBounds(152, 135, 175, 32);
		LoginPanel.add(uNameTextField);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBackground(new Color(230,230,230));
		emailTextField.setBounds(152, 196, 175, 32);
		LoginPanel.add(emailTextField);
		emailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});

		myPagePanel = new JPanel();
		// tabViewer.addTab("My Page", null, myPagePanel, null);
		myPagePanel.setLayout(null);

		// HERE

				}
			};
		tabViewer.addTab("Login", null, LoginPanel, null);
		LoginPanel.setLayout(null);
		

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserWindow n = new NewUserWindow();
				n.setVisible(true);
				tabViewer.remove(0);
				tabViewer.addTab("My Page", null, myPagePanel, null);
				tabViewer.addTab("Meetings", null, meetingsPanel, null);
				tabViewer.addTab("Friends", null, friendsPanel, null);
				tabViewer.addTab("Search", null, searchPanel, null);
				tabViewer.setSelectedIndex(0);
			}
		});
		btnCreateNewUser.setBounds(476, 247, 126, 32);
		LoginPanel.add(btnCreateNewUser);

		uNameTextField = new JTextField();
		uNameTextField.setBounds(150, 135, 175, 32);
		LoginPanel.add(uNameTextField);
		uNameTextField.setColumns(10);
		uNameTextField.setBackground(Color.lightGray);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 136, 78, 29);
		LoginPanel.add(lblNewLabel);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblLogin.setBounds(60, 90, 78, 29);
		LoginPanel.add(lblLogin);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEmail.setBounds(60, 191, 69, 29);
		LoginPanel.add(lblEmail);

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBounds(150, 189, 175, 32);
		LoginPanel.add(emailTextField);
		emailTextField.setBackground(Color.lightGray);

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
							tabViewer.addTab("My Page", null, myPagePanel, null);
							tabViewer.addTab("Meetings", null, meetingsPanel, null);
							tabViewer.addTab("Friends", null, friendsPanel, null);
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
		lblHaventRegisteredYet.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblHaventRegisteredYet.setBounds(476, 150, 217, 32);
		LoginPanel.add(lblHaventRegisteredYet);

		JLabel lblCreateNewUser = new JLabel("Create New User Here:");
		lblCreateNewUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCreateNewUser.setBounds(476, 200, 192, 28);
		LoginPanel.add(lblCreateNewUser);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmSixDegrees.dispose();
			}
		});
		btnClose.setBounds(249, 247, 78, 32);
		LoginPanel.add(btnClose);

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

					SixDegreesViewer.this.StatusTextField.setVisible(true);
					SixDegreesViewer.this.StatusTextField.grabFocus();
					SixDegreesViewer.this.StatusTextField.selectAll();
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


					SixDegreesViewer.this.phoneText.setVisible(true);
					SixDegreesViewer.this.addressText.setVisible(true);
					SixDegreesViewer.this.emailText.setVisible(true);
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



		this.StatusTextField = new JTextField();
		this.StatusTextField.setVisible(false);
		this.StatusTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String status = SixDegreesViewer.this.StatusTextField
							.getText();
					SixDegrees.getCurrentUser().setStatus(status);
					SixDegreesViewer.this.StatusTextField.setVisible(false);
					lblStatusMessage.setVisible(true);
					lblStatusMessage
							.setText(SixDegreesViewer.this.StatusTextField
									.getText());
				}
			}
		});
		this.StatusTextField.setText("What are you doing?");
		this.StatusTextField.setBounds(140, 53, 250, 28);
		myPagePanel.add(this.StatusTextField);
		this.StatusTextField.setColumns(10);

		this.phoneText = new JTextField();
		this.phoneText.setVisible(false);
		this.phoneText.addKeyListener(new KeyAdapter() {

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

						current.setPhone(phoneFmt
								.parse(SixDegreesViewer.this.phoneText
										.getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					setDisplayedUser(current);
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);

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
		this.phoneText.setBounds(110, 315, 150, 18);
		myPagePanel.add(this.phoneText);
		this.phoneText.setColumns(10);

		this.addressText = new JTextField();
		this.addressText.setVisible(false);
		this.addressText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					current = SixDegrees.getCurrentUser();
					if (!SixDegreesViewer.this.addressText.getText().equals(""))
						;
					current.setAddress(SixDegreesViewer.this.addressText
							.getText());
					setDisplayedUser(current);
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		this.addressText.setColumns(10);
		this.addressText.setBounds(110, 338, 150, 18);
		myPagePanel.add(this.addressText);

		this.emailText = new JTextField();
		this.emailText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					current = SixDegrees.getCurrentUser();
					current.setEmail(SixDegreesViewer.this.emailText.getText());
					setDisplayedUser(current);
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);

					current.setEmail(emailText.getText());
					setCurrentUser(current);
					emailText.setVisible(false);
					addressText.setVisible(false);
					phoneText.setVisible(false);

					lblContactinfomessage.setVisible(true);
				}
			}
		});
		this.emailText.setVisible(false);
		this.emailText.setColumns(10);
		this.emailText.setBounds(110, 365, 150, 18);
		myPagePanel.add(this.emailText);
		addLogo(myPagePanel);

		final JPanel groupPanel = new JPanel();
		groupPanel.setBounds(433, 32, 300, 426);
		myPagePanel.add(groupPanel);
		groupPanel.setLayout(null);

		final ExpandableLabel workLabel = new ExpandableLabel("Work");

		workLabel.setBounds(10, 0, 270, 24);
		workLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		groupPanel.add(workLabel);

		final ExpandableLabel dormLabel = new ExpandableLabel("Dorm");
		dormLabel.setBounds(10, 41, 270, 24);
		dormLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		groupPanel.add(dormLabel);

		final JPanel work_panel = new JPanel();
		work_panel.setBounds(40, 24, 250, 10);
		groupPanel.add(work_panel);
		work_panel.setLayout(new GridLayout(0, 1, 0, 1));

		final JPanel dorm_panel = new JPanel();
		dorm_panel.setLayout(new GridLayout(0, 1, 0, 1));
		dorm_panel.setBounds(40, 65, 250, 12);
		groupPanel.add(dorm_panel);

		final ExpandableLabel lblSocialOrganization = new ExpandableLabel(
				"Social Organization");
		lblSocialOrganization.setBounds(10, 84, 270, 24);
		lblSocialOrganization
				.setFont(new Font("Times New Roman", Font.BOLD, 20));
		groupPanel.add(lblSocialOrganization);

		final JPanel socorg_panel = new JPanel();
		socorg_panel.setLayout(new GridLayout(0, 1, 0, 1));
		socorg_panel.setBounds(40, 113, 250, 12);
		groupPanel.add(socorg_panel);

		// ///Expand ////////////////
		workLabel.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JComponent[] comps = { dormLabel, dorm_panel,
						lblSocialOrganization, socorg_panel };
				int length = 0;
				if (!workLabel.isExpanded()) {
					JLabel lbl = new JLabel();
					lbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
					lbl.setText(displayed.getWork());
					lbl.setBounds(0, 0, 50, subHeadingHeight);
					work_panel.add(lbl);
					length = (work_panel.getComponents().length)
							* subHeadingHeight;
				} else {
					length = -((work_panel.getComponents().length) * subHeadingHeight);
					work_panel.removeAll();
				}
				workLabel.expand(work_panel, comps, length);
			}

		});

		dormLabel.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				JComponent[] comps = { lblSocialOrganization, socorg_panel };
				int length = 0;
				if (!dormLabel.isExpanded()) {
					JLabel lbl = new JLabel();
					lbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
					lbl.setText(displayed.getDorm());
					lbl.setBounds(0, 0, 50, subHeadingHeight);
					dorm_panel.add(lbl);
					length = (dorm_panel.getComponents().length)
							* subHeadingHeight;
				} else {
					length = -((dorm_panel.getComponents().length) * subHeadingHeight);
					dorm_panel.removeAll();
				}
				dormLabel.expand(dorm_panel, comps, length);
			}

		});

		lblSocialOrganization
				.addPropertyChangeListener(new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent arg0) {
						JComponent[] comps = {};
						int length = 0;
						if (!lblSocialOrganization.isExpanded()) {
							for (Group org : displayed.getOrganizations()) {
								JLabel lbl = new JLabel();
								lbl.setFont(new Font("Times New Roman",
										Font.PLAIN, 14));
								lbl.setText(org.getName());
								lbl.setBounds(0, 0, 50, subHeadingHeight);
								socorg_panel.add(lbl);
							}
							length = (socorg_panel.getComponents().length)
									* subHeadingHeight;
						} else {
							length = -((socorg_panel.getComponents().length) * subHeadingHeight);
							socorg_panel.removeAll();
						}
						lblSocialOrganization.expand(socorg_panel, comps,
								length);
					}

				});

		// /////////////////////////////

		emailText.setVisible(false);
		emailText.setColumns(10);
		emailText.setBounds(110, 365, 150, 18);
		myPagePanel.add(emailText);


		// /MEETINGS/////////////////////////////////////////////////////////////

		meetingsPanel = new JPanel();
		// tabViewer.addTab("Meetings", null, meetingsPanel, null);
		meetingsPanel.setLayout(null);

		CalendarControl calendarControl = new CalendarControl();
		calendarControl.setBounds(0, 0, 743, 465);
		meetingsPanel.add(calendarControl);

		addLogo(meetingsPanel);

		JButton btnNewEvent = new JButton("New Event");
		btnNewEvent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewEventWindow evWin = new NewEventWindow();
			}
		});
		btnNewEvent.setBounds(10, 476, 106, 35);
		meetingsPanel.add(btnNewEvent);

		// /FRIENDS//////////////////////////////////////////////////////////////////

		friendsPanel = new JPanel();
		// tabViewer.addTab("Friends", null, friendsPanel, null);
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

		FriendPanel rowPanel = null;
		this.textFriendSearch = new JTextField();
		this.textFriendSearch.setBounds(245, 24, 168, 20);
		friendsPanel.add(this.textFriendSearch);
		this.textFriendSearch.setColumns(10);

		final JButton btnFriendSearch = new JButton("Search");

		btnFriendSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				columnPanel.removeAll();
				String query = SixDegreesViewer.this.textFriendSearch.getText()
						.toLowerCase();
				System.out.println(query);

				for (User u : SixDegrees.getUsers().values()) {
					String[] name = u.getName().split("\\s+");
					// boolean found = false;
					for (int i = 0; i < name.length; i++) {
						// System.out.println(name[i]);
						if (name[i].toLowerCase().startsWith(query)
								|| u.getFname().toLowerCase().startsWith(query)
								|| u.getLname().toLowerCase().endsWith(query)
								|| u.getUserName().equalsIgnoreCase(query)) {
							FriendPanel rowPanel = new FriendPanel(u);
							rowPanel.setPreferredSize(new Dimension(120, 90));
							columnPanel.add(rowPanel);
							rowPanel.setLayout(null);
							btnFriendSearch.setFocusable(false);
							break;

						}
						btnFriendSearch.setFocusable(true);
					}
				}
				friendsScrollPane.setViewportView(friendScroller);

			}

		});

		this.textFriendSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnFriendSearch.doClick();
				}
			}
		});

		btnFriendSearch.setBounds(423, 23, 89, 23);
		friendsPanel.add(btnFriendSearch);
		
		friendsPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				SixDegreesViewer.this.textGeneralSearch.selectAll();
				
			}
		});

		// /////////////////////////////////////////////////////////////////
		searchPanel = new JPanel();
		// tabViewer.addTab("Search", null, generalSearchTab, null);
		searchPanel.setLayout(null);

		addLogo(searchPanel);

		final JScrollPane generalScrollPane = new JScrollPane();
		generalScrollPane.setBounds(10, 64, 723, 396);
		searchPanel.add(generalScrollPane);

		final JPanel generalScroller = new JPanel();
		generalScrollPane.setViewportView(generalScroller);
		generalScroller.setLayout(new BorderLayout(0, 0));

		final JPanel generalColumnPanel = new JPanel();
		generalScroller.add(generalColumnPanel, BorderLayout.NORTH);
		generalColumnPanel.setLayout(new GridLayout(0, 1, 0, 1));

		// FriendPanel panel_1 = new FriendPanel();
		// panel_1.setBounds(0, 0, 1200, 900);
		FriendPanel generalRowPanel = null;
		searchPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub.
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				SixDegreesViewer.this.textGeneralSearch.selectAll();
				
			}
		});
		this.textGeneralSearch = new JTextField();
		this.textGeneralSearch.setColumns(10);
		this.textGeneralSearch.setBounds(245, 24, 168, 20);
		searchPanel.add(this.textGeneralSearch);
		final JButton btnGeneralSearch = new JButton("Search");
		btnGeneralSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generalColumnPanel.removeAll();
				String query = SixDegreesViewer.this.textGeneralSearch
						.getText().toLowerCase();
				for (User u : SixDegrees.getUsers().values()) {
					if (u.getName().equalsIgnoreCase(query)
							|| u.getFname().equalsIgnoreCase(query)
							|| u.getLname().equalsIgnoreCase(query)
							|| u.getUserName().equalsIgnoreCase(query)) {
						FriendPanel generalRowPanel = new FriendPanel(u);
						generalRowPanel
								.setPreferredSize(new Dimension(120, 90));
						generalColumnPanel.add(generalRowPanel);
						generalRowPanel.setLayout(null);
						btnGeneralSearch.setFocusable(false);

					}
					btnGeneralSearch.setFocusable(true);
				}

				for (Group g : SixDegrees.getGroups().values()) {
					if (g.getName().equalsIgnoreCase(query) || query.equalsIgnoreCase("")) {
						GroupPanel generalRowPanel = new GroupPanel(g);
						generalRowPanel
								.setPreferredSize(new Dimension(120, 90));
						generalColumnPanel.add(generalRowPanel);
						generalRowPanel.setLayout(null);
						btnGeneralSearch.setFocusable(false);

					}
					btnGeneralSearch.setFocusable(true);
				}
				generalScrollPane.setViewportView(generalScroller);
			}

		});

		btnGeneralSearch.setBounds(423, 23, 89, 23);
		searchPanel.add(btnGeneralSearch);

		this.textGeneralSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnGeneralSearch.doClick();
				}
			}
		});

	}

	private void addLogo(JComponent comp) {
		ImagePanel image = new ImagePanel();
		image.setBounds(683, 469, 50, 50);
		comp.add(image);
	}

	public static void setDisplayedUser(User u) {
		displayed = u;
		SixDegrees.setCurrent(u);
		profilePictureFrame = null;
		profilePictureFrame = new ImagePanel(u.getImageURL(), 120, 120);
		profilePictureFrame.setBounds(10, 11, 120, 120);
		myPagePanel.add(profilePictureFrame);
		lblStatusMessage.setText(u.getStatus());
		lblNameLabel.setText(u.getName());
		lblBasicInfoMessage.setText(u.getBasicInfo());
		lblContactinfomessage.setText(u.getContactInfo());
		lblAbouttext.setText(u.getAbout());
	}
}
