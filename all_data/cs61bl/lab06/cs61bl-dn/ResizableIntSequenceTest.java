import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	
	public void testAddResizeableConstructor(){
		IntSequence test = new ResizableIntSequence(2);
		test.add(1);
		test.add(2);
		test.add(3);		
		assertTrue  (test.myValues.length == 3);
	}
	
	public void testInsertResizeableConstructor(){
		IntSequence test = new ResizableIntSequence(2);
		test.add(1);
		test.add(2);
		test.insert(4,1);
		assertTrue  (test.myValues.length == 3);
	}	
	
	public void testRemoveResizeableConstructor(){
		IntSequence test = new ResizableIntSequence(4);
		test.add(1);
		test.add(2);
		test.remove(1);
		assertTrue  (test.myValues.length == 4);
	}	
	
	
	public void testConstructor() {
		IntSequence test = new IntSequence(5);
		assertTrue  (test.myValues.length == 5);
		assertFalse (test.myValues.length == 4);
		assertTrue  (test.myCount == 0);
		assertFalse (test.myCount == 5);
	}
	
	public void testAdd() {
		IntSequence test = new IntSequence(5);
		test.add(5);
		assertTrue  (test.myCount == 1);
		assertTrue (test.myValues[0] == 5);
		test.add(10);
		assertTrue  (test.myCount == 2);
		assertTrue (test.myValues[0] == 5);
		assertTrue (test.myValues[1] == 10);	
	}
	
	public void testInsert() {
		IntSequence test = new IntSequence(5);
		test.add(5);
		test.add(10);
		assertTrue  (test.myCount == 2);
		assertTrue (test.myValues[0] == 5);
		assertTrue (test.myValues[1] == 10);
		test.insert(15,1);
		assertTrue  (test.myCount == 3);
		assertTrue (test.myValues[0] == 5);
		assertTrue (test.myValues[1] == 15);	
		assertTrue (test.myValues[2] == 10);	
	}
	
	public void testIsEmpty() {
		IntSequence test = new IntSequence(5);
		assertTrue  (test.myCount == 0);
		assertEquals (test.isEmpty(), true);
		test.add(5);
		assertTrue  (test.myCount == 1);
		assertEquals (test.isEmpty(), false);
	}
	
	public void testSize() {
		IntSequence test = new IntSequence(5);
		test.add(5);
		test.add(10);
		test.add(15);
		test.add(20);
		assertEquals (test.size(), 4);
	}
	
	public void testElementAt() {
		IntSequence test = new IntSequence(3);
		test.add(3);
		test.add(1);
		test.add(4);
		assertEquals (test.elementAt(0), 3);
	}
	
	public void testToString() {
		IntSequence test = new IntSequence(3);
		test.add(3);
		test.add(1);
		test.add(4);
		assertTrue (test.toString().equals("3 1 4"));
	}

	public void testRemove() {
		IntSequence test = new IntSequence(5);
		test.add(3);
		test.add(1);
		test.add(4);
		test.remove(1);
		assertTrue (test.myValues[0] == 3);
		assertTrue (test.myValues[1] == 4);	
		assertTrue (test.myValues[2] == 0);	
		assertTrue  (test.myCount == 2);
	}
	
	public void testContains() {
		IntSequence test = new IntSequence(6);
		assertTrue  (test.myCount == 0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		assertTrue  (test.myCount == 4);
		assertEquals (test.contains(2), true);
		assertEquals (test.contains(4), true);
		assertEquals (test.contains(5), false);

	}
}
	
	