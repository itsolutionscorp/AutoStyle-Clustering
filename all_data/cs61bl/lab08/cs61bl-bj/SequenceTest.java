import junit.framework.TestCase;

public class SequenceTest extends TestCase {
	public void testTwostring() {
		Sequence<Integer> i = new Sequence<Integer>(10);
		// i.myValues ={3, -7, 42, -11, 0, 6, 9, 0, 0, 0};
		i.add(3);
		i.add(-7);
		i.add(42);
		i.add(-11);
		i.add(0);
		i.add(6);
		i.add(9);
		assertEquals("3 -7 42 -11 0 6 9", i.toString());

		Sequence<String> j = new Sequence<String>(10);
		// i.myValues ={3, -7, 42, -11, 0, 6, 9, 0, 0, 0};
		j.add("3");
		j.add("-7");
		j.add("42");
		j.add("-11");
		j.add("0");
		j.add("6");
		j.add("9");

	}

	public void testRemove() {
		Sequence<Integer> i = new Sequence<Integer>(10);
		i.add(3);
		i.add(-7);
		i.add(42);
		i.add(-11);
		i.add(0);
		i.add(6);
		i.add(9);
		i.remove(2);

		assertEquals("3 -7 -11 0 6 9", i.toString());
	}

	public void testInsert() {
		Sequence<Integer> i = new Sequence<Integer>(4);

		i.add(3);
		i.add(-7);
		i.insert(0, 1);
		assertEquals("1 3 -7", i.toString());
	}

	public void testContains() {
		Sequence<Integer> i = new Sequence<Integer>(4);
		i.add(9);
		assertTrue(i.contains(9));
		assertFalse(i.contains(5));
	}
}
