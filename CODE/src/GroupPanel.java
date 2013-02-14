import classes.Group;
import classes.SixDegrees;



/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj.
 *         Created Feb 13, 2013.
 */
public class GroupPanel extends SearchPanel {

	/**
	 * Create the panel.
	 */
	public GroupPanel(Group group) {
		super();
		super.setMainInfo(group.getName());
		super.setGroupImage(group);
		super.setAdditionalInfo("");
	}

}
