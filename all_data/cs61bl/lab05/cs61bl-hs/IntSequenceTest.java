import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	public void testtoString() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		String b = a.toString();
		String actual = "1 2 3 4 5";
		assertEquals(b, actual);
	}
	
	public void testConstructor() {
		IntSequence a = new IntSequence(20);
		assertTrue(a.myValues.length == 20);
		assertTrue(a.myCount == 0);
	}
	
	public void testAdd() {
		IntSequence a = new IntSequence(4);
		a.add(10);
		a.add(100);
		a.add(1000);
		assertTrue(a.elementAt(0) == 10);
		assertTrue(a.elementAt(1) == 100);
		assertTrue(a.elementAt(2) == 1000);
		a.add(10000);
		assertTrue(a.elementAt(3) == 10000);
	}
	
	public void testisEmpty() {
		IntSequence a = new IntSequence(5);
		assertTrue(a.isEmpty());
		IntSequence b = new IntSequence(5);
		b.add(1);
		assertFalse(b.isEmpty());
	}
	
	public void testSize() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		assertTrue(a.size() == 1);
		a.add(2);
		assertTrue(a.size() == 2);
		a.add(3);
		assertTrue(a.size() == 3);
		a.add(4);
		assertTrue(a.size() == 4);
		a.add(5);
		assertTrue(a.size() == 5);
	}
	
	public void testelementAt() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		assertTrue(a.elementAt(0) == 1);
		assertTrue(a.elementAt(1) == 2);
		assertTrue(a.elementAt(2) == 3);
		assertTrue(a.elementAt(3) == 4);
		assertTrue(a.elementAt(4) == 5);
	}
	
	public void testRemove() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.remove(4);
		assertTrue(a.myCount == 4);
		assertTrue(a.elementAt(0) == 1);
		assertTrue(a.elementAt(1) == 2);
		assertTrue(a.elementAt(2) == 3);
		assertTrue(a.elementAt(3) == 4);
		assertTrue(a.myValues[4] == 5);
		a.add(5);
		a.remove(3);
		assertTrue(a.myCount == 4);
		assertTrue(a.elementAt(0) == 1);
		assertTrue(a.elementAt(1) == 2);
		assertTrue(a.elementAt(2) == 3);
		assertTrue(a.elementAt(3) == 5);
		assertTrue(a.myValues[4] == 5);
		a.add(6);
		a.remove(0);
		assertTrue(a.myCount == 4);
		assertTrue(a.elementAt(0) == 2);
		assertTrue(a.elementAt(1) == 3);
		assertTrue(a.elementAt(2) == 5);
		assertTrue(a.elementAt(3) == 6);
		assertTrue(a.myValues[4] == 6);
		IntSequence b = new IntSequence(5);
		b.add(1);
		b.add(2);
		b.add(3);
		b.remove(2);
		assertTrue(b.elementAt(0) == 1);
		assertTrue(b.elementAt(1) == 2);
		assertTrue(b.myCount == 2);
	}
	
	public void testInsert() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.insert(10, 0);
		assertTrue(a.myCount == 4);
		assertTrue(a.elementAt(0) == 10);
		assertTrue(a.elementAt(1) == 1);
		assertTrue(a.elementAt(2) == 2);
		assertTrue(a.elementAt(3) == 3);
		a.insert(50, 3);
		assertTrue(a.myCount == 5);
		assertTrue(a.elementAt(0) == 10);
		assertTrue(a.elementAt(1) == 1);
		assertTrue(a.elementAt(2) == 2);
		assertTrue(a.elementAt(3) == 50);
		assertTrue(a.elementAt(4) == 3);
		a.remove(3);
		a.insert(20, 3);
		assertTrue(a.myCount == 5);
		assertTrue(a.elementAt(0) == 10);
		assertTrue(a.elementAt(1) == 1);
		assertTrue(a.elementAt(2) == 2);
		assertTrue(a.elementAt(3) == 20);
		assertTrue(a.elementAt(4) == 3);
	}
	
	public void testContains() {
		IntSequence a = new IntSequence(5);
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		assertTrue(a.contains(1));
		assertTrue(a.contains(2));
		assertTrue(a.contains(3));
		assertTrue(a.contains(4));
		assertTrue(a.contains(5));
		assertFalse(a.contains(1000));
		assertFalse(a.contains(50));
	}
}
