import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testadd() {
		ResizableIntSequence mySequence = new ResizableIntSequence(2);
		mySequence.add(1);
		assertTrue (mySequence.elementAt(0) == 1);
		mySequence.add(2);
		assertTrue (mySequence.elementAt(0)==1 && mySequence.elementAt(1)==2);
		assertTrue (mySequence.myCount == 2);
		mySequence.add(3);
		assertTrue (mySequence.myCount == 3);
		assertTrue (mySequence.elementAt(0)==1 && mySequence.elementAt(1)==2 && 
				mySequence.elementAt(2)== 3);	
	}
	
	public void testinsert() {	
		ResizableIntSequence mySequence = new ResizableIntSequence(5);
		mySequence.add(2);
		mySequence.add(4);
		mySequence.insert(1, 0);
		mySequence.insert(3, 2);
		mySequence.insert(5, 4);
		mySequence.insert(10, 0);
		assertTrue (mySequence.elementAt(0) == 10 && mySequence.elementAt(1) == 1 
				&& mySequence.elementAt(2) == 2 && mySequence.elementAt(3) == 3
				&& mySequence.elementAt(4) == 4 && mySequence.elementAt(5) == 5 
				&& mySequence.size() == 6); 
		
	}
	public void testremove() {
		ResizableIntSequence mySequence = new ResizableIntSequence(4);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		assertTrue (mySequence.remove(2) == 3);
		mySequence.remove(0);
		assertTrue (mySequence.elementAt(0) == 2);
		assertTrue (mySequence.myValues.length == 2);
		mySequence.remove(1);
		assertTrue (mySequence.elementAt(0)== 2);
		mySequence.remove(0);
		assertTrue (mySequence.isEmpty());

	}
}
	
