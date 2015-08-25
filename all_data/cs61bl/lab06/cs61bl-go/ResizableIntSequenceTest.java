import static org.junit.Assert.*;

import org.junit.Test;

public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence tester = new ResizableIntSequence(5);
		for (int i = 1; i < 6; i++) {
			tester.add(i);
		}
		assertEquals(1, tester.elementAt(0));
		assertEquals(5, tester.elementAt(4));
		assertEquals(5, tester.myValues.length);
		tester.add(9);
		assertEquals(9, tester.elementAt(5));
		assertEquals(10, tester.myValues.length);
	}

	@Test
	public void testInsert() {

		ResizableIntSequence tester = new ResizableIntSequence(5);
		assertEquals(0, tester.elementAt(0));
		for (int i = 1; i < 6; i++) {
			tester.add(i);
		}
		assertEquals(1, tester.elementAt(0));
		assertEquals(5, tester.elementAt(4));
		assertEquals(5, tester.myValues.length);
		tester.insert(2, 4);
		assertEquals(2, tester.elementAt(4));
		assertEquals(10, tester.myValues.length);
	}

	@Test
	public void testRemove() {
		ResizableIntSequence tester = new ResizableIntSequence(90);
		for (int i = 1; i < 6; i++) {
			tester.add(i);
		}
		assertEquals(5, tester.size());
		assertEquals(1, tester.elementAt(0));
		assertEquals(90, tester.myValues.length);
		tester.remove(2);
		assertEquals(45, tester.myValues.length);
		tester.remove(3);
		assertEquals(22, tester.myValues.length);

	}
}
