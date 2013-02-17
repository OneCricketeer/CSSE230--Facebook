import classes.SixDegrees;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SixDegrees.load();
		SixDegreesViewer _6Deg = new SixDegreesViewer();
		_6Deg.setVisible(true);
	}

}
