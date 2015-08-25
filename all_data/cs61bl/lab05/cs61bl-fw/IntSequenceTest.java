import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence seq = new IntSequence(10);
		assertTrue(seq.myValues.length == 10);
		assertTrue(seq.myCount == 0);
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(10);
		seq.add(1);
		assertEquals(1, seq.myCount);
		assertEquals(1, seq.myValues[0]);
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(10);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.insert(5, 1);
		assertEquals(5, seq.size());
		assertEquals("1 5 2 3 4", seq.toString());
	}
	
	public void testIsEmpty() {
		IntSequence seq = new IntSequence(10);
		assertTrue(seq.isEmpty() == true);
		seq.add(1);
		assertFalse(seq.isEmpty() == true);
	}
	
	public void testSize() {
		IntSequence seq = new IntSequence(10);
		assertTrue(seq.size() == 0);
		seq.add(1);
		seq.add(2);
		assertTrue(seq.size() == 2);
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(10);
		seq.add(1);
		seq.add(2);
		assertTrue(seq.elementAt(0) == 1);
		assertTrue(seq.elementAt(1) == 2);
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(10);
		assertEquals("", seq.toString());
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertEquals("1 2 3 4", seq.toString());
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(10);
		assertEquals("", seq.toString());
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertEquals(3, seq.remove(2));
		assertEquals("1 2 4", seq.toString());
		assertEquals(3, seq.myCount);
		assertEquals(1, seq.remove(0));
		assertEquals("2 4", seq.toString());
		assertEquals(2, seq.myCount);
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(10);
		assertEquals(false, seq.contains(2));
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertEquals(true, seq.contains(1));
		assertEquals(false, seq.contains(5));
	}
}
