import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testAdd(){
		IntSequence test = new IntSequence(5);
		test.add(5);
		assertTrue(test.myCount==1);
		test.add(6);
		test.add(7);
		assertTrue(test.myCount==3);
	}
	
	public void testIsEmpty(){
		IntSequence test = new IntSequence(5);
		assertTrue(test.isEmpty() == true);
		test.add(5);
		assertTrue(test.isEmpty() == false);
	}
	
	public void testSize(){
		IntSequence test = new IntSequence(5);
		assertTrue(test.size() == 0);
		test.add(5);
		assertTrue(test.size() == 1);
		test.add(17);
		assertTrue(test.size() == 2);
		test.add(43);
		test.add(9);
		test.add(10);
		assertTrue(test.size() == 5);
	}
	
	public void testElementAt(){
		IntSequence test = new IntSequence(5);
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		assertTrue(test.elementAt(0) == 17);
		assertTrue(test.elementAt(3) == 10);
//		assertEquals("Index not found",test.elementAt(4));
//		assertEquals("Index not found",test.elementAt(10));
	}
	
	public void testToString(){
		IntSequence test = new IntSequence(5);
		assertEquals("",test.toString());
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		assertEquals("17 43 9 10",test.toString());
		test.add(25);
		assertEquals("17 43 9 10 25",test.toString());
	}
	
	public void testRemove(){
		IntSequence test = new IntSequence(10);
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		test.add(25);
		test.add(67);
		test.add(99);
		assertTrue(test.remove(2)==9);
		assertTrue(test.size()==6);
		assertEquals("17 43 10 25 67 99",test.toString());
		assertTrue(test.remove(0)==17);
		assertTrue(test.size()==5);
		assertEquals("43 10 25 67 99",test.toString());
		assertTrue(test.remove(4)==99);
		assertTrue(test.size()==4);
		assertEquals("43 10 25 67",test.toString());
	}
	
	public void testInsert(){
		IntSequence test = new IntSequence(10);
		test.add(17);
		test.add(43);
		test.add(9);
		test.add(10);
		test.add(25);
		test.insert(64,2);
		assertTrue(test.size()==6);
		assertEquals("17 43 64 9 10 25",test.toString());
		test.insert(1,0);
		assertTrue(test.size()==7);
		assertEquals("1 17 43 64 9 10 25",test.toString());
		test.insert(11,7);
		assertTrue(test.size()==8);
		assertEquals("1 17 43 64 9 10 25 11",test.toString());
		test.insert(34,7);
		assertTrue(test.size()==9);
		assertEquals("1 17 43 64 9 10 25 34 11",test.toString());
	}
	
	public void testContains(){
		IntSequence test = new IntSequence(10);
		test.add(40);
		test.add(21);
		test.add(97);
		test.add(18);
		test.add(32);
		assertTrue(test.contains(40)==true);
		assertTrue(test.contains(32)==true);
		assertTrue(test.contains(97)==true);
		assertTrue(test.contains(100)==false);
		assertTrue(test.contains(0)==false);
	}
}
