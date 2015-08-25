import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testConstructor() {
		IntSequence mySequence = new IntSequence(20);
		assertTrue (mySequence.myValues.length == 20);
		assertTrue (mySequence.myCount == 0);
	}
	
	public void testadd() {
		IntSequence mySequence = new IntSequence(2);
		mySequence.add(1);
		assertTrue (mySequence.elementAt(0) == 1);
		mySequence.add(2);
		assertTrue (mySequence.elementAt(0)==1 && mySequence.elementAt(1)==2);
		assertTrue (mySequence.myCount == 2);
	}
	
	public void testisEmpty() {
		IntSequence mySequence = new IntSequence(2);
		assertTrue (mySequence.isEmpty());
		mySequence.add(5);
		assertFalse (mySequence.isEmpty());
	}
	public void testsize() {
		IntSequence mySequence = new IntSequence(2);
		assertTrue (mySequence.size() == 0);
		mySequence.add(654647);
		assertTrue (mySequence.size() == 1);
	}
	public void testelementAt() {
		IntSequence mySequence = new IntSequence(2);
		mySequence.add(432);
		assertTrue (mySequence.elementAt(0) == 432);
	}
	public void testremove() {
		IntSequence mySequence = new IntSequence(4);
		mySequence.add(1);
		mySequence.add(2);
		mySequence.add(3);
		mySequence.add(4);
		assertTrue (mySequence.remove(2) == 3);
		mySequence.remove(0);
		assertTrue (mySequence.elementAt(0) == 2);
		mySequence.remove(1);
		assertTrue (mySequence.elementAt(0)== 2);
		mySequence.remove(0);
		assertTrue (mySequence.isEmpty());
	}
	public void testinsert() {	
		IntSequence mySequence = new IntSequence(5);
		mySequence.add(2);
		mySequence.add(4);
		mySequence.insert(1, 0);
		mySequence.insert(3, 2);
		mySequence.insert(5, 4);
		assertTrue (mySequence.elementAt(0) == 1 && mySequence.elementAt(1) == 2 
				&& mySequence.elementAt(2) == 3 && mySequence.elementAt(3) == 4
				&& mySequence.elementAt(4) == 5 && mySequence.size() == 5); 
		
	}
	public void testcontains() {
		IntSequence mySequence = new IntSequence(5);
		mySequence.add(2);
		mySequence.add(4);
		mySequence.insert(1, 0);
		mySequence.insert(3, 2);
		mySequence.insert(5, 4);
		assertTrue (mySequence.contains(5));
		assertFalse (mySequence.contains(3000));
	}
}
