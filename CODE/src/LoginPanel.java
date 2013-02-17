

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
	public LoginPanel() {
		initialize();
	}

	private void initialize() {
		setLayout(null);

		JButton btnCreateNewUser = new JButton("Create New User");
		btnCreateNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				NewUserWindow n = new NewUserWindow();
				n.setVisible(true);
//				tabViewer.remove(0);
//				tabViewer.addTab("My Page", null, myPagePanel, null);
//				tabViewer.addTab("Meetings", null, meetingsPanel, null);
//				tabViewer.addTab("Friends", null, friendsPanel, null);
//				tabViewer.addTab("Search", null, searchPanel, null);
//				tabViewer.setSelectedIndex(0);
			}
		});
		btnCreateNewUser.setBounds(476, 247, 126, 32);
		add(btnCreateNewUser);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel.setBounds(60, 136, 78, 29);
		add(lblNewLabel);

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		lblLogin.setBounds(60, 90, 78, 29);
		add(lblLogin);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblEmail.setBounds(60, 191, 69, 29);
		add(lblEmail);



	}

	@Override
	protected void paintComponent(Graphics g) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(
					"./src/IMAGES/SixDegreesLogo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, -100, -30, 1500, 558, null);
	}

}
