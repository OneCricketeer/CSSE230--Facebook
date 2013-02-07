import gui.ImagePanel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.Random;

import javax.swing.JProgressBar;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class FriendPanel extends JPanel {
	private JLabel lblUsername;
	/**
	 * Create the panel.
	 */
	public FriendPanel() {
		setLayout(null);
		
//		JPanel picPanel = new JPanel();
//		picPanel.setBackground(Color.LIGHT_GRAY);
//
//		picPanel.setBounds(10, 10, 60, 60);
		
		ImagePanel friend = new ImagePanel(0);
		friend.setBounds(10, 10, 60, 60);
		add(friend);
		
		//add(picPanel);
		
		
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

	}
	public void setUsername(String text){
		lblUsername.setText(text);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);		
		g.drawLine(10, 80, 700, 80);
	}
}
