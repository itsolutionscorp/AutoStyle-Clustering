import junit.framework.TestCase;


public class ResizeableIntSequenceTest extends TestCase {

	public void testAdd() {
		ResizeableIntSequence a = new ResizeableIntSequence(6);
		a.add(3);
		a.add(2);
		a.add(1);
		a.add(2);
		a.add(5);
		a.add(2);
		assertEquals(a.capacity(), 9);
	}

	public void testInsert() {
		ResizeableIntSequence a = new ResizeableIntSequence(10);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.insert(10, 2);
		a.insert(8, 4);
		a.insert(3, 1);
		a.insert(4, 3);
		a.insert(3, 1);
		a.insert(4, 3);
		assertEquals(a.capacity(), 15);
	}

	public void testRemove() {
		ResizeableIntSequence a = new ResizeableIntSequence(20);
		a.add(3);
		a.add(-7);
		a.add(42);
		a.add(6);
		a.add(9);
		a.add(-11);
		a.add(0);
		a.add(6);
		a.add(9);
		a.remove(2);
		a.remove(1);
		a.remove(3);
		a.remove(0);
		a.remove(1);
		assertEquals(a.capacity(), 10);
	}

}
