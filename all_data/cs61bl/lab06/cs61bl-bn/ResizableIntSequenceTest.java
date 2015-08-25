import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence i = new ResizableIntSequence(1);
		i.add(1);
		i.add(2);
		i.add(3);
		i.add(4);
		assertEquals("1 2 3 4 ", i.toString(i.getNewValues()));
		System.out.println(i.toString(i.getNewValues()));

		i.add(5);
		i.add(6);
		i.add(7);
		i.add(8);
		i.add(9);
		i.add(10);
		i.add(11);
		i.add(12);
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 ",
				i.toString(i.getNewValues()));
		System.out.println(i.toString(i.getNewValues()));

	}

	public void testInsert() {
		ResizableIntSequence i = new ResizableIntSequence(1);
		i.add(1);
		i.add(2);
		i.add(3);
		i.add(4);
		i.insert(i.myValues, 4, 5);
		assertEquals("1 2 3 4 5 ", i.toString(i.getNewValues()));
		System.out.println(i.toString(i.getNewValues()));
	}

	public void testRemove() {
		ResizableIntSequence i = new ResizableIntSequence(1);
		i.add(1);
		i.add(2);
		i.add(3);
		i.add(4);
		i.remove(i.myValues);
		assertEquals("1 2 ", i.toString(i.getNewValues()));
		System.out.println(i.toString(i.getNewValues()));
	}
}
