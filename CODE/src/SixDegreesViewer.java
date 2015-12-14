import gui.ExpandableLabel;
import gui.ImagePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import classes.Group;
import classes.SixDegrees;
import classes.User;

//import SearchPanel.PopupListener;

/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {

	private JFrame frmSixDegrees;

	// Tabs
	static JTabbedPane tabViewer;
	private static JPanel LoginPanel;
	private static JPanel myPagePanel;
	private static JPanel meetingsPanel;
	private static JPanel friendsPanel;
	private static JPanel searchPanel;

	// Login
	private JTextField uNameTextField;
	private JTextField emailTextField;

	// Search
	private JTextField textFriendSearch;
	private JTextField textGeneralSearch;

	/* My Page */
	private static ImagePanel profilePictureFrame;
	private static JLabel lblStatusMessage;
	private static JLabel lblNameLabel;
	static JLabel lblAbouttext;
	private static JLabel lblContactinfomessage;
	private static JLabel lblBasicInfoMessage;
	// Editable fields
	private JTextField statusText;
	private JTextArea aboutTextPane;
	private JTextField phoneText;
	private JTextField addressText;
	private JTextField emailText;

	private static final int subHeadingHeight = 20;
	private static User displayed; // Shows other person's information
	private static JButton btnBack;
	private static JButton btnRemoveFromFriends;

	private static JPanel friendPanel;
	/**
	 * Launch the application.
	 */

	private JLabel lblshiftEnter;

	static CalendarControl calendarControl;


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
		addLogo();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Frame Creation /////////////////////////////////////////////////
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

		tabViewer = new JTabbedPane(JTabbedPane.TOP);
		tabViewer.setBounds(0, 0, 748, 558);
		this.frmSixDegrees.getContentPane().add(tabViewer);

		// Login Panel /////////////////////////////////////////////
		displayLogin();

		// My Page /////////////////////////////////////////////////
		myPagePanel = new JPanel();
		myPagePanel.setLayout(null);
		myPagePanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				myPagePanel.repaint();

			}
		});

		lblNameLabel = new JLabel("Name");
		lblNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNameLabel.setBounds(10, 142, 214, 28);
		myPagePanel.add(lblNameLabel);

		// Status

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(new Color(178, 34, 34));
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStatus.setBounds(140, 14, 214, 28);
		myPagePanel.add(lblStatus);

		lblStatusMessage = new JLabel("Status Message");
		lblStatusMessage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStatusMessage.setBounds(140, 53, 228, 28);

		lblStatusMessage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() == 2) {
					lblStatusMessage.setVisible(false);
					statusText.setVisible(true);
					statusText.grabFocus();
					statusText.setText(lblStatusMessage.getText());
					statusText.selectAll();
				}
			}
		});

		this.statusText = new JTextField();
		this.statusText.setVisible(false);
		this.statusText.setBounds(140, 53, 250, 28);
		this.statusText.setColumns(10);
		this.statusText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					String status = statusText.getText();
					SixDegrees.getCurrentUser().setStatus(status);
					statusText.setVisible(false);
					lblStatusMessage.setVisible(true);
					lblStatusMessage.setText(statusText.getText());
				} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					statusText.setVisible(false);
					lblStatusMessage.setVisible(true);
				}
			}
		});

		myPagePanel.add(this.statusText);
		myPagePanel.add(lblStatusMessage);

		// End status

		JLabel lblContactInfo = new JLabel("Contact Info:");
		lblContactInfo.setForeground(new Color(178, 34, 34));
		lblContactInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContactInfo.setBounds(10, 283, 214, 28);
		myPagePanel.add(lblContactInfo);

		// About

		JLabel lblAbout = new JLabel("About:");
		lblAbout.setForeground(new Color(178, 34, 34));
		lblAbout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAbout.setBounds(10, 181, 214, 28);
		myPagePanel.add(lblAbout);

		lblAbouttext = new JLabel("AboutText");
		lblAbouttext.setVerticalAlignment(SwingConstants.TOP);
		lblAbouttext.setForeground(new Color(0, 0, 0));
		lblAbouttext.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAbouttext.setBounds(46, 210, 344, 62);
		lblAbouttext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					lblAbouttext.setVisible(false);
					aboutTextPane.setVisible(true);
					lblshiftEnter.setVisible(true);
					aboutTextPane.grabFocus();
					String s = lblAbouttext.getText().replace("<html>", "").replace("</html>", "");
					s = s.replace("<br />", "\r\n");
					aboutTextPane.setText(s);
					aboutTextPane.selectAll();
				}
			}
		});
		myPagePanel.add(lblAbouttext);

		aboutTextPane = new JTextArea();
		aboutTextPane.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		aboutTextPane.setLineWrap(true);
		aboutTextPane.setVisible(false);
		aboutTextPane.setBounds(46, 204, 250, 68);
		aboutTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.isShiftDown()) {
						int loc = aboutTextPane.getSelectionStart();
						String text = aboutTextPane.getText();
						String begin = text.substring(0, loc);

						String end = text.substring(loc, text.length());

						aboutTextPane.setText(begin + "\n" + end);
						System.out.println(text);
						aboutTextPane.setSelectionStart(loc + 1);
						aboutTextPane.setSelectionEnd(loc + 1);
					} else {
						aboutTextPane.setVisible(false);
						lblshiftEnter.setVisible(false);
//						String about = "<html>"	+ .replace("\n", "<br />") + "</html>";
						SixDegrees.getCurrentUser().setAbout(aboutTextPane.getText());
						System.out.println(SixDegrees.getCurrentUser().getAbout());
						lblAbouttext.setText(SixDegrees.getCurrentUser().getAbout());
						lblAbouttext.setVisible(true);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					aboutTextPane.setVisible(false);
					lblshiftEnter.setVisible(false);
					lblAbouttext.setVisible(true);
				}
			}
		});
		myPagePanel.add(aboutTextPane);
		aboutTextPane.setText("About message");

		// End about

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
					phoneText.grabFocus();
					SixDegreesViewer.this.addressText.setVisible(true);
					SixDegreesViewer.this.emailText.setVisible(true);
					phoneText.setText(SixDegrees.getCurrentUser().getPhone()
							.toString());
					addressText.setText(SixDegrees.getCurrentUser()
							.getAddress());
					emailText.setText(SixDegrees.getCurrentUser().getEmail());
				}

			}
		});
		lblContactinfomessage.setVerticalAlignment(SwingConstants.TOP);
		lblContactinfomessage.setForeground(Color.BLACK);
		lblContactinfomessage.setFont(new Font("Times New Roman", Font.PLAIN,
				14));
		lblContactinfomessage.setBounds(110, 316, 346, 81);
		myPagePanel.add(lblContactinfomessage);

		JLabel lblBasicInfo = new JLabel("Basic Info:");
		lblBasicInfo.setForeground(new Color(178, 34, 34));
		lblBasicInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBasicInfo.setBounds(10, 407, 214, 28);
		myPagePanel.add(lblBasicInfo);

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
		lblBasicInfoMessage.setBounds(114, 438, 276, 54);
		myPagePanel.add(lblBasicInfoMessage);

		phoneText = new JTextField();
		this.phoneText.setBounds(110, 315, 150, 18);
		this.phoneText.setColumns(10);
		phoneText.setVisible(false);
		phoneText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					DecimalFormat phoneFmt = new DecimalFormat("####,###,###");
					try {
						SixDegrees.getCurrentUser().setPhone(
								phoneFmt.parse(SixDegreesViewer.this.phoneText
										.getText()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					setDisplayedUser(SixDegrees.getCurrentUser());
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});

		myPagePanel.add(this.phoneText);

		this.addressText = new JTextField();
		this.addressText.setColumns(10);
		this.addressText.setBounds(110, 338, 150, 18);
		this.addressText.setVisible(false);
		this.addressText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					SixDegrees.getCurrentUser().setAddress(
							SixDegreesViewer.this.addressText.getText());
					setDisplayedUser(SixDegrees.getCurrentUser());
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		myPagePanel.add(this.addressText);

		this.emailText = new JTextField();
		this.emailText.setVisible(false);
		this.emailText.setColumns(10);
		this.emailText.setBounds(110, 365, 150, 18);
		this.emailText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					SixDegrees.getCurrentUser().setEmail(
							SixDegreesViewer.this.emailText.getText());
					setDisplayedUser(SixDegrees.getCurrentUser());
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					SixDegreesViewer.this.emailText.setVisible(false);
					SixDegreesViewer.this.addressText.setVisible(false);
					SixDegreesViewer.this.phoneText.setVisible(false);
					lblContactinfomessage.setVisible(true);
				}
			}
		});
		myPagePanel.add(this.emailText);

		// Expandable Group Panel

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

		workLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent[] comps = { dormLabel, dorm_panel,
						lblSocialOrganization, socorg_panel };
				int length = 0;
				if (workLabel.isExpanded()) {
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

		dormLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent[] comps = { lblSocialOrganization, socorg_panel };
				int length = 0;
				if (dormLabel.isExpanded()) {
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

		lblSocialOrganization.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent[] comps = {};
				int length = 0;
				if (lblSocialOrganization.isExpanded()) {
					for (Group org : displayed.getOrganizations()) {
						// System.out.println(org);
						if (org != null) {
							JLabel lbl = new JLabel();
							lbl.setFont(new Font("Times New Roman", Font.PLAIN,
									14));
							lbl.setText(org.getName());
							lbl.setBounds(0, 0, 50, subHeadingHeight);
							socorg_panel.add(lbl);
						}
					}
					length = (socorg_panel.getComponents().length)
							* subHeadingHeight;
				} else {
					length = -((socorg_panel.getComponents().length) * subHeadingHeight);
					socorg_panel.removeAll();
				}
				lblSocialOrganization.expand(socorg_panel, comps, length);
			}

		});

		btnBack = new JButton("Back to Your Page");
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnBack.setBounds(433, 491, 121, 28);
		myPagePanel.add(btnBack);

		btnRemoveFromFriends = new JButton("Remove from Friends");
		btnRemoveFromFriends
				.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnRemoveFromFriends.setBounds(240, 142, 150, 28);
		if (SixDegrees.getCurrentUser() != null) {
			btnRemoveFromFriends.setVisible(SixDegrees.getCurrentUser()
					.hasFriend(displayed));
		}
		btnRemoveFromFriends.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(SixDegrees.getCurrentUser().getFriends().contains(displayed)){
					SixDegrees.getCurrentUser().removeFriend(displayed);
					btnBack.doClick();
				}
				else{
					SixDegrees.getCurrentUser().addFriend(displayed);
					btnRemoveFromFriends.setText("Remove Friend");
				}	
			}
		});
		myPagePanel.add(btnRemoveFromFriends);

		lblshiftEnter = new JLabel("(Shift + Enter to enter a new line)");
		lblshiftEnter.setBounds(52, 273, 228, 14);
		lblshiftEnter.setVisible(false);
		myPagePanel.add(lblshiftEnter);

		btnBack.setVisible(false);
		btnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SixDegreesViewer.setDisplayedUser(SixDegrees.getCurrentUser());
				btnBack.setVisible(false);
			}

		});

		// /MEETINGS/////////////////////////////////////////////////////////////

		meetingsPanel = new JPanel();
		//tabViewer.addTab("Meetings", null, meetingsPanel, null);
		meetingsPanel.setLayout(null);

		calendarControl = new CalendarControl();
		calendarControl.setBounds(0, 0, 743, 465);
		meetingsPanel.add(calendarControl);

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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				columnPanel.removeAll();
				String query = SixDegreesViewer.this.textFriendSearch.getText()
						.toLowerCase();
				System.out.println(query);

				for (User u : SixDegrees.getCurrentUser().getFriends()) {
					String[] name = u.getName().split("\\s+");
					// boolean found = false;
					for (String element : name) {
						// System.out.println(name[i]);
						if (element.toLowerCase().startsWith(query)
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

		friendsPanel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SixDegreesViewer.this.textGeneralSearch.selectAll();

			}
		});

		// /////////////////////////////////////////////////////////////////
		searchPanel = new JPanel();
		// tabViewer.addTab("Search", null, generalSearchTab, null);
		searchPanel.setLayout(null);

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
		searchPanel.addMouseListener(new MouseAdapter() {

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
			@Override
			public void actionPerformed(ActionEvent arg0) {
				generalColumnPanel.removeAll();
				String query = SixDegreesViewer.this.textGeneralSearch
						.getText().toLowerCase();
				for (User u : SixDegrees.getUsers().values()) {
					String[] name = u.getName().split("\\s+");
					// boolean found = false;
					for (String element : name) {
						// System.out.println(name[i]);
						if (element.toLowerCase().startsWith(query)
								|| u.getFname().toLowerCase().startsWith(query)
								|| u.getLname().toLowerCase().endsWith(query)
								|| u.getUserName().equalsIgnoreCase(query)) {
							FriendPanel generalRowPanel = new FriendPanel(u,
									false);
							generalRowPanel.setPreferredSize(new Dimension(120,
									90));
							generalColumnPanel.add(generalRowPanel);
							generalRowPanel.setLayout(null);
							btnGeneralSearch.setFocusable(false);
							break; // found a match
						}

					}
					btnGeneralSearch.setFocusable(true);
				}

				for (Group g : SixDegrees.getGroups().values()) {
					String[] name = g.getName().split("\\s+");
					for (String element : name) {
						if (element.toLowerCase().startsWith(query)
								|| element.equalsIgnoreCase(query)
								|| query.equalsIgnoreCase("")) {
							GroupPanel generalRowPanel = new GroupPanel(g);
							generalRowPanel.setPreferredSize(new Dimension(120,
									90));
							generalColumnPanel.add(generalRowPanel);
							generalRowPanel.setLayout(null);
							btnGeneralSearch.setFocusable(false);
							break; // found a match
						}
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

	private static void addLogo(JComponent comp) {
		ImagePanel image = new ImagePanel();
		image.setBounds(683, 469, 50, 50);
		comp.add(image);
	}

	public static void setDisplayedUser(User u) {
		displayed = u;
		if(profilePictureFrame!=null)
			myPagePanel.remove(profilePictureFrame);
		profilePictureFrame = new ImagePanel(u.getImageURL(), 120, 120);
		profilePictureFrame.setBounds(10, 11, 120, 120);
		myPagePanel.add(profilePictureFrame);
		lblStatusMessage.setText(u.getStatus());
		lblNameLabel.setText(u.getName());
		lblAbouttext.setText(u.getAbout());
		lblContactinfomessage.setText(u.getContactInfo());
		lblBasicInfoMessage.setText(u.getBasicInfo());
		myPagePanel.repaint();
		tabViewer.setSelectedIndex(0);
		btnRemoveFromFriends.setVisible(false);

		if (u.compareTo(SixDegrees.getCurrentUser()) != 0) {
			btnBack.setVisible(true);			
			if(SixDegrees.getCurrentUser().hasFriend(
					displayed)){
				btnRemoveFromFriends.setText("Remove Friend");				
			}
			else{
				btnRemoveFromFriends.setText("Add Friend");
			}
			btnRemoveFromFriends.setVisible(true);
		}				
	}

	public static void setCurrentUser(User current) {
		SixDegrees.setCurrent(current);
		calendarControl.setDays();
		setDisplayedUser(current);
	}

	private void addLogo() {
		addLogo(myPagePanel);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SixDegrees.save();
				tabViewer.removeAll();
				tabViewer.addTab("Log in", null, LoginPanel, null);
			}
		});
		btnLogOut.setBounds(564, 491, 89, 28);
		myPagePanel.add(btnLogOut);
		addLogo(meetingsPanel);
		addLogo(friendsPanel);
		addLogo(searchPanel);
	}

	private void load() {
		tabViewer.remove(0);
		tabViewer.addTab("My Page", null, myPagePanel, null);
		tabViewer.addTab("Meetings", null, meetingsPanel, null);
		tabViewer.addTab("Friends", null, friendsPanel, null);
		tabViewer.addTab("Search", null, searchPanel, null);
		goToProfile();
	}

	public static void goToProfile() {
		tabViewer.setSelectedIndex(0);
	}

	public void setVisible(boolean b) {
		frmSixDegrees.setVisible(true);
	}

	private void displayLogin() {
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
				SixDegreesViewer.setCurrentUser(n.getCreatedUser());
				SixDegreesViewer.this.load();
			}
		});
		btnCreateNewUser.setBounds(476, 247, 126, 32);
		LoginPanel.add(btnCreateNewUser);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblLogin.setBounds(60, 90, 78, 29);
		LoginPanel.add(lblLogin);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 136, 78, 29);
		LoginPanel.add(lblNewLabel);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEmail.setBounds(60, 191, 69, 29);
		LoginPanel.add(lblEmail);
		uNameTextField = new JTextField();
		uNameTextField.setColumns(10);
		uNameTextField.setBackground(new Color(230, 230, 230));
		uNameTextField.setBounds(152, 135, 175, 32);
		LoginPanel.add(uNameTextField);
		uNameTextField.grabFocus();

		emailTextField = new JTextField();
		emailTextField.setColumns(10);
		emailTextField.setBackground(new Color(230, 230, 230));
		emailTextField.setBounds(152, 196, 175, 32);
		LoginPanel.add(emailTextField);

		final JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(150, 247, 78, 32);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				User load = null;
				for (User u : SixDegrees.getUsers().values()) {
					if (u.getUserName().equals(
							SixDegreesViewer.this.uNameTextField.getText())
							&& u.getEmail().equals(
									SixDegreesViewer.this.emailTextField
											.getText())) {
						load = u;
						break;
					}
				}

				if (load == null) {
					JOptionPane.showMessageDialog(null,
							"Invalid Username or Email!", "Login Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					SixDegreesViewer.setCurrentUser(load);
					SixDegreesViewer.this.load();
				}
			}
		});

		LoginPanel.add(btnLogin);

		emailTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});

		JButton btnClose = new JButton("Close");
		btnClose.setBounds(249, 247, 78, 32);
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SixDegreesViewer.this.frmSixDegrees.dispose();
			}
		});
		LoginPanel.add(btnClose);

		JLabel lblHaventRegisteredYet = new JLabel("Haven't Registered Yet?");
		lblHaventRegisteredYet.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblHaventRegisteredYet.setBounds(476, 150, 217, 32);
		LoginPanel.add(lblHaventRegisteredYet);

		JLabel lblCreateNewUser = new JLabel("Create New User Here:");
		lblCreateNewUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblCreateNewUser.setBounds(476, 200, 192, 28);
		LoginPanel.add(lblCreateNewUser);

	}
	
	public static void setFriendPage(User friend){
		// TODO: Implement this?
	}
}
