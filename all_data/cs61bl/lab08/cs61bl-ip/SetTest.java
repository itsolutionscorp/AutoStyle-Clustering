import junit.framework.TestCase;

public class SetTest extends TestCase {
	
	public void testConstructor() {
		Set s = new Set(2);
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));
	}
	
	public void testIterator() {
		Set s = new Set(10);
		s.insert(0);
		s.insert(3);
		s.insert(5);
		s.insert(9);
		
		s.initIterator();
		assertEquals(s.next(), 0);
		assertTrue(s.hasNext());
		assertEquals(s.next(), 3);
		assertTrue(s.hasNext());
		assertEquals(s.next(), 5);
		assertTrue(s.hasNext());
		assertEquals(s.next(), 9);
		assertFalse(s.hasNext());
	}

}
