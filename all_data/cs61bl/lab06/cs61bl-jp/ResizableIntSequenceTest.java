import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	
	public void testConstructor() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		s.insert(0);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.insert(0);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		s.insert(0);
		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}
	
	public void testTwoInsertsAndRemoves() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		s.insert(0);
		s.insert(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertTrue (s.contains(1));

		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(1);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.contains(0));
		assertFalse (s.contains(1));
	}

}
