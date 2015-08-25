import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testAdd() {
		ResizableIntSequence R = new ResizableIntSequence(5);
		for (int _ = 0; _ < 5; _++) {
			R.add(_);
		}
		R.add(10);
		assertEquals(R.elementAt(5), 10);
	}
	
	@Test
	public void testInsert() {
		ResizableIntSequence R = new ResizableIntSequence(5);
		for (int _ = 0; _ < 6; _++) {
			R.add(_);
		}
		R.insert(10, 2);
		assertEquals(R.elementAt(2), 10);
		assertEquals(R.elementAt(6), 5);
	}
	
	@Test
	public void testIRemove() {
		ResizableIntSequence R = new ResizableIntSequence(12);
		for (int _ = 0; _ < 12; _++) {
			R.add(_);
		}
		assertEquals(12, R.myValues.length);
		for (int _ = 0; _ < 9; _++) {
			R.remove(0);
		}
		assertEquals(6, R.myValues.length);
	}

}
