import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testResizeable() {
		ResizableIntSequence s = new ResizableIntSequence(3);
		s.add(1);
		s.add(2);
		s.add(3);
		assertTrue(s.size() == 3);
			s.add(4);
		assertTrue(s.size() == 4);
		ResizableIntSequence s2 = new ResizableIntSequence(1);
		s2.add(1);
		s2.insert(2, 0);
		//System.out.println(s2.toString());
		assertTrue(s2.toString().equals("2 1"));
		ResizableIntSequence s3 = new ResizableIntSequence(8);
		s3.add(1);
		s3.add(2);
		s3.add(3);
		s3.remove(2);
		assertTrue(s3.getCapacity() == 6);
		ResizableIntSequence s4 = new ResizableIntSequence(1000);
		for(int i = 1; i <= 1000; i++) {
			s4.add(i);
		}
		// s4 = {1, 2, 3, 4, ..., 999, 1000}
		for(int i = 0; i < 500; i++) {
			s4.remove(0);
		}
		System.out.println(s4.size());
		assertTrue(s4.getCapacity() == 750);
	}
	
}
