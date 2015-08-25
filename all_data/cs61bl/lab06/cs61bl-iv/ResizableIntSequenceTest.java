import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testConstructor() {
		ResizableIntSequence a = new ResizableIntSequence(10);
		assertTrue(a.myValues.length == 10);
	}
	
	public void testAdd() {
		ResizableIntSequence a = new ResizableIntSequence(3);
		a.add(1);
		a.add(2);
		assertEquals("1 2", a.toString());
		a.add(3);
		a.add(4);
		assertTrue(a.elementAt(3) == 4);
		assertTrue(a.size() == 4);
	}
	
	public void testInsert() {
		ResizableIntSequence a = new ResizableIntSequence(3);
		a.add(1);
		a.insert(2,1);
		assertEquals("1 2", a.toString());
		a.add(3);
		a.insert(4,2);
		assertTrue(a.elementAt(3) == 3);
		assertTrue(a.size() == 4);
	}

	public void testRemove() {
		ResizableIntSequence a = new ResizableIntSequence(10);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.remove(3);
		assertTrue(a.size() == 6);
		assertTrue(a.myValues.length == 10);
		assertTrue(a.elementAt(3) == 5);
		a.remove(3);
		assertTrue(a.size() == 5);
		assertTrue(a.myValues.length == 7);
		assertTrue(a.elementAt(3) == 6);
		
	}
}
