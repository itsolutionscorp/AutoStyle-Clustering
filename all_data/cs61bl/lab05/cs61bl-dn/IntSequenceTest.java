import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence s = new IntSequence(10);
		assertTrue (s.isEmpty());
		assertTrue (s.size() == 0);
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(10);
		s.add(2);
		assertTrue (s.elementAt(0) == 2);
		assertTrue (s.myCount == 1);
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 5; i++) {
			s.add(i);
		}
		assertTrue (s.toString().equals("0 1 2 3 4"));
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 5; i++) {
			s.add(i);
		}
		int k = s.remove(2);
		assertTrue (s.toString().equals("0 1 3 4"));
		assertTrue (k == 2);
		assertTrue(s.myCount == 4);
		k = s.remove(0);
		assertTrue (s.toString().equals("1 3 4"));
		assertTrue (k == 0);
		assertTrue(s.myCount == 3);
		k = s.remove(2);
		assertTrue (s.toString().equals("1 3"));
		assertTrue (k == 4);
		assertTrue(s.myCount == 2);
	}
	
	public void testInsertMiddle() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		s.insert(12, 1);
		assertTrue (s.toString().equals("0 12 1 2"));
		assertTrue(s.myCount == 4);
	}
	
	public void testInsertBeginning(){
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		s.insert(12, 0);
		assertTrue (s.toString().equals("12 0 1 2"));
		assertTrue(s.myCount == 4);
	}
	
	public void testInsertEnd() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		s.insert(12, 2);
		assertTrue (s.toString().equals("0 1 12 2"));
		assertTrue(s.myCount == 4);
	}
	
	public void testContainsBeginning() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		assertTrue (s.contains(0));
		assertFalse(s.myCount == 4);
	}
	
	public void testContainsMiddle() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		assertTrue (s.contains(1));
		assertFalse(s.myCount == 5);
	}
	
	public void testContainsEnd() {
		IntSequence s = new IntSequence(10);
		for (int i = 0; i < 3; i++) {
			s.add(i);
		}
		assertTrue (s.toString().equals("0 1 2"));
		assertTrue (s.contains(2));
		assertFalse(s.myCount == 6);
	}
}
