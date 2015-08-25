import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence s = new IntSequence (5);
		assertEquals(s.isEmpty(), true);
		assertEquals(s.size(), 0);
		for (int i=0; i < 5; i++){
			assertEquals(s.elementAt(i), 0);
		}
	}
	public void testAdd(){
		IntSequence s = new IntSequence (3);
		s.add(1);
		s.add(2);
		assertEquals(s.isEmpty(), false);
		assertEquals(s.size(), 2);
		assertEquals(s.elementAt(0), 1);
		assertEquals(s.elementAt(1), 2);
		assertEquals(s.elementAt(2), 0);
		s.add(3);
		assertEquals(s.size(), 3);
		assertEquals(s.elementAt(2), 3);
	}
	public void testToString(){
		IntSequence s = new IntSequence (3);
		s.add(1);
		s.add(2);
		assertEquals(s.toString(), "1 2");
		s.add(0);
		assertEquals(s.toString(), "1 2 0");
	}
	public void testRemove(){
		IntSequence s = new IntSequence (4);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertEquals(s.toString(), "1 2 3 4");
		s.remove(2);
		assertEquals(s.toString(), "1 2 4");
		s.remove(0);
		assertEquals(s.toString(), "2 4");
		s.remove(1);
		assertEquals(s.toString(), "2");
		s.remove(0);
		assertEquals(s.toString(), "");
	}
	public void testInsert(){
		IntSequence s = new IntSequence (7);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.insert(0,2);
		assertEquals(s.toString(), "1 2 0 3 4");
		s.insert(0,0);
		assertEquals(s.toString(), "0 1 2 0 3 4");
		s.insert(0,6);
		assertEquals(s.toString(), "0 1 2 0 3 4 0");
		IntSequence s2 = new IntSequence (1);
		s2.insert(0,0);
		assertEquals(s2.toString(), "0");
	}
	public void testContains(){
		IntSequence s = new IntSequence (3);
		s.add(1);
		s.add(2);
		assertEquals(s.contains(2), true);
		assertEquals(s.contains(0), false);
		s.remove(1);
		assertEquals(s.contains(2), false);
		
	}
}
