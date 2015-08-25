import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor(){
		IntSequence seq = new IntSequence(10);
		assertEquals (seq.toString(), ""); 
	}
	public void testIsEmpty(){
		IntSequence seq = new IntSequence(5);
		assertTrue (seq.isEmpty());
	}
	public void testAdd(){
		IntSequence seq = new IntSequence(4);
		seq.add(1);
		seq.add(3);
		assertEquals (seq.toString(), "1 3");
		seq.add(2);
		seq.add(5);
		assertEquals (seq.toString(), "1 3 2 5");		
	}
	public void testElementAt(){
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(5);
		seq.add(2);
		assertTrue (seq.elementAt(1) == 5);
		assertTrue (seq.elementAt(0) == 1);
	}
	
	public void testSize(){
		IntSequence seq = new IntSequence(7);
		seq.add(5); 
		seq.add(7);
		assertTrue(seq.size() == 2);
	}
	public void testRemove(){
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue(seq.remove(1) == 2);
		assertTrue(seq.size() == 2);
		assertTrue(seq.remove(1) == 3);
		assertTrue(seq.size() == 1);
	}
	public void testInsert(){
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.insert(7, 1);
		assertEquals(seq.toString(), "1 7 2 3");
		seq.insert(9, 4);
		assertEquals(seq.toString(), "1 7 2 3 9");
	}
	public void testContains(){
		IntSequence seq = new IntSequence(4);
		seq.add(4);
		seq.add(1);
		seq.add(0);
		assertTrue(seq.contains(4));
		assertTrue(seq.contains(0));
		assertTrue(!seq.contains(7));
		
	}
}
