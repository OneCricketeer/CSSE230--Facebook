package classes;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class SixDegrees implements Serializable {

	static HashMap<Integer, User> users = new HashMap<Integer, User>();
	static HashMap<Integer, Group> groups = new HashMap<Integer, Group>();
	static User current;

	public SixDegrees() {
		users = new HashMap<Integer, User>();
		groups = new HashMap<Integer, Group>();
		current = null;
	}

	public static void cleanUp() {
		User.resetCounter();
		if (users != null)
			users.clear();
		if (groups != null)
			groups.clear();
		current = null;
	}

	/**
	 * @param args
	 */
	public static void load() {
		users.clear();
		groups.clear();
		
		users = (HashMap<Integer, User>) XMLFileIO.read_HashMap("userHash.xml");
		groups = (HashMap<Integer, Group>) XMLFileIO.read_HashMap("groupHash.xml");
		
		if (users == null) {
			users = new HashMap<Integer, User>();
		}
		if (groups == null) {
			groups = new HashMap<Integer, Group>();
		}
		
	}
	
	public static void save() {
		try {
			XMLFileIO.write(users, "userHash.xml");
			XMLFileIO.write(groups, "groupHash.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		load();
	}
	public static void addUser(User user) {
		users.put(user.getUID(), user);
	}
	
	public static void addGroup(Group group) {
		groups.put(group.getGID(), group);
	}

	public static void setCurrentUser(User user) {
		current = user;
	}

	public static int getDistance(Integer uID) {
        if (uID < 0 || !users.containsKey(uID))
              return -1;
        if (current == null || current.getUID() == uID)
              return 0;
        
        int degree = 0;
        Queue<Node> nodeQue = new LinkedList<Node>();
        Hashtable<Integer, User> visitedUser = new Hashtable<Integer, User>();
        
        Node currentNode = new Node(degree, current);
        nodeQue.offer(currentNode);
        visitedUser.put(current.getUID(), current);
        Node next = nodeQue.poll();

        if (current.getUID() == uID) {
              return degree;
        }

        while (next != null) {
              ArrayList<User> friends = next.user.getFriends();

              for (User friend : friends) {
                    if (friend.getUID() == uID) {
                          return next.level + 1;
                    } else {
                          if(!visitedUser.containsKey(friend.getUID())){
                        	    visitedUser.put(friend.getUID(),friend);
                                Node friendNode = new Node(next.level + 1, friend);
                                nodeQue.offer(friendNode);
                          }                             
                    }
              }
              next = nodeQue.poll();
        }
        
        //Friend can't be found.
        return -1;
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

	public static HashMap<Integer, User> getUsers() {
		return users;
	}
	
	public static HashMap<Integer, Group> getGroups() {
		return groups;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public static User getCurrentUser() {
		return current;
	}

}
