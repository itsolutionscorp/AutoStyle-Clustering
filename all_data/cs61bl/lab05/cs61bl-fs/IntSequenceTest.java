import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testToString() {
		IntSequence mySequence = new IntSequence(3);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		assertEquals(mySequence.toString(), "1 2 3");
	}
	
	public void testIntSequence(){
		IntSequence mySequence = new IntSequence(3);
		assertEquals(mySequence.myCount, 0);
		
	}
	public void testadd(){
		IntSequence mySequence = new IntSequence(3);
		mySequence.add(1);
		assertEquals(mySequence.myCount, 1);
		mySequence.add(2);
		assertEquals(mySequence.myCount, 2);
		mySequence.add(3);
		assertEquals(mySequence.myCount, 3);

	}
	public void testsize(){
		IntSequence mySequence = new IntSequence(3);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		assertEquals(mySequence.size(), 3);
	}
	public void testIsEmpty(){
		IntSequence mySequence = new IntSequence(3);
		assertTrue(mySequence.isEmpty());
	}
	public void testelementAt(){
		IntSequence mySequence = new IntSequence(3);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		assertEquals(mySequence.elementAt(2), 3);
	}
	public void testremove(){
		IntSequence mySequence = new IntSequence(3);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.remove(1);
		assertEquals(mySequence.elementAt(1), 3);
		assertEquals(mySequence.myCount, 2);
		mySequence.remove(0);
		assertEquals(mySequence.elementAt(0), 3);
		assertEquals(mySequence.myCount, 1);
	}
	public void testinsert() {
		IntSequence mySequence = new IntSequence(5);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.insert(9, 2);
		assertEquals(mySequence.elementAt(2), 9);
		assertEquals(mySequence.elementAt(3), 3);
		assertEquals(mySequence.elementAt(4), 4);
	}
	public void testcontains() {
		IntSequence mySequence = new IntSequence(5);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		assertTrue(mySequence.contains(4));
		assertFalse(mySequence.contains(9));
	}
}
