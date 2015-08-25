import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author cs61bl-ec and cs61bl-eb 
 *
 */
public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence is = new IntSequence(10);
		assertTrue (is.isEmpty() == true);
	}
	
	public void testAdd() {
		IntSequence is = new IntSequence(10);
		is.add(7);
		assertEquals(is.size(), 1);
		assertEquals(is.elementAt(0), 7);
	}
	
	public void testIsEmpty() {
		IntSequence is = new IntSequence(1);
		assertTrue (is.isEmpty() == true);
		is.add(7);
		assertEquals(is.isEmpty(), false);
	}

	public void testSize() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 10; ++i) {
			assertEquals(is.size(), i);
			is.add(i);
		}
		System.out.println(is);
	}
	
	public void testElementAt() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 10; ++i) {
			is.add(i);
			assertEquals(is.elementAt(i), i);
		}
		System.out.println(is);
	}
	
	public void testToString() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 10; ++i) {
			is.add(i);
		}
		assertEquals(is.toString(), "0 1 2 3 4 5 6 7 8 9");
	}
	
	public void testRemove() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 10; ++i) {
			is.add(i);
		}
		int victim = is.remove(2);
		assertEquals(victim, 2);
		assertEquals(is.size(), 9);
		for (int i = 0; i < 2; ++i) {
			assertEquals(is.elementAt(i), i);
		}
		for (int i = 2; i < 9; ++i) {
			assertEquals(is.elementAt(i), i + 1);
		}
	}
	
	public void testInsert() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 7; ++i) {
			is.add(i + 1);
		}
		is.insert(100, 4);
		for (int i = 0; i < 4; ++i) {
			assertEquals(is.elementAt(i), i + 1);
		}
		assertEquals(is.elementAt(4), 100);
		for (int i = 5; i < 8; ++i) {
			assertEquals(is.elementAt(i), i);
		}
	}
	
	public void testContains() {
		IntSequence is = new IntSequence(10);
		for (int i = 0; i < 7; ++i) {
			is.add(i);
		}
		System.out.println(is);
		for (int i = 0; i < 7; ++i) {
			assertEquals(is.contains(i), true);
		}
		assertEquals(is.contains(-1), false);
		assertEquals(is.contains(7), false);
	}

}
