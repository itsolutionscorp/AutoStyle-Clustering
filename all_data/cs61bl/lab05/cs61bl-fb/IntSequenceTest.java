import junit.framework.TestCase;



public class IntSequenceTest extends TestCase {

	public void testAdd() {
		IntSequence IntS = new IntSequence(6);
		IntS.add(1);
		assertTrue (IntS.myValues[0] == 1);
		assertTrue (IntS.myCount == 1);
		IntS.add(2);
		assertTrue(IntS.myValues[1]==2);
		assertTrue(IntS.myCount == 2);
		
	}
	
	public void testIsEmpty() {
		IntSequence emptyTest = new IntSequence(6);
		assertTrue (emptyTest.isEmpty());//test for isEmpty
		emptyTest.insert(1, 0);
		assertFalse(emptyTest.isEmpty());//test for insert
		emptyTest.remove(0);
		assertTrue (emptyTest.isEmpty());//test for remove
	}
	
	public void testInsert() {
		//inserting when there is 0 element
		IntSequence Test = new IntSequence(6);
		assertTrue (Test.isEmpty());//test for isEmpty
		Test.insert(1, 0);
		assertFalse(Test.isEmpty());//test for insert
		Test.insert(0, 0);
		//inserting when there already is an element
		String returnString = Test.toString();
		assertTrue(returnString.equals("0 1"));
		Test.add(3);
		Test.add(4);
		Test.add(5);		
		Test.insert(2, 0);
        assertTrue(Test.size() == 6);
        String returnString2 = Test.toString();
		assertTrue(returnString2.equals("2 0 1 3 4 5"));
	}
	
	public void testRemove() {
		//test where there is only one element
		IntSequence Test = new IntSequence(6);
		Test.insert(1, 0);
		assertFalse (Test.isEmpty());
		Test.remove(0);
		assertTrue (Test.isEmpty());
	}
	
	//finished testing
	public void testElementAt() {
		//basic operation works
		IntSequence Test = new IntSequence(6);
		Test.insert(1, 0);
		assertTrue (Test.elementAt(0) == 1);
		//Create 1 2 3 4 5 6; myCount == capacity
		Test.add(2);
		Test.add(3);
		Test.add(4);
		Test.add(5);
		Test.add(6);
		assertTrue (Test.elementAt(5) == 6);
		String returnString = Test.toString();
		assertTrue(returnString.equals("1 2 3 4 5 6"));
		
		
		
	}
	//finished testing;
	public void testToString() {
		//empty IntSequence where mycount == 0 / empty myValues initialized to be zero 
		IntSequence ToStringTest = new IntSequence(20);
		
		String return_ToStringTest = ToStringTest.toString();
		assertTrue(return_ToStringTest.equals(""));
		//normal case test
		IntSequence Test = new IntSequence(6);
		Test.add(1);
		Test.add(2);
		Test.add(3);
		String test = Test.toString();
		assertTrue(test.equals("1 2 3"));
		//test case where myCount is equal to capacity.
		Test.add(4);
		Test.add(5);
		Test.add(6);
		String test2 = Test.toString();
		assertTrue(test2.equals("1 2 3 4 5 6"));

		IntSequence Test2 = new IntSequence(6);
		Test2.insert(1, 0);
		Test2.insert(2, 0);
		Test2.insert(3, 0);
		Test2.insert(4, 0);
		Test2.insert(5, 0);
		Test2.insert(6, 0);
		String test3 = Test2.toString();
		assertTrue(test3.equals("6 5 4 3 2 1"));
		
	}
	
	//tested all the boundary cases
	public void testSize() {
		//normal case where it works.
		IntSequence Test = new IntSequence(20);
		Test.add(1);
		Test.add(2);
		Test.add(3);
		assertTrue(Test.size() == 3);
        //boundary case where size of the IntSequence is zero 
		IntSequence SizeTest = new IntSequence(7);
        assertTrue(SizeTest.size() == 0);
        //boundary case where size of the IntSequence is equal to capacity
        SizeTest.add(1);
        SizeTest.add(2);
        SizeTest.add(3);
        SizeTest.add(4);
        SizeTest.add(5);
        SizeTest.add(6);
        SizeTest.add(7);
        assertTrue(SizeTest.size()== 7);
	}
	//finished testing
	public void testContains() {
		//Make sure default 0 values are not counted
		IntSequence Test = new IntSequence(6);
		assertFalse(Test.contains(0));
		//normal case
		Test.add(1);
		Test.add(2);
		assertTrue(Test.contains(1));
		assertTrue(Test.contains(2));
		//boundary case where capacity is equal to myCount
		Test.add(3);
		Test.add(4);
		Test.add(5);
		Test.add(6);
		assertTrue(Test.contains(6));
	}
	
	}

