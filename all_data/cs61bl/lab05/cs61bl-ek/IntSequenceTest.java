import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence s = new IntSequence(20);
		assertTrue (s.isEmpty());
		s.add(2);
		assertFalse (s.isEmpty());
		assertEquals(2, s.elementAt(0));
	}

	public void testElementAt() {
		// IntSequence s = new IntSequence(20);
		// s.elementAt(14); 
		// this would be used to test error
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(1);
		assertEquals(0, s.size());
		s.add(2);
		assertEquals(1,s.size());
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(1);
		s.add(2);
		assertEquals(1,s.size());
		// s.add(2); would be used to test error
		
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(5);
		s.add(3);
		s.add(7);
		assertTrue(s.toString().equals("3 7"));
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(4);
		s.add(3);
		s.add(7);
		s.add(7);
		s.add(3);
		// s.remove(4); This should error
		s.remove(3);
		assertTrue(s.toString().equals("3 7 7"));
		s.remove(1);
		assertTrue(s.toString().equals("3 7"));
		s.remove(0);
		assertTrue(s.toString().equals("7"));
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(8);
		s.add(3);
		s.add(7);
		s.add(7);
		s.add(3);
		s.insert(8, 2);
		assertTrue(s.toString().equals("3 7 8 7 3"));
		s.insert(8, 0);
		assertTrue(s.toString().equals("8 3 7 8 7 3"));
		s.insert(8, 6);
		assertTrue(s.toString().equals("8 3 7 8 7 3 8"));
		s.insert(8, 8);// nothing happens!
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(7);
		s.add(3);
		assertTrue(s.contains(3));
		assertFalse(s.contains(7));
	}
	
}
