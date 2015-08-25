import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		int capacity = 10;
		IntSequence test = new IntSequence(capacity);
		assertEquals(test.myValues.length, capacity);
		assertEquals(0, test.myCount);
	}

	public void testadd() {
		int tobeadd = 4;
		IntSequence test = new IntSequence(3);
		test.add(tobeadd);
		assertEquals(test.myCount, 1);
		assertEquals(test.myValues[test.myCount - 1], tobeadd);

		int tobeadd2 = 5;
		test.add(tobeadd2);
		assertEquals(test.myCount, 2);
		assertEquals(test.myValues[test.myCount - 1], tobeadd2);

		int tobeadd3 = 6;
		test.add(tobeadd3);
		assertEquals(test.myCount, 3);
		assertEquals(test.myValues[test.myCount - 1], tobeadd3);
	}

	public void testremove() {
		int toberemove = 2;
		IntSequence test = new IntSequence(10);
		test.add(3);
		test.add(-7);
		test.add(42);
		test.add(-11);
		test.add(0);
		test.add(6);
		test.add(9);
		test.remove(toberemove);
		assertEquals(test.myCount, 6);
		assertEquals("3 -7 -11 0 6 9", test.toString());

		test.remove(0);
		assertEquals(test.myCount, 5);
		assertEquals("-7 -11 0 6 9", test.toString());

	}

	public void testinsert() {
		int toinsert = 100;
		int pos = 4;
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.add(7);

		test.insert(toinsert, pos);
		assertEquals(test.myCount, 8);
		assertEquals("1 2 3 4 100 5 6 7", test.toString());

		test.insert(55, 3);
		assertEquals(test.myCount, 9);
		assertEquals("1 2 3 55 4 100 5 6 7", test.toString());
	}

	public void testisEmpty() {
		IntSequence test = new IntSequence(10);
		assertTrue(test.myCount == 0);
	}

	public void testsize() {
		IntSequence test = new IntSequence(10);
		assertEquals(test.myCount, test.size());
		test.add(1);
		test.add(1);
		test.add(1);
		assertEquals(test.myCount, test.size());
	}

	public void testelementAt() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		assertEquals(test.elementAt(2), 3);
		assertEquals(test.elementAt(0), 1);
	}

	public void testtoString() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		assertEquals("1", test.toString());
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		assertEquals("1 2 3 4 5", test.toString());
		test.add(6);
		assertEquals("1 2 3 4 5 6", test.toString());
	}
	
	public void testcontains() {
		IntSequence test = new IntSequence(10);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		assertFalse(test.contains(6));
		assertTrue(test.contains(4));
		assertTrue(test.contains(5));
		assertTrue(test.contains(1));
		
	}

}
