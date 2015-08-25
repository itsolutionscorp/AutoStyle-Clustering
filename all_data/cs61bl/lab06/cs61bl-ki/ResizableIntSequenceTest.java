import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void add() {
		ResizableIntSequence rSeq = new ResizableIntSequence(3);
		rSeq.add(1);
		rSeq.add(2);
		rSeq.add(3);
		rSeq.add(4);
		rSeq.add(5);
		rSeq.add(6);
		rSeq.add(7);
		rSeq.add(8);
		System.out.println("add");
		System.out.println(rSeq);
		assertTrue(rSeq.size()==8);
		assertEquals(rSeq.toString(), "1 2 3 4 5 6 7 8");

	}
	
	@Test
	public void insert() {
		ResizableIntSequence rSeq = new ResizableIntSequence(3);
		rSeq.add(1);
		rSeq.add(2);
		rSeq.add(3);
		rSeq.insert(4,0);
		rSeq.insert(8,1);
		rSeq.insert(5,4);
		rSeq.insert(6,5);
		rSeq.insert(9,4);
		System.out.println("insert");
		System.out.println(rSeq);
		assertTrue(rSeq.size()==8);
		assertEquals(rSeq.toString(), "4 8 1 2 9 5 6 3");
	}
	
	@Test
	public void remove() {
		ResizableIntSequence rSeq = new ResizableIntSequence(20);
		for (int i=0; i<12; i++){
			rSeq.add(i);
		}
		rSeq.remove(1);
		rSeq.remove(1);
		rSeq.remove(1);
		rSeq.remove(1);
		rSeq.remove(1);
		System.out.println("remove");
		System.out.println(rSeq);
		assertTrue(rSeq.size()==7);
		assertEquals(rSeq.toString(), "0 6 7 8 9 10 11");
	}

}
