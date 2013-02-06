package classes;
import java.util.ArrayList;
import java.util.TreeSet;

public class User {
	private static int userCount;
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
		this.friends.add(friend);
	}
	
	public ArrayList<User> getFriends() {
		ArrayList<User> ret = new ArrayList<User>();
		ret.addAll(friends);
		return ret;
	}

	public Integer getID() {
		return this.uid;
	}
	
	
	
	
}
