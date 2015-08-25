import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	
	
	public void testinsert() {
		IntSequence s = new IntSequence(10);
		s.insert(6, 1);
		s.insert(6, 2);
		assertTrue (s.myCount == 2);	
	}
	
	public void testaddremovecontains() {
		IntSequence s = new IntSequence(5);
		assertTrue (s.isEmpty());
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.myCount == 4);
		s.remove(4);
		s.remove(2);
		assertTrue (s.myCount == 2);
		assertTrue (s.contains(1));
	}
	
	
	
}

