import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

import classes.Group;
import classes.User;

/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj. Created Feb 5, 2013.
 */
public class SearchPanel extends JPanel {
	protected JLabel lblMainInfo;
	protected JLabel lblAdditionalInfo;
	protected User friend;
	protected Group group;
	protected JLabel lblFriendDistance;
	protected JPopupMenu contextMenu;
	protected JMenuItem menuItem;
	protected JMenuItem mainInfoMenuItem;
	protected JMenuItem addFriendItem;
	protected JMenuItem addGroupItem;

	/**
	 * Create the panel.
	 */
	public SearchPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		friend = null;
		group = null;

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
		mainInfoMenuItem = new JMenuItem("<html><b>"
				+ (friend != null ? friend.getName() : "Username")
				+ "</b></html>");
		mainInfoMenuItem.setFocusable(false);
		mainInfoMenuItem.setEnabled(false);


		MouseListener popupListener = new PopupListener();
		addMouseListener(popupListener);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(10, 80, 700, 80);
	}

	public String getAdditionalInfo() {
		return this.lblAdditionalInfo.getText();
	}

	public void setAdditionalInfo(String info) {
		this.lblAdditionalInfo.setText(info);
	}

	public String getMainInfo() {
		return this.lblMainInfo.getText();
	}

	public void setMainInfo(String info) {
		this.lblMainInfo.setText(info);
	}

	class PopupListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				contextMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
}
