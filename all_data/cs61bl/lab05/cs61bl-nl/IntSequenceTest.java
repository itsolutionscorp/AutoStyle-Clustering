import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	IntSequence mySequence = new IntSequence(20);
	
	public void testRemove () {
		mySequence.add(1);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(1);
		mySequence.add(3);
		
		mySequence.remove(3);
		assertTrue (mySequence.myValues[0] == 1);
		assertTrue (mySequence.myValues[1] == 1);
		assertTrue (mySequence.myValues[2] == 2);
		assertTrue (mySequence.myValues[3] == 3);
		assertTrue (mySequence.myValues[4] == 0);
	
		mySequence.remove(0);
		assertTrue (mySequence.myValues[0] == 1);
		assertTrue (mySequence.myValues[1] == 2);
		assertTrue (mySequence.myValues[2] == 3);
		assertTrue (mySequence.myValues[3] == 0);
		
		mySequence.add(3);
		mySequence.remove(4);
		assertTrue (mySequence.myValues[0] == 1);
		assertTrue (mySequence.myValues[1] == 2);
		assertTrue (mySequence.myValues[2] == 3);
		assertTrue (mySequence.myValues[3] == 0);
	}
	public void testInsert(){
		mySequence.add(1);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(1);
		mySequence.add(3);
		
		mySequence.insert(3, 4);
		assertTrue (mySequence.myValues[0] == 1);
		assertTrue (mySequence.myValues[1] == 1);
		assertTrue (mySequence.myValues[2] == 2);
		assertTrue (mySequence.myValues[3] == 1);
		assertTrue (mySequence.myValues[4] == 3);
		assertTrue (mySequence.myValues[5] == 3);
		
		mySequence.insert(3, 3);
		assertTrue (mySequence.myValues[0] == 1);
		assertTrue (mySequence.myValues[1] == 1);
		assertTrue (mySequence.myValues[2] == 2);
		assertTrue (mySequence.myValues[3] == 3);
		assertTrue (mySequence.myValues[4] == 1);
		assertTrue (mySequence.myValues[5] == 3);
		assertTrue (mySequence.myValues[6] == 3);
		
		mySequence.insert(3, 0);
		assertTrue (mySequence.myValues[0] == 3);
		assertTrue (mySequence.myValues[1] == 1);
		assertTrue (mySequence.myValues[2] == 1);
		assertTrue (mySequence.myValues[3] == 2);
		assertTrue (mySequence.myValues[4] == 3);
		assertTrue (mySequence.myValues[5] == 1);
		assertTrue (mySequence.myValues[6] == 3);
		assertTrue (mySequence.myValues[7] == 3);
	}
	public void testContains() {
		mySequence.add(1);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(1);
		mySequence.add(3);
		
		assertTrue (mySequence.contains(1));
		assertTrue (mySequence.contains(2));
		assertTrue (mySequence.contains(3));
	}
	

}
