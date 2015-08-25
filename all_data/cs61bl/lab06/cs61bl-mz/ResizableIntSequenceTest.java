import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence seq;
		seq = new ResizableIntSequence(10);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		assertTrue (seq.cap() == 10);
		seq.add(1); //11th one. Should have resized.
		assertTrue (seq.cap() == 20);
		assertTrue (seq.myValues[10] == 1);
		assertTrue (seq.myValues[12] == 0);
	}
	
	@Test
	public void testInsert() {
		ResizableIntSequence seq;
		seq = new ResizableIntSequence(10);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		assertTrue (seq.cap() == 10);
		seq.insert(7, 0); //11th one. Should have resized.
		assertTrue (seq.cap() == 20);
		assertTrue (seq.myValues[10] == 1);
		assertTrue (seq.myValues[0] == 7);
		assertTrue (seq.myValues[12] == 0);
	}

	@Test
	public void testRemove() {
		ResizableIntSequence seq;
		seq = new ResizableIntSequence(10);
		seq.add(1);
		seq.add(3);
		seq.add(1);
		seq.add(1);
		seq.add(1);
		seq.remove(0);
		assertTrue(seq.cap() == 5);
		assertTrue(seq.myValues[4] == 0);
		assertTrue(seq.myValues[0] == 3);
	}
}
