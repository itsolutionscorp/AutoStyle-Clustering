
import junit.framework.TestCase;

	public class ResizableIntSequenceTest extends TestCase {

		public void testAdd() {
			ResizableIntSequence testInt = new ResizableIntSequence(5);
			testInt.add(0);
			assertTrue(testInt.elementAt(0) == 0);
			testInt.add(1);
			testInt.add(2);
			testInt.add(3);
			testInt.add(4);
			assertTrue(testInt.myCount == 5);
			testInt.add(5);
			assertTrue(testInt.elementAt(5) == 5);
		}
		
	public void testInsert() {
		ResizableIntSequence testInt = new ResizableIntSequence(6);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		testInt.insert(8, 2);
		assertTrue(testInt.elementAt(0) == 1);
		assertTrue(testInt.elementAt(1) == 2);
		assertTrue(testInt.elementAt(2) == 8);
		assertTrue(testInt.elementAt(3) == 3);
		assertTrue(testInt.elementAt(4) == 4);
		assertTrue(testInt.myCount == 5);
		assertTrue(testInt.size() == 5);
		testInt.insert(5,2);
		assertTrue(testInt.size() == 6);
		assertTrue(testInt.elementAt(2) == 5);
		assertTrue(testInt.elementAt(3) == 8);
	}
	
	public void testInsertMore() {
		ResizableIntSequence testInt = new ResizableIntSequence(3);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		assertTrue(testInt.myCount == 3);
		assertTrue(testInt.myValues.length == 3);
		testInt.insert(8,1);
		assertTrue(testInt.elementAt(0) == 1);
		assertTrue(testInt.elementAt(1) == 8);
		assertTrue(testInt.elementAt(2) == 2);
		assertTrue(testInt.elementAt(3) == 3);
		assertTrue(testInt.myCount == 4);
	}

	public void testRemoveNew() {
		ResizableIntSequence testInt = new ResizableIntSequence(50);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		testInt.remove(1);
		assertTrue(testInt.elementAt(0) == 1);
		assertTrue(testInt.elementAt(1) == 3);
		assertTrue(testInt.elementAt(2) == 4);
		assertTrue(testInt.myCount == 3);
		assertTrue(testInt.myValues.length == 4);
		
	}
	
	public void testRemoveMore() {
		ResizableIntSequence testInt = new ResizableIntSequence(6);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		testInt.remove(1);
		assertTrue(testInt.elementAt(0) == 1);
		assertTrue(testInt.elementAt(1) == 3);
		assertTrue(testInt.elementAt(2) == 4);
		assertTrue(testInt.myCount == 3);
		assertTrue(testInt.myValues.length == 6);
		assertTrue(testInt.myValues[testInt.myCount] == 0);
	}
	
	}