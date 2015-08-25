import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testIntSequence() {
		IntSequence a = new IntSequence(5);
		assertTrue(a.isEmpty());
	}

	public void testAdd() {
		IntSequence a = new IntSequence(5);
		a.add(3);
		a.add(2);
		a.add(1);
		a.add(5);
		assertEquals(a.size(), 4);
	}

	public void testInsert() {
		IntSequence a = new IntSequence(10);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.insert(10, 2);
		System.out.println(a);
		assertEquals(a.elementAt(0), 3);
		assertEquals(a.elementAt(1), 4);
		assertEquals(a.elementAt(2), 10);
		assertEquals(a.elementAt(3), 5);
		assertEquals(a.elementAt(4), 6);
		
	}

	public void testIsEmpty() {
		IntSequence a = new IntSequence(10);
		assertTrue(a.isEmpty());
		a.add(1);
		assertFalse(a.isEmpty());
	}

	public void testSize() {
		IntSequence a = new IntSequence(3);
		assertEquals(a.size(), 0);
		a.add(1);
		assertEquals(a.size(), 1);
		a.add(2);
		assertEquals(a.size(), 2);
		a.add(3);
		assertEquals(a.size(), 3);
	}
	
	public void testRemove() {
		IntSequence a = new IntSequence(10);
		a.add(3);
		a.add(-7);
		a.add(42);
		a.add(-11);
		a.add(0);
		a.add(6);
		a.add(9);
		a.remove(2);
		assertEquals(a.elementAt(1), -7);
		assertEquals(a.elementAt(2), -11);
		assertEquals(a.elementAt(3), 0);
		assertEquals(a.elementAt(4), 6);
	}

	public void testElementAt() {
		IntSequence a = new IntSequence(3);
		a.add(2);
		assertEquals(a.elementAt(0), 2);
		a.add(3);
		assertEquals(a.elementAt(1), 3);
		a.add(1);
		assertEquals(a.elementAt(2), 1);
	}

	public void testToString() {
		IntSequence a = new IntSequence(5);
		a.add(3);
		a.add(2);
		a.add(1);
		String test = a.toString();
		assertEquals(test, "3 2 1");
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(10);
		a.add(3);
		a.add(-7);
		a.add(42);
		a.add(-11);
		a.add(0);
		a.add(6);
		a.add(9);
		a.remove(2);
		assertFalse(a.contains(2));
		assertTrue(a.contains(-7));
		assertTrue(a.contains(-11));
		assertTrue(a.contains(0));
	}
}
