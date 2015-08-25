import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testIntSequence() {
		IntSequence kevin = new IntSequence(10);
		// 3 -7 42 -11 0 6
		kevin.add(3);
		kevin.add(-7);
		kevin.add(42);
		kevin.add(-11);
		kevin.add(0);
		kevin.add(6);
		System.out.println(kevin.toString());
		assertEquals(kevin.toString(), "3 -7 42 -11 0 6");
	}
	
	@Test
	public void testElementAt() {
		IntSequence kevin = new IntSequence(10);
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
		assertEquals(kevin.elementAt(9), 0); // should remain 0
	}
	
	@Test
	public void testIsEmpty() {
		IntSequence kevin = new IntSequence(10);
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
		IntSequence kevin = new IntSequence(10);
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
		IntSequence kevin = new IntSequence(10);
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
		IntSequence kevin = new IntSequence(10);
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
		IntSequence kevin = new IntSequence(10);
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