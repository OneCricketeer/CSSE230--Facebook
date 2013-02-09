package classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import com.thoughtworks.xstream.XStream;

public class XMLFileIO {

	final static XStream xstream = new XStream();

	public static void main(String[] args) throws Exception {
		
		ArrayList<User> l = new ArrayList<User>();
		
		User pSher = new User("pSherman", "P.", "Sherman");
		User nemo = new User("findMe", "Nemo", "the Clownfish");
		User dory = new User("blueFish", "Dory", "");
		
		pSher.addFriend(nemo);
		pSher.addFriend(dory);
		
		pSher.setStatus("Found some neat fish");
		nemo.setStatus("Find me!");
		dory.setStatus("Just keep swimming...");
		
		l.add(pSher);
		l.add(nemo);
		l.add(dory);
		
		
		System.out.println("Encoding: ");
		System.out.println("=================");
		
		for (User u : l) {
			System.out.println(u);
		}
		
		write(l, "fooList.xml");
		
		// Wait for it to write
		Thread.sleep(1000);
		
		System.out.println("\n\nDecoding: ");
		System.out.println("=================");
		
		ArrayList<User> new_userList = read_List("fooList.xml");
		
		new_userList.get(1).setStatus("I was found!");
		
		for (User u : new_userList) {
			System.out.println(u);
		}
		
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