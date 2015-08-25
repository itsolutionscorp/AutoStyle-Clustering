import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	IntSequence sequence = new IntSequence(10);
	
	public void testToString(){
		sequence.myValues[0] = 2;
		sequence.myValues[1] = 1;
		sequence.myValues[2] = 0;
		sequence.myValues[3] = 9;
		sequence.myValues[4] = 6;
		sequence.myCount = 5;
		assertEquals("2 1 0 9 6", sequence.toString());
		
		sequence.myCount = 0;
		assertEquals("", sequence.toString());	
	}
	
	public void testIsEmpty(){
		sequence.myCount = 0;
		assertTrue(sequence.isEmpty());
		
		sequence.myValues[0] = 1;
		sequence.myValues[1] = 2;
		sequence.myValues[2] = 3;
		sequence.myCount = 3;
		assertFalse(sequence.isEmpty());
	}
	
	public void testSize(){
		sequence.myCount = 0;
		assertEquals(0, sequence.size());
		
		sequence.myValues[0] = 1;
		sequence.myValues[1] = 2;
		sequence.myValues[2] = 3;
		sequence.myCount = 3;
		assertEquals(3, sequence.size());
	}
	
	public void testElementAt(){
		sequence.myValues[0] = 2;
		sequence.myValues[1] = 1;
		sequence.myValues[2] = 0;
		sequence.myValues[3] = 9;
		sequence.myValues[4] = 6;
		sequence.myCount = 5;
		assertEquals(2, sequence.elementAt(0));
		assertEquals(6, sequence.elementAt(4));
		assertEquals(0, sequence.elementAt(5));
	}
	
	public void testAdd(){
		sequence.add(2);
		sequence.add(1);
		sequence.add(0);
		sequence.add(9);
		sequence.add(6);
		sequence.add(7);
		assertTrue(sequence.myCount == 6);
		assertTrue(sequence.myValues[5] == 7);
		
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		assertTrue(sequence.myCount == 10);
		assertTrue(sequence.myValues[9] == 4);	
	}
	
	public void testRemove(){
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		sequence.remove(2);
		assertTrue(sequence.myValues[2] == 4);
		assertTrue(sequence.myCount == 4);
		assertTrue(sequence.remove(0) == 1);
	}
	
	public void testInsert(){
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		sequence.insert(8, 2);
		assertTrue(sequence.myValues[2] == 8);
		assertTrue(sequence.myCount == 6);
		assertTrue(sequence.myValues[5] == 5);
		sequence.insert(8, -2);
		sequence.insert(8, 10);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		sequence.insert(8, 2);
		sequence.insert(5, 2);
	}
	
	public void testContains(){
		sequence.add(1);
		sequence.add(2);
		sequence.add(3);
		sequence.add(4);
		sequence.add(5);
		assertTrue(sequence.contains(1));
		assertFalse(sequence.contains(6));
	}
	
}
