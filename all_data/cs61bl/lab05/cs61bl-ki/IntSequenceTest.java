import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void remove() {
		IntSequence mySequence = new IntSequence(20);
		mySequence.add(1);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.add(5);
		mySequence.remove(1);
		assertFalse(mySequence.isEmpty());
		assertTrue(mySequence.elementAt(1)==4);
		IntSequence mySeq = new IntSequence(3);
		mySeq.add(0);
		mySeq.remove(0);
		assertTrue(mySeq.isEmpty());
		IntSequence mySeq2 = new IntSequence(10);
		mySeq2.add(3);
		mySeq2.add(4);
		mySeq2.add(6);
		mySeq2.remove(2);
		assertTrue(mySeq2.elementAt(1)==4);
		IntSequence mySeq3 = new IntSequence(4);
		mySeq3.add(1);
		mySeq3.add(2);
		mySeq3.add(3);
		mySeq3.add(4);
		mySeq3.remove(3);
		assertTrue(mySeq3.elementAt(2)==3);
		mySeq3.remove(1);
//		mySeq3.remove(99); //should do nothing
		assertTrue(mySeq3.elementAt(1)==3);
		mySeq3.remove(0);
		mySeq3.remove(0);
		assertTrue(mySeq3.isEmpty());
		mySeq3.remove(0); //should do nothing
		assertTrue(mySeq3.isEmpty());
	
	}
	
	@Test
	public void insert() {
		IntSequence myS = new IntSequence(1);
		myS.insert(2, 0);
		assertTrue(myS.isEmpty());
		IntSequence myS2= new IntSequence(4);
		myS2.add(1);
		myS2.add(2);
		myS2.add(3);
		myS2.insert(5, 0);
		assertTrue(myS2.elementAt(2)==2);
		assertTrue(myS2.elementAt(0)==5);
		myS2.insert(9,2); // NO MORE SPACE, should do nothing
		assertFalse(myS2.elementAt(2)==9);
	}
	
	@Test
	public void contains(){
		IntSequence myS = new IntSequence(10);
		myS.add(1);
		myS.add(2);
		myS.add(3);
		myS.add(4);
		myS.add(5);
		assertTrue(myS.contains(5));
		assertTrue(myS.contains(1));
		assertFalse(myS.contains(6));
	}
	
}
