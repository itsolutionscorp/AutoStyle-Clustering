import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testElementAt() {
		IntSequence testInt = new IntSequence(5);
		testInt.add(4);
		testInt.add(5);
		testInt.add(6);
		assertTrue(testInt.elementAt(0) == 4);
		assertTrue(testInt.elementAt(1) == 5);
		assertTrue(testInt.elementAt(2) == 6);
		assertTrue(testInt.myCount == 3);
		assertTrue(testInt.size() == 3);
	}
	
	public void testAdd() {
		IntSequence testInt = new IntSequence(5);
		testInt.add(4);
		assertTrue(testInt.elementAt(0) == 4);
	}
	
	public void testInsert() {
		IntSequence testInt = new IntSequence(6);
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
	}
	
	public void testInsertAgain() {
		IntSequence testInt = new IntSequence(9);
		testInt.add(1);
		testInt.add(2);
		testInt.add(0);
		testInt.add(4);
		testInt.add(2);
		testInt.add(3);
		testInt.add(7);
		testInt.insert(2, 3);
		assertTrue(testInt.elementAt(3) == 2);
		assertTrue(testInt.elementAt(4) == 4);
		assertTrue(testInt.elementAt(5) == 2);
		assertTrue(testInt.elementAt(6) == 3);
	}
	
	public void testRemove() {
		IntSequence testInt = new IntSequence(5);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		assertTrue(testInt.remove(1) == 2);
		assertTrue(testInt.elementAt(1) == 3);
		assertTrue(testInt.elementAt(2) == 4);
		assertTrue(testInt.myCount == 3);
	}
	
	public void testContains() {
		IntSequence testInt = new IntSequence(5);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		assertTrue(testInt.contains(1));
		assertTrue(testInt.contains(8) == false);
		assertTrue(testInt.contains(0) == false);
	}
	
	public void testString() {
		IntSequence testInt = new IntSequence(5);
		testInt.add(1);
		testInt.add(2);
		testInt.add(3);
		testInt.add(4);
		assertEquals(testInt.toString(), "1 2 3 4");
		
	}
}
