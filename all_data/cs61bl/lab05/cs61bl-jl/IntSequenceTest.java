import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testConstructor() {
		IntSequence a = new IntSequence(5); 
		assertTrue (a.isEmpty()); 
		assertTrue (a.size() == 0); 
		a.add(1);
		assertFalse (a.isEmpty()); 
		assertTrue (a.size() == 1); 
		assertTrue (a.elementAt(0) == 1); 
		a.add(2);
		assertTrue (a.elementAt(1) == 2); 
		a.add(0);
		assertTrue (a.elementAt(2) == 0); 
		a.add(3);
		assertTrue (a.elementAt(3) == 3); 
		a.add(4);
		assertTrue (a.elementAt(4) == 4); 
		assertTrue (a.size() == 5); 
		// a.add(5); should print error message
		// a.elementAt(5); should print error message 
		assertTrue (a.toString().equals("1 2 0 3 4"));
	}
	
	@Test
	public void testRemove() {
		IntSequence a = new IntSequence(5); 
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		assertTrue (a.remove(2) == 3);
		assertTrue (a.size() == 3);
		assertTrue (a.toString().equals("1 2 4"));
	}
	
	@Test
	public void testInsert() {
		IntSequence a = new IntSequence(10); 
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.insert(5, 0);
		assertTrue (a.size() == 5);
		assertTrue (a.toString().equals("5 1 2 3 4"));
		a.insert(5, 5);
		assertTrue (a.size() == 6);
		assertTrue (a.toString().equals("5 1 2 3 4 5"));
		a.insert(5, 3);
		assertTrue (a.size() == 7);
		assertTrue (a.toString().equals("5 1 2 5 3 4 5"));
		
	}
	
	@Test
	public void testInsert2() {
		IntSequence a = new IntSequence(10); 
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.insert(100, 4);
		assertTrue (a.toString().equals("1 2 3 4 100 5 6 7"));
	}
	
	@Test
	public void contains() {
		IntSequence a = new IntSequence(10); 
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		assertTrue (a.contains(4));
		assertFalse (a.contains(5));
	}


}

