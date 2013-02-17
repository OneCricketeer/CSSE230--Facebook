package classes;

import java.io.Serializable;
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
	private final MyCalendar calendar;
	private String work;
	private String dorm;
	private final ArrayList<Group> groups;
	private final TreeSet<User> friends;
	private final String date;
	private String image;
	private boolean gender;
	private String email;
	private Date birthday;
	private Number phone;
	private String address;
	private String hometown;
	private String about;

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
		this.groups = new ArrayList<Group>();
		this.friends = new TreeSet<User>();
		about = "<html>" + getUserName() + "<br /># Friends = "
				+ friends.size() + "<br />In " + getOrganizations().size()
				+ " organization(s)" + "</html>";
		setBirthday(Calendar.getInstance().getTime());
		SixDegrees.addUser(this);
	}

	public User(String userName, String fname, String lname) {
		this();
		this.userName = userName;
		setEmail(getUserName() + "@example.com");
		setFname(fname);
		setLname(lname);
	}

	public Integer getUID() {
		return this.uid;
	}

	@Override
	public int compareTo(User u) {
		if (!(u instanceof User))
			return 0;
		else {
			User comp = u;
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

	public String getName() {
		return fname + " " + lname;
	}

	public void setFname(String name) {
		this.fname = name;
	}

	public void setImage(String url) {
		this.image = url;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String uName) {
		this.userName = uName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public MyCalendar getCalendar() {
		return calendar;
	}

	public String getWork() {
		return work;
	}

	public String getDorm() {
		return dorm;
	}

	public void setDorm(String dorm) {
		this.dorm = dorm;
	}

	public ArrayList<Group> getOrganizations() {
		return groups;
	}

	public void addFriend(User friend) {
		if (!this.friends.contains(friend)) {
			this.friends.add(friend);
		}
		if (!friend.friends.contains(this)) {
			friend.addFriend(this);
		}
	}

	public ArrayList<User> getFriends() {
		ArrayList<User> ret = new ArrayList<User>();
		ret.addAll(friends);
		return ret;
	}

	public void addOrganization(Group organization) {
		this.groups.add(organization);
		organization.addMember(getUID());
	}

	public String getBasicInfo() {
		SimpleDateFormat fmt = new SimpleDateFormat("MMMMM dd yyyy");

		return "<html>" + fmt.format(this.birthday) + "<br />"
				+ this.getGender() + "<br />" + this.hometown + "</html>";
	}

	public String getContactInfo() {
		return "<html>" + this.phone.toString() + "<br />" + this.address
				+ "<br />" + "<br />" + this.email + "</html>";
	}

	public String getAbout() {
		if (about == null)
			return "AboutText";
		about = "<html>" + getUserName() + "<br /># Friends = "
				+ friends.size() + "<br />In " + getOrganizations().size()
				+ " organization(s)" + "</html>";
		return about.replace("\n", "<br />");

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

		return cal.getTime();
	}

	public String getEvents() {
		return this.calendar.getEvents_List().toString();
	}

	public String getImageURL() {
		return this.image;
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
		this.gender = gender;
	}

	public String getGender() {
		return this.gender ? "Male" : "Female";
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param friend
	 * @return
	 */
	public boolean hasFriend(User friend) {
		return this.friends.contains(friend);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param group
	 */
	public void addGroup(Group group) {
		if (!this.groups.contains(group)) {
			this.groups.add(group);
		}
	}

	public boolean isInGroup(Group g) {
		return this.groups.contains(g);
	}

	public String getAddress() {
		return this.address;
	}

	public Number getPhone() {
		return this.phone;
	}

	public void startEventCalendar() {
		Thread t = new Thread(calendar);
		t.start();
	}

	public void setAbout(String about) {
		if (about.startsWith("<html>") && about.endsWith("</html>")) {
			this.about = about;
		} else {
			this.about = "<html>" + about.replace("\n", "<br />") + "</html>";
		}
	}

	public void removeFriend(User displayed) {
		if(friends.contains(displayed)){
			friends.remove(displayed);
			displayed.removeFriend(this);
		}
	}

}
