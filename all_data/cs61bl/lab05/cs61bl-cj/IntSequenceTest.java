import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testEmpty() {
		IntSequence array1 = new IntSequence(5);
		assertTrue (array1.isEmpty());
	}
	
	public void testSize() {
		IntSequence array1 = new IntSequence(6);
		assertEquals (array1.size(), 0);
	}
	
	public void testElementAt() {
		IntSequence array1 = new IntSequence(6);
		array1.add(3);
		assertTrue(array1.elementAt(0) == 3);
		array1.add(4);
		array1.add(5);
		array1.add(6);
		array1.add(7);
		assertTrue(array1.elementAt(5) == 0);
		array1.add(8);
		assertTrue(array1.elementAt(5) == 8);
	}
	
	public void testAdd() {
		IntSequence array1 = new IntSequence(3);
		assertTrue(array1.elementAt(0) == 0);
		assertTrue(array1.size() == 0);
		array1.add(5);
		array1.add(6);
		assertTrue(array1.size() == 2);
		array1.add(7);
		assertTrue(array1.size() == 3);
	}
	
	public void testToString() {
		IntSequence array1 = new IntSequence(6);
		array1.add(4);
		array1.add(4);
		array1.add(4);
		array1.add(4);
		assertEquals("4 4 4 4", array1.toString());
	}
	
	public void testRemove() {
		IntSequence array1 = new IntSequence(6);
		array1.add(4);
		array1.add(5);
		array1.add(6);
		array1.add(7);
		array1.remove(2);
		assertTrue(array1.myCount == 3);
		assertTrue(array1.elementAt(2) == 7);
		array1.remove(1);
		assertTrue(array1.myCount == 2);
		assertTrue(array1.elementAt(1) == 7);
	}
	
	public void testInsert() {
		IntSequence array1 = new IntSequence(4);
		array1.add(4);
		array1.add(5);
		array1.insert(6, 2);
		assertTrue(array1.size() == 3);
		assertTrue(array1.elementAt(2) == 6);
		array1.insert(3, 0);
		assertTrue(array1.elementAt(0) == 3);
		assertTrue(array1.size() == 4);
	}
	
	public void testContains() {
		IntSequence array1 = new IntSequence(4);
		array1.add(4);
		assertTrue(array1.contains(4) == true);
		array1.add(5);
		array1.insert(6, 2);
		assertTrue(array1.size() == 3);
		array1.insert(3, 0);
		assertTrue(array1.size() == 4);
	}
}
