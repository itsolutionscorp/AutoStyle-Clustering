import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence s = new IntSequence(2);
		assertTrue (s.myValues.length ==2);
		assertTrue (s.myCount ==0);
	}
	
	public void testRemove(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		assertEquals(6, s.remove(2));
		assertEquals ("3 4 7 7", s.toString());
		assertEquals(4, s.myCount);
		assertEquals(4, s.myValues.length);
		
		ResizableIntSequence s1 = new ResizableIntSequence(6);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(6);
		s1.add(7);
		s1.add(8);
		s1.add(9);
		assertEquals(3, s1.remove(2));
		assertEquals(8, s1.myValues.length);
		assertEquals(4, s1.remove(2));
		assertEquals(7, s1.myValues.length);
		assertEquals(6, s1.remove(3));

		assertEquals ("1 2 5 7 8 9", s1.toString());
		assertEquals(6, s1.myValues.length);
	}
	
	public void testInsert(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		s.insert(2, 4);
		assertEquals ("3 4 6 7 2 7", s.toString());
		assertEquals(6, s.myCount);
		
		ResizableIntSequence s1 = new ResizableIntSequence(5);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(6);
		s1.insert(2, 5);
		assertEquals ("1 2 3 4 5 2 6", s1.toString());
		assertEquals(7, s1.myCount);
	}

	public void testAdd(){
		ResizableIntSequence s = new ResizableIntSequence(5);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		s.add(7);
		assertEquals ("3 4 6 7 7 7", s.toString());
		assertEquals(6, s.myCount);
	}
	
	
}