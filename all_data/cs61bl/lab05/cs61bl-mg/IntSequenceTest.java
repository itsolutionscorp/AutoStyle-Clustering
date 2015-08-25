import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {

	public void testRemove() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.remove(0);
		assertFalse(a.contains(1));
		assertTrue(a.size() == 2);
	}
	
	public void testInsert() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(4);
		a.add(5);
		a.insert(3, 2);
		assertTrue(a.myValues[2] == 3 && a.myValues[3] == 4 && a.size() == 5);
		
		IntSequence b = new IntSequence(6);
		b.add(1);
		b.add(2);
		b.add(3);
		b.add(4);
		b.insert(5, 4);
		assertTrue(b.myValues[4] == 5 && b.myValues[5] == 0 && b.size() == 5);
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		assertTrue(a.contains(1));
	}

}
