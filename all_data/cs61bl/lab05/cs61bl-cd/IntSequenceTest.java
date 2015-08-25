import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	
	public void testRemove() {
		IntSequence seq = new IntSequence(7);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.remove(3);
		// int[] check1 = {1, 2, 3, 5, 6, 7}; 
		// assertEquals(check1, seq);
		assertEquals(seq.toString(), "1 2 3 5 6 7 ");
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(8);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.insert(0, 0);
		assertEquals(seq.toString(), "0 1 2 3 4 5 6 7 ");
		IntSequence seq1 = new IntSequence(8);
		seq1.add(1);
		seq1.add(2);
		seq1.add(3);
		seq1.add(4);
		seq1.add(5);
		seq1.add(6);
		seq1.add(7);
		seq1.insert(15, 6);
		assertEquals(seq1.toString(), "1 2 3 4 5 6 15 7 ");
		
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(8);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.contains(4);
		assertTrue(seq.contains(4));
		assertFalse(seq.contains(50));
	
	}
}
