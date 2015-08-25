import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testResizableIntSequence() {
		ResizableIntSequence kevin = new ResizableIntSequence(5);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		kevin.add(-9);
		kevin.add(-10);
		kevin.add(-11);
		System.out.println(kevin.toString());
		assertEquals(kevin.toString(), "3 -7 42 -11 0 6 -9 -10 -11");
	}
	
	@Test
	public void testElementAt() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		assertEquals(kevin.elementAt(0), 3);
		assertEquals(kevin.elementAt(2), 42);
		assertEquals(kevin.elementAt(5), 6);
	}
	
	@Test
	public void testIsEmpty() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		assertTrue(kevin.isEmpty());
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		assertFalse(kevin.isEmpty());
	}
	
	@Test
	public void testSize() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		assertEquals(kevin.size(),6);
		assertFalse(kevin.size() == 264214);
	}
	
	@Test
	public void testRemove() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		kevin.remove(0);
		System.out.println(kevin.toString());
		assertEquals(kevin.toString(), "-7 42 -11 0 6");
	}
	
	@Test
	public void testInsert() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		kevin.remove(0);
		System.out.println("remove : " + kevin.toString());
		assertEquals(kevin.toString(), "-7 42 -11 0 6");
		kevin.insert(3, 0);
		System.out.println("insert : " + kevin.toString());
		assertEquals(kevin.toString(), "3 -7 42 -11 0 6");
	}
	
	
	public void testContains() {
		ResizableIntSequence kevin = new ResizableIntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		kevin.remove(0);
		System.out.println(kevin.toString());
		assertTrue(kevin.contains(3));
		assertFalse(kevin.contains(69));
	}

	
}