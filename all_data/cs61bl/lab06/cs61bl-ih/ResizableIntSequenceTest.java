import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd() {
		ResizableIntSequence n = new ResizableIntSequence(3);
		n.add(1);
		n.add(2);
		assertEquals(n.elementAt(1), 2);
		n.add(3);
		n.add(4);
		assertEquals(n.size(), 4);
		assertEquals(n.elementAt(3), 4);
		assertEquals(n.elementAt(2), 3);
	}
	
	public void testRemove() {
		ResizableIntSequence m = new ResizableIntSequence(4);
		m.add(3);
		m.add(2);
		m.add(7);
		m.add(8);
		m.remove(1);
		assertEquals(m.size(), 3);
		assertEquals(m.elementAt(1), 7);
		m.add(5);
		m.add(1);
		assertEquals(m.size(), 5);
		m.remove(2);
		assertEquals(m.size(), 4);
		assertEquals(m.elementAt(2), 5);
		assertEquals(m.elementAt(1), 7);
		assertEquals(m.elementAt(3), 1);
	}

}
