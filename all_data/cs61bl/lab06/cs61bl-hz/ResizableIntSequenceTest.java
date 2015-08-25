import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	
	public void testConstructor() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		assertTrue (s.isEmpty());
	}
	
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(1);
		s.add(5);
		s.add(4);
		assertTrue(s.toString().equals("5 4"));
		s.add(3);
		assertTrue(s.toString().equals("5 4 3"));
		s.add(4);
		assertTrue(s.toString().equals("5 4 3 4"));
		s.add(4);
		assertTrue(s.toString().equals("5 4 3 4 4"));
		s.add(4);
		assertTrue(s.toString().equals("5 4 3 4 4 4"));
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(5);
		s.add(4);
		s.add(3);
		assertTrue(s.toString().equals("5 4 3"));
		s.insert(0, 0);
		assertTrue(s.toString().equals("0 5 4 3"));
		s.insert(2, 2);
		assertTrue(s.toString().equals("0 5 2 4 3"));
		s.insert(4, 7);
		assertTrue(s.toString().equals("0 5 2 4 7 3"));
	}
	
}
