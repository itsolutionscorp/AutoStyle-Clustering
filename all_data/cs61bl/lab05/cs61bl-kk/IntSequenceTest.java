import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testConstructor() {
		IntSequence s = new IntSequence(2);
		assertTrue (s.isEmpty());
//		assertFalse (s.member(0));
//		assertFalse (s.member(1));
	}
	@Test
	public void testisEmpty() {
		IntSequence s = new IntSequence(2);
		assertTrue (s.myCount == 0);
		assertTrue (s.myValues[0] == 0);
		assertTrue (s.myValues[1] == 0);
	}
	
	@Test
	public void testadd() {
		IntSequence s = new IntSequence(2);
		s.add(12);
		assertFalse(s.isEmpty());
		assertTrue(s.myValues[0] == 12);
		s.add(25);
		assertTrue (s.contains(25));
	}
	
	@Test
	public void testsize() {
		IntSequence s = new IntSequence(2);
		assertTrue (s.myCount == 0);
		s.add(0);
		assertTrue (s.myCount == 1);
		s.add(1);
		assertTrue (s.myCount == 2);
		assertTrue (s.myValues[1] == 1);
	}
	@Test
	public void testelementAt() {
		IntSequence s = new IntSequence(4);
		s.add(5);
		s.add(11);
		s.add(3);
		assertTrue (s.contains(11));
		assertTrue (s.elementAt(1) == s.myValues[1]);
	}
	@Test
	public void testtoString() {
		IntSequence s = new IntSequence(4);
		s.add(24);
		s.add(58);
		s.add(1);
		assertEquals (s.toString(), "24 58 1");
	}
	@Test
	public void testremove() {
		IntSequence s = new IntSequence(7);
		s.add(3);
		s.add(47);
		s.add(42);
		s.add(11);
		assertTrue (s.myCount == 4);
	}
	
	@Test
	public void testinsert() {
		IntSequence s = new IntSequence(7);
		s.add(3);
		s.add(47);
		s.add(42);
		s.add(11);
		s.insert(90, 2);
		assertTrue (s.myValues[2] == 90);
		assertTrue (s.myValues[3] == 42);
	}
}
