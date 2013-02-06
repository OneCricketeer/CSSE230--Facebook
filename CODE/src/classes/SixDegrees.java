package classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;


public class SixDegrees {

	static HashMap<Integer, User> users;
	static HashMap<String, Group> groups;
	User current;
	
	public SixDegrees() {
		users = new HashMap<Integer, User>();
		groups = new HashMap<String, Group>();
		current = null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public int getDistanceFrom(Integer uid) {
		int dist = 0;
		
		if (current == null) {
			System.err.println("Current user does not exist");
		}
		Queue<User> q = new LinkedList<User>();
		ArrayList<User> friends = new ArrayList<User>(current.getFriends());
		q.offer(current);
		q.addAll(friends);
		
		User next = q.contains(uid) ? q.poll(): null; 
		while (next != null && next.getID() != uid) {
			friends = next.getFriends();
			q.offer(next);
			next = q.poll();
		}
		
		
		
		
		return dist;
		
	}

}
