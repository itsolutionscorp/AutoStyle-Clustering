import junit.framework.TestCase;

public class IntSequenceTest extends TestCase {
	
	public void testConstructor(){
		IntSequence a = new IntSequence(10);
		assertTrue(a.myCount == 0);
		assertTrue(a.toString().equals("Empty Sequence!"));
		assertTrue(a.capacity() == 10);
	}

	public void testAdd() {
		IntSequence a = new IntSequence(10);
		a.add(1);
		a.add(4);
		a.add(6);
		a.add(3);
		assertTrue(a.size() == 4);
		assertTrue(a.toString().equals("1 4 6 3"));
	}

	public void testRemove() {
		IntSequence a = new IntSequence(5);
		a.add(1);a.add(4);a.add(6);a.add(3);
		a.remove(1);
		assertTrue(a.toString().equals("1 6 3"));
		a.remove(0);
		assertTrue(a.toString().equals("6 3"));
		a.remove(1);
		assertTrue(a.toString().equals("6"));
		a.remove(0);
		assertTrue(a.toString().equals("Empty Sequence!"));
	}
	
	public void testInsert(){
		IntSequence a = new IntSequence(6);
		a.add(1);a.add(4);a.add(3);
		a.insert(5,3);
		assertTrue(a.toString().equals("1 4 3 5"));
		assertTrue(a.size() == 4);
		a.insert(9,0);
		assertTrue(a.toString().equals("9 1 4 3 5"));
		a.insert(9,1);
		assertTrue(a.toString().equals("9 9 1 4 3 5"));
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(6);
		a.add(1);a.add(4);a.add(3);
		assertTrue(a.contains(4));
		assertFalse(a.contains(2));
		assertFalse(a.contains(0));
	}

}
