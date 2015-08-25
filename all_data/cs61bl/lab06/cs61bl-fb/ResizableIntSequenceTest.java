
import junit.framework.TestCase;




public class ResizableIntSequenceTest extends TestCase{


	public void testAdd() {
		//base case;
		ResizableIntSequence IntS = new ResizableIntSequence(20);
		IntS.add(1);
		assertTrue (IntS.myValues[0] == 1);
		assertTrue (IntS.myCount == 1);
		IntS.add(2);
		assertTrue(IntS.myValues[1]==2);
		assertTrue(IntS.myCount == 2);	
		//tests if ResizableIntSequence's add method creates a integer array bigger than the capacity
		for (int i=0; i<=40;i++){
		IntS.add(3);
		}
		assertTrue(IntS.size() == 43);
	}
	public void testInsert() {
		//inserting when there is 0 element
		ResizableIntSequence Test = new ResizableIntSequence(6);
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
		//asserts that its capacity has been overcome
		Test.insert(2, 0);
		assertTrue(Test.size() ==7);
	}
	
	public void testRemove() {
		//test where there is only one element
		ResizableIntSequence Test = new ResizableIntSequence(6);
		Test.insert(1, 0);
		assertFalse (Test.isEmpty());
		Test.remove(0);
		assertTrue (Test.isEmpty());
		
		/*I set the limit of changing behavior to 5.
		 * if myValues' length is higher than 5, it reduces the size of myValues.
		 * else, it does not.
		 */
		//1. myValues.length>5
		for (int i = 0; i<=5; i++){
		Test.add(1);
		}
		
		assertTrue(Test.size() == 6);
		Test.remove(0);
		assertTrue(Test.myValues.length == 5);
		//2. myValues.length<=5 : would not change after removing!
		Test.remove(0);
		assertTrue(Test.myValues.length == 5);
	
	}
		
}

