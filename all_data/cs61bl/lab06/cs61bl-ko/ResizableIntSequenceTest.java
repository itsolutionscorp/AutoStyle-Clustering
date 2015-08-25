import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testadd() {
		ResizableIntSequence a = new ResizableIntSequence(4);
		a.add(3);
		a.add(33);
		a.add(17);
		a.add(24);
		assertTrue(a.myCount == 4);
		a.add(50);
		assertTrue(a.myCount == 5);
		assertTrue(a.myValues.length == 6);
	}
		
	@Test
	public void testinsert() {
		ResizableIntSequence a = new ResizableIntSequence(4);
		a.add(3);
		a.add(33);
		a.add(17);
		a.add(24);
		assertTrue(a.myCount == 4);
		a.insert(50, 2);
		assertTrue(a.myCount == 5);
		assertTrue(a.myValues.length == 6);
	}

	@Test
	public void testremove() {
		ResizableIntSequence a = new ResizableIntSequence(8);
		a.add(3);
		a.add(33);
		a.add(17);
		a.add(24);
		a.add(7);
		a.add(9);
		assertTrue(a.myCount == 6);
		a.remove(2);
		a.remove(3);
		a.remove(0);
		assertTrue(a.myCount == 3);
		assertTrue(a.myValues.length == 6);
		a.remove(0);
		assertTrue(a.myCount == 2);
		assertTrue(a.myValues.length == 5);
	}
}
