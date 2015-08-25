import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {
	
	@Test
	public void testConstructor() {
		IntSequence s1 = new IntSequence(20);
		assertTrue(s1.myCount == 0);
		assertTrue(s1.isEmpty());
		assertTrue(s1.size() == 0);
		IntSequence s2 = new IntSequence(0);
		assertTrue(s2.myCount == 0);
		assertTrue(s2.isEmpty());
		assertTrue(s2.size() == 0);
	}
	
	@Test
	public void testIsEmpty() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(i);
		}
		IntSequence s2 = new IntSequence(20);
		assertTrue(s2.isEmpty());
		assertFalse(s1.isEmpty());
		s2.add(777);
		assertFalse(s2.isEmpty());
		s2.remove(0);
		assertTrue(s2.isEmpty());
	}
	
	@Test
	public void testSize() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(i);
		}
		assertTrue(s1.size() == 10);
		IntSequence s2 = new IntSequence(0);
		IntSequence s3 = new IntSequence(20);
		assertTrue(s2.size() == 0);
		assertTrue(s3.size() == 0);
		s3.add(1);
		assertTrue(s3.size() == 1);
		s3.remove(0);
		assertTrue(s3.size() == 0);
	}
	
	@Test
	public void testElementAt() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(i);
		}
		for (int i = 0; i < 10; i++) {
			assertTrue(s1.elementAt(i) == i);
		}
		//for testing only
		//assert(s1.elementAt(10) == -1);
		//IntSequence s2 = new IntSequence(0);
		//IntSequence s3 = new IntSequence(20);
		//assertTrue(s2.elementAt(0) == -1);
		//assertTrue(s3.elementAt(0) == -1);
	}
	
	@Test
	public void testAdd() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(i);
		}
		assertTrue(s1.myCount == 10);
		for (int i = 0; i < 10; i++) {
			assertTrue(s1.elementAt(i) == i);
		}
		s1.add(10);
		assertTrue(s1.myCount == 11);
		assertTrue(s1.elementAt(10) == 10);
	}
	
	@Test
	public void testInsert() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(2*i);
		}
		for (int i = 1; i < 20; i += 2) {
			s1.insert(i, i);
		}
		assertTrue(s1.myCount == 20);
		for (int i = 0; i < 20; i++) {
			assertTrue(s1.elementAt(i) == i);
		}
		IntSequence s2 = new IntSequence(1);
		s2.insert(3,0);
		assertTrue(s2.elementAt(0) == 3);
	}
	
	@Test
	public void testToString() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 20; i++) {
			s1.add(i);
		}
		assertTrue(s1.toString().equals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19"));
		IntSequence s2 = new IntSequence(0);
		assertTrue(s2.toString().equals(""));
	}
	
	
	@Test
	public void testRemove() {
		IntSequence s1 = new IntSequence(20);
		for (int i = 0; i < 10; i++) {
			s1.add(i);
		}
		s1.remove(5);
		assertTrue(s1.myCount == 9);
		IntSequence s2 = new IntSequence(20);
		for (int i = 0; i < 20; i++) {
			s2.add(i);
		}
		for (int i = 20; i >= 0; i--){
			s2.remove(i);
			assertTrue(s2.size() == i);
		}
	}

}
