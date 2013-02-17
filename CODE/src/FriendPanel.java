import gui.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import classes.SixDegrees;
import classes.User;



/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj.
 *         Created Feb 13, 2013.
 */
public class FriendPanel extends SearchPanel {
	/**
	 * Create the panel.
	 */
	public FriendPanel() {
		super();
		initialize();
	}

	public FriendPanel(User user) {
		this();
		setUser(user);
		super.setAdditionalInfo(user.getStatus());
	}


	public FriendPanel(User user, boolean isFriend) {
		this(user);
		if (!(isFriend || user == null)) {
			setDistance(SixDegrees.getDistance(user.getUID()));
		}

	}

	private void initialize() {
		addFriendItem = new JMenuItem("Add Friend");
		addFriendItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SixDegrees.getCurrentUser().addFriend(friend);
				SixDegreesViewer.lblAbouttext.setText(SixDegrees.getCurrentUser().getAbout());
				setDistance(1);
				lblFriendDistance.repaint();
			}
		});
		contextMenu.add(addFriendItem);

		menuItem = new JMenuItem("Schedule an event");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = friend != null ? friend.getName() : "Username";
				new NewEventWindow(friend);
				System.out.println("Schedule an event with " + name);

			}
		});
		contextMenu.add(menuItem);
		
		contextMenu.add(mainInfoMenuItem);
		contextMenu.addSeparator(); // separator
		menuItem = new JMenuItem("View Profile");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (friend != null) {
					SixDegreesViewer.setDisplayedUser(friend);
				}
			}
		});
		contextMenu.add(menuItem);
	}

	public String getdistanceInfo() {
		return "" + Integer.parseInt(this.lblFriendDistance.getText());
	}

	public void setDistance(int dist) {
		String deg = "\u00b0";
		if (dist >= 1) {
			this.lblFriendDistance.setText(dist + deg);
		} else if (dist == -1) {
			this.lblFriendDistance.setText("\u221e" + deg); // infinity
		} else {
			this.lblFriendDistance.setText("@" + deg);
		}
	}

	public User getUser() {
		return this.friend;
	}

	public void setUser(User u) {
		this.friend = u;
//		contextMenu.remove(addGroupItem);
		mainInfoMenuItem.setText(u.getName());
		if (SixDegrees.getCurrentUser().hasFriend(friend)) {
			contextMenu.remove(addFriendItem);
		}

		super.setMainInfo(u.getName());

		ImagePanel image;
		String check = u.getImageURL();
		if (u.getImageURL() != null && u.getImageURL() != "") {
			image = new ImagePanel(u.getImageURL(), 60, 60);
		} else {
			image = new ImagePanel(u.getUID());
		}
		image.setBounds(10, 10, 60, 60);
		add(image);
	}

}
