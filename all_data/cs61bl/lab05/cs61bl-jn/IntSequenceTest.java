import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testAdd(){
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		int[] one;
		one = new int[50];
		one[0] = 1;
		one[1] = 2;
		one[2] = 3;
		assertEquals(test.toString(), "1 2 3");
		assertEquals(test.myCount,3);
		test.add(5);
		assertTrue(test.myCount == 4);
	}
	
	public void testElementAdd(){
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		assertEquals(test.elementAt(0), 1);
		assertEquals(test.elementAt(1), 2);
		assertEquals(test.elementAt(2), 3);
		assertEquals(test.size(),3);
//		test.elementAt(3); Does successfully exit, and prints it out
		
		
	}
	public void testRemove(){
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		test.add(6);
		test.remove(0);
		assertEquals(test.toString(), "2 3 4 5 6");
		test.remove(2);
		assertEquals(test.toString(), "2 3 5 6");
		test.remove(3);
		assertEquals(test.toString(), "2 3 5");
		test.remove(0);
		test.remove(0);
		test.remove(0);
		assertTrue(test.isEmpty());
	}
	public void testInsert(){
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(0, 0);
		assertEquals(test.toString(),"0 1 2 3");
		test.insert(9, 2);
		assertEquals(test.toString(),"0 1 9 2 3");
		test.insert(5, 5);
		assertEquals(test.toString(),"0 1 9 2 3 5");
	}
	
	public void testContains() {
		IntSequence test=new IntSequence(0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.insert(0, 0);
		assertTrue(test.contains(1));
		assertFalse(test.contains(8));
	}
}
