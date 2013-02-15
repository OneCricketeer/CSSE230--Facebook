import gui.ImagePanel;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
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
import java.util.Currency;
import java.util.Random;

import javax.swing.JProgressBar;

import classes.Group;
import classes.SixDegrees;
import classes.User;

/**
 * TODO Put here a description of what this class does.
 * 
 * @author sternetj. Created Feb 5, 2013.
 */
public class SearchPanel extends JPanel {
	private JLabel lblMainInfo;
	private User friend;
	private Group group;
	private JLabel lblAdditionalInfo;
	private JLabel lblFriendDistance;
	private JPopupMenu contextMenu;
	private JMenuItem menuItem;
	private JMenuItem userNameMenuItem;
	private JMenuItem addFriendItem;
	private JMenuItem addGroupItem;

	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		// JPanel picPanel = new JPanel();
		// picPanel.setBackground(Color.LIGHT_GRAY);
		//
		// picPanel.setBounds(10, 10, 60, 60);



		// add(picPanel);

		friend = null;

		lblMainInfo = new JLabel("Main Info");
		lblMainInfo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMainInfo.setBounds(77, 11, 565, 22);
		add(lblMainInfo);

		lblAdditionalInfo = new JLabel("Additional Info");
		lblAdditionalInfo.setForeground(Color.GRAY);
		lblAdditionalInfo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdditionalInfo.setVerticalAlignment(SwingConstants.TOP);
		lblAdditionalInfo.setBounds(77, 34, 565, 34);
		add(lblAdditionalInfo);
		
		lblFriendDistance = new JLabel("");
		lblFriendDistance.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblFriendDistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblFriendDistance.setBounds(652, 10, 60, 60);
		add(lblFriendDistance);
		
		contextMenu = new JPopupMenu();
		final String name = lblMainInfo.getText();
		userNameMenuItem = new JMenuItem("<html><b>" + (friend != null ? friend.getName() : "Username")  + "</b></html>");
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
	    
	    addFriendItem = new JMenuItem("Add Friend");
	    addFriendItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		SixDegrees.getCurrentUser().addFriend(friend);
	    		System.out.println("View " + name + "'s Profile");
	    	}
	    });
	    contextMenu.add(addFriendItem);
	    
	    addGroupItem = new JMenuItem("Add Group");
	    addGroupItem.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		SixDegrees.getCurrentUser().addGroup(group);
	    	}
	    });
	    contextMenu.add(addGroupItem);


	    MouseListener popupListener = new PopupListener();
	    addMouseListener(popupListener);
	}

	public void setUsername(String text) {
		lblMainInfo.setText(text);
	}

	public void setUser(User u) {
		this.friend = u;
		contextMenu.remove(addGroupItem);
		userNameMenuItem.setText(u.getName());
		if (SixDegrees.getCurrentUser().hasFriend(friend))
	    	contextMenu.remove(addFriendItem);		
		setUsername(u.getName());
		
		ImagePanel image;
		String check = u.getImageURL();
		if (u.getImageURL() != null && u.getImageURL() != "")
			image = new ImagePanel(u.getImageURL(),60,60);
		else
			image = new ImagePanel(0);
		image.setBounds(10, 10, 60, 60);
		add(image);
	}
	
	public void setGroup(Group g){
		this.group = g;
		contextMenu.remove(addFriendItem);
		userNameMenuItem.setText(g.getName());
		if (SixDegrees.getCurrentUser().isInGroup(g))
	    	contextMenu.remove(addGroupItem);		
		setUsername(g.getName());

		ImagePanel image = new ImagePanel(g);
		image.setBounds(10, 10, 60, 60);
		add(image);
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
	
	public String getAdditionalInfo(){
		return this.lblAdditionalInfo.getText();
	}
	
	public void setAdditionalInfo(String info){
		this.lblAdditionalInfo.setText(info);
	}
	
	public String getMainInfo(){
		return this.lblMainInfo.getText();		
	}
	
	public void setMainInfo(String info){
		this.lblMainInfo.setText(info);
	}
	
	public void setGroupImage(Group g){
		ImagePanel image;
			image = new ImagePanel(g);
		image.setBounds(10, 10, 60, 60);
		add(image);
	}
	
	public String getdistanceInfo(){
		return this.lblFriendDistance.getText();
	}
	
	public void setDistance(int dist){		
		if(dist>=1){	
			this.lblFriendDistance.setText(dist + "\u00b0");
		}else if (dist == -1)
			this.lblFriendDistance.setText("\u221e\u00b0");
		else
			this.lblFriendDistance.setText("@\u00b0");
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
