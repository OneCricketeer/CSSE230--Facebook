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
		
		User Teddy =new User("sternetj", "Teddy", "Sterne");
		SixDegrees.addUser(Teddy);
		Teddy.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA984226893827.jpg");
		
		User Yashi =new User("yadavy", "Yashi", "Yadav");
		SixDegrees.addUser(Yashi);
		Yashi.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA639261010203.jpg");
		
		User Xinyu =new User("chengx", "Xinyu", "Cheng");
		SixDegrees.addUser(Xinyu);
		Xinyu.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA623581715011.jpg");
		
		User Jordan =new User("moorejm", "Jordan", "Moore");
		SixDegrees.addUser(Jordan);
		Jordan.setImage("https://lh6.googleusercontent.com/-UkGlE8ti1VI/UQSmcikr8-I/AAAAAAAAEaY/0jQOlZO675Y/s261/19e0a8e.jpg");
		
		User Logan =new User("ameslc", "Logan", "Ames");
		SixDegrees.addUser(Logan);
		Logan.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA631319860408.jpg");
		
		User Chris =new User("andrewca", "Christopher", "Andrews");
		SixDegrees.addUser(Chris);
		Chris.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA117476273108.jpg");
		
		User Ethan =new User("campbeeg", "Ethan", "Campbell");
		SixDegrees.addUser(Ethan);
		Ethan.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA206154545857.jpg");
		
		User Maxwell =new User("cookmj", "Maxwell", "Cook");
		SixDegrees.addUser(Maxwell);
		Maxwell.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA667385404666.jpg");
		
		User Rain =new User("darttrf", "Rain", "Dartt");
		SixDegrees.addUser(Rain);
		Rain.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA496213189669.jpg");
		
		User Laura =new User("daveyle", "Laura", "Davey");
		SixDegrees.addUser(Laura);
		Laura.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA356631901201.jpg");
		
		User Kevin =new User("Baconnk", "Kevin", "Bacon");
		SixDegrees.addUser(Kevin);
		Kevin.setImage("http://static5.businessinsider.com/image/505206dc6bb3f7d021000007/kevin-bacon-footloose.jpg");
		
		User DanH =new User("hansondg", "Dan", "Hanson");
		SixDegrees.addUser(DanH);
		DanH.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA025472706770.jpg");
		
		User Mark =new User("heinmr", "Mark", "Hein");
		SixDegrees.addUser(Mark);
		Mark.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA280590849592.jpg");
		
		User Ian =new User("kowalsif", "Ian", "Kowalski");
		SixDegrees.addUser(Ian);
		Ian.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA324286011462.jpg");
		
		User JohnK =new User("kulczajr", "John", "Kulczak");
		SixDegrees.addUser(JohnK);
		JohnK.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA499054679287.jpg");
		
		User Josh =new User("maurerja", "Joshua", "Mauer");
		SixDegrees.addUser(Josh);
		Josh.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA792363723553.jpg");
		
		User Xiangbo =new User("mengx", "Xiangbo", "Meng");
		SixDegrees.addUser(Xiangbo);
		Xiangbo.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA462902089322.jpg");
		
		User JohnM =new User("mccormjt", "John", "McCormack");
		SixDegrees.addUser(JohnM);
		JohnM.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA074219886758.jpg");
		
		User Johnathan =new User("pearsojw", "Johnathan", "Pearson");
		SixDegrees.addUser(Johnathan);
		Johnathan.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA831473005455.jpg");
		
		User Isaac =new User("sanderib", "Isaac", "Sanders");
		SixDegrees.addUser(Isaac);
		Isaac.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA499093738515.jpg");
		
		User Sydney =new User("satchwsm", "Sydney", "Satchwill");
		SixDegrees.addUser(Sydney);
		Sydney.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA805500251911.jpg");
		
		User Brandon =new User("tombn", "Brandon", "Tom");
		SixDegrees.addUser(Brandon);
		Brandon.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA002819062544.jpg");
		
		User Maisey =new User("tuckerme", "Maisey", "Tucker");
		SixDegrees.addUser(Maisey);
		Maisey.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA054647514533.jpg");
		
		User DanW =new User("daveyle", "Dan", "Wells");
		SixDegrees.addUser(DanW);
		DanW.setImage("http://angel.rose-hulman.edu/IDphotos/AAAAAAAAAAAAAAAAAA949751781149.jpg");		
		
		
		
		
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
			System.err.println(filename + " could not be found or is in invalid XML format");
//			e.printStackTrace();
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
	
	public static HashMap<?, ?> read_HashMap(String filename) {
		HashMap<?, ?> h = null;
		try {
			h = (HashMap<?, ?>) xstream.fromXML(new File(filename));
			
			
		} catch (Exception e) {
			System.err.println(filename + " could not be found or is in invalid XML format");
			
		}
		return h;
	}
	
	

		public static void write(User u, String filename)
			throws FileNotFoundException {
		String xml = xstream.toXML(u);
		PrintStream ps = new PrintStream(filename);
		ps.print(xml);
		ps.close();
	}
	
	public static User read_User(String filename) {
		User u = null;
		try {
			u = (User) xstream.fromXML(new File(filename));
		} catch (Exception e) {
			System.err.println(filename + " could not be found or is in invalid XML format");
			
		}
		return u;
	}
	
}