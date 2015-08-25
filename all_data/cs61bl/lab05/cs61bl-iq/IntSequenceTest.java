import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor () {
		IntSequence sequence = new IntSequence(10);
		assertEquals (10, sequence.myValues.length);
		assertEquals (0, sequence.myCount);
	}
	
	public void testAdd () {
		IntSequence sequence = new IntSequence(10);
		sequence.add(2);
		assertEquals(1, sequence.myCount);
		sequence.add(5);
		sequence.add(0);
		assertEquals(3, sequence.myCount);
	}

	public void testToString () {
		IntSequence sequence = new IntSequence(10);
		sequence.add(3);
		sequence.add(2);
		sequence.add(5);
		sequence.add(6);
		assertEquals (sequence.toString(), "3 2 5 6");
	}
	
	public void testInsert() {
		IntSequence sequence = new IntSequence(10);
		sequence.add(2);
		sequence.add(3);
		sequence.add(5);
		sequence.add(6);
		sequence.insert(4, 2);
		assertEquals(2, sequence.elementAt(0));
		assertEquals(3, sequence.elementAt(1));
		assertEquals(4, sequence.elementAt(2));
		assertEquals(5, sequence.elementAt(3));
		assertEquals(6, sequence.elementAt(4));
		assertEquals(sequence.myCount, 5);
		sequence.insert(7, 5);
		assertEquals(7, sequence.elementAt(5));
	}
	
	public void testIsEmpty() {
		IntSequence sequence = new IntSequence(10);
		assertEquals(true, sequence.isEmpty());
		sequence.add(2);
		assertEquals(false, sequence.isEmpty());
	}
	
	public void testSize () {
		IntSequence sequence = new IntSequence(10);
		assertEquals(0, sequence.size());
		sequence.add(2);
		sequence.add(5);
		assertEquals(2, sequence.size());
	}
	
	public void testElementAt () {
		IntSequence sequence = new IntSequence(10);
		/* assertEquals(0, sequence.elementAt(0)) */
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		assertEquals(2, sequence.elementAt(0));
		assertEquals(4, sequence.elementAt(2));
	}
	
	public void testRemove() {
		IntSequence sequence = new IntSequence(10);
		assertTrue(sequence.remove(0) == 0);
		sequence.add(5);
		sequence.remove(0);
		assertTrue(sequence.remove(0) == 0);
		sequence.add(5);
		sequence.add(4);
		sequence.add(7);
		assertTrue(sequence.remove(5) == 0);
		assertTrue(sequence.remove(2) == 7);
		assertTrue(sequence.myCount == 2);
	}
	
	public void testContains() {
		IntSequence sequence = new IntSequence(10);
		assertFalse(sequence.contains(0));
		sequence.add(2);
		sequence.add(3);
		assertTrue(sequence.contains(2));
		assertTrue(sequence.contains(3));
		assertFalse(sequence.contains(0));
		sequence.add(0);
		assertTrue(sequence.contains(0));
		sequence.remove(0);
		assertFalse(sequence.contains(2));
		
	}
}
