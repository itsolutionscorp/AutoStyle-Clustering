import junit.framework.TestCase;

public class SetTest extends TestCase {
	
	public void testConstructor() {
		Set s = new Set(2);
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));
	}
	
	public void testInsert() {
		Set s = new Set(2);
		s.insert(0);
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));

		s.insert(0);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));
	}
	
	public void testRemove() {
		Set s = new Set(2);
		s.insert(0);
		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));
	}
	
	public void testTwoInsertsAndRemoves() {
		Set s = new Set(2);
		s.insert(0);
		s.insert(1);
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertTrue (s.member(1));

		s.remove(1);
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));

		s.remove(1);                // should have no effect
		assertFalse (s.isEmpty());
		assertTrue (s.member(0));
		assertFalse (s.member(1));

		s.remove(0);
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));

		s.remove(0);                // should have no effect
		assertTrue (s.isEmpty());
		assertFalse (s.member(0));
		assertFalse (s.member(1));
	}

}
