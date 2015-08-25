import junit.framework.TestCase;

/**
 * 
 */

/**
 * @author cs61bl-ec and cs61bl-eb 
 *
 */
public class ResizableIntSequenceTest extends TestCase {

	public void testConstructor() {
		ResizableIntSequence is = new ResizableIntSequence(10);
		assertTrue (is.isEmpty() == true);
	}
	
	public void testAdd() {
		ResizableIntSequence is = new ResizableIntSequence(10);
		is.add(7);
		assertEquals(is.size(), 1);
		assertEquals(is.elementAt(0), 7);
	}
	
	public void testAdd2() { // grow
		ResizableIntSequence is = new ResizableIntSequence(10);
		for (int i = 0; i < 100; ++i) {
			is.add(7);
		}
		assertEquals(is.size(), 100);
		for (int i = 0; i < 100; ++i) {
			assertEquals(is.elementAt(i), 7);
		}
	}
	
	public void testInsert() {
		ResizableIntSequence is = new ResizableIntSequence(10);
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
	
	public void testInsert2() { // grow
		ResizableIntSequence is = new ResizableIntSequence(10);
		for (int i = 0; i < 100; ++i) {
			is.insert(i, i);
		}
		for (int i = 0; i < 100; ++i) {
			assertEquals(is.elementAt(i), i);
		}
	}
	
	public void testRemove() {
		ResizableIntSequence is = new ResizableIntSequence(10);
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


}
