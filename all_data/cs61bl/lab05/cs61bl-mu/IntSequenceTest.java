import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {

	

	public void testConstructor() {
		IntSequence s = new IntSequence(2);
		assertTrue (s.myValues.length ==2);
		assertTrue (s.myCount ==0);
	}
	
	public void testRemove(){
		IntSequence s = new IntSequence(5);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		assertEquals(6, s.remove(2));
		assertEquals ("3 4 7 7", s.toString());
		assertEquals(4, s.myCount);
		
		IntSequence s1 = new IntSequence(6);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(6);
		assertEquals(3, s1.remove(2));
		assertEquals ("1 2 4 5 6", s1.toString());
		assertEquals(5, s1.myCount);
	}
	
	public void testInsert(){
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		s.insert(2, 5);
		assertEquals ("3 4 6 7 7 2", s.toString());
		assertEquals(6, s.myCount);
		
		IntSequence s1 = new IntSequence(20);
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(6);
		s1.insert(2, 6);
		assertEquals ("1 2 3 4 5 6 2", s1.toString());
		assertEquals(7, s1.myCount);
	}
	
	public void testContains(){
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		assertEquals ("3 4 6 7 7", s.toString());
		assertEquals(5, s.myCount);
		assertEquals (true, s.contains(3));
		assertEquals (false, s.contains(5));
	}
	
	public void testIsEmpty(){
	IntSequence s = new IntSequence(20);
	assertEquals("", s.toString());
	assertEquals(0, s.myCount);
	}
	
	public void testSize(){
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(4);
		assertEquals("3 4", s.toString());
		assertEquals(2, s.myCount);
	}
	
	public void testElementAt(){
		IntSequence s = new IntSequence(20);
		s.add(3);
		s.add(3);
		s.add(4);
		s.add(6);
		s.add(7);
		s.add(7);
		assertEquals(6, s.elementAt(3));
		assertEquals("3 3 4 6 7 7", s.toString());
	}
}
	
