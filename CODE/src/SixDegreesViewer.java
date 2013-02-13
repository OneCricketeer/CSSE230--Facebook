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

import classes.Group;
import classes.SixDegrees;
import classes.User;
import classes.XMLFileIO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.HashMap;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {

	private JFrame frmSixDegrees;
	private JTextField textFriendSearch;
	private JTextField textGeneralSearch;
	private static User current;
	private static JLabel lblStatusMessage;
	private static JLabel lblBasicInfoMessage;
	private static JLabel lblNameLabel;
	private static JLabel lblContactinfomessage;
	private static JLabel lblAbouttext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final boolean design = true;
//		data = new SixDegrees();
		
		
		
		SixDegrees.load();
	 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDegreesViewer window = new SixDegreesViewer();
					if (!design) {
						User pSherman = new User("pSherman", "P.", "Sherman");
						window.setCurrentUser(pSherman);
					}
					User pSherman = new User("pSherman", "P.", "Sherman");
					SixDegrees.addUser(pSherman);
					SixDegrees.setCurrentUser(pSherman);
					window.setCurrentUser(pSherman);

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
		frmSixDegrees.setIconImage(Toolkit.getDefaultToolkit().getImage(
				SixDegreesViewer.class.getResource("/logo.png")));
		frmSixDegrees.setResizable(false);
		frmSixDegrees.setTitle("Six Degrees");
		frmSixDegrees.setBounds(100, 100, 754, 586);
		frmSixDegrees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSixDegrees.getContentPane().setLayout(null);

		JTabbedPane tabViewer = new JTabbedPane(JTabbedPane.TOP);
		tabViewer.setBounds(0, 0, 748, 558);
		frmSixDegrees.getContentPane().add(tabViewer);

		JPanel myPagePanel = new JPanel();
		tabViewer.addTab("My Page", null, myPagePanel, null);
		myPagePanel.setLayout(null);

		JTree tree = new JTree();
		tree.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tree.setBounds(483, 11, 250, 411);
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setBounds(483, 11, 250, 411);
		myPagePanel.add(treeView);

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
		lblStatusMessage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStatusMessage.setBounds(140, 43, 214, 28);
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
		lblContactinfomessage.setVerticalAlignment(SwingConstants.TOP);
		lblContactinfomessage.setForeground(Color.BLACK);
		lblContactinfomessage.setFont(new Font("Times New Roman", Font.PLAIN,
				14));
		lblContactinfomessage.setBounds(103, 316, 346, 81);
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
		lblBasicInfoMessage.setBounds(114, 441, 353, 54);
		myPagePanel.add(lblBasicInfoMessage);

		addLogo(myPagePanel);

		// /MEETINGS/////////////////////////////////////////////////////////////

		JPanel meetingsPanel = new JPanel();
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
				NewEventWindow evWin = new NewEventWindow();
			}
		});
		btnNewEvent.setBounds(10, 476, 106, 35);
		meetingsPanel.add(btnNewEvent);

		// /FRIENDS//////////////////////////////////////////////////////////////////

		JPanel friendsPanel = new JPanel();
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
				for (User u : SixDegrees.getUsers().values()) {
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
		
		///////////////////////////////////////////////////////////////////

		JPanel generalSearchTab = new JPanel();
		tabViewer.addTab("Search", null, generalSearchTab, null);
		generalSearchTab.setLayout(null);

		addLogo(generalSearchTab);
		
		final JScrollPane generalScrollPane = new JScrollPane();
		generalScrollPane.setBounds(10, 64, 723, 396);
		generalSearchTab.add(generalScrollPane);

		final JPanel generalScroller = new JPanel();
		generalScrollPane.setViewportView(generalScroller);
		generalScroller.setLayout(new BorderLayout(0, 0));

		final JPanel generalColumnPanel = new JPanel();
		generalScroller.add(generalColumnPanel, BorderLayout.NORTH);
		generalColumnPanel.setLayout(new GridLayout(0, 1, 0, 1));

		// FriendPanel panel_1 = new FriendPanel();
		// panel_1.setBounds(0, 0, 1200, 900);
		FriendPanel generalRowPanel = null;
		

		

		textGeneralSearch = new JTextField();
		textGeneralSearch.setColumns(10);
		textGeneralSearch.setBounds(245, 24, 168, 20);
		generalSearchTab.add(textGeneralSearch);
		final JButton btnGeneralSearch = new JButton("Search");
		btnGeneralSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generalColumnPanel.removeAll();
				String query = textGeneralSearch.getText().toLowerCase();
				System.out.println(query);
				for (User u : SixDegrees.getUsers().values()) {
					if (u.getName().equalsIgnoreCase(query)
							|| u.getFname().equalsIgnoreCase(query)
							|| u.getLname().equalsIgnoreCase(query)
							|| u.getUserName().equalsIgnoreCase(query)) {
						FriendPanel generalRowPanel = new FriendPanel();
						generalRowPanel.setUsername(u.getName());
						generalRowPanel.setPreferredSize(new Dimension(120, 90));
						generalColumnPanel.add(generalRowPanel);
						generalRowPanel.setLayout(null);
						btnGeneralSearch.setFocusable(false);
						
					}
					btnGeneralSearch.setFocusable(true);
				}
				
				
				for (Group g : SixDegrees.getGroups().values()) {
					if (g.getName().equalsIgnoreCase(query)) {
						FriendPanel generalRowPanel = new FriendPanel();
						generalRowPanel.setUsername(g.getName());
						generalRowPanel.setPreferredSize(new Dimension(120, 90));
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
		generalSearchTab.add(btnGeneralSearch);

		JPanel Testingpanel = new JPanel();
		tabViewer.addTab("New tab", null, Testingpanel, null);
		Testingpanel.setLayout(null);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewUserWindow n = new NewUserWindow();
				n.setVisible(true);
			}
		});
		btnCreateNewUser.setBounds(270, 31, 200, 65);
		Testingpanel.add(btnCreateNewUser);

		JPanel panel = new JPanel();
		panel.setBounds(105, 149, 568, 329);
		Testingpanel.add(panel);

	}

	private void addLogo(JComponent comp) {
		ImagePanel image = new ImagePanel();
		image.setBounds(683, 469, 50, 50);
		comp.add(image);
	}

	public void setCurrentUser(User u) {
		current = u;
		lblStatusMessage.setText(u.getStatus());
		lblNameLabel.setText(u.getName());
		lblBasicInfoMessage.setText(u.getBasicInfo());
		lblContactinfomessage.setText(u.getContactInfo());
		lblAbouttext.setText(u.getAbout());
	}
}
