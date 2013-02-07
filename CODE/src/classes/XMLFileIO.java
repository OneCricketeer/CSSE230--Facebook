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
		ArrayList<Student> l = new ArrayList<Student>();
		Student stu = new Student();
		Student stu2 = new Student();

		stu.setID("4");
		stu.setFirstName("Larry");
		stu.setLastName("Stooge");
		stu.setFavoriteColor("brunette");
		ArrayList<Student> f1 = new ArrayList<Student>();
		f1.add(stu2);
		stu.setFriends(f1);
		l.add(stu);

		stu2.setID("1");
		stu2.setFirstName("Moe");
		stu2.setLastName("Stooge");
		stu2.setFavoriteColor("blonde");
		ArrayList<Student> f2 = new ArrayList<Student>();
		f2.add(stu);
		stu2.setFriends(f2);
		l.add(stu2);

		// for (Student s : l)
		// System.out.println(s);
		//
		// write(l, "foo.xml");

		Hashtable<String, Student> h = new Hashtable<String, Student>();
		h.put("hello", stu);
		write(h, "foo.xml");

		// ArrayList<Student> l2 = read("foo.xml");
		// for (Student s : l2)
		// System.out.println(s);
	}

	public static void write(Hashtable<String, Student> l, String filename)
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

	public static ArrayList<Student> read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(filename)));
		ArrayList<Student> l = (ArrayList<Student>) decoder.readObject();
		decoder.close();
		return l;
	}

}