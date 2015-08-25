import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testResize() {
		ResizableIntSequence ris = new ResizableIntSequence(1);
		ris.add(1);
		ris.add(2);
		assertEquals(2, ris.size());
		ris.insert(3, 1);
		assertEquals(3, ris.size());
		assertEquals(1, ris.elementAt(0));
		assertEquals(3, ris.elementAt(1));
		assertEquals(2, ris.elementAt(2));
		assertEquals(4, ris.getCapacity());
		
		ris.remove(0);
		ris.remove(1);
		assertEquals(3, ris.getCapacity());
	}

}
