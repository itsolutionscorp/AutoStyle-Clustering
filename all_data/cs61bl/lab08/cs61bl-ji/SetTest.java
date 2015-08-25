import static org.junit.Assert.*;

import org.junit.Test;


public class SetTest {

	@Test
	public void testIterator() {
		Set set = new Set(10);
		set.initIterator();
		assertTrue(set.k() == 0);
		assertTrue(set.hasNext() == false);
		set.insert(1);
		assertTrue(set.hasNext());
		assertTrue(set.next() == 1);
		set.insert(5);
		assertTrue(set.next() == 5);
		
		
	}

}
