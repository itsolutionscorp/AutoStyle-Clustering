import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testAdd(){
		ResizableIntSequence fdsa = new ResizableIntSequence(1);
		fdsa.add(1);
		fdsa.add(2);
		fdsa.add(3);
		fdsa.add(4);
		fdsa.add(5);
		fdsa.add(6);
		fdsa.add(7);
		fdsa.add(8);
		fdsa.add(9);
		fdsa.add(0);
		fdsa.add(1);
		fdsa.add(2);
		fdsa.add(3);
		fdsa.add(4);
		fdsa.add(5);
		fdsa.add(6);
		fdsa.add(7);
		fdsa.add(8);
		fdsa.add(9);
		fdsa.add(0);
		fdsa.add(1234);
		assertTrue (fdsa.myValues[0] == 0);
		assertTrue (fdsa.myValues[1] == 1);
		assertTrue (fdsa.myValues[21] == 1234);
		assertTrue (fdsa.myCount == 22);
	}
	
	public void testInsert(){
		ResizableIntSequence numbers = new ResizableIntSequence(2);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.add(8);
		numbers.add(9);
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.add(8);
		numbers.add(9);
		numbers.add(0);
		numbers.insert(9, 3);
		numbers.insert(2, 4);
		numbers.insert(8, 2);
		assertTrue (numbers.myValues[2] == 8);
		assertTrue (numbers.myValues[4] == 9);
		assertTrue (numbers.myValues[5] == 2);
		assertTrue (numbers.myValues[23] == 9);
		assertTrue (numbers.myValues[24] == 0);
		assertTrue (numbers.myValues[25] == 0);
		assertTrue (numbers.myCount == 25);
	}
	
	public void testRemove(){
		ResizableIntSequence remove = new ResizableIntSequence(2);
		remove.add(2);
		remove.add(3);
		assertTrue(remove.remove(1) == 0);
		assertTrue(remove.myValues.length == 18);
	}
}
