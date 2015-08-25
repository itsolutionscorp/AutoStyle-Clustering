import static org.junit.Assert.*;

import org.junit.Test;

public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence seq = new ResizableIntSequence(3);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		assertTrue(seq.toString().equals("1 2 3 4 5 6 7"));

	}
	@Test
	public void testInsert() {
		ResizableIntSequence seq1 = new ResizableIntSequence(4);
		seq1.add(1);
		seq1.add(2);
		seq1.add(3);
		seq1.add(4);
		seq1.insert(11, 0);
		assertTrue(seq1.toString().equals("11 1 2 3 4"));
		
		seq1.insert(22, 2);
		assertTrue(seq1.toString().equals("11 1 22 2 3 4"));
		
		seq1.insert(33, 4);
		assertTrue(seq1.toString().equals("11 1 22 2 33 3 4"));
		
		seq1.insert(44, 6);
		assertTrue(seq1.toString().equals("11 1 22 2 33 3 44 4"));
		
		seq1.insert(55, 8);
		assertTrue(seq1.toString().equals("11 1 22 2 33 3 44 4 55"));
		
		
	}
	
	@Test
	public void testRemove() {
		
		ResizableIntSequence seq = new ResizableIntSequence(24);
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		assertTrue(seq.myValues.length == 24);
		
		seq.remove(0);
		seq.remove(6);
		assertTrue(seq.toString().equals("2 3 4 5 6 7"));
		assertTrue(seq.myValues.length == 24);
		
		seq.remove(2);
		assertTrue(seq.toString().equals("2 3 5 6 7"));
		assertTrue(seq.myValues.length == 12);
		
		seq.remove(3);
		seq.remove(1);
		seq.remove(0);
		assertTrue(seq.toString().equals("5 7"));
		assertTrue(seq.myValues.length == 12);
		
		seq.remove(1);		
		assertTrue(seq.toString().equals("5"));
		assertTrue(seq.myValues.length == 6);
		
		
	}

}