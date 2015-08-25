import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testConstructor(){
		IntSequence asdf = new IntSequence(1);
		assertTrue (asdf.myValues.length == 21);
		assertTrue (asdf.myCount == 1);
		
	}
	
	public void testAdd(){
		IntSequence fdsa = new IntSequence(1);
		fdsa.add(123123);
		assertTrue (fdsa.myValues[0] == 0);
		assertTrue (fdsa.myValues[1] == 123123);
		assertTrue (fdsa.myValues[2] == 0);
		assertTrue (fdsa.myCount == 2);
	}
	
	public void testInsert(){
		IntSequence numbers = new IntSequence(4);
		numbers.add(1);
		numbers.insert(1, 3);
		assertTrue (numbers.myValues[5] == 1);
		assertTrue (numbers.myValues[3] == 1);
		assertTrue (numbers.myCount == 6);
	}
	
	public void testIsEmpty(){
		IntSequence qwer = new IntSequence(0);
		assertTrue (qwer.isEmpty());
	}
	
	public void testSize(){
		IntSequence sizeTest = new IntSequence(6);
		assertTrue (sizeTest.size() == 6);
	}
	
	public void testElementAt(){
		IntSequence ele = new IntSequence(5);
		ele.add(5);
		assertTrue (ele.elementAt(5) == 5);
	}
	
	public void testToString(){
		IntSequence write = new IntSequence(3);
		write.add(3);
		assertTrue (write.toString().equals("0 0 0 3 "));
	}
	
	public void testRemove(){
		IntSequence remove = new IntSequence(2);
		remove.add(2);
		remove.add(3);
		assertTrue(remove.remove(2) == 2);
	}
	
	public void testContains(){
		IntSequence has = new IntSequence(0);
		has.add(1);
		has.add(2);
		has.add(5);
		has.add(4);
		has.add(3);
		assertTrue (has.contains(3));
	}
}
