import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(0);
		assertEquals(seq.capacity(), 0);
		seq.add(1);
		assertEquals(seq.capacity(), 2);
		seq.add(2);
		seq.add(3);
		assertEquals(seq.capacity(), 6);		
	}
	
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(5);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		assertEquals(seq.capacity(), 5);
		seq.remove(0);
		seq.remove(0);
		seq.remove(0);
		assertEquals(seq.capacity(), 5);
		seq.remove(0);
		assertEquals(seq.capacity(), 2);
	}
	
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(1);
		assertEquals(seq.capacity(), 1);
		seq.insert(0, 0);
		assertEquals(seq.capacity(), 1);
		seq.insert(1, 0);
		assertEquals(seq.capacity(), 4);		
	}
	
	
}
