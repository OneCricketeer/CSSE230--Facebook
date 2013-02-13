import gui.ExpandableLabel;
import gui.ImagePanel;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {

	private JFrame frmSixDegrees;
	private JTextField textFriendSearch;
	private JTextField textGeneralSearch;
	private static final int subHeadingHeight = 20;
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
		final boolean design = false;
		// data = new SixDegrees();

		SixDegrees.load();
		final User usr = SixDegrees.getUsers().get(0);
		usr.setDorm("Percopo");
		usr.setWork("Ventures");
		for (Group grp : SixDegrees.getGroups().values()) {
			usr.addOrganization(grp);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDegreesViewer window = new SixDegreesViewer();
					if (!design) {

						window.setCurrentUser(usr);
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
					lbl.setText(current.getWork());
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
					lbl.setText(current.getDorm());
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
							for (Group org : current.getOrganizations()) {
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

		// /MEETINGS/////////////////////////////////////////////////////////////

		JPanel meetingsPanel = new JPanel();
		tabViewer.addTab("Meetings", null, meetingsPanel, null);
		meetingsPanel.setLayout(null);

		CalendarControl calendarControl = new CalendarControl();
		calendarControl.setBounds(0, 0, 743, 465);
		meetingsPanel.add(calendarControl);

		addLogo(meetingsPanel);

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
					String[] name = u.getName().split("\\s+");
					// boolean found = false;
					for (int i = 0; i < name.length; i++) {
						// System.out.println(name[i]);
						if (name[i].toLowerCase().startsWith(query)
								|| u.getFname().toLowerCase().startsWith(query)
								|| u.getLname().toLowerCase().endsWith(query)
								|| u.getUserName().equalsIgnoreCase(query)) {
							FriendPanel rowPanel = new FriendPanel();
							rowPanel.setUser(u);
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
		btnFriendSearch.setBounds(423, 23, 89, 23);
		friendsPanel.add(btnFriendSearch);

		JPanel panelTab4 = new JPanel();
		tabViewer.addTab("Search", null, panelTab4, null);
		panelTab4.setLayout(null);

		addLogo(panelTab4);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 64, 723, 396);
		panelTab4.add(scrollPane_1);

		textGeneralSearch = new JTextField();
		textGeneralSearch.setColumns(10);
		textGeneralSearch.setBounds(245, 24, 168, 20);
		panelTab4.add(textGeneralSearch);

		JButton btnGeneralSearch = new JButton("Search");
		btnGeneralSearch.setBounds(423, 23, 89, 23);
		panelTab4.add(btnGeneralSearch);

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
