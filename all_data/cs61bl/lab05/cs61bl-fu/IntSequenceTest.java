import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence mySeq = new IntSequence(10);
		assertTrue(mySeq.myCount == 0);
	}
	
	public void testAdd() {
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.elementAt(0) == 1);
		assertTrue(mySeq.elementAt(1) == 2);
		assertTrue(mySeq.myCount == 3);
	}
	
	public void testInsert() {
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		mySeq.add(4);
		mySeq.insert(9, 0);
		mySeq.insert(13, 2);
		mySeq.insert(33, 6);
		System.out.println(mySeq);
		IntSequence mySeq2 = new IntSequence(10);
		mySeq2.add(9);
		mySeq2.add(1);
		mySeq2.add(13);
		mySeq2.add(2);
		mySeq2.add(3);
		mySeq2.add(4);
		mySeq2.add(33);
		System.out.println(mySeq2);
		assertTrue(mySeq.equals(mySeq2));
	}
	
	public void testIsEmpty() {
		IntSequence mySeq = new IntSequence(10);
		assertTrue(mySeq.isEmpty());
		mySeq.add(3);
		assertFalse(mySeq.isEmpty());
	}
	
	public void testSize() {
		IntSequence mySeq = new IntSequence(10);
		assertTrue(mySeq.size() == 0);
		mySeq.add(1);
		assertTrue(mySeq.size() == 1);
		mySeq.add(2);
		assertTrue(mySeq.size() == 2);
		mySeq.add(3);
		assertTrue(mySeq.size() == 3);
		
		
	}
	
	public void testElementAt() {
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.elementAt(0) == 1);
		assertTrue(mySeq.elementAt(1) == 2);
		
	}
	
	public void testToString() {
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		// System.out.println(mySeq);
		assertEquals(mySeq.toString(), "1 2 3");
		
	}
	
	public void testRemove() {
		IntSequence mySeq1 = new IntSequence(10);
		mySeq1.add(1);
		mySeq1.add(2);
		mySeq1.add(3);
		IntSequence mySeq2 = new IntSequence(10);
		mySeq2.add(1);
		mySeq2.add(3);
		// System.out.println(mySeq1.remove(1));
		assertTrue(mySeq1.remove(1) == 2);
		//System.out.println(mySeq1);
		//System.out.println(mySeq2);
		assertTrue(mySeq1.equals(mySeq2));
		//Edge case -- removing stuff out of bounds.
		//assertTrue(mySeq1.remove(2) == mySeq2.remove(2));
		IntSequence mySeq3 = new IntSequence(10);
		mySeq3.add(1);
		mySeq3.add(2);
		mySeq1 = new IntSequence(10);
		mySeq1.add(1);
		mySeq1.add(2);
		mySeq1.add(3);
		mySeq1.remove(2);
		assertTrue(mySeq1.equals(mySeq3));
	}
	
	public void testRemove2() {
		IntSequence mySeq1 = new IntSequence(10);
		mySeq1.add(1);
		mySeq1.add(2);
		mySeq1.add(3);
		IntSequence mySeq2 = new IntSequence(10);
		mySeq2.add(1);
		mySeq2.add(2);
		IntSequence.remove(mySeq1, 2);
		//System.out.println(mySeq1);
		assertTrue(mySeq1.equals(mySeq2)); /*
		System.out.println(mySeq1);
		System.out.println(mySeq2);
		assertTrue(mySeq1.equals(mySeq2)); */
	}
	
	public void testContains() {
		IntSequence mySeq1 = new IntSequence(10);
		mySeq1.add(1);
		mySeq1.add(2);
		mySeq1.add(3);
		assertTrue(mySeq1.contains(1));
		assertFalse(mySeq1.contains(5));
	}
}
