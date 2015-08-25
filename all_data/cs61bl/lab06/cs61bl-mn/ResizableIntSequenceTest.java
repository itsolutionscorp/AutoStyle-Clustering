import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence r1 = new ResizableIntSequence(4);
		assertTrue (r1.cap() == 4);
		r1.add(0);
		r1.add(1);
		assertTrue (r1.cap() == 4);
		r1.add(2);
		assertTrue (r1.cap() == 5);
		r1.add(3);
		assertTrue (r1.cap() == 6);
	}
	public void testInsert() {
		ResizableIntSequence r1 = new ResizableIntSequence(5);
		assertTrue (r1.cap() == 5);
		r1.add(0);
		r1.add(1);
		r1.add(2);
		r1.add(3);
		r1.insert(4, 2);
		assertTrue (r1.elementAt(2) == 4);
		assertTrue (r1.elementAt(3) == 2);
		assertTrue (r1.cap() == 7);
	}
	public void testRemove() {
		ResizableIntSequence r1 = new ResizableIntSequence(7);
		assertTrue (r1.cap() == 7);
		r1.add(0);
		r1.add(1);
		r1.add(2);
		r1.add(3);
		r1.add(4);
		r1.add(5);
		assertTrue (r1.cap() == 8);
		r1.remove(2);
		assertTrue (r1.cap() == 7);
		r1.remove(3);
		assertTrue (r1.cap() == 6);
	}
}
