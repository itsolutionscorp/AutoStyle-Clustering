import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructorandIsEmpty() {
		IntSequence seq = new IntSequence(20);
		assertTrue(seq.isEmpty());
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(20);
		seq.add(3);
		seq.add(5);
		seq.add(7);
		seq.add(9);
		seq.add(11);
		assertTrue(seq.toString().equals("3 5 7 9 11"));
		assertTrue(seq.contains(3));
		assertTrue(seq.contains(7));
		assertTrue(seq.contains(11));
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(20);
		seq.add(4);
		assertFalse(seq.isEmpty());
		assertTrue(seq.elementAt(0)==4);
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(20);
		seq.add(3);
		seq.add(5);
		seq.add(7);
		seq.add(9);
		seq.add(11);
		assertTrue(seq.toString().equals("3 5 7 9 11"));
		seq.remove(2);
		assertTrue(seq.toString().equals("3 5 9 11"));
		seq.remove(3);
		assertTrue(seq.toString().equals("3 5 9"));
		seq.remove(0);
		assertTrue(seq.toString().equals("5 9"));
	}
	
	public void testRemoveZeroes() {
		IntSequence seq = new IntSequence(20);
		seq.add(0);
		seq.add(1);
		seq.add(0);
		seq.add(0);
		seq.add(0);
		assertTrue(seq.toString().equals("0 1 0 0 0"));
		seq.removezeroes();
		assertTrue(seq.toString().equals("1"));
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(20);
		seq.add(3);
		seq.add(5);
		seq.add(7);
		seq.add(9);
		seq.add(11);
		assertTrue(seq.toString().equals("3 5 7 9 11"));
		seq.insert(2,  0);
		assertTrue(seq.toString().equals("2 3 5 7 9 11"));
		seq.insert(4,  3);
		assertTrue(seq.toString().equals("2 3 5 4 7 9 11"));
		seq.insert(10,  7);
		assertTrue(seq.toString().equals("2 3 5 4 7 9 11 10"));
	}

	public void testSize() {
		IntSequence seq = new IntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		assertTrue(seq.size()==4);
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		assertTrue(seq.elementAt(0)==2);
		assertTrue(seq.elementAt(1)==4);
		assertTrue(seq.elementAt(2)==6);
		assertTrue(seq.elementAt(3)==8);
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(20);
		seq.add(2);
		seq.add(4);
		seq.add(6);
		seq.add(8);
		//assertTrue(seq.toString().equals("2 4 6 8"));
		assertEquals(seq.toString(), "2 4 6 8");	
	}
	
}
