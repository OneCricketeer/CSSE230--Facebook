package classes;

import java.util.ArrayList;
import java.util.TreeSet;

public class User implements Comparable<User> {
	private static int userCount = -1;
	private Integer uid;
	private String name, status, userName;
	private MyCalendar calendar;
	private String work;
	private String dorm;
	private ArrayList<Group> organizations;
	private TreeSet<User> friends;

	public User() {
		this.userName = "<User Name>";
		this.uid = ++userCount;
		this.status = "What's happening?";
		this.name = "Default User";
		this.calendar = new MyCalendar();
		this.work = this.dorm = "";
		this.organizations = new ArrayList<Group>();
		this.friends = new TreeSet<User>();

	}

	public User(String userName, String name) {
		this();
		userName = userName;
		name = name;
	}

	public void addFriend(User friend) {
		if (!this.friends.contains(friend))
			this.friends.add(friend);
		if (!friend.friends.contains(this))
			friend.addFriend(this);
	}

	public Integer getUid() {
		return this.uid;
	}
	public ArrayList<User> getFriends() {
		ArrayList<User> ret = new ArrayList<User>();
		ret.addAll(friends);
		return ret;
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
		s+="[ " + getUid() + "] Username: " + getUserName();
		s+="Full Name:" + getName();
		s+="Status: " + getStatus();
		s+="Work: " + getWork() + "Dorm: " + getDorm();
		return s;
	}

	/**
	 * @return the name
	 */
	private String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the status
	 */
	private String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	private void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the work
	 */
	private String getWork() {
		return work;
	}

	/**
	 * @param work the work to set
	 */
	private void setWork(String work) {
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

	/**
	 * @param organizations the organizations to set
	 */
	private void setOrganizations(ArrayList<Group> organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the userName
	 */
	private String getUserName() {
		return userName;
	}

	/**
	 * @return the calendar
	 */
	private MyCalendar getCalendar() {
		return calendar;
	}

	public static void resetCounter() {
		userCount = -1;		
	}

}
