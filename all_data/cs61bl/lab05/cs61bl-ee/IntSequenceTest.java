import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence s = new IntSequence(3);
		assertTrue(s.isEmpty());
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(2);
		s.add(1);
		assertTrue(s.elementAt(0) == 1);
		s.add(2);
		assertTrue(s.elementAt(1) == 2);
		/*s.add(3271);
		assertTrue(s.elementAt(0) == 1);
		assertTrue(s.elementAt(1) == 2);*/
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(3);
		s.insert(5, 0);
		assertTrue(s.elementAt(0) == 5);
		s.insert(7, 0);
		assertTrue(s.elementAt(0) == 7);
		assertTrue(s.elementAt(1) == 5);
		s.insert(6, 1);
		assertTrue(s.elementAt(0) == 7);
		assertTrue(s.elementAt(1) == 6);
		assertTrue(s.elementAt(2) == 5);
		
		IntSequence s2 = new IntSequence(8);
		s2.add(1);
		s2.add(2);
		s2.add(3);
		s2.add(4);
		s2.add(5);
		s2.add(6);
		s2.add(7);
		s2.insert(100, 4);
		assertEquals("1 2 3 4 100 5 6 7", s2.toString());
	}
	
	public void testIsEmpty() {
		IntSequence s = new IntSequence(5);
		assertTrue(s.isEmpty());
		s.add(1);
		assertFalse(s.isEmpty());
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(2);
		assertTrue(s.size() == 0);
		s.add(10);
		assertTrue(s.size() == 1);
		s.add(31);
		assertTrue(s.size() == 2);
		/*s.add(3271);
		assertTrue(s.size() == 2);*/
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(3);
		assertEquals("", s.toString());
		s.add(1);
		assertEquals("1", s.toString());
		s.add(3);
		assertEquals("1 3", s.toString());
		s.insert(2, 1);
		assertEquals("1 2 3", s.toString());
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		s.add(6);
		s.add(2);
		s.remove(1);
		assertEquals("1 2", s.toString());
		assertTrue(s.size() == 2);
		s.remove(1);
		assertEquals("1", s.toString());
		assertTrue(s.size() == 1);
		s.remove(0);
		assertEquals("", s.toString());
		assertTrue(s.size() == 0);
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		assertTrue(s.contains(1));
		s.add(2);
		assertTrue(s.contains(2));
		assertFalse(s.contains(3));
		s.remove(0);
		assertFalse(s.contains(1));
	}
}
