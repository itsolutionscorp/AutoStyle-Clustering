import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence s = new IntSequence(10);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(1);
		s.add(3);
		assertFalse (s.isEmpty());
		assertTrue (s.size() == 1);
		assertTrue (s.elementAt(0) == 3);
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(3);
		s.add(1);
		s.add(2);
		s.add(3);
		assertEquals (s.toString(), "1 2 3");
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.remove(3);
		assertTrue (s.size() == 3);
		assertFalse (s.isEmpty());
		assertTrue (s.myCount == 3);
		s.remove(0);
		assertTrue (s.size() == 2);
		assertFalse (s.isEmpty());
		assertTrue (s.myCount == 2);
		assertTrue (s.elementAt(0) == 2);
		assertTrue (s.elementAt(1) == 3);
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.insert(5, 2);
		assertTrue (s.size() == 5);
		assertTrue (s.myCount == 5);
		assertTrue (s.elementAt(2) == 5);
		assertTrue (s.elementAt(3) == 3);
		assertTrue (s.elementAt(4) == 4);
		assertFalse (s.isEmpty());
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(5);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.contains(6) == false);
		assertFalse (s.contains(1) == false);
		assertTrue (s.contains(2) == true);
		assertFalse (s.contains (10) == true);
	}
}