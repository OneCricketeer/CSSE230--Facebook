package classes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class XMLFileIO {

	public static void main(String[] args) throws Exception {
		ArrayList<User> l = new ArrayList<User>();
		User stu = new User();
		User stu2 = new User();

		stu.setFName("Larry");
		stu.setLname("Stooge");
		stu.addFriend(stu2);
		l.add(stu);

		stu2.setFName("Moe");
		stu2.setLname("Stooge");
//		ArrayList<Student> f2 = new ArrayList<Student>();

//		stu2.addFriend(stu);
		l.add(stu2);

		// for (Student s : l)
		// System.out.println(s);
		//
		// write(l, "foo.xml");

		Hashtable<String, User> h = new Hashtable<String, User>();
		System.out.println(stu);
		h.put("hello", stu);
		write(h, "foo.xml");

//		 ArrayList<User> l2 = read("foo.xml");
//		 for (User s : l2)
//		 System.out.println(s);
	}

	public static void write(Hashtable<String, User> l, String filename)
			throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream(filename)));
		encoder.writeObject(l);
		encoder.close();
	}

	// public static void write(ArrayList<Student> l, String filename) throws
	// Exception{
	// XMLEncoder encoder =
	// new XMLEncoder(
	// new BufferedOutputStream(
	// new FileOutputStream(filename)));
	// encoder.writeObject(l);
	// encoder.close();
	// }

	public static ArrayList<User> read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(filename)));
		ArrayList<User> l = (ArrayList<User>) decoder.readObject();
		decoder.close();
		return l;
	}

}