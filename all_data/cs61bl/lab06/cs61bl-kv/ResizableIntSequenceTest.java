import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testConstructor() {
		ResizableIntSequence s = new ResizableIntSequence(20);
		assertTrue (s.isEmpty());
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.insert(2, 0);
		assertFalse (s.isEmpty());
		assertEquals ("2", s.toString());

		s.insert(1, 0);
		assertFalse (s.isEmpty());
		assertEquals ("1 2", s.toString());
		
		s.insert(3, 2);
		assertFalse (s.isEmpty());
		assertEquals ("1 2 3", s.toString());
		
		s.insert(1, 0);
		s.insert(1, 0);
		s.insert(1, 0);
		assertTrue(s.myCount == 6);
		assertTrue(s.cap() == 15);
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		s.add(0);
		s.add(1);
		s.add(2);
		s.add(3);
		assertEquals ("0 1 2 3", s.toString());
		s.remove(1);
		assertEquals ("0 2 3", s.toString());
		assertFalse (s.isEmpty());
		
		s.insert(1, 1);
		assertEquals ("0 1 2 3", s.toString());
		s.remove(0);
		assertEquals ("1 2 3", s.toString());
		s.remove(2);
		assertEquals ("1 2", s.toString());
		s.remove(1);
		assertTrue(s.cap() == 6);
	}
	
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(0);
		s.add(1);
		s.add(2);
		s.add(3);
		assertEquals ("0 1 2 3", s.toString());
		assertTrue(s.contains(1));
		assertTrue(s.contains(0));
		assertTrue(s.contains(3));
		assertFalse(s.contains(4));
		s.add(4);
		s.add(5);
		assertEquals ("0 1 2 3 4 5", s.toString());
		assertTrue(s.myCount == 6);
		assertTrue(s.cap() == 15);
		
		s.remove(2);
		assertEquals ("0 1 3 4 5", s.toString());
		assertFalse(s.contains(2));
	}
}