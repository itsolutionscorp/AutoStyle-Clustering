import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructorNisEmpty() {
		IntSequence test = new IntSequence(10);
		assertEquals(test.myCount, 0);
		assertTrue(test.isEmpty());
	}
	
	public void testAddNSizeNElementAt() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		assertEquals(test.myCount, 1);
		assertEquals(test.elementAt(0), 1);
		
		test.add(3);
		assertEquals(test.myCount, 2);
		assertEquals(test.elementAt(1), 3);
		
		assertEquals(test.size(), 2);
	}
	
	public void testRemove() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(3);
		test.add(5);
		test.add(7);
		test.add(9);
		
		test.remove(3);
		assertEquals(test.size(), 4);
		assertEquals(test.elementAt(3), 9);
		assertEquals(test.elementAt(1), 3);
		
		test.remove(0);
		assertEquals(test.size(), 3);
		assertEquals(test.elementAt(0), 3);
	}
	
	public void testInsert() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.add(7);
		test.insert(100, 4);
		
		assertEquals(test.size(), 8);
		assertEquals(test.elementAt(4), 100);
		assertEquals(test.elementAt(5), 5);
		assertEquals(test.elementAt(6), 6);
	}
	
	public void testContains() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.add(7);
		
		assertFalse(test.contains(8));
		assertTrue(test.contains(6));
	}
}
