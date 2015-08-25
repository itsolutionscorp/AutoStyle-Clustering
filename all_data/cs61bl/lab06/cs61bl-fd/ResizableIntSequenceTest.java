import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {
	
	@Test
	public void addHmm() {
		ResizableIntSequence one = new ResizableIntSequence(3);
		one.add(1);
		one.add(2);
		one.add(3);
		assertEquals(3, one.size());
		one.add(4);
		assertEquals(4, one.elementAt(3));
		assertEquals(4, one.size());
	}
	
	@Test
	public void ins() {
		ResizableIntSequence one = new ResizableIntSequence(3);
		one.add(1);
		one.add(3);
		one.add(4);
		assertEquals(3, one.size());
		one.insert(2, 1);
		assertEquals(4, one.size());
		assertEquals(1, one.elementAt(0));
		assertEquals(2, one.elementAt(1));
		assertEquals(3, one.elementAt(2));
		assertEquals(4, one.elementAt(3));
	}

	@Test
	public void rem() {
		ResizableIntSequence one = new ResizableIntSequence(8);
		one.add(1);
		one.add(2);
		one.add(3);
		one.add(4);
		one.add(5);
		assertEquals(8, one.myValues.length);
		one.remove(0);
		one.remove(0);
		assertEquals(4, one.myValues.length);
		assertEquals(3, one.elementAt(0));
	}
	
}
