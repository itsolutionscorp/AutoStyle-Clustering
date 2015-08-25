import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	public void testToString() {
		IntSequence seq = new IntSequence(8);
		seq.add(1);
		assertEquals(seq.toString(), "1");
		seq.add(3);
		seq.add(2);
		assertEquals(seq.toString(), "1 3 2");
		seq.add(4);
		seq.add(5);
		assertEquals(seq.toString(), "1 3 2 4 5"); // same as testing add
	}

	public void testInsert() {
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.insert(7, 0);
		System.out.println(seq.toString());
		assertTrue(seq.toString().equals("7 1 2 3"));
		seq.insert(4, 2);
		assertTrue(seq.toString().equals("7 1 4 2 3"));
		
	}

	public void testSize() {
		IntSequence seq = new IntSequence(5);
		assertTrue(seq.size() == 0);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue(seq.size() == 3);
	}

	public void testElement() {
		IntSequence seq = new IntSequence(8);
		seq.add(1);
		seq.add(3);
		seq.add(2);
		seq.add(4);
		seq.add(5);
		assertTrue(seq.elementAt(4) == 5);
		assertTrue(seq.elementAt(3) == 4);
		assertTrue(seq.elementAt(2) == 2);
		assertTrue(seq.elementAt(1) == 3);
		assertTrue(seq.elementAt(0) == 1);
	}

	public void testIsEmpty() {
		IntSequence seq = new IntSequence(8);
		assertTrue(seq.isEmpty());
		seq.add(2);
		assertFalse(seq.isEmpty());
	}
	public void testRemove(){
		IntSequence seq = new IntSequence(8);
		seq.add(1);
		seq.add(3);
		seq.add(2);
		seq.add(4);
		seq.add(5);
		int n = seq.remove(3);
		assertTrue(n==4);
		//System.out.println(seq.toString());
		assertEquals(seq.toString(), "1 3 2 5");
		int n2 = seq.remove(0);
		assertTrue(n2 == 1 && seq.size() == 3);
		assertEquals(seq.toString(), "3 2 5");
	}
	public void testContains(){
		IntSequence seq = new IntSequence(7);
		seq.add(1);
		seq.add(3);
		seq.add(2);
		seq.add(4);
		assertTrue(seq.contains(3));
		assertFalse(seq.contains(90));
		assertTrue(seq.contains(1));
		assertTrue(seq.contains(4));

	
	}
}
