package classes;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeSet;

/**
 * @author moorejm
 */
public class User implements Comparable<User>, Serializable {
	private static int userCount = -1;
	private Integer uid;
	private String fname, lname, status, userName;
	private MyCalendar calendar;
	private String work;
	private String dorm;
	private boolean isMale;
	private String email;
	private ArrayList<Group> organizations;
	private TreeSet<User> friends;
	private String date;
	private Date birthday;
	private Number phone;
	private String address;
	private String hometown;

	public static void resetCounter() {
		userCount = -1;
	}

	public User() {
		this.userName = "<User Name>";
		this.uid = ++userCount;
		this.status = "What's happening?";
		this.fname = "Default";
		this.lname = "User";
		this.calendar = new MyCalendar();
		setPhone(123456789L);
		setWork("");
		setDorm("");
		this.date = "";
		setAddress("123 Main Street");
		setHometown("Somewhere, USA");
		setEmail("");
		this.organizations = new ArrayList<Group>();
		this.friends = new TreeSet<User>();
		setBirthday(Calendar.getInstance().getTime());
		SixDegrees.addUser(this);
	}

	public User(String userName, String fname, String lname) {
		this();
		this.userName = userName;
		setEmail(getUserName()+"@example.com");
		setFname(fname);
		setLname(lname);
	}

	public Integer getUID() {
		return this.uid;
	}

	public int compareTo(User u) {
		if (!(u instanceof User))
			return 0;
		else {
			User comp = (User) u;
			return comp.getUID().compareTo(this.getUID());
		}
	}

	@Override
	public String toString() {
		String s = "";
		String space = "    ";
		String line = "\n";
		s += "[" + getUID() + "] Username: " + getUserName() + line;
		s += space + "Full Name: " + getName() + line;
		s += space + "Status: " + getStatus() + line;
		s += space + "Work: " + getWork() + line;
		s += space + "Dorm: " + getDorm() + line;
		s += space + "# of Friends: " + friends.size();
		return s;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return fname + " " + lname;
	}

	/*
	 * @param name the name to set
	 */
	public void setFname(String name) {
		this.fname = name;
	}

	public String getFname() {
		return this.fname;
	}

	public void setLname(String name) {
		this.lname = name;
	}

	public String getLname() {
		return this.lname;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String uName) {
		this.userName = uName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the calendar
	 */
	public MyCalendar getCalendar() {
		return calendar;
	}

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
	 * @param work
	 *            the work to set public void setWork(String work) { this.work =
	 *            work; }
	 * 
	 *            /**
	 * @return the dorm
	 */
	public String getDorm() {
		return dorm;
	}

	/**
	 * @param dorm
	 *            the dorm to set
	 */
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	/**
	 * @return the organizations
	 */
	public ArrayList<Group> getOrganizations() {
		return organizations;
	}

	public void addFriend(User friend) {
		if (!this.friends.contains(friend))
			this.friends.add(friend);
		if (!friend.friends.contains(this))
			friend.addFriend(this);
	}

	/**
	 * @return
	 */
	public ArrayList<User> getFriends() {
		ArrayList<User> ret = new ArrayList<User>();
		ret.addAll(friends);
		return ret;
	}

	/**
	 * 
	 * @param organization
	 *            organization to add
	 */
	public void addOrganization(Group organization) {
		this.organizations.add(organization);
	}

	public String getBasicInfo() {
		SimpleDateFormat fmt = new SimpleDateFormat("MMMMM dd yyyy");

		return "<html>" + fmt.format(this.birthday) + "<br />"
				+ this.getGender() + "<br />" + this.hometown + "</html>";
		// return this.birthday.toString() + "\n" + "Male" + "\n" +
		// this.hometown;
	}

	public String getContactInfo() {
		return "<html>" + this.phone.toString() + "<br />" + this.address
				+ "<br />" + "<br />" + this.email + "</html>";
	}

	public String getAbout() {
		return "<html>" + getUserName() + "<br /># Friends = " + friends.size()
				+ "</html>";

	}

	public Date getBirthDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("MMMMM dd yyyy");

		Date d = null;
		try {
			d = fmt.parse(date);
			cal.setTime(fmt.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// cal.set(d.getYear(), d.getMonth(), d.getDay(), 0, 0);
		return cal.getTime();
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setPhone(Number phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;

	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public void setUID(Integer uID) {
		this.uid = uID;
	}

	public void setGender(boolean gender) {
		this.isMale = gender;
	}

	public String getGender() {
		return this.isMale ? "Male" : "Female";
	}

	// public static void main(String[] args) {
	// User u = new User();
	// System.out.println(u.getBirthDate_String());
	// }

	// private int getBirthDay() {
	// return birthDay;
	// }
	//
	// private int getBirthMonth() {
	// return 0;
	// }
	//
	// private int getBirthYear() {
	// return 0;
	// }

	// public String getBirthDate_String() {
	// SimpleDateFormat fmt = new SimpleDateFormat("MMMMM dd yyyy");
	// return fmt.format(getBirthDate());
	// }
}
