import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd () {
		ResizableIntSequence sequence = new ResizableIntSequence(3);
		sequence.add(2);
		assertEquals(1, sequence.myCount);
		sequence.add(5);
		sequence.add(0);
		assertEquals(3, sequence.myCount);
		sequence.add(2);
		assertEquals(4, sequence.myCount);
	}
	
	public void testInsert() {
		ResizableIntSequence sequence = new ResizableIntSequence(7);
		sequence.add(2);
		sequence.add(3);
		sequence.add(5);
		sequence.add(6);
		sequence.insert(4, 2);
		assertEquals(2, sequence.elementAt(0));
		assertEquals(3, sequence.elementAt(1));
		assertEquals(4, sequence.elementAt(2));
		assertEquals(5, sequence.elementAt(3));
		assertEquals(6, sequence.elementAt(4));
		assertEquals(sequence.myCount, 5);
		sequence.insert(7, 5);
		assertEquals(7, sequence.elementAt(5));
		sequence.insert(3, 4);
		assertEquals(7, sequence.myCount);
		assertEquals(3, sequence.elementAt(4));
	}
	
	public void testRemove() {
		ResizableIntSequence sequence = new ResizableIntSequence(5);
		assertTrue(sequence.remove(0) == 0);
		sequence.add(5);
		sequence.remove(0);
		assertTrue(sequence.remove(0) == 0);
		sequence.add(5);
		sequence.add(4);
		sequence.add(7);
		assertTrue(sequence.remove(5) == 0);
		assertTrue(sequence.remove(2) == 7);
		assertTrue(sequence.myCount == 2);
		sequence.add(5);
		sequence.add(4);
		sequence.add(7);
		sequence.remove(3);
		assertTrue(sequence.myCount == sequence.myValues.length);
	}
	
	
}
