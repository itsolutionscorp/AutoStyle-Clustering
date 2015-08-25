import junit.framework.TestCase;
	
public class ResizableIntSequenceTest extends TestCase {
	
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(0);
		s.add(1);
		s.add(2);
		s.add(3);
		assertEquals("0 1 2 3", s.toString());
		s.add(4);
		assertEquals("0 1 2 3 4", s.toString());
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.insert(2, 0);
		s.insert(3, 1);
		s.insert(1, 0);
		s.insert(0, 0);
		assertEquals("0 1 2 3", s.toString());
		s.insert(4, 4);
		assertEquals("0 1 2 3 4", s.toString());
		s.insert(9000, 3);
		assertEquals("0 1 2 9000 3 4", s.toString());
	}
	
	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		for (int i = 0; i < 5; i++) {
			s.add(i);
		}
		assertEquals("0 1 2 3 4", s.toString());
		s.remove(0);
		assertEquals("1 2 3 4", s.toString());
		assertTrue(s.myValues.length == 5);
		s.remove(3);
		assertEquals("1 2 3", s.toString());
		assertTrue(s.myValues.length == 4);
		s.add(4);
		assertEquals("1 2 3 4", s.toString());
		assertTrue(s.myValues.length == 4);
		s.remove(0);
		assertEquals("2 3 4", s.toString());
		assertTrue(s.myValues.length == 4);
	}
}
