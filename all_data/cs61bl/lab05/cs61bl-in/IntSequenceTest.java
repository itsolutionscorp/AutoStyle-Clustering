import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence s = new IntSequence(4);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(3);
		s.add(5);
		assertTrue (s.elementAt(0) == 5);
		assertTrue (s.size() == 1);
		assertFalse (s.isEmpty());
		
		s.add(6);
		assertTrue (s.elementAt(0) == 5);
		assertTrue (s.elementAt(1) == 6);
		assertTrue (s.size() == 2);
		
		
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(8);
		s.add(5);
		s.add(6);
		s.add(7);
		s.add(9);
		s.add(10);
		s.add(3);
		s.remove(2);
		assertTrue (s.size() == 5);
		assertTrue (s.elementAt(2) == 9);
		assertTrue (s.elementAt(1) == 6);
		assertTrue (s.elementAt(3) == 10);
		assertTrue (s.elementAt(4) == 3);
	}

	public void testToString() {
		IntSequence s = new IntSequence(4);
		assertEquals (s.toString(), "");
		s.add(1);
		s.add(3);
		s.add(5);
		assertEquals (s.toString(), "1 3 5");
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(9);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.add(5);
		s.add(6);
		s.add(7);
		s.insert(100,5);
		assertTrue (s.elementAt(5) == 100);
		assertTrue (s.elementAt(6) == 6);
		assertTrue (s.elementAt(7) == 7);
		assertTrue (s.size() == 8);
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(5);
		s.add(4);
		s.add(5);
		s.add(6);
		assertTrue (s.contains(4));
		assertFalse (s.contains(2));
	}
}
