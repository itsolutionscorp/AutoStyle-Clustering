import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void testResizableAdd(){
		ResizableIntSequence test = new ResizableIntSequence(3);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		assertTrue(test.myCount == 4);
		assertTrue(test.elementAt(3) == 4);
		test.add(5);
		assertTrue(test.myCount == 5);
		assertTrue(test.elementAt(4) == 5);
	}
	
	public void testResizableInsert(){
		ResizableIntSequence test = new ResizableIntSequence(3);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(7,1);
		assertTrue(test.myCount == 4);
		assertTrue(test.elementAt(3) == 3);
	}
	
	public void testResizableRemove(){
		ResizableIntSequence test = new ResizableIntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.remove(3);
		assertTrue(test.myValues.length == 5);
	}
}
