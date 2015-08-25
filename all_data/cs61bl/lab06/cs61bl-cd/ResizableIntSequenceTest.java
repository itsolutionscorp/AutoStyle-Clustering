import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {

	public void testAdd(){
		ResizableIntSequence firstSequence = new ResizableIntSequence(2);
		firstSequence.add(3);
		firstSequence.add(4);
		firstSequence.add(5);
		ResizableIntSequence secondSequence = new ResizableIntSequence(3);
		secondSequence.add(3);
		secondSequence.add(4);
		secondSequence.add(5);
		assertTrue(firstSequence.toString().equals(secondSequence.toString()));
		assertTrue(firstSequence.size()== secondSequence.size());
	}
	
	public void testInsert(){
		ResizableIntSequence firstSequence = new ResizableIntSequence(3);
		firstSequence.add(3);
		firstSequence.add(4);
		firstSequence.add(5);
		firstSequence.insert(9,2);
		ResizableIntSequence secondSequence = new ResizableIntSequence(4);
		secondSequence.add(3);
		secondSequence.add(4);
		secondSequence.add(5);
		secondSequence.insert(9,2);
		assertTrue(firstSequence.toString().equals(secondSequence.toString()));
		assertTrue(firstSequence.size()== secondSequence.size());
	}
}
