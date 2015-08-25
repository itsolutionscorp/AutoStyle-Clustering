import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence a = new IntSequence(4);
		assertEquals(a.myCount, 0);
		assertEquals(a.myCapacity, 4);
	}
	
	public void testAdd() {
		IntSequence a = new IntSequence(4);
		a.add(1);
		assertEquals(a.myValues[0], 1);
		a.add(2);
		assertEquals(a.myValues[1], 2);
		a.add(3);
		assertEquals(a.myValues[2], 3);
		a.add(4);
		assertEquals(a.myValues[3], 4);
		//a.add(5); //delete "//" to test if you can continue adding to full array
	}
	
	public void testIsEmpty() {
		IntSequence a = new IntSequence(10);
		assertTrue(a.isEmpty());
		a.add(10);
		a.insert(4, 2);
		assertFalse(a.isEmpty());
	}
	
	public void testSize(){
		IntSequence a = new IntSequence(10);
		assertEquals(a.size(), 0);
		a.add(10);
		a.insert(4, 2);
		assertEquals(a.size(), 2);
	}
	
	public void testElementAt() {
		IntSequence a = new IntSequence(10);
		a.add(10);
		assertEquals(a.elementAt(0), 10);
		a.add(11);
		a.add(12);
		a.add(13);
		a.insert(4, 2);
		assertEquals(a.elementAt(2), 4);
	}
	
	public void testToString() {
		IntSequence a = new IntSequence(10);
		a.add(10);
		a.add(11);
		a.add(12);
		a.add(13);
		assertEquals("10 11 12 13", a.toString());
		a.insert(9, 0);
		a.insert(14, 5);
		assertEquals("9 10 11 12 13 14", a.toString());
	}
	
	public void testRemove() {
		IntSequence a = new IntSequence(10);
		a.add(10);
		a.add(11);
		a.add(12);
		a.add(13);
		a.remove(0);
		assertEquals("11 12 13", a.toString());
		a.remove(2);
		assertEquals("11 12", a.toString());
		a.add(13);
		a.remove(1);
		assertEquals("11 13", a.toString());
		a.remove(0);
		a.remove(0);
		assertEquals(0, a.size());
		//a.remove(0); //remove "//" to test for removing an index that doesn't exist.
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(10);
		a.add(10);
		a.add(11);
		a.add(12);
		a.add(13);
		assertTrue(a.contains(10));
		assertTrue(a.contains(11));
		assertTrue(a.contains(13));
		assertFalse(a.contains(14));
		assertFalse(a.contains(9));
	}
}



