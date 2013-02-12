import gui.ImagePanel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JProgressBar;

import classes.User;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class FriendPanel extends JPanel {
	private JLabel lblUsername;
	private User friend;

	/**
	 * Create the panel.
	 */
	public FriendPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		// JPanel picPanel = new JPanel();
		// picPanel.setBackground(Color.LIGHT_GRAY);
		//
		// picPanel.setBounds(10, 10, 60, 60);

		ImagePanel image = new ImagePanel(0);
		image.setBounds(10, 10, 60, 60);
		add(image);

		// add(picPanel);

		friend = null;

		lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsername.setBounds(77, 11, 633, 22);
		add(lblUsername);

		JLabel lblNewLabel = new JLabel("Additional Info");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(77, 34, 633, 34);
		add(lblNewLabel);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getID() == e.BUTTON2) {
					System.out.println("right?");
				}
				else System.out.println("left?");
			}
		});
	}

	public void setUsername(String text) {
		lblUsername.setText(text);
	}

	public void setUser(User u) {
		this.friend = u;
		setUsername(u.getName());
	}
	
	public User getUser() {
		return this.friend;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(10, 80, 700, 80);
	}
}
