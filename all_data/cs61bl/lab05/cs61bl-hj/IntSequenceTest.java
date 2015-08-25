import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest {

	@Test
	public void testAdd() {
		IntSequence is1 = new IntSequence(3);
		is1.add(1);
		is1.add(8);
		is1.add(5);
		assertEquals(1, is1.elementAt(0));
		assertEquals(8, is1.elementAt(1));
		assertEquals(5, is1.elementAt(2));
	}
	
	@Test
	public void testSize(){
		IntSequence is1 = new IntSequence(10);
		is1.add(1);
		is1.add(8);
		is1.add(5);
		assertEquals(3, is1.size());
	}
	
	@Test
	public void testEmpty(){
		IntSequence is1 = new IntSequence(10);
		assertEquals(true, is1.isEmpty());
		is1.add(1);
		assertEquals(false, is1.isEmpty());
	}
	
	@Test
	public void testString(){
		IntSequence is1 = new IntSequence(10);
		is1.add(1);
		is1.add(8);
		is1.add(5);
		assertEquals("1 8 5", is1.toString());
	}
	
	@Test
	public void testRemove(){
		IntSequence is1 = new IntSequence(10);
		is1.add(1);
		is1.add(8);
		is1.add(5);
		assertEquals(5, is1.remove(2));
		assertEquals(2, is1.size());
		is1.add(6);
		is1.remove(1);
		assertEquals(1, is1.elementAt(0));
		assertEquals(6, is1.elementAt(1));
		assertEquals("1 6", is1.toString());
	}

	@Test
	public void testInsert(){
		IntSequence is1 = new IntSequence(10);
		is1.add(1);
		is1.add(8);
		is1.add(5);
		is1.insert(3, 1);
		assertEquals(1, is1.elementAt(0));
		assertEquals(3, is1.elementAt(1));
		assertEquals(8, is1.elementAt(2));
		assertEquals(5, is1.elementAt(3));
		assertEquals(4, is1.size());
		
		is1.insert(2, 4);
		assertEquals(2, is1.elementAt(4));
		assertEquals(5, is1.elementAt(3));
		assertEquals(5, is1.size());
	}
	
	@Test
	public void testContains(){
		IntSequence is1 = new IntSequence(10);
		is1.add(1);
		is1.add(8);
		assertEquals(false, is1.contains(5));
		assertEquals(true, is1.contains(1));
	}
}
