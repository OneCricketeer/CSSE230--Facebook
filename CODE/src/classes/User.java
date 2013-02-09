package classes;

<<<<<<< HEAD

=======
>>>>>>> XML Writer
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author moorejm
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> XML Writer
 */
public class User implements Comparable<User>, Serializable {
	private static int userCount = -1;
	private Integer uid;
	private String fname, lname, status, userName;
	private MyCalendar calendar;
	private String work;
	private String dorm;
	private ArrayList<Group> organizations;
	private TreeSet<User> friends;

	public static void resetCounter() {
<<<<<<< HEAD
		userCount = -1;		
=======
		userCount = -1;
>>>>>>> XML Writer
	}

	public User() {
		this.userName = "<User Name>";
		this.uid = ++userCount;
		this.status = "What's happening?";
		this.fname = "Default";
		this.lname = "User";
		this.calendar = new MyCalendar();
		this.work = this.dorm = "";
		this.organizations = new ArrayList<Group>();
		this.friends = new TreeSet<User>();
		SixDegrees.addUser(this);
	}

	public User(String userName, String fname, String lname) {
		this();
		this.userName = userName;
<<<<<<< HEAD
		setFName(fname);
		setLname(lname);
	}

	public Integer getUid() {
		return this.uid;
	}
	
	public void setUid(Integer uid) {
		this.uid = uid;
	}
=======
		setFname(fname);
		setLname(lname);
	}

	public Integer getUID() {
		return this.uid;
	}
>>>>>>> XML Writer

	public int compareTo(User u) {
		if (!(u instanceof User))
			return 0;
		else {
			User comp = (User) u;
<<<<<<< HEAD
			return comp.getUid().compareTo(this.getUid());
=======
			return comp.getUID().compareTo(this.getUID());
>>>>>>> XML Writer
		}
	}

	@Override
	public String toString() {
		String s = "";
<<<<<<< HEAD
		String line = "\n";
		s+="[" + getUid() + "] Username: " + getUserName() + line;
		s+="Full Name:" + getName() + line;
		s+="Status: " + getStatus() + line;
		s+="Work: " + getWork() + "Dorm: " + getDorm() + line;
		s+="# of Friends: " + friends.size();
=======
		String space = "    ";
		String line = "\n";
		s += "[" + getUID() + "] Username: " + getUserName() + line;
		s += space + "Full Name: " + getName() + line;
		s += space + "Status: " + getStatus() + line;
//		s += space + "Work: " + getWork() + line;
//		s += space + "Dorm: " + getDorm() + line;
		s += space + "# of Friends: " + friends.size();
>>>>>>> XML Writer
		return s;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return fname + " " + lname;
	}

	/**
<<<<<<< HEAD
	 * @param name the name to set
	 */
	public void setFName(String name) {
		this.fname = name;
	}
	
	public String getFName() {
		return this.fname;
	}
	
=======
	 * @param name
	 *            the name to set
	 */
	public void setFname(String name) {
		this.fname = name;
	}

	public String getFname() {
		return this.fname;
	}

>>>>>>> XML Writer
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
<<<<<<< HEAD
	
=======

>>>>>>> XML Writer
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
<<<<<<< HEAD
	 * @param status the status to set
=======
	 * @param status
	 *            the status to set
>>>>>>> XML Writer
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
<<<<<<< HEAD
	
	
=======

	public void setCalendar(MyCalendar c) {
		calendar = c;
	}
>>>>>>> XML Writer

	/**
	 * @return the work
	 */
	public String getWork() {
		return work;
	}

	/**
<<<<<<< HEAD
	 * @param work the work to set
=======
	 * @param work
	 *            the work to set
>>>>>>> XML Writer
	 */
	public void setWork(String work) {
		this.work = work;
	}

	/**
	 * @return the dorm
	 */
	private String getDorm() {
		return dorm;
	}

	/**
<<<<<<< HEAD
	 * @param dorm the dorm to set
=======
	 * @param dorm
	 *            the dorm to set
>>>>>>> XML Writer
	 */
	private void setDorm(String dorm) {
		this.dorm = dorm;
	}

	/**
	 * @return the organizations
	 */
	private ArrayList<Group> getOrganizations() {
		return organizations;
	}
<<<<<<< HEAD
	
=======

>>>>>>> XML Writer
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
<<<<<<< HEAD
	 * @param organization organization to add
=======
	 * @param organization
	 *            organization to add
>>>>>>> XML Writer
	 */
	public void addOrganization(Group organization) {
		this.organizations.add(organization);
	}

	public String getBasicInfo() {
		return "Basic information about " + getName();
	}

	public String getContactInfo() {
		return "Contact information for " + getName();
	}
<<<<<<< HEAD
	
	public String getAbout() {
		return "<html>" + getUserName() + "<br /># Friends = " + friends.size() + "</html>"; 
				
	}
}
=======

	public String getAbout() {
		return "<html>" + getUserName() + "<br /># Friends = " + friends.size()
				+ "</html>";

	}
}
>>>>>>> XML Writer
