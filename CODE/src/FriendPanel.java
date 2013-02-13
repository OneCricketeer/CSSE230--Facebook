import gui.ImagePanel;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class FriendPanel extends JPanel  {
	private JLabel lblUsername;
	private User friend;
	private JPopupMenu contextMenu;
	private JMenuItem menuItem;
	private JMenuItem userNameMenuItem;

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
		
		contextMenu = new JPopupMenu();
		final String name = lblUsername.getText();
		userNameMenuItem = new JMenuItem("<html><b>" + name  + "</b></html>");
//	    userNameMenuItem.addActionListener(this);
	    userNameMenuItem.setFocusable(false);
	    userNameMenuItem.setEnabled(false);
	    contextMenu.add(userNameMenuItem);
	    contextMenu.addSeparator(); // separator
	    menuItem = new JMenuItem("View Profile");
	    menuItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		String name = friend != null ? friend.getName() : "Username";
	    		System.out.println("View " + name + "'s Profile");
	    	}
	    });
	    contextMenu.add(menuItem);
	    menuItem = new JMenuItem("Schedule an event");
	    menuItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		String name = friend != null ? friend.getName() : "Username";
	    		System.out.println("Schedule an event with " + name);
	    		
	    	}
	    });
	    contextMenu.add(menuItem);
	    
	    
	    MouseListener popupListener = new PopupListener();
	    addMouseListener(popupListener);

	}

	public void setUser(User u) {
		this.friend = u;
		lblUsername.setText(u.getName());
		userNameMenuItem.setText("<html><b>" + u.getName()  + "</b></html>");
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
	
	class PopupListener extends MouseAdapter {
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	        if (e.isPopupTrigger()) {
	            contextMenu.show(e.getComponent(),
	                       e.getX(), e.getY());
	        }
	    }
	}
}
