import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor() {
		IntSequence seq = new IntSequence(2);
		assertTrue (seq.isEmpty());
		assertFalse (seq.contains(0));
		assertFalse (seq.contains(1));
		assertTrue (seq.size() == 0);
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(2);
		seq.add(0);
		assertFalse (seq.isEmpty());
		assertTrue (seq.contains(0));
		assertTrue (seq.size() == 1);
		assertTrue (seq.elementAt(0) == 0);
		seq.add(1);
		assertTrue (seq.contains(1));
		assertTrue (seq.size() == 2);
		assertTrue (seq.elementAt(1) == 1);
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(2);
		seq.add(0);
		seq.add(1);
		seq.remove(1);
		assertTrue (seq.contains(0));
		assertFalse (seq.contains(1));
		assertTrue (seq.size() == 1);
		assertTrue (seq.elementAt(0) == 0);
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(2);
		seq.insert(1, 1);
		assertTrue (seq.size() == 3);
		assertTrue (seq.contains(0));
		assertTrue (seq.contains(1));
		assertTrue (seq.contains(2));
		assertTrue (seq.elementAt(1) == 1);
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(5);
		assertTrue (seq.toString().equals(""));
		seq.add(0);
		assertTrue (seq.toString().equals("0"));
		seq.add(1);
		assertTrue (seq.toString().equals("0 1"));
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertTrue (seq.toString().equals("0 1 2 3 4"));
		seq.remove(4);
		assertTrue (seq.toString().equals("0 1 2 3"));
		seq.remove(2);
		assertTrue (seq.toString().equals("0 1 3"));
	}

}
