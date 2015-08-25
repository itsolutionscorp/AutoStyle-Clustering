import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence test = new ResizableIntSequence(2);
		test.add(1);
		test.add(2);
		test.add(3);
		assertTrue(test.myValues.length==3);
		assertTrue(test.myValues[2]==3);
	}
	public void testInsert() {
		ResizableIntSequence test = new ResizableIntSequence(2);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(4,2);
		assertTrue(test.myValues.length == 4);
		assertTrue(test.myValues[2]==4);
		assertTrue(test.myValues[3]==3);
	}
	public void testRemove() {
		ResizableIntSequence test = new ResizableIntSequence(2);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(4,2);
		test.remove(1);
		assertTrue(test.myValues.length == 3);
		assertTrue(test.myValues[1]==4);
		assertTrue(test.myValues[2]==3);
	}

}
