import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testOthers() {
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(1);
		s.add(4);
		s.add(2);
		assertEquals ("3 1 4 2", s.toString());
	}

		

	public void testInsert() {
		IntSequence s = new IntSequence(20);
		
		s.add(3);
		s.add(1);
		s.add(4);
		s.add(2);
		s.insert(100, 2);
		assertEquals("3 1 100 4 2", s.toString());
	}
	public void testRemove() {
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(1);
		s.add(4);
		s.add(2);
		s.remove(2);
		assertEquals ("3 1 2", s.toString());

		
	}
}
