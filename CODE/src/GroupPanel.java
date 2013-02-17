import gui.ImagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import classes.Group;
import classes.SixDegrees;

/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj. Created Feb 13, 2013.
 */
public class GroupPanel extends SearchPanel {

	/**
	 * Create the panel.
	 */
	public GroupPanel(final Group group) {
		super();
		super.setAdditionalInfo("");
		setGroup(group);
	}

	public void setGroupImage(Group g) {
		ImagePanel image = new ImagePanel(g);
		image.setBounds(10, 10, 60, 60);
		add(image);
	}

	public void setGroup(Group g) {
		group = g;
		mainInfoMenuItem.setText(g.getName());
		if (SixDegrees.getCurrentUser().isInGroup(g)) {
			contextMenu.remove(addGroupItem);
		}
		super.setMainInfo(g.getName());
		super.setAdditionalInfo(group.getDesc());
		setGroupImage(g);

		if (!SixDegrees.getCurrentUser().isInGroup(group)) {
			addGroupItem = new JMenuItem("Add Group");
			addGroupItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					SixDegrees.getCurrentUser().addGroup(group);
					SixDegreesViewer.lblAbouttext.setText(SixDegrees.getCurrentUser().getAbout());
				}
			});

			contextMenu.add(addGroupItem);
		}
	}

	public Group getGroup() {
		return group;
	}

}
