package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * @author moorejm
 *
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
		userCount = -1;		
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
		setFName(fname);
		setLname(lname);
	}

	public Integer getUid() {
		return this.uid;
	}

	public int compareTo(User u) {
		if (!(u instanceof User))
			return 0;
		else {
			User comp = (User) u;
			return comp.getUid().compareTo(this.getUid());
		}
	}

	@Override
	public String toString() {
		String s = "";
		String line = "\n";
		s+="[" + getUid() + "] Username: " + getUserName() + line;
		s+="Full Name:" + getName() + line;
		s+="Status: " + getStatus() + line;
		s+="Work: " + getWork() + "Dorm: " + getDorm() + line;
		s+="# of Friends: " + friends.size();
		return s;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return fname + " " + lname;
	}

	/**
	 * @param name the name to set
	 */
	public void setFName(String name) {
		this.fname = name;
	}
	
	public void setLname(String name) {
		this.lname = name;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
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
	 * @param work the work to set
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
	 * @param dorm the dorm to set
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
	 * @param organization organization to add
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
	
	public String getAbout() {
		return "<html>" + getUserName() + "<br /># Friends = " + friends.size() + "</html>"; 
				
	}
}
