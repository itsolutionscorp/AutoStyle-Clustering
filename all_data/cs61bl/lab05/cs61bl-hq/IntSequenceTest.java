import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstruct() {
		IntSequence seq = new IntSequence(5);
		assertTrue (seq.myValues.length == 5);
	}
	
	public void testAdd() {
		IntSequence seq = new IntSequence(5);
		seq.add(1);
		assertTrue (seq.myValues[0] == 1);
		assertTrue (seq.myCount == 1);
		seq.add(10);
		assertTrue (seq.myValues[1] == 10);
		assertTrue (seq.myCount == 2);
	}
	
	public void testInsert() {
		IntSequence seq = new IntSequence(7);
		seq.add(1);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue (seq.myCount == 4);
		seq.insert(2, 1);
		assertTrue (seq.myValues[0] == 1);
		assertTrue (seq.myValues[1] == 2);
		assertTrue (seq.myValues[2] == 3);
		assertTrue (seq.myValues[3] == 4);
		assertTrue (seq.myValues[4] == 5);
		assertTrue (seq.myCount == 5);
	}
	
	public void testIsEmpty() {
		IntSequence seq = new IntSequence(5);
		assertTrue (seq.isEmpty());
	}
	
	public void testSize() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		assertTrue (seq.size() == 4);
	}
	
	public void testElementAt() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertTrue (seq.elementAt(2) == 2);
	}
	
	public void testToString() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		assertEquals ("0 1 2 3 4", seq.toString());
	}
	
	public void testRemove() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(1);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue (seq.myCount == 5);
		seq.remove(1);
		assertTrue (seq.elementAt(0) == 0);
		assertTrue (seq.elementAt(1) == 3);
		assertTrue (seq.elementAt(2) == 4);
		assertTrue (seq.elementAt(3) == 5);
		assertTrue (seq.myCount == 4);
		seq.remove(2);
		assertTrue (seq.elementAt(0) == 0);
		assertTrue (seq.elementAt(1) == 3);
		assertTrue (seq.elementAt(2) == 5);
		assertTrue (seq.myCount == 3);
	}
	
	public void testContains() {
		IntSequence seq = new IntSequence(5);
		seq.add(0);
		seq.add(1);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue (seq.contains(3));
		assertFalse (seq.contains(2));
	}
}
