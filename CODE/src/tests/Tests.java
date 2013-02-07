package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import classes.Event;
import classes.SixDegrees;
import classes.User;

public class Tests {
	private User[] small = new User[5];
	private User[] medium = new User[10];
	private User[] large = new User[20];
	final int[][] small_distanceNums = { 
			{ 0, 1, 1, 1, 2 },
			{ 1, 0, 2, 2, 1 },
			{ 1, 2, 0, 1, 1 }, 
			{ 1, 2, 1, 0, 1 },
			{ 2, 1, 1, 1, 0 } };
	final int[][] medium_distanceNums = { 
			{ 0, 1, 2, 1, 1, 1, 1, 1, 2, 2 },
			{ 1, 0, 1, 1, 2, 2, 2, 1, 1, 1 },
			{ 2, 1, 0, 1, 2, 3, 3, 2, 2, 1 },
			{ 1, 1, 1, 0, 1, 2, 2, 2, 2, 2 },
			{ 1, 2, 2, 1, 0, 1, 2, 2, 3, 3 },
			{ 1, 2, 3, 2, 1, 0, 1, 2, 3, 3 },
			{ 1, 2, 3, 2, 2, 1, 0, 1, 2, 3 },
			{ 1, 1, 2, 2, 2, 2, 1, 0, 1, 2 },
			{ 2, 1, 2, 2, 3, 3, 2, 1, 0, 1 },
			{ 2, 1, 1, 2, 3, 3, 3, 2, 1, 0 }, };
	final int[][] large_distanceNums = {
			{ 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 3, 1 },
			{ 1, 0, 2, 2, 2, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5, 4, 2 },
			{ 1, 2, 0, 2, 2, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 4, 5, 4, 2 },
			{ 1, 2, 2, 0, 2, 3, 3, 3, 3, 3, 3, 1, 1, 3, 3, 3, 4, 5, 2, 2 },
			{ 1, 2, 2, 2, 0, 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1, 2, 3, 2, 2 },
			{ 2, 1, 3, 3, 3, 0, 2, 2, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 5, 3 },
			{ 2, 1, 3, 3, 3, 2, 0, 2, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 5, 3 },
			{ 2, 1, 3, 3, 3, 2, 2, 0, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 5, 3 },
			{ 2, 3, 1, 3, 3, 4, 4, 4, 0, 2, 2, 4, 4, 4, 4, 4, 5, 6, 5, 1 },
			{ 2, 3, 1, 3, 3, 4, 4, 4, 2, 0, 2, 4, 4, 4, 4, 4, 5, 6, 5, 3 },
			{ 2, 3, 1, 3, 3, 4, 4, 4, 2, 2, 0, 4, 4, 4, 4, 4, 5, 6, 5, 3 },
			{ 2, 3, 3, 1, 3, 4, 4, 4, 4, 4, 4, 0, 2, 4, 4, 4, 5, 6, 3, 3 },
			{ 2, 3, 3, 1, 3, 4, 4, 4, 4, 4, 4, 2, 0, 2, 4, 4, 5, 6, 1, 3 },
			{ 2, 3, 3, 3, 1, 4, 4, 4, 4, 4, 4, 4, 2, 0, 2, 2, 3, 4, 1, 3 },
			{ 2, 3, 3, 3, 1, 4, 4, 4, 4, 4, 4, 4, 4, 2, 0, 2, 3, 4, 3, 3 },
			{ 2, 3, 3, 3, 1, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 0, 1, 2, 3, 3 },
			{ 3, 4, 4, 4, 2, 5, 5, 5, 5, 5, 5, 5, 5, 3, 3, 1, 0, 1, 4, 4 },
			{ 4, 5, 5, 5, 3, 6, 6, 6, 6, 6, 6, 6, 6, 4, 4, 2, 1, 0, 5, 5 },
			{ 3, 4, 4, 2, 2, 5, 5, 5, 5, 5, 5, 3, 1, 1, 3, 3, 4, 5, 0, 4 },
			{ 1, 2, 2, 2, 2, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 4, 5, 4, 0 } };

