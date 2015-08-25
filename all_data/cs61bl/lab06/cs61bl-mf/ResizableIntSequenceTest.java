import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(4);
		seq.add(1);
		assertTrue(seq.isEmpty() == false);
		assertTrue(seq.size() == 1);
		assertTrue(seq.elementAt(0) == 1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		assertTrue(seq.myValues.length == 5);
		ResizableIntSequence seq2 = new ResizableIntSequence(40);
		for (int i = 0; i < 40; i++) {
			seq2.add(i);
		}
		assertEquals(40, seq2.myValues.length);
		seq2.add(41);
		assertEquals(50, seq2.myValues.length);
	}
	
	public void testInsert() {
		ResizableIntSequence seq = new ResizableIntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.insert(0, 0);
		assertEquals("0 1 2", seq.toString());
		ResizableIntSequence seq2 = new ResizableIntSequence(5);
		seq2.add(1);
		seq2.add(2);
		seq2.add(3);
		seq2.add(4);
		seq2.insert(5, 4);
		assertEquals("1 2 3 4 5", seq2.toString());
		ResizableIntSequence seq3 = new ResizableIntSequence(5);
		seq3.add(1);
		seq3.add(2);
		seq3.add(4);
		seq3.add(5);
		seq3.insert(3, 2);
		assertEquals("1 2 3 4 5", seq3.toString());
		seq3.insert(6, 5);
		assertEquals("1 2 3 4 5 6", seq3.toString());
		seq3.insert(0, 0);
		assertEquals("0 1 2 3 4 5 6", seq3.toString());
	}
	
	public void testRemove() {
		ResizableIntSequence seq = new ResizableIntSequence(3);
		seq.add(1);
		seq.remove(0);
		assertTrue(seq.isEmpty());
		assertTrue(seq.myValues.length == 2);
		
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.remove(1);
		assertEquals(seq.toString(), "1 3");
		
		ResizableIntSequence seq2 = new ResizableIntSequence(4);
		for (int i = 0; i < 4; i++) {
			seq2.add(i);
		}
		assertEquals(4, seq2.myValues.length);
		seq2.remove(0);
		assertEquals(4, seq2.myValues.length);
		seq2.remove(0);
		assertEquals(4, seq2.myValues.length);
		seq2.remove(0);
		assertEquals(3, seq2.myValues.length);
		
		
	}
}
