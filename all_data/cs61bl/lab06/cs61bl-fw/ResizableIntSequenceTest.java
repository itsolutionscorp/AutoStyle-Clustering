import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(1);
		seq.add(1);
		assertEquals(1, seq.myCount);
		assertEquals(1, seq.myValues.length);
		seq.add(2);
		assertEquals(2, seq.myCount);
		assertEquals(2, seq.myValues.length);
		seq.add(3);
		assertEquals(3, seq.myCount);
		assertEquals(4, seq.myValues.length);
		assertEquals("1 2 3", seq.toString());
	}
	
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(1);
		seq.add(1);
		assertEquals(1, seq.myCount);
		seq.insert(5, 0);
		assertEquals(2, seq.myCount);
		assertEquals(2, seq.myValues.length);
		seq.insert(4, 1);
		assertEquals(3, seq.myCount);
		assertEquals(4, seq.myValues.length);
		seq.insert(5, 1);
		assertEquals(4, seq.myCount);
		assertEquals(4, seq.myValues.length);
		seq.insert(6, 3);
		assertEquals(5, seq.myCount);
		assertEquals(8, seq.myValues.length);
		assertEquals("5 5 4 6 1", seq.toString());
	}
	
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(3);
		assertEquals("", seq.toString());
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		
		assertEquals(3, seq.remove(2));
		assertEquals("1 2 4 1 2 3 4 1 2 3 4", seq.toString());
		assertEquals(11, seq.myCount);
		assertEquals(12, seq.myValues.length);
		
		assertEquals(1, seq.remove(0));
		assertEquals(2, seq.remove(0));
		assertEquals(4, seq.remove(0));
		assertEquals(1, seq.remove(0));
		assertEquals(2, seq.remove(0));
		assertEquals(3, seq.remove(0));
		
		assertEquals("4 1 2 3 4", seq.toString());
		assertEquals(5, seq.myCount);
		assertEquals(4, seq.remove(0));
		assertEquals(4, seq.myCount);
		assertEquals(10, seq.myValues.length);
	}

}
