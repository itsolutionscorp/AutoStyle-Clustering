import junit.framework.TestCase;

public class ResizableIntSequenceTest extends TestCase {
	public void testAdd() {
		ResizableIntSequence s = new ResizableIntSequence(2);
		s.add(2);
		s.add(2);
		s.add(2);
		assertTrue (s.elementAt(2) == 2);
		assertTrue (s.myCount == 3);
		assertTrue (s.myValues.length == 3);
	}
	
	public void testToString() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		for (int i = 0; i < 5; i++) {
			s.add(i);
		}
		assertTrue (s.toString().equals("0 1 2 3 4"));
	}
	

	public void testInsert() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		for (int i = 0; i < 3; i++) {
			s.add(2);
		}
		s.insert(12, 1);
		assertTrue (s.toString().equals("2 12 2 2"));
		assertTrue (s.elementAt(2) == 2);
		assertTrue (s.myCount == 4);
		assertTrue (s.myValues.length == 4);
	}
	

	public void testRemove() {
		ResizableIntSequence s = new ResizableIntSequence(10);
		for (int i = 0; i < 5; i++) {
			s.add(i);
		}
		int k = s.remove(2);
		System.out.println(s.myValues.length);
		assertTrue (s.toString().equals("0 1 3 4"));
		assertTrue (k == 2);
		assertTrue(s.myCount == 4);
		assertTrue(s.myValues.length == 8);
	}
}
