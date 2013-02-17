package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import classes.Group;
import classes.SixDegrees;

/**
 * TODO Put here a description of what this class does.
 *
 * @author sternetj. Created Feb 5, 2013.
 */
public class ImagePanel extends JPanel {

	private BufferedImage image;

	public ImagePanel() {
		try {
			image = resizeImage(
					ImageIO.read(new File("./src/IMAGES/logo.png")), 2, 50, 50);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public ImagePanel(int friend) {
		String path;
		boolean male = SixDegrees.getUsers().get(friend).getGender()
				.equalsIgnoreCase("Male");
		if (male) {
			path = "./src/IMAGES/DefaultUserMale.gif";
		} else {
			path = "./src/IMAGES/DefaultUserFemale.gif";
		}

		try {
			image = resizeImage(ImageIO.read(new File(path)), 2, 60, 60);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public ImagePanel(Group g) {
		String path = "./src/IMAGES/Group-icon.gif";
		try {
			image = resizeImage(ImageIO.read(new File(path)), 2, 60, 60);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// public ImagePanel(String path, int width, int height, int fake) {
	// try {
	// image = resizeImage(ImageIO.read(new File(path)),2,width,height);
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }

	public ImagePanel(String url, int width, int height) {

		try {
			URL myUrl = new URL(url);
			image = ImageIO.read(myUrl);
			image = resizeImage(image, 2, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the
										// parameters
	}

	private static BufferedImage resizeImage(BufferedImage originalImage,
			int type, int width, int height) {
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.dispose();

		return resizedImage;
	}

}
