import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence seq = new IntSequence(3);
		assertTrue(seq.isEmpty());
		assertTrue(seq.size() == 0);
		
		
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		assertTrue(seq.isEmpty() == false);
		assertTrue(seq.size() == 1);
		assertTrue(seq.elementAt(0) == 1);
		seq.add(2);
		seq.add(3);
		// seq.add(4);         Tested this to make sure we get the right error message
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue(seq.elementAt(0) == 1);
		assertTrue(seq.elementAt(1) == 2);
		assertTrue(seq.elementAt(2) == 3);
		// seq.elementAt(3);        Tested this to make sure we get the right error message
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);

		assertEquals(seq.toString(), "1");
		
		seq.add(2);
		assertEquals(seq.toString(), "1 2");
		seq.add(3);		
		assertEquals(seq.toString(), "1 2 3");
		
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		seq.remove(0);
		assertTrue(seq.isEmpty());
		
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.remove(1);
		assertEquals(seq.toString(), "1 3");
		
	}

	public void testInsert() {
		IntSequence seq = new IntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.insert(0, 0);
		assertEquals("0 1 2", seq.toString());
		IntSequence seq2 = new IntSequence(5);
		seq2.add(1);
		seq2.add(2);
		seq2.add(3);
		seq2.add(4);
		seq2.insert(5, 4);
		assertEquals("1 2 3 4 5", seq2.toString());
		IntSequence seq3 = new IntSequence(5);
		seq3.add(1);
		seq3.add(2);
		seq3.add(4);
		seq3.add(5);
		seq3.insert(3, 2);
		assertEquals("1 2 3 4 5", seq3.toString());
	}
		public void testContains() {
			IntSequence seq = new IntSequence(3);
			assertFalse(seq.contains(0));
			seq.add(1);
			seq.add(2);
			seq.add(4);
			assertTrue(seq.contains(2));
			assertTrue(seq.contains(1));
			assertFalse(seq.contains(3));
			
		}
	}
	