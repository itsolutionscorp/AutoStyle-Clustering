import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd() {
		ResizableIntSequence ii = new ResizableIntSequence(0);
		assertTrue(ii.size() == 0);
		ii.add(1);
		ii.add(2);
		ii.add(3);
		ii.add(4);
		assertTrue(ii.size() == 4);
	}
	
	public void testInsert() {
		ResizableIntSequence ii = new ResizableIntSequence(0);
		ii.add(1);
		ii.add(2);
		ii.add(3);
		ii.add(4);
		ii.insert(10, 2);
		assertTrue(ii.elementAt(1) == 2);
		assertTrue(ii.elementAt(2) == 10);
		assertTrue(ii.elementAt(3) == 3);
		assertTrue(ii.size() == 5);
	}
	
	public void testRemove() {
		ResizableIntSequence ii = new ResizableIntSequence(0);
		ii.add(1);
		ii.add(2);
		ii.add(3);
		ii.add(4);
		ii.remove(2);
		assertTrue(ii.elementAt(1) == 2);
		assertTrue(ii.elementAt(2) == 4);
		assertTrue(ii.size() == 3);
	}


}
