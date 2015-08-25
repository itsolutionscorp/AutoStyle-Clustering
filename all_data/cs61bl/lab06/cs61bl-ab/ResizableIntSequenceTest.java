import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence r = new ResizableIntSequence(5);
		r.add(9);
		r.add(8);
		r.add(7);
		assertEquals("9 8 7", r.toString());
		r.add(6);
		r.add(5);
		assertEquals("9 8 7 6 5", r.toString());
		r.add(4);
		assertEquals("9 8 7 6 5 4", r.toString());
	}
	
	@Test
	public void testInsert() {
		ResizableIntSequence r = new ResizableIntSequence(5);
		r.add(9);
		r.add(8);
		r.add(7);
		r.add(6);
		r.add(5);
		assertEquals("9 8 7 6 5", r.toString());
		r.insert(1, 3);
		assertEquals("9 8 7 1 6 5", r.toString());
	}

}
