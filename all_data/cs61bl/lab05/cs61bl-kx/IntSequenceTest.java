import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor(){
		IntSequence s = new IntSequence(5);
		assertTrue(s.myCount == 0);
	}
	public void testIsEmpty(){
		IntSequence s = new IntSequence(2);
		assertTrue(s.isEmpty()==true);
		
		s.add(7);
		assertTrue(s.isEmpty()==false);
		
		
	}
	
	public void testSize(){
		IntSequence s = new IntSequence(2);
		assertTrue(s.size()==0);
		
		s.add(9);
		assertTrue(s.size()==1);
		
		
	}
	public void testElementAt(){
		IntSequence s = new IntSequence(5);
		s.add(9);
		assertTrue(s.elementAt(0)==9);
		
		s.add(10);
		assertTrue(s.elementAt(1)==10);
		
		assertTrue(s.elementAt(6)==-1);
		
		assertTrue(s.elementAt(4)==-1);
		assertTrue(s.elementAt(-10)==-1);
		
				
	}
	public void testAdd(){
		IntSequence s = new IntSequence(2);
		s.add(9);
		s.add(87);
		assertTrue(s.add(41)==-1);
		
		
	}
	
	public void testToString(){
		IntSequence s = new IntSequence(5);
		s.add(9);
		s.add(10);
		s.add(90);
		assertEquals("9 10 90", s.toString());
		
		s.add(8);
		s.add(6);
		assertEquals("9 10 90 8 6", s.toString());
	}
	public void testRemove(){
		IntSequence s = new IntSequence(6);
		s.add(9);
		s.add(8);
		s.add(5);
		s.add(1);
		
		assertTrue(s.remove(7)==-1);
		s.remove(2);
		assertEquals("9 8 1", s.toString());
		
		s.remove(2);
		assertEquals("9 8", s.toString());
		
		s.remove(0);
		assertEquals("8", s.toString());
		
		s.remove(0);
		assertTrue(s.remove(0)==-1);
		
		assertTrue(s.remove(90)==-1);
	}
	public void testInsert(){
		IntSequence s = new IntSequence(6);
		s.add(9);
		s.add(8);
		s.add(5);
		s.insert(6, 3);
		assertEquals("9 8 5 6", s.toString());
		assertTrue(s.insert(99, 5)==-1);
		
		s.insert(76, 2);
		
		assertEquals("9 8 76 5 6", s.toString());
		
		s.insert(89,  0);
		assertEquals("89 9 8 76 5 6", s.toString());
		
		assertTrue(s.insert(89,  0)==-1);
		
		assertTrue(s.insert(8, 90)==-1);
	
	}
	public void testContains(){
		IntSequence s = new IntSequence(6);
		s.add(9);
		s.add(8);
		s.add(5);
		assertTrue(s.contains(9));
		assertTrue(!s.contains(90));
		
	}
	

}
