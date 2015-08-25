import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testInsert() {
		IntSequence s = new IntSequence(4);
		s.insert(3, 0);
		s.insert(4, 0);
		s.insert(5, 2);
		assertEquals("4 3 5", s.toString());
		
		s.insert(6, 1);
		assertEquals("4 6 3 5", s.toString());
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(3);
		s.add(3);
		s.add(4);
		s.add(5);
		assertEquals("3 4 5", s.toString());
	}
	
	public void testIsEmpty() {
		IntSequence s = new IntSequence(3);
		assertEquals(true, s.isEmpty());
		s.add(3);
		assertEquals(false, s.isEmpty());
		s.add(4);
		assertEquals(false, s.isEmpty());
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(3);
		assertEquals(0, s.size());
		s.add(3);
		assertEquals(1, s.size());
		s.add(4);
		assertEquals(2, s.size());
	}
	
	public void testElementAt() {
		IntSequence s = new IntSequence(3);
		s.add(4);
		s.add(5);
		s.add(6);
		assertEquals(4, s.elementAt(0));
		assertEquals(5, s.elementAt(1));
		
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(4);
		s.add(3);
		s.add(4);
		s.add(5);
		s.add(6);
		s.remove(2);
		assertEquals("3 4 6", s.toString());
		s.remove(0);
		assertEquals("4 6", s.toString());
		
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(4);
		s.add(3);
		s.add(4);
		s.add(5);
		assertEquals(true, s.contains(4));
		assertEquals(false, s.contains(6));
		s.add(6);
		assertEquals(true, s.contains(6));
	}
}
