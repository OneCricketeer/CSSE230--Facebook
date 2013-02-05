import java.util.ArrayList;
import java.util.TreeSet;

public class User {
	private static int userCount;
	private Integer uid;
	private String name, status, userName;
	private MyCalendar calendar;
	private String work;
	private String dorm;
	private ArrayList<String> organizations;
	private TreeSet<User> friends;
	
	public User() {
		
	}
	
	public User(int id, String userName, String name) {
		id = ++userCount;
		userName = userName;
		name = name;
	}
	
	public void addFriend(User friend) {
		this.friends.add(friend);
	}
	
	public User[] getFriends() {
		 return this.friends.toArray(new User[0]);
	}
	
	
}
