import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testIsEmpty() {
		IntSequence i = new IntSequence(2);
		assertEquals(i.isEmpty(), true);
		i.insert(2, 0);
		assertEquals(i.isEmpty(), false);
	}
	
	public void testSize() {
		IntSequence i = new IntSequence(4); 
		i.add(3);
		assertEquals(i.size(), 1);
		i.add(3);
		i.add(3);
		assertEquals(i.size(), 3);
		assertEquals(i.toString(), "3 3 3");
	}
	
	public void testInsert() {
		IntSequence i = new IntSequence(4);
		i.insert(3, 0);
		assertEquals(i.size(), 1);
		i.insert(3, 3);
		assertEquals(i.size(), 1);
		assertEquals(i.toString(), "3");
	}
	
	public void testElementAt () {
		IntSequence i = new IntSequence(4);
		i.add(3);
		assertEquals(i.elementAt(0), 3);
		i.add(2);
		i.add(4);
		assertEquals(i.elementAt(2), 4);
		assertEquals(i.toString(), "3 2 4");
	}
	
	public void testAdd() {
		IntSequence i = new IntSequence(2);
		i.add(3);
		i.add(0);
		assertEquals(i.toString(), "3 0");
	}
	
	public void testRemove() {
		IntSequence i = new IntSequence(4);
		i.add(1);
		i.add(2);
		i.add(3);
		i.add(4);
		assertEquals(i.remove(3), 4);
		assertEquals(i.myCount, 3);
		assertEquals(i.toString(), "1 2 3");
		i.add(4);
		assertEquals(i.remove(0), 1);
		assertEquals(i.toString(), "2 3 4");
	}
	
	public void testContains() {
		IntSequence i = new IntSequence(2);
		i.add(2);
		i.add(3);
		assertEquals(i.contains(2), true);
		assertEquals(i.contains(1), false);
	}

}
