package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SixDegreesViewer {

	private JFrame frmSixDegrees;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SixDegreesViewer window = new SixDegreesViewer();
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

		JTabbedPane tabViewer = new JTabbedPane(JTabbedPane.TOP);
		frmSixDegrees.getContentPane().add(tabViewer, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabViewer.addTab("My Page", null, panel, null);
		panel.setLayout(null);

		JTree tree = new JTree();
		tree.setFont(new Font("Times New Roman", Font.BOLD, 18));
		tree.setBounds(483, 11, 250, 411);
		panel.add(tree);

		JPanel profilePictureFrame = new JPanel();
		profilePictureFrame.setBackground(Color.GRAY);
		profilePictureFrame.setBounds(10, 11, 120, 120);
		panel.add(profilePictureFrame);

		JLabel lblNameLabel = new JLabel("Name");
		lblNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNameLabel.setBounds(10, 142, 214, 28);
		panel.add(lblNameLabel);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setForeground(new Color(178, 34, 34));
		lblStatus.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblStatus.setBounds(140, 14, 214, 28);
		panel.add(lblStatus);

		JLabel lblStatusMessage = new JLabel("Status Message");
		lblStatusMessage.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblStatusMessage.setBounds(140, 43, 214, 28);
		panel.add(lblStatusMessage);

		JLabel lblContactInfo = new JLabel("Contact Info:");
		lblContactInfo.setForeground(new Color(178, 34, 34));
		lblContactInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblContactInfo.setBounds(10, 283, 214, 28);
		panel.add(lblContactInfo);

		JLabel lblAbout = new JLabel("About:");
		lblAbout.setForeground(new Color(178, 34, 34));
		lblAbout.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblAbout.setBounds(10, 181, 214, 28);
		panel.add(lblAbout);

		JLabel lblBasicInfo = new JLabel("Basic Info:");
		lblBasicInfo.setForeground(new Color(178, 34, 34));
		lblBasicInfo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblBasicInfo.setBounds(10, 407, 214, 28);
		panel.add(lblBasicInfo);

		JLabel lblAbouttext = new JLabel("AboutText");
		lblAbouttext.setVerticalAlignment(SwingConstants.TOP);
		lblAbouttext.setForeground(new Color(0, 0, 0));
		lblAbouttext.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblAbouttext.setBounds(46, 210, 403, 62);
		panel.add(lblAbouttext);

		JLabel lblContactLabel = new JLabel(
				"<html>Phone:<br />Address:<br /><br />Email:</html>");
		lblContactLabel.setVerticalAlignment(SwingConstants.TOP);
		lblContactLabel.setForeground(Color.BLACK);
		lblContactLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblContactLabel.setBounds(46, 315, 52, 81);
		panel.add(lblContactLabel);

		JLabel lblContactinfomessage = new JLabel("ContactInfoMessage");
		lblContactinfomessage.setVerticalAlignment(SwingConstants.TOP);
		lblContactinfomessage.setForeground(Color.BLACK);
		lblContactinfomessage.setFont(new Font("Times New Roman", Font.PLAIN,
				14));
		lblContactinfomessage.setBounds(103, 316, 346, 81);
		panel.add(lblContactinfomessage);

		JLabel lblBasicInfoLabel = new JLabel(
				"<html>Birthdate:<br />Sex:<br />Hometown:</html>");
		lblBasicInfoLabel.setVerticalAlignment(SwingConstants.TOP);
		lblBasicInfoLabel.setForeground(Color.BLACK);
		lblBasicInfoLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBasicInfoLabel.setBounds(46, 438, 70, 81);
		panel.add(lblBasicInfoLabel);

		JLabel lblBasicInfoMessage = new JLabel("BasicInfoMessage");
		lblBasicInfoMessage.setVerticalAlignment(SwingConstants.TOP);
		lblBasicInfoMessage.setForeground(Color.BLACK);
		lblBasicInfoMessage
				.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBasicInfoMessage.setBounds(114, 441, 353, 54);
		panel.add(lblBasicInfoMessage);

		ImagePanel panelLogo = new ImagePanel();
		panelLogo.setBounds(681, 470, 50, 50);
		panel.add(panelLogo);

		JPanel panelTab2 = new JPanel();
		tabViewer.addTab("Meetings", null, panelTab2, null);
		panelTab2.setLayout(null);

		ImagePanel imagePanel2 = new ImagePanel();
		imagePanel2.setBounds(683, 469, 50, 50);
		panelTab2.add(imagePanel2);

		JPanel panelTab3 = new JPanel();
		tabViewer.addTab("Friends", null, panelTab3, null);
		panelTab3.setLayout(null);

		ImagePanel imagePanel3 = new ImagePanel();
		imagePanel3.setBounds(683, 469, 50, 50);
		panelTab3.add(imagePanel3);

		JPanel panelTab4 = new JPanel();
		tabViewer.addTab("Search", null, panelTab4, null);
		panelTab4.setLayout(null);

		ImagePanel imagePanel4 = new ImagePanel();
		imagePanel4.setBounds(683, 469, 50, 50);
		panelTab4.add(imagePanel4);

	}
}
