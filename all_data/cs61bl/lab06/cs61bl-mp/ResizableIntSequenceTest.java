import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(2);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
		s.add(1);
		s.add(2);
		assertTrue (s.size() == 2);
		assertFalse (s.isEmpty());
		s.add(3);
		assertTrue (s.size() == 3);
		assertTrue (s.elementAt(2) == 3);
		assertTrue (s.elementAt(0) == 1);
		assertTrue (s.elementAt(1) == 2);
		s.add(0);
		assertTrue (s.size() == 4);
		assertTrue (s.elementAt(3) == 0);
	}
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(2);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
		s.add(1);
		s.add(2);
		assertTrue (s.size() == 2);
		assertFalse (s.isEmpty());
		s.insert(3, 1);
		assertTrue (s.elementAt(2) == 2);
		assertTrue (s.elementAt(1) == 3);
		assertTrue (s.elementAt(0) == 1);
		s.insert(4, 3); 
		assertTrue (s.elementAt(3) == 4);
		assertTrue (s.elementAt(2) == 2);
		assertTrue (s.elementAt(0) == 1);
		assertTrue (s.elementAt(1) == 3);
		s.insert(0, 0);
		assertTrue (s.elementAt(4) == 4);
		assertTrue (s.elementAt(3) == 2);
		assertTrue (s.elementAt(1) == 1);
		assertTrue (s.elementAt(2) == 3);
		assertTrue (s.elementAt(0) == 0);
		assertTrue (s.size() == 5);
	}
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(7);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.size() == 4);
		assertFalse (s.isEmpty());
		s.remove(3);
		assertTrue (s.size() == 3);
		assertTrue (s.myValues.length == 6);
		s.remove(2);
		s.remove(1);
		s.remove(0);
		assertTrue (s.size() == 0);
		assertTrue (s.myValues.length == 3);
	}
}

