import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAddAndInsert() {
		IntSequence s = new ResizableIntSequence(20);
		s.add(1);
		s.add(2);
		assertEquals(2,s.size());
		assertEquals("1 2", s.toString());
		s.insert(5, 0);
		s.insert(6, 0);
		assertEquals(4,s.size());
		assertEquals("6 5 1 2", s.toString());
	}
	
	public void testRemove() {
		IntSequence s = new ResizableIntSequence();
		s.add(1);
		s.add(2);
		s.add(3);
		s.remove(1);
		assertEquals(2,s.size());
		assertEquals("1 3", s.toString());
	}
}
