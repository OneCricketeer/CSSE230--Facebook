package tests;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import classes.Event;


public class Tests {

	@Before
	public void setUp() throws Exception {
		Event e = new Event(new Date(113, 5, 2), new Date(2013, 7, 2));
		System.out.println(e);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
