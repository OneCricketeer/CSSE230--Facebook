package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.thoughtworks.xstream.XStream;

public class XMLFileIO {

	/**
	 * XStream is open-source available from xstream.codehaus.org
	 */
	final static XStream xstream = new XStream();

	public static void main(String[] args) throws Exception {

		// ArrayList<User> l = new ArrayList<User>();
		//
		// User pSher = new User("pSherman", "P.", "Sherman");
		// User nemo = new User("findMe", "Nemo", "the Clownfish");
		// User dory = new User("blueFish", "Dory", "");
		//
		// pSher.addFriend(nemo);
		// pSher.addFriend(dory);
		//
		// pSher.setStatus("Found some neat fish");
		// nemo.setStatus("Find me!");
		// dory.setStatus("Just keep swimming...");
		//
		// l.add(pSher);
		// l.add(nemo);
		// l.add(dory);
		//
		// System.out.println("Encoding: ");
		// System.out.println("=================");
		//
		// for (User u : l) {
		// System.out.println(u);
		// }
		//
		// write(l, "fooList.xml");
		//
		// // Wait for it to write
		// Thread.sleep(1000);
		//
		// System.out.println("\n\nDecoding: ");
		// System.out.println("=================");
		//
		// ArrayList<User> new_userList = read_List("fooList.xml");
		//
		// new_userList.get(1).setStatus("I was found!");
		//
		// for (User u : new_userList) {
		// System.out.println(u);
		// }
		initInfo();
	}

	private static void initInfo() {
		User Teddy = new User("sternetj", "Teddy", "Sterne");
		SixDegrees.addUser(Teddy);
		User Yashi = new User("yadavy", "Yashi", "Yadav");
		SixDegrees.addUser(Yashi);
		User Xinyu = new User("chengx", "Xinyu", "Cheng");
		SixDegrees.addUser(Xinyu);
		User Jordan = new User("moorejm", "Jordan", "Moore");
		SixDegrees.addUser(Jordan);
		User Logan = new User("ameslc", "Logan", "Ames");
		SixDegrees.addUser(Logan);
		User Chris = new User("andrewca", "Christopher", "Andrews");
		SixDegrees.addUser(Chris);
		User Ethan = new User("campbeeg", "Ethan", "Campbell");
		SixDegrees.addUser(Ethan);
		User Maxwell = new User("cookmj", "Maxwell", "Cook");
		SixDegrees.addUser(Maxwell);
		User Rain = new User("darttrf", "Rain", "Dartt");
		SixDegrees.addUser(Rain);
		User Laura = new User("daveyle", "Laura", "Davey");
		SixDegrees.addUser(Laura);

		Group g10 = new Group("Student Activities Board");
		SixDegrees.addGroup(g10);
		g10.addMember(Teddy.getUID());
		g10.addMember(Yashi.getUID());
		g10.setDesc("This group plans student activities around campus.");

		Group g9 = (new Group("Delta Delta Delta"));
		SixDegrees.addGroup(g9);
		g9.addMember(Yashi.getUID());
		g9.setDesc("This organization is a sorority. ");

		Group g7 = (new Group("Society of Women Engineers"));
		SixDegrees.addGroup(g7);
		g7.addMember(Yashi.getUID());
		g7.addMember(Laura.getUID());
		g7.setDesc("This group consists of women engineers across campus.");

		Group g1 = (new Group("Engineers Without Borders"));
		SixDegrees.addGroup(g1);
		g1.addMember(Xinyu.getUID());
		g1.addMember(Jordan.getUID());
		g1.addMember(Logan.getUID());
		g1.setDesc("This organization travels to developing communities across the world in order to help improve their living conditions.");

		Group g2 = (new Group("Student Government Association"));
		SixDegrees.addGroup(g2);
		g2.addMember(Chris.getUID());
		g2.addMember(Ethan.getUID());
		g2.setDesc("This group represents the student body at Rose-Hulman.");

		Group g3 = (new Group("RHA"));
		SixDegrees.addGroup(g3);
		g3.addMember(Teddy.getUID());
		g3.addMember(Yashi.getUID());
		g3.addMember(Xinyu.getUID());
		g3.addMember(Jordan.getUID());
		g3.addMember(Logan.getUID());
		g3.addMember(Chris.getUID());
		g3.addMember(Ethan.getUID());
		g3.addMember(Maxwell.getUID());
		g3.addMember(Rain.getUID());
		g3.addMember(Laura.getUID());
		g3.setDesc("This organization is for students living in a residence hall on campus.");

		Group g4 = (new Group("Thorn"));
		SixDegrees.addGroup(g4);
		g4.addMember(Maxwell.getUID());
		g4.addMember(Rain.getUID());
		g4.setDesc("This organization publishes the campus newspaper called Rose Thorn");

		Group g5 = (new Group("The Monkey"));
		SixDegrees.addGroup(g5);
		g5.addMember(Laura.getUID());
		g5.setDesc("This organization is the student radio station at Rose-Hulman.");

		Group g6 = (new Group("Ultimate Frisbee"));

		SixDegrees.addGroup(g6);
		g6.addMember(Teddy.getUID());
		g6.addMember(Yashi.getUID());
		g6.addMember(Xinyu.getUID());
		g6.addMember(Jordan.getUID());
		g6.setDesc("This group plays Ultimate Frisbee on campus.");
		
		SixDegrees.save();
	}

	public static void write(ArrayList l, String filename)
			throws FileNotFoundException {
		String xml = xstream.toXML(l);
		PrintStream ps = new PrintStream(filename);
		ps.print(xml);
		ps.close();
	}

	public static ArrayList<User> read_List(String filename) {
		ArrayList<User> l = null;
		try {
			l = (ArrayList<User>) xstream.fromXML(new File(filename));
		} catch (Exception e) {
			System.err.println(filename
					+ " could not be found or is in invalid XML format");
			// e.printStackTrace();
		}
		return l;
	}

	public static void write(HashMap<?, ?> h, String filename)
			throws FileNotFoundException {
		String xml = xstream.toXML(h);
		PrintStream ps = new PrintStream(filename);
		ps.print(xml);
		ps.close();
	}

	public static void write(User u, String filename)
			throws FileNotFoundException {
		String xml = xstream.toXML(u);
		PrintStream ps = new PrintStream(filename);
		ps.print(xml);
		ps.close();
	}

	public static HashMap<?, ?> read_HashMap(String filename) {
		HashMap<?, ?> h = null;
		try {
			h = (HashMap<?, ?>) xstream.fromXML(new File(filename));

		} catch (Exception e) {
			System.err.println(filename
					+ " could not be found or is in invalid XML format");

		}
		return h;
	}

	public static User read_User(String filename) {
		User u = null;
		try {
			u = (User) xstream.fromXML(new File(filename));
		} catch (Exception e) {
			System.err.println(filename
					+ " could not be found or is in invalid XML format");

		}
		return u;
	}

}