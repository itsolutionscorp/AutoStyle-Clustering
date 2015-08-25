import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor(){
		IntSequence test1 = new IntSequence(100);
		assertTrue(test1.myValues.length == 100);
		assertTrue(test1.myCount == 0);
	}
	
	public void testIsEmpty(){
		IntSequence test1 = new IntSequence(100);
		assertTrue(test1.isEmpty());
		test1.add(1);
		assertFalse(test1.isEmpty());
	}
	
	public void testSize(){
		IntSequence test1 = new IntSequence(100);
		assertTrue(test1.size() == 0);
		test1.add(1);
		test1.add(2);
		assertTrue(test1.size() == 2);
	}
	
	public void testElementAt(){
		IntSequence test1 = new IntSequence(100);
		test1.add(1);
		test1.add(2);
		test1.add(3);
		assertTrue(test1.elementAt(0) == 1);
		assertTrue(test1.elementAt(1) == 2);
	}
	
	public void testAdd(){
		IntSequence test1 = new IntSequence(100);
		test1.add(1);
		test1.add(2);
		test1.add(3);
		assertTrue(test1.elementAt(2) == 3);
	}
	
	public void testToString(){
		IntSequence test1 = new IntSequence(100);
		test1.add(1);
		test1.add(2);
		assertEquals("1 2", test1.toString());
		test1.add(3);
		assertEquals("1 2 3", test1.toString());
	}
	
	public void testRemove(){
		IntSequence test1 = new IntSequence(100);
		for(int i = 0; i < 10; i++){
			test1.add(i);
		}
		test1.remove(1);
		assertEquals("0 2 3 4 5 6 7 8 9", test1.toString());
		assertTrue(test1.myCount == 9);
		assertTrue(test1.myValues.length == 100);
		test1.remove(8);
		assertEquals("0 2 3 4 5 6 7 8", test1.toString());
		test1.remove(0);
		assertEquals("2 3 4 5 6 7 8", test1.toString());
	}
	
	public void testInsert(){
		IntSequence test1 = new IntSequence(100);
		for(int i = 0; i < 10; i++){
			test1.add(i);
		}
		test1.insert(99, 4);
		assertEquals("0 1 2 3 99 4 5 6 7 8 9", test1.toString());
		assertTrue(test1.myCount == 11);
		test1.insert(999, 0);
		assertEquals("999 0 1 2 3 99 4 5 6 7 8 9", test1.toString());
		test1.insert(9999, 11);
		assertEquals("999 0 1 2 3 99 4 5 6 7 8 9999 9", test1.toString());
	}
	
	public void testContains(){
		IntSequence test1 = new IntSequence(100);
		for(int i = 0; i < 10; i++){
			test1.add(i);
		}
		assertTrue(test1.contains(5));
		assertFalse(test1.contains(10));
	}
}
