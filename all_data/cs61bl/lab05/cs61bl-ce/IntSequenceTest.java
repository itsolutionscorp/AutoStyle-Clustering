import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	public void testIntSequence() {
		IntSequence yellow = new IntSequence(20);
		assertTrue(yellow.myValues.length == 20);
	}
	
	public void testisEmpty() {
		IntSequence yellow = new IntSequence(20);
		assertEquals(true, yellow.isEmpty());
		yellow.add(7);
		assertEquals(false, yellow.isEmpty());
	}
	public void testSize() {
		IntSequence yellow = new IntSequence(20);
		yellow.add(7);
		yellow.add(9);
		assertEquals(2, yellow.size());
	}
	
	public void testElementAt() {
		IntSequence yellow = new IntSequence(20);
		yellow.add(7);
		yellow.add(9);
		assertEquals(9, yellow.elementAt(1));
		assertEquals(-1, yellow.elementAt(5));
	}
	
	public void testAdd() {
		IntSequence yellow = new IntSequence(20);
		yellow.add(7);
		yellow.add(9);
		assertEquals(9, yellow.elementAt(1));
		IntSequence blue = new IntSequence(2);
		blue.add(1);
		blue.add(2);
		assertEquals(2, blue.elementAt(1));
	}
	public void testRemove() {
		IntSequence blue = new IntSequence(5);
		blue.add(2);
		blue.add(4);
		blue.add(6);
		blue.add(8);
		blue.remove(2);
		assertEquals(“2 4 8”, blue.toString() );
		blue.remove(3);

		//nothing in position to remove, no change expected 

		assertEquals(“2 4 8”, blue.toString() ); 

		}
}


