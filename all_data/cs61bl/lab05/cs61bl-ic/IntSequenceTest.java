import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence s = new IntSequence(10);
		assertTrue (s.isEmpty());
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(7);
		s.add(3);
		assertTrue(s.size() == 3);
		s.remove(1);
		assertEquals(s.size(), 2);
		assertEquals(s.elementAt(1), 3);
		assertTrue(s.toString().equals("5 3"));
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(7);
		s.add(3);
		assertTrue(s.size() == 3);
		s.insert(1, 1);
		assertEquals(s.size(), 4);
		assertEquals(s.elementAt(1), 1);
		assertTrue(s.toString().equals("5 1 7 3"));
		s.insert(7, 0);
		assertEquals(s.size(), 5);
		assertEquals(s.elementAt(0), 7);
		assertTrue(s.toString().equals("7 5 1 7 3"));
		s.insert(9, 5);
		assertEquals(s.size(), 6);
		assertEquals(s.elementAt(5), 9);
		assertTrue(s.toString().equals("7 5 1 7 3 9"));
	}

	public void testContains() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(7);
		s.add(3);
		assertTrue(s.contains(5));
		assertTrue(s.contains(7));
		assertTrue(s.contains(3));
		assertFalse(s.contains(0));
	}
	
}
