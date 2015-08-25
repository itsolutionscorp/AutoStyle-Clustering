package lab06;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence ReSeq = new ResizableIntSequence(3);
		ReSeq.add(0);
		ReSeq.add(1);
		ReSeq.add(2);
		ReSeq.add(3);
		assertTrue(ReSeq.myValues[0] == 0);
		assertTrue(ReSeq.myValues[1] == 1);
		assertTrue(ReSeq.myValues[2] == 2);
		assertTrue(ReSeq.myValues[3] == 3);
		ReSeq.add(4);
		ReSeq.add(5);
		assertTrue(ReSeq.myValues[4] == 4);
		assertTrue(ReSeq.myValues[5] == 5);
	}
	
	public void testInsert() {
		ResizableIntSequence ReSeq = new ResizableIntSequence(4);
		ReSeq.add(0);
		ReSeq.add(1);
		ReSeq.add(2);
		ReSeq.add(3);
		ReSeq.insert(2,0);
		assertTrue(ReSeq.myValues[0] == 2);
		assertTrue(ReSeq.myValues[1] == 0);
		assertTrue(ReSeq.myValues[2] == 1);
		assertTrue(ReSeq.myValues[3] == 2);
		assertTrue(ReSeq.myValues[4] == 3);
		ReSeq.insert(4,5);
		assertTrue(ReSeq.myValues[4] == 3);
		assertTrue(ReSeq.myValues[5] == 4);
		ReSeq.insert(3,2);
		assertTrue(ReSeq.myValues[0] == 2);
		assertTrue(ReSeq.myValues[1] == 0);
		assertTrue(ReSeq.myValues[2] == 3);
		assertTrue(ReSeq.myValues[3] == 1);
		assertTrue(ReSeq.myValues[4] == 2);
		assertTrue(ReSeq.myValues[5] == 3);
		assertTrue(ReSeq.myValues[6] == 4);
	}
	
	public void testRemove() {
		ResizableIntSequence ReSeq = new ResizableIntSequence(6);
		ReSeq.add(0);
		ReSeq.add(1);
		ReSeq.add(2);
		ReSeq.add(3);
		ReSeq.add(4);
		ReSeq.add(5);
		ReSeq.remove(1);
		assertTrue(ReSeq.myValues[0] == 0);
		assertTrue(ReSeq.myValues[1] == 2);
		assertTrue(ReSeq.myValues[2] == 3);
		ReSeq.remove(4);
		assertTrue(ReSeq.myValues[2] == 3);
		assertTrue(ReSeq.myValues[3] == 4);
		ReSeq.remove(0);
		assertTrue(ReSeq.myValues[0] == 2);
		assertTrue(ReSeq.myValues[1] == 3);
		assertTrue(ReSeq.myValues[2] == 4);
	}

}
