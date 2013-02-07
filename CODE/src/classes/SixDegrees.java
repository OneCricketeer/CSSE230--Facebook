package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class SixDegrees {

	static HashMap<Integer, User> users = new HashMap<Integer, User>();
	static HashMap<String, Group> groups;
	static User current;

	public SixDegrees() {
		users = new HashMap<Integer, User>();
		groups = new HashMap<String, Group>();
		current = null;
		

	}

	public static void cleanUp() {
		if (users != null) {
			users.clear();
			users=new HashMap<Integer, User>();
		}
		if (groups != null) groups.clear();
		current = null;
		User.resetCounter();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User[] testUser = new User[20];
		for (int i = 0; i < 20; i++) {
			testUser[i] = new User();
		}

		current = testUser[10];

		int dist = getDistance(testUser[4].getUid());

	}

	public static void addUser(User user) {
		users.put(user.getUid(), user);
	}

	public static void setCurrentUser(User user) {
		current = user;
	}

	public static int getDistance(Integer uID) {
		if (uID < 0) // || !users.containsKey(uID))
			return -1;
		if (current == null || current.getUid() == uID)
			return 0;
		int degree = 0;
		Queue<Node> nodeQue = new LinkedList<Node>();
		Node currentNode = new Node(degree, current);
		nodeQue.offer(currentNode);
		Node next = nodeQue.poll();

		if (current.getUid() == uID) {
			return degree;
		}

		while (next != null) {
			ArrayList<User> friends = next.user.getFriends();

			for (User friend : friends) {
//				System.out.println(friend.getUid());
				if (friend.getUid() == uID) {
					return next.level + 1;
				} else {
					Node friendNode = new Node(next.level + 1, friend);
					nodeQue.offer(friendNode);
				}
			}
			next = nodeQue.poll();
		}

		if (degree > 6)
			System.out
					.println("You are further than 6 degrees from this person");
		return degree;
	}

	static class Node {
		public int level;
		private User user;

		public Node() {
			this.level = 0;
			this.user = null;
		}

		public Node(int level, User user) {
			this.level = level;
			this.user = user;
		}
	}


}
