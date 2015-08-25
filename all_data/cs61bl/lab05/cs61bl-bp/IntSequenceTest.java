import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence seq = new IntSequence(20);
		assertEquals(seq.toString(),"");
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertEquals(seq.toString(),"3 4 5");
//		seq.add(6);
//		assertEquals(seq.toString(),"3 4 5");
		
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(6);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.insert(6, 2);
		assertEquals(seq.toString(),"3 4 6 5");
		seq.insert(7, 0);
		assertEquals(seq.toString(),"7 3 4 6 5");
		seq.insert(8, 5);
		assertEquals(seq.toString(),"7 3 4 6 5 8");
	}
	
	public void testSize() {
		IntSequence seq = new IntSequence(3);
		assertEquals(seq.size(), 0);
		seq.add(93);
		assertEquals(seq.size(), 1);
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertEquals(seq.elementAt(0), 3);
		assertEquals(seq.elementAt(1), 4);
		assertEquals(seq.elementAt(2), 5);
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.remove(1);
		assertEquals(seq.toString(), "3 5");
		seq.remove(0);
		assertEquals(seq.toString(),"5");
		seq.add(6);
		seq.remove(1);
		assertEquals(seq.toString(),"5");
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertEquals(seq.contains(3),true);
		assertEquals(seq.contains(6),false);
	}
}
