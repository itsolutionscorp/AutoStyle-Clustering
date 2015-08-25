import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testResizableIntSequence() {
		ResizableIntSequence r = new ResizableIntSequence(4);
		assertTrue(r.myCount == 0);
		assertTrue(r.myCapacity == 4);
	}
	
	public void testadd() {
		ResizableIntSequence r = new ResizableIntSequence(4);
		r.add(1);
		r.add(2);
		r.add(3);
		assertEquals(r.size(), 3);
		assertTrue(r.myCapacity == 4);
		r.add(4);
		assertEquals(r.size(), 4);
		r.add(5);
		assertEquals(r.size(), 5);
		assertTrue(r.myCapacity == 5);
	}
	
	public void testinsert() {
		ResizableIntSequence r = new ResizableIntSequence(4);
		r.add(1);
		r.add(2);
		r.add(3);
		assertEquals(r.size(), 3);
		assertTrue(r.myCapacity == 4);
		r.insert(6, 1);
		assertEquals(r.elementAt(1), 6);
		assertEquals(r.elementAt(2), 2);
		assertTrue(r.size() == 4);
		assertTrue(r.myCapacity == 4);
		r.insert(7, 3);
		assertTrue(r.myCapacity == 5);
		assertEquals(r.elementAt(3), 7);
		assertEquals(r.elementAt(4), 3);
		assertTrue(r.size() == 5);
		assertTrue(r.myCapacity == 5);
		
	}
	
	public void testremove() {
		ResizableIntSequence r = new ResizableIntSequence(20);
		r.add(1);
		r.add(2);
		r.add(3);
		r.add(4);
		r.add(5);
		assertTrue(r.myCapacity == 20);
		assertTrue(r.size() == 5);
		assertTrue(r.remove(1) == 2);
		assertTrue(r.elementAt(1) == 3);
		assertTrue(r.size() == 4);
		assertTrue(r.myCapacity == 10);
	}
	
	
}
