import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence test = new IntSequence (6);
		assertTrue (test.myCount == 0);
		assertTrue (test.myValues[0] == 0);
		assertTrue (test.myValues[1] == 0);
		assertTrue (test.myValues[5] == 0);
	}
	
	public void testAdd(){
		IntSequence test = new IntSequence (4);
		assertTrue (test.myCount == 0);
		test.add(3);
		test.add(-4);
		test.add(7);
		assertTrue (test.myValues[0] == 3);
		assertTrue (test.myValues[1] == -4);
		assertTrue (test.myValues[2] == 7);
		assertTrue (test.myValues[3] == 0);
	}
	
	public void testInsert(){
		IntSequence test = new IntSequence (10);
		assertTrue (test.myCount == 0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		assertTrue (test.myCount == 5);
		test.insert(7,2);
		assertTrue (test.myValues[0] == 1);
		assertTrue (test.myValues[1] == 2);
		assertTrue (test.myValues[2] == 7);
		assertTrue (test.myValues[3] == 3);
		assertTrue (test.myValues[4] == 4);
		assertTrue (test.myValues[5] == 5);
		assertTrue (test.myCount == 6);
	}
	
	public void testRemove(){
		IntSequence test = new IntSequence (10);
		assertTrue (test.myCount == 0);
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		assertTrue (test.myCount == 5);
		test.remove(2);
		test.remove(1);
		assertTrue (test.myValues[0] == 1);
		assertTrue (test.myValues[1] == 4);
		assertTrue (test.myValues[2] == 5);
		assertTrue (test.myValues[3] == 0);
		assertTrue (test.myValues[9] == 0);
		assertTrue (test.myCount == 3);
	}
	
	public void testIsEmpty(){
		IntSequence test = new IntSequence (10);
		assertTrue(test.isEmpty());
		
		IntSequence test2 = new IntSequence (10);
		test2.add(5);
		assertTrue(!(test2.isEmpty()));
	}
	
	public void testSize(){
		IntSequence test = new IntSequence (10);
		assertTrue(test.size() == 0);
		
		IntSequence test2 = new IntSequence (10);
		test2.add(5);
		test2.add(7);
		test2.remove(0);
		assertTrue(test2.size() == 1);
	}
	
	public void testElementAt(){
		IntSequence test = new IntSequence (10);
		test.add(5);
		test.add(7);
		test.add(9);
		test.remove(1);
		assertTrue(test.elementAt(1) == 9);
	}

	public void testToString(){
		IntSequence test = new IntSequence (5);
		test.add(1);
		test.add(2);
		test.add(3);
		assertTrue (test.toString().equals("1 2 3"));
	}
	
	public void testContains(){
		IntSequence test = new IntSequence (5);
		test.add(1);
		test.add(5);
		test.add(3);
		test.add(8);
		assertTrue (test.contains(3) == true);
		assertTrue (test.contains(8) == true);
		assertTrue (test.contains(0) == false);
	}
	
}
