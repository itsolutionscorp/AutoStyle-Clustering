import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testContructor() {
		IntSequence a = new IntSequence(0);
		assertTrue(a.myValues.length == 0);
		IntSequence b = new IntSequence(20);
		assertTrue(b.myValues.length == 20);
	}
	
	public void testIsEmpty() {
		IntSequence a = new IntSequence(0);
		assertTrue(a.isEmpty() == true);
		IntSequence b = new IntSequence(20);
		assertTrue(b.isEmpty() == true);
	}
	
	public void testSize() {
		IntSequence a = new IntSequence(0);
		assertTrue(a.size() == 0);
		IntSequence b = new IntSequence(20);
		assertTrue(b.size() == 0);
		for (int k = 0; k < 10; k++) {
			b.add(2*k);
		}
		assertTrue(b.size() == 10);
	}
	
	public void testElementAt() {
		IntSequence a = new IntSequence(20);
		// Indices outside the dimensions of a.myValues cannot be tested, because it 
		// will break the code.
		for (int k = 0; k < 10; k++) {
			a.add(2*k);
		}
		assertTrue(a.elementAt(0) == 0);
		assertTrue(a.elementAt(9) == 18);
	}
	
	public void testAdd() {
		IntSequence a = new IntSequence(10);
		for (int k = 0; k < 10; k++) {
			a.add(2*k);
		}
		
		assertEquals("0 2 4 6 8 10 12 14 16 18", a.toString());
		assertTrue(a.elementAt(0) == 0);
		assertTrue(a.elementAt(9) == 18);
	}
	
	public void testToString() {
		IntSequence a = new IntSequence(15);
		for (int k = 0; k < 15; k++) {
			a.add(k+1);
		}
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15", a.toString());
		
		IntSequence b = new IntSequence(0);
		assertTrue(b.toString() == null);
	}
	
	public void testRemove() {
		IntSequence a = new IntSequence(20);
		for (int k = 0; k < 10; k++) {
			a.add(k+1);
		}
		assertTrue(a.remove(0) == 1);
		assertEquals("2 3 4 5 6 7 8 9 10", a.toString());
		assertTrue(a.size() == 9);
		assertTrue(a.remove(8) == 10);
		assertEquals("2 3 4 5 6 7 8 9", a.toString());
		assertTrue(a.size() == 8);
	}

	public void testInsert() {
		IntSequence a = new IntSequence(20);
		for (int k = 0; k < 10; k++) {
			a.add(k+1);
		}
		a.insert(0,0);
		assertEquals("0 1 2 3 4 5 6 7 8 9 10", a.toString());
		assertEquals(11, a.size());
		a.insert(11,11);
		assertEquals("0 1 2 3 4 5 6 7 8 9 10 11", a.toString());
		assertEquals(12, a.size());
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(20);
		for (int k = 0; k < 10; k++) {
			a.add(k+1);
		}
		assertTrue(a.contains(9));
		assertTrue(a.contains(11) == false);
	}
}
