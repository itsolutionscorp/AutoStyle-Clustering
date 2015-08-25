import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testConstructor() {
		ResizableIntSequence s = new ResizableIntSequence(20);
		assertTrue (s.isEmpty());
		s.add(2);
		assertFalse (s.isEmpty());
		assertEquals(2, s.elementAt(0));
	}

	
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(2);
		s.add(2);
		s.add(4);
		assertTrue(s.myValues.length == 3);
		s.add(5);
		assertTrue(s.toString().equals("2 2 4 5"));
		assertTrue(s.myValues.length == 6);
		
	}
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(7);
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
		s.insert(8, 7);
		assertTrue(s.toString().equals("8 3 7 8 7 3 8 8"));
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(14);
		s.add(3);
		s.add(7);
		s.add(7);
		s.add(3);
		assertTrue(s.myValues.length == 14);
		s.remove(0);
		assertTrue(s.myValues.length == 7);
		assertTrue(s.toString().equals("7 7 3"));
		
	}
	
	public void testContains() {
		ResizableIntSequence s = new ResizableIntSequence(7);
		s.add(3);
		assertTrue(s.contains(3));
		assertFalse(s.contains(7));
	}
	
}
