import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	public void testIsEmpty() {
		IntSequence i1 = new IntSequence(5);
		assertTrue (i1.isEmpty());
		i1.add(5);
		assertFalse (i1.isEmpty());
	}
	
	public void testSize() {
		IntSequence i1 = new IntSequence(5);
		assertTrue (i1.size() == 0);
		i1.add(1);
		assertTrue (i1.size() == 1);
		i1.add(2);
		i1.add(3);
		assertTrue (i1.size() == 3);
		i1.add(4);
		i1.add(5); 
	}
	
	public void testRemove() {
		IntSequence i1 = new IntSequence(5);
		i1.add(1);
		i1.add(2);
		i1.add(3);
		i1.remove(1);
		assertTrue (i1.size() == 2);
		assertTrue (i1.elementAt(1) == 3);
		//i1.remove(4); //return out of bound error
		
	}
	
	public void testInsert() {
		IntSequence i1 = new IntSequence(7);
		i1.add(0);
		i1.add(1);
		i1.add(2);
		i1.add(3);
		i1.add(4);
		i1.add(5);
		assertTrue (i1.size() == 6);
		i1.insert(100, 2);
		assertTrue (i1.elementAt(2) == 100);
		assertTrue (i1.elementAt(6) == 5);
		assertTrue (i1.size() == 7);
		
	}
	
	public void testElementAt() {
		IntSequence i1 = new IntSequence(5);
		i1.add(1);
		i1.add(2);
		i1.add(3);
		i1.add(4);
		assertTrue (i1.elementAt(2) == 3);
		//i1.elementAt(4) == 0; returns Index out of bound error
	}
	
	public void testToString() {
		IntSequence i1 = new IntSequence(5);
		i1.add(1);
		i1.add(2);
		i1.add(3);
		i1.add(4);
		assertEquals (i1.toString(), "1 2 3 4");
	}
	
	public void testContains() {
		IntSequence i1 = new IntSequence(4);
		i1.add(100);
		i1.add(50);
		i1.add(0);
		i1.add(3);
		assertTrue (i1.contains(0));
		assertTrue (i1.contains(3));
		assertFalse (i1.contains(1));
		i1.remove(2);
		assertFalse (i1.contains(0));
		
	}
}
