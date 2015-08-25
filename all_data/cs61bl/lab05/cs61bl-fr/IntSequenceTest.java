import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {
	@Test
	public void constructorTest () {
		IntSequence test = new IntSequence(15);
		assertTrue(test.isEmpty());
		assertEquals(test.size(), 0);
	}
	
	@Test
	public void addTest () {
		IntSequence test = new IntSequence(10);
		test.add(10);
		test.add(50);
		assertTrue(test.isEmpty() == false);
		assertEquals(test.size(), 2);
		assertEquals(test.elementAt(0), 10);
		assertEquals(test.elementAt(1), 50);
	}
	@Test
	public void toStringTest () {
		IntSequence test = new IntSequence(5);
		test.add(6);
		test.add(75);
		assertEquals("6 75", test.toString());
	}
	@Test
	public void removeTest() {
		IntSequence test = new IntSequence(5);
		test.add(1);
		test.add(134);
		test.add(0);
		assertEquals(test.remove(0), 1);
		assertTrue(test.size() == 2);
		assertTrue(test.elementAt(1) == 0);
	}
	@Test
	public void insertTest() {
		IntSequence test = new IntSequence(5);
		test.add(1);
		test.add(134);
		test.add(0);
		test.insert(500, 0);
		System.out.println(test.size());
		assertEquals(test.size(), 4);
		assertTrue(test.elementAt(0) == 500);
		assertTrue(test.elementAt(3) == 0);
	}
	
	@Test
	public void containsTest() {
		IntSequence test = new IntSequence(5);
		test.add(1);
		test.add(134);
		test.add(0);
		assertTrue(test.contains(1));
		assertTrue(test.contains(0));
		test.insert(500, 0);
		assertTrue(test.contains(500));
		assertTrue(!test.contains(100));
	}


}
