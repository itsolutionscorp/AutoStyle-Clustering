import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence mySequence = new IntSequence(5);
		assertTrue(mySequence.myCount == 0);
		assertTrue(mySequence.isEmpty());
	}
	public void testadd() {
		IntSequence s = new IntSequence(3);
		s.add(2);
		assertTrue(s.elementAt(0) == 2);
		assertTrue(s.elementAt(1) == 0);
		s.add(3);
		s.add(4);
		assertTrue(s.elementAt(1) == 3);
		assertTrue(s.elementAt(2) == 4);
//		s.add(5);
//		assertTrue(s.myCount == 3);
//		We don't know how to test for the error checking when it reaches capacity. 
//		Since the system doesn't return anything but just prints things out, its hard to test.
	}
	public void testInsert() {
        IntSequence s = new IntSequence(5);		
		s.add(11);
		s.add(12);
		s.add(-2);
		s.add(-11);
		s.insert(1, 2);
		assertTrue(s.elementAt(0) == 11);
		assertTrue(s.elementAt(1) == 2);
		assertTrue(s.elementAt(2) == 12);
		assertTrue(s.elementAt(3) == -2);
		assertTrue(s.elementAt(4) == -11);
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(3);
		s.add(1);
		assertTrue(s.size() == 1);
		s.add(2);
		assertTrue(s.size() == 2);
	
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(3);
		s.add(1);
		s.add(2);
		assertEquals(s.toString(),"1 2 ");
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(5);
		s.add(11);
		s.add(12);
		s.add(-2);
		s.add(-11);
		s.remove(2);
		assertEquals(s.toString(),"11 12 -11 ");
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(5);
		s.add(11);
		s.add(12);
		s.add(-2);
		assertTrue(s.contains(11));
		assertFalse(s.contains(10));
	}
}