	public int getDistance(int current, int user, int[][] expected) {
		return expected[current][user];
	}
	
	private void reset() {
		SixDegrees.cleanUp();
	}

	public boolean testUserGraph(int uid, User[] users, int[][] expectedNums) {
		boolean b = false;
		SixDegrees.setCurrentUser(users[uid]);

		for (int i = 0; i < users.length; i++) {
			int expected = getDistance(uid, i, expectedNums);
			int actual = SixDegrees.getDistance(i);
			b = expected == actual;
			if (!b) {
				System.err.printf("Error: User%d To User%d\n", uid, i);
				System.err.printf("Exepcted: %d\tBut was: %d\n", expected,
						actual);
				break;
			}
		}
		if (b) {
			System.out.printf(
					"User%d passed\n==================================\n", uid);
		}
		return b;
	}

	@Test
	public void smallGraph() {
		reset();
		for (int i = 0; i < small.length; i++) {
			small[i] = new User();
			SixDegrees.addUser(small[i]);
		}
	
		small[0].addFriend(small[1]);
		small[0].addFriend(small[2]);
		small[0].addFriend(small[3]);
	
		small[1].addFriend(small[4]);
		small[2].addFriend(small[4]);
		small[2].addFriend(small[3]);
		small[3].addFriend(small[4]);
	
		for (int i = 0; i < small.length; i++) {
			System.out.println("=================================\nUser" + i
					+ " start");
			assertTrue(testUserGraph(i, small, small_distanceNums));
		}
	}

	@Test
	public void mediumGraph() {
		reset();
		for (int i = 0; i < medium.length; i++) {
			medium[i] = new User();
			SixDegrees.addUser(medium[i]);
		}
	
		// medium[0].addFriend(medium[1]);
		medium[0].addFriend(medium[3]);
		medium[0].addFriend(medium[4]);
		medium[0].addFriend(medium[5]);
		medium[0].addFriend(medium[6]);
		medium[0].addFriend(medium[7]);
	
		medium[1].addFriend(medium[3]);
		// medium[1].addFriend(medium[2]);
		medium[1].addFriend(medium[9]);
		medium[1].addFriend(medium[8]);
		medium[1].addFriend(medium[7]);
	
		// medium[2].addFriend(medium[3]);
		medium[2].addFriend(medium[9]);
	
		for (int i = 0; i < 9; i++) {
			medium[i].addFriend(medium[i + 1]);
		}
	
		for (int i = 0; i < medium.length; i++) {
			System.out.println("=================================\nUser" + i
					+ " start");
			assertTrue(testUserGraph(i, medium, medium_distanceNums));
		}
	}

	@Test
	public void largeGraph() {
		reset();
		for (int i = 0; i < large.length; i++) {
			large[i] = new User();
			SixDegrees.addUser(large[i]);
		}

		large[0].addFriend(large[1]);
		large[0].addFriend(large[2]);
		large[0].addFriend(large[3]);
		large[0].addFriend(large[4]);
		large[1].addFriend(large[5]);
		large[1].addFriend(large[6]);
		large[1].addFriend(large[7]);
		large[2].addFriend(large[8]);
		large[2].addFriend(large[9]);
		large[2].addFriend(large[10]);
		large[3].addFriend(large[11]);
		large[3].addFriend(large[12]);
		large[4].addFriend(large[13]);
		large[4].addFriend(large[14]);
		large[4].addFriend(large[15]);
		large[8].addFriend(large[19]);
		large[12].addFriend(large[18]);
		large[13].addFriend(large[18]);
		large[15].addFriend(large[16]);
		large[16].addFriend(large[17]);
		large[19].addFriend(large[0]);

		for (int i = 0; i < large.length; i++) {
			System.out.println("=================================\nUser" + i
					+ " start");
			assertTrue(testUserGraph(i, large, large_distanceNums));
		}
	}

}
