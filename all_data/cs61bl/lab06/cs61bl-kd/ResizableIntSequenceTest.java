import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		IntSequence seq1 = new ResizableIntSequence(4);
		seq1.add(1);
		seq1.add(3);
		seq1.add(5);
		seq1.add(7);
		seq1.add(9);
		assertTrue(seq1.elementAt(4) == 9);
		
		IntSequence seq2 = new ResizableIntSequence(3);
		seq2.add(0);
		seq2.add(2);
		seq2.add(4);
		seq2.add(6);
		seq2.add(8);
		assertTrue(seq2.elementAt(3) == 6);
		assertTrue(seq2.elementAt(4) == 8);
	}
	
	public void testInsert() {
		IntSequence seq3 = new ResizableIntSequence(5);
		seq3.add(0);
		seq3.add(2);
		seq3.add(3);
		seq3.add(4);
		seq3.add(5);
		seq3.insert(1, 1);
		assertTrue(seq3.elementAt(1) == 1);
		assertTrue(seq3.elementAt(5) == 5);
		
		IntSequence seq4 = new ResizableIntSequence(3);
		seq4.add(3);
		seq4.add(6);
		seq4.add(12);
		seq4.insert(9, 2);
		assertTrue(seq4.elementAt(2) == 9);
		assertTrue(seq4.elementAt(3) == 12);
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(7);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
		s.add(1);
		s.add(2);
		s.add(3);
		assertTrue (s.size() == 4);
		assertFalse (s.isEmpty());
		s.remove(3);
		assertTrue (s.size() == 3);

	}
}



