import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence ris = new ResizableIntSequence(50);
		for(int i = 0; i<49; i++) {
			ris.add(i);
		}
		ris.add(49);
		assertEquals(50, ris.size());
		ris.add(50);
		assertEquals(49, ris.elementAt(49));
		for(int i = 51; i < 100; i++) {
			ris.add(i);
		}
		assertEquals(99, ris.size());
	}
	public void testInsert() {
		ResizableIntSequence ris = new ResizableIntSequence(50);
		for(int i = 0; i<50; i++) {
			ris.add(i);
		}
		ris.insert(50, 49);
		assertEquals(50, ris.elementAt(49));
		assertEquals(51, ris.size());
	}
	public void testRemove() {
		ResizableIntSequence ris = new ResizableIntSequence(50);
		for(int i = 0; i<26; i++) {
			ris.add(i);
		}
		ris.remove(3);
		assertEquals(25, ris.capacity);
	}

}
