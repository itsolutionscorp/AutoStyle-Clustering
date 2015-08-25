import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void testContruct() {
		ResizableIntSequence seq = new ResizableIntSequence(5);
		assertTrue (seq.myValues.length == 5);
	}
	
	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(5);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue (seq.myValues.length == 5);
		seq.add(6);
		assertTrue (seq.myValues.length == 10);
	}
	
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(4);
		seq.add(1);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue (seq.myValues.length == 4);
		seq.insert(2, 1);
		assertTrue (seq.myValues.length == 9);
		assertTrue (seq.myValues[0] == 1);
		assertTrue (seq.myValues[1] == 2);
		assertTrue (seq.myValues[2] == 3);
		assertTrue (seq.myValues[3] == 4);
		assertTrue (seq.myValues[4] == 5);
	}
	
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(10);
		seq.add(0);
		seq.add(1);
		seq.add(3);
		assertTrue (seq.myCount == 3);
		assertTrue (seq.myValues.length == 10);
		seq.remove(1);
		assertTrue (seq.elementAt(0) == 0);
		assertTrue (seq.elementAt(1) == 3);
		assertTrue (seq.myCount == 2);
		assertTrue (seq.myValues.length == 8);
	}
}
