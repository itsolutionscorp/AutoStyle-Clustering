import static org.junit.Assert.*;

import org.junit.Test;


public class ResizableIntSequenceTest {

	@Test
	public void testadd() {
		ResizableIntSequence Values = new ResizableIntSequence(2);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(4);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 4);
		assertTrue (Values.contains(4) == true);
		
		
		Values.add(2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(4) == true);
		// adding another integer should change the size to three
		Values.add(3);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		assertEquals (Values.elementAt(2), 3);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(3) == true);
	}
	@Test
	public void testinsert() {
		ResizableIntSequence Values = new ResizableIntSequence(2);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(4);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 4);
		assertTrue (Values.contains(4) == true);
		
		
		Values.add(2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(4) == true);
		// adding another integer should change the size to three
		Values.insert(3,1);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		assertEquals (Values.elementAt(2), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(3) == true);
		
		Values.insert(4,2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 4);
		assertEquals (Values.elementAt(2), 4);
		assertEquals (Values.elementAt(3), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(3) == true);
		
		Values.insert(5,0);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 5);
		assertEquals (Values.elementAt(0), 5);
		assertTrue (Values.contains(4) == true);
		assertTrue (Values.contains(3) == true);
	
	}
	@Test
	public void testremove() { //will reduce size if less than 25 percent, not that the factor is floored so if the number is 2.5 then it will have to be udner 2
		ResizableIntSequence Values = new ResizableIntSequence(20);
		assertTrue (Values.isEmpty() == true);
		assertTrue (Values.size() == 0);
		
		Values.add(4);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 4);
		assertTrue (Values.contains(4) == true);
		
		
		Values.add(2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(4) == true);
		// adding another integer should change the size to three
		Values.insert(3,1);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		assertEquals (Values.elementAt(2), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(3) == true);
		
		Values.insert(4,2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 4);
		assertEquals (Values.elementAt(2), 4);
		assertEquals (Values.elementAt(3), 2);
		assertTrue (Values.contains(2) == true);
		assertTrue (Values.contains(3) == true);
		
		Values.remove(0);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 3);
		assertEquals (Values.elementAt(0), 3);
		assertTrue (Values.contains(4) == true);
		assertTrue (Values.contains(2) == true);
		
		Values.remove(2);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 2);
		assertEquals (Values.elementAt(1), 4);
		assertTrue (Values.contains(3) == true);
		assertTrue (Values.contains(2) == false);
		//length should now be 10;
		Values.remove(1);
		assertFalse (Values.isEmpty() == true);
		assertTrue (Values.size() == 1);
		assertEquals (Values.elementAt(0), 3);
		assertTrue (Values.contains(3) == true);
		assertTrue (Values.contains(2) == false);
		//length should now be 5
	}
		

}
