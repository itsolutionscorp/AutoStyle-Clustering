import junit.framework.TestCase;


public class ResizableIntSequenceTest extends TestCase {

	public void testConstructor() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		assertTrue(r.isEmpty());
		assertFalse(r.size() > 0);
	}
	
	public void testAdd() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 10; i++) { //0, 1, 2, 3,...9, size = 10
			r.add(i);
		}
		assertFalse(r.isEmpty());
		assertTrue(r.size() == 10);
		
		r.add(-5); //0, 1, 2, 3,...9, -5, size = 11
		assertTrue(r.size() == 11);
		assertTrue(r.elementAt(10) == -5);
		assertTrue(r.elementAt(0) == 0);
		
		for (int i = 0; i < 10; i++) {
			r.add(0);
		}
		assertTrue(r.size() == 21); 
		assertTrue(r.elementAt(20) == 0);
		

	}
	
	public void testInsert() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 10; i++) { //0, 1, 2, 3...9, size = 10
			r.add(i);
		}
		r.insert(-5,  0); //-5, 0, 1, 2..., size = 11
		assertTrue(r.size() == 11);
		assertTrue(r.elementAt(0) == -5);
		assertTrue(r.elementAt(1) == 0); 
		assertTrue(r.elementAt(10) == 9);
		
		r.insert(-5, 3); //-5, 0, 1, -5, 2, 3, 4,...,9, size = 12
		assertTrue(r.size() == 12);
		assertTrue(r.elementAt(3) == -5);
		assertTrue(r.elementAt(11) == 9);
		
		r.insert(-5, 12);
		assertTrue(r.size() == 13);
		assertTrue(r.elementAt(12) == -5);
		
		for (int i = 0; i < 10; i++) {
			r.insert(0, 15);
		}
		assertTrue(r.elementAt(14) == 0);
		assertTrue(r.size() == 23);
		
	}
	
	public void testRemove() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 20; i++) { //0, 1, 2, 3...19, size = 20
			r.add(i);
		}
		r.remove(0); //1, 2, 3...19, size = 19
		assertTrue(r.elementAt(0) == 1);
		assertTrue(r.elementAt(18) == 19);
		assertTrue(r.size() == 19);
		
		r.remove(18); //1, 2, 3...18, size = 18
		assertTrue(r.elementAt(0) == 1);
		assertTrue(r.elementAt(17) == 18);
		assertTrue(r.size() == 18);
		
		for (int i = 0; i < 13; i++){ //14, 15, 16, 17, 18
			r.remove(0);
		}
		assertTrue(r.size() == 5);
		assertTrue(r.elementAt(0) == 14);
		
		r.remove(2);
		assertTrue(r.size() == 4); 
		assertTrue(r.elementAt(1) == 15);
		assertTrue(r.elementAt(2) == 17);
		
	}
	
	public void testIsEmpty() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		assertTrue(r.isEmpty());
		r.add(-5);
		assertFalse(r.isEmpty());
		r.insert(0,  0);
		assertFalse(r.isEmpty());
		r.remove(0);
		r.remove(0);
		assertTrue(r.isEmpty());
		
	}
	
	public void testSize() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		assertTrue(r.size() == 0);
		for (int i = 0; i < 20; i++) { //0, 1, 2, 3...19, size = 20
			r.add(i);
		}
		assertTrue(r.size() == 20);
		
	}
	
	public void testElementAt() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 20; i++) { //0, 1, 2, 3...19, size = 20
			r.add(i);
		}
		assertTrue(r.elementAt(0) == 0);
		assertTrue(r.elementAt(3) == 3);
		assertTrue(r.elementAt(19) == 19);
	}
	
	public void testToString() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 5; i++) { //0, 1, 2, 3, 4, size = 5
			r.add(i);
		}
		assertEquals("0 1 2 3 4", r.toString());
	}
	
	public void testContains() {
		ResizableIntSequence r = new ResizableIntSequence(10);
		for (int i = 0; i < 20; i++) { 
			r.add(5);
		}
		assertTrue(r.contains(5));
		assertFalse(r.contains(0)); 
	}
	
	
	
}
