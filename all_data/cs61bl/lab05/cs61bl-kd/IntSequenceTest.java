import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testRemove() {
		IntSequence mySeq = new IntSequence(10);
		mySeq.add(3);
		mySeq.add(-7);
		mySeq.add(42);
		mySeq.add(-11);
		mySeq.add(0);
		mySeq.add(6);
		mySeq.add(9);
		int test1 = mySeq.remove(2);
		assertTrue(test1 == 42);
		assertTrue(mySeq.toString().equals("3 -7 -11 0 6 9 "));
	}
	@Test
	public void testInsert() {
		IntSequence mySeq = new IntSequence(5);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(4);
		mySeq.add(5);
		mySeq.add(6);
		System.out.println(mySeq.toString());
		mySeq.insert(3, 2);
		System.out.println(mySeq.toString());
		assertTrue(mySeq.elementAt(2) == 3);
		assertTrue(mySeq.elementAt(3) == 4);
		assertTrue(mySeq.elementAt(4) == 5);
		assertTrue(mySeq.elementAt(5) == 6);
	}
	@Test
	public void testContains() {
		IntSequence mySeq = new IntSequence(3);
		mySeq.add(1);
		mySeq.add(2);
		mySeq.add(3);
		assertTrue(mySeq.contains(3) == true);
	}
}
