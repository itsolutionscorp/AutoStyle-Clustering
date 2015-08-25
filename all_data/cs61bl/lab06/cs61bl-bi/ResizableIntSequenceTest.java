import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence ris = new ResizableIntSequence(5);
		ris.add(1);
		ris.add(2);
		ris.add(3);
		ris.add(4);
		ris.add(5);
		// here it should overload
		ris.add(6);
		assertTrue(ris.size() == 6);
		assertEquals(ris.toString(), "1 2 3 4 5 6");
	}
	
	public void testInsert() {
		ResizableIntSequence ris = new ResizableIntSequence(5);
		ris.add(1);
		ris.add(2);
		ris.add(3);
		ris.add(4);
		ris.add(5);
		// here it should overload
		ris.insert(10, 0);
		ris.insert(20, 3);
		assertTrue(ris.size() == 7);
		assertEquals(ris.toString(), "10 1 2 20 3 4 5");
	}
	
	public void testRemove() {
		ResizableIntSequence ris = new ResizableIntSequence(50);
		ris.add(1);
		ris.add(2);
		ris.add(3);
		ris.add(4);
		ris.add(5);
		int element = ris.remove();
//		assertTrue(ris.size() == 4);
//		assertTrue(element == 5);
	}
}
