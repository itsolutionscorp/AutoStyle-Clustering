import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		
		for (int i = 0; i < 25; i++) {
		    s.add(i);
		}
		System.out.println(s.size());
		assertTrue (s.size() == 25);
		for (int i = 0; i < 25; i++) {
			assertTrue (s.elementAt(i) == i);
		}
	}
	
	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(5);
		
		for (int i = 0; i < 5; i++) {
		    s.add(i);
		}
		
		s.insert(10,  0);
		s.insert(20,  6);
		assertTrue (s.size() == 7);
		assertTrue (s.elementAt(0) == 10);
		assertTrue (s.elementAt(1) == 0);
		assertTrue (s.elementAt(2) == 1);
		assertTrue (s.elementAt(3) == 2);
		assertTrue (s.elementAt(4) == 3);
		assertTrue (s.elementAt(5) == 4);
		assertTrue (s.elementAt(6) == 20);
	}
}
