import junit.framework.TestCase;


public class SetTest extends TestCase {
	public void testIter() {
		Set s = new Set(5);
		s.insert(1);
		s.insert(2);
		s.insert(4);
		s.initIterator();
		assertTrue(s.hasNext());
		assertEquals(s.next(), 1);
		assertEquals(s.next(), 2);
		assertTrue(s.hasNext());
		assertEquals(s.next(), 4);
		assertTrue(!s.hasNext());
	}

}
