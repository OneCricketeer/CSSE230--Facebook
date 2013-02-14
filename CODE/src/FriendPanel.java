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
	public FriendPanel(User user) {
		super();
		super.setUser(user);
		super.setDistance(SixDegrees.getDistance(user.getUID()));
		super.setAdditionalInfo(user.getStatus());
	}
	
	public FriendPanel() {
		super();
	}

}
