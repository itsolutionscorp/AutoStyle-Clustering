import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence s = new IntSequence(3);
		assertTrue (s.isEmpty());
	}
	
	public void testisEmpty() {
		IntSequence s = new IntSequence(3);
		assertTrue (s.isEmpty());
		s.add(3);
		assertFalse(s.isEmpty());
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(10);
		assertTrue (s.size() == 0);
		s.add(5);
		assertTrue (s.size() == 1);
		s.add(5);
		s.add(5);
		s.add(5);
		s.add(5);
		assertTrue (s.size() == 5);
	}
	
	public void testelementAt() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(4);
		s.add(3);
		s.add(2);
		s.add(1);
		s.add(0);
		assertTrue(s.elementAt(0) == 5);
		assertTrue(s.elementAt(1) == 4);
		assertTrue(s.elementAt(2) == 3);
		assertTrue(s.elementAt(3) == 2);
		assertTrue(s.elementAt(4) == 1);
		assertTrue(s.elementAt(5) == 0);
	}
	
	public void testtoString() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(4);
		s.add(3);
		s.add(2);
		s.add(1);
		s.add(0);
		assertTrue(s.toString().equals("5 4 3 2 1 0"));
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(10);
		s.add(0);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.add(5);
		s.remove(3);
		assertTrue(s.toString().equals("0 1 2 4 5"));
		s.remove(4);
		assertTrue(s.toString().equals("0 1 2 4"));
		s.remove(0);
		assertTrue(s.toString().equals("1 2 4"));
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(4);
		s.add(3);
		assertTrue(s.toString().equals("5 4 3"));
		s.insert(0, 0);
		assertTrue(s.toString().equals("0 5 4 3"));
		s.insert(2, 2);
		assertTrue(s.toString().equals("0 5 2 4 3"));
		s.insert(4, 7);
		assertTrue(s.toString().equals("0 5 2 4 7 3"));
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(10);
		s.add(5);
		s.add(4);
		s.add(3);
		assertTrue(s.contains(3) && s.contains(4) && s.contains(5));
		assertFalse(s.contains(0));
	}
}
