import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testInit() {
		IntSequence test = new IntSequence(5);
		assertEquals (test.myCount, 0);
		assertFalse (test.contains(0));
		assertTrue (test.myValues.length == 5);
	}
	
	public void testIsEmpty() {
		IntSequence test = new IntSequence(6);
		assertTrue (test.isEmpty());
		test.add(6);
		assertFalse (test.isEmpty());
		test.insert(0, 0);
		test.remove(1);
		assertFalse (test.isEmpty());
		test.remove(0);
		assertTrue (test.isEmpty());
		test.add(0);
		assertFalse (test.isEmpty());
	}
	
	public void testSize() {
		IntSequence test = new IntSequence(6);
		assertEquals (test.size(), 0);
		test.add(6);
		test.add(8);
		test.insert(50, 1);
		assertEquals (test.size(), 3);
		test.remove(2);
		assertEquals (test.size(), 2);
		test.remove(0);
		test.remove(0);
		assertEquals (test.size(), 0);
	}
	
	public void testElementAt() {
		IntSequence test = new IntSequence(4);
		test.add(5);
		test.add(0);
		test.add(60);
		test.add(8);
		assertEquals (test.elementAt(0), 5);
		assertEquals (test.elementAt(3), 8);
		assertEquals (test.elementAt(1), 0);
		test.remove(2);
		assertEquals (test.elementAt(2), 8);
		// test.elementAt(3); Gave me 'index out of bounds' error, as expected
		test.insert(56, 0);
		assertEquals (test.elementAt(0), 56);
	}
	
	public void testAdd() {
		IntSequence test = new IntSequence(4);
		test.add(1);
		test.add(2);
		assertEquals ("1 2", test.toString());
		assertEquals (test.myCount, 2);
		test.add(5);
		test.add(6);
		assertEquals ("1 2 5 6", test.toString());
		assertEquals (test.myCount, 4);
		// test.add(8); Works how I want it to. Won't add; error message
	}
	
	public void testToString() {
		IntSequence test = new IntSequence(4);
		assertEquals ("", test.toString());
		test.add(1);
		test.add(2);
		assertEquals ("1 2", test.toString());
		test.insert(58, 1);
		test.add(97);
		test.remove(0);
		assertEquals ("58 2 97", test.toString());
	}
	
	public void testInsert() {
		IntSequence test = new IntSequence(7);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.insert(8, 2);
		assertTrue (test.myCount == 5);
		assertEquals ("1 2 8 3 4", test.toString());
		test.insert(5, 0);
		assertEquals ("5 1 2 8 3 4", test.toString());
		test.insert(90, 6);
		assertEquals ("5 1 2 8 3 4 90", test.toString());
		assertTrue (test.myCount == 7);
		// test.insert(5235, 7); Gave 'index out of bounds' error, as expected
	}
	
	public void testRemove() {
		IntSequence test = new IntSequence(5);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		assertTrue (test.remove(2) == 3);
		assertEquals("1 2 4", test.toString());
		test.add(6);
		test.add(8);
		
		IntSequence test2 = new IntSequence(4);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		assertTrue (test2.remove(0) == 1);
		assertEquals ("2 3", test2.toString());
		
		IntSequence test3 = new IntSequence(3);
		test3.add(8);
		test3.add(9);
		test3.add(10);
		assertTrue (test3.remove(0) == 8);
		assertEquals ("9 10", test3.toString());
		
		IntSequence test4 = new IntSequence(4);
		test4.add(18);
		test4.add(13);
		test4.add(15);
		assertTrue (test4.remove(2) == 15);
		assertEquals ("18 13", test4.toString());
		
		IntSequence test5 = new IntSequence(4);
		test5.add(18);
		test5.add(13);
		test5.add(15);
		test5.add(15);
		assertTrue (test5.remove(3) == 15);
		assertEquals ("18 13 15", test5.toString());
	}
	
	public void testContains() {
		IntSequence test = new IntSequence(5);
		assertFalse (test.contains(0));
		test.add(50);
		test.add(13);
		test.add(-291486);
		assertTrue (test.contains(50));
		assertTrue (test.contains(-291486));
		assertFalse (test.contains(0));
		assertFalse (test.contains(1));
		test.remove(0);
		assertFalse (test.contains(50));
		test.insert(0, 1);
		assertTrue (test.contains(0));
	}
	
	
}
