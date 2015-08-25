import junit.framework.TestCase;


public class IntSequenceTest extends TestCase {
	public void testConstructor() {
		IntSequence s = new IntSequence(20);
		assertTrue(s.isEmpty());
		assertFalse(s.size() == 20); 
		
	}
	
	public void testIsEmpty() {
		IntSequence s = new IntSequence(20); 
		assertTrue(s.size() == 0);
		assertTrue(s.isEmpty());
		s.add(2);
		assertTrue(s.size() == 1); 
		assertFalse(s.isEmpty());
	}
	
	public void testSize() {
		IntSequence s = new IntSequence(20);
		assertTrue(s.size() == 0); 
		for (int i = 0; i < 10; i += 2) { //(0, 4, 8, 12, 16)
			s.add(i*2);
		}
		assertTrue(s.size() == 5);
	}
	
	public void testElementAt() {
		IntSequence s = new IntSequence(20);
		for (int i = 0; i < 10; i += 2) { //(0, 4, 8, 12, 16)
			s.add(i*2);
		}
		assertTrue(s.elementAt(0) == 0);
		assertTrue(s.elementAt(1) == 4);
		assertTrue(s.elementAt(2) == 8);
		assertTrue(s.elementAt(3) == 12);
		assertTrue(s.elementAt(4) == 16);
		
		IntSequence s2 = new IntSequence(20);
		s2.add(2);
		assertTrue(s2.elementAt(0) == 2);
		
		
			
	}
	
	public void testAdd() {
		IntSequence s = new IntSequence(20);
		s.add(2);
		assertFalse(s.isEmpty());
		assertTrue(s.size() == 1);
		assertTrue(s.elementAt(0) == 2);
		s.add(9);
		assertFalse(s.isEmpty());
		assertTrue(s.size() == 2);
		assertTrue(s.elementAt(1) == 9);
				
		
	}
	
	public void testRemove() {
		IntSequence s = new IntSequence(20); //(2, 4, 6, 8, 10, 12), size = 6
		for (int i = 1; i <=6; i++) {
			s.add(i * 2); 
		}
		
		s.remove(0); //(4, 6, 8, 10, 12), size = 5
		assertTrue(s.size() == 5);
		assertTrue(s.elementAt(0) == 4);
		assertTrue(s.elementAt(4) == 12);
		
		s.remove(3); //(4, 6, 8, 12), size = 4
		assertTrue(s.size() == 4);
		assertTrue(s.elementAt(3) == 12);
		assertTrue(s.elementAt(0) == 4);
		
		s.remove(3); //(4, 6, 8), size = 3
		assertTrue(s.size() == 3);
		assertTrue(s.elementAt(2) == 8);
		
			
	}
	
	public void testInsert() {
		IntSequence s = new IntSequence(20);
		for (int i = 0; i < 10; i += 2) { //(0, 4, 8, 12, 16), size = 5
			s.add(i*2);
		}
		assertTrue(s.size() == 5);
		s.insert(5, 0); //(5, 0, 4, 8, 12, 16), size = 6
		assertTrue(s.elementAt(0) == 5);
		assertTrue(s.elementAt(1) == 0);
		assertTrue(s.elementAt(5) == 16);
		assertTrue(s.size() == 6);
		
		s.insert(5, 6); //(5, 0, 4, 8, 12, 16, 5), size = 7
		assertTrue(s.elementAt(0) == 5);
		assertTrue(s.elementAt(5) == 16);
		assertTrue(s.elementAt(6) == 5);
		assertTrue(s.size() == 7);
		
		s.insert(5, 3); //(5, 0, 4, 5, 8, 12, 16, 5), size = 8
		assertTrue(s.elementAt(0) == 5);
		assertTrue(s.elementAt(3) == 5);
		assertTrue(s.elementAt(4) == 8);
		assertTrue(s.elementAt(7) == 5);
		assertTrue(s.size() == 8);
	}
	
	public void testContains() {
		IntSequence s = new IntSequence(20);
		for (int i = 0; i < 10; i += 2) { //(0, 4, 8, 12, 16), size = 5
			s.add(i*2);
		}
		assertTrue(s.contains(0));
		assertTrue(s.contains(16));
		assertTrue(s.contains(8));
		assertFalse(s.contains(7));
		
		IntSequence s2 = new IntSequence(20);
		for (int i = 0; i < 5; i++) { //(5, 5, 5, 5, 5), size = 5
			s2.add(5);
		}
		assertTrue(s2.contains(5));
		assertFalse(s2.contains(0)); //place holder 0s do not count!! 
		
		IntSequence s3 = new IntSequence(20);
		for (int i = 0; i < 6; i++) { //(0, 1, 2, 3, 4, 5)
			s3.add(i);
		}
		s3.remove(5); //(0, 1, 2, 3, 4)
		assertFalse(s.contains(5));
		s3.remove(2);
		assertFalse(s.contains(2));
	}
	
	public void testToString() {
		IntSequence s = new IntSequence(20);
		for (int i = 0; i < 10; i += 2) { //(0, 4, 8, 12, 16), size = 5
			s.add(i*2);
		}
		assertEquals("0 4 8 12 16", s.toString());
		
	}
}

