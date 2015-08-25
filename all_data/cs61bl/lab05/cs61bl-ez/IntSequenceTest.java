import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	
	public void testConstructor() {
		IntSequence mySequence = new IntSequence(20);
		assertTrue(mySequence.isEmpty());
		mySequence.add(5);
	 	assertFalse(mySequence.isEmpty());
		
		
	}
	
	public void testInsert() {
		IntSequence mySequence = new IntSequence(20);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.add(5);
		mySequence.insert(7,2);
		assertTrue(mySequence.elementAt(2) == 7);	
		assertTrue(mySequence.elementAt(0) == 1);		
		assertTrue(mySequence.elementAt(5) == 5);		
	}
	
	public void testRemove() {
		IntSequence mySequence = new IntSequence(20);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.add(5);		
		assertTrue(mySequence.remove(2) == 3);
		assertTrue(mySequence.elementAt(2) == 4);
		assertTrue(mySequence.elementAt(0) == 1);
	}

	public void testContain() {
		IntSequence mySequence = new IntSequence(20);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		mySequence.add(5);		
		assertTrue(mySequence.contains(1));
		assertTrue(mySequence.contains(5));
		assertFalse(mySequence.contains(20));
	}
}
