import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.add(1);
		a.add(2); //full now
		assertEquals("1 2", a.toString());
		a.add(3); //should extend
		assertEquals("1 2 3", a.toString());
		a.add(4);
		assertEquals("1 2 3 4", a.toString());
	}

	public void testInsert() {
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.insert(5, 4);
		assertEquals("1 2 3 4 5", a.toString());
		a.insert(0, 0);
		assertEquals("0 1 2 3 4 5", a.toString());
		a.insert(8, 2);
		assertEquals("0 1 8 2 3 4 5", a.toString());
	}
	
	public void testRemove() {
		ResizableIntSequence a = new ResizableIntSequence(2);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.insert(5, 4);
		a.insert(0, 0);
		a.insert(8, 2);
		a.remove(2);
		assertEquals("0 1 2 3 4 5", a.toString());
		assertEquals(6, a.myCapacity);
		a.remove(0);
		assertEquals("1 2 3 4 5", a.toString());
		assertEquals(5, a.myCapacity);
		a.remove(4);
		assertEquals("1 2 3 4", a.toString());
		assertEquals(4, a.myCapacity);
	}

}
