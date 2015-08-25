import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Test;


public class IntSequenceTest extends TestCase{

	public void testConstructor() {
		IntSequence test = new IntSequence(20);
		assertTrue(test.isEmpty());
	}
	
	public void testAdd() {
		IntSequence test = new IntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		assertTrue(test.myCount == 3);
		assertTrue(test.elementAt(0) == 1);
		assertTrue(test.elementAt(1)==5);
		assertEquals(test.toString(), "1 5 7");
	}
	
	public void testInsert() {
		IntSequence test = new IntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		test.insert(9, 1);
		assertTrue(test.myCount == 4);
		assertEquals(test.toString(), "1 9 5 7");

		test.insert(99, 2);
		assertTrue(test.myCount == 5);
		assertEquals(test.toString(), "1 9 99 5 7");
		
		IntSequence test2 = new IntSequence(200);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		test2.add(4);
		test2.add(5);
		test2.add(6);
		test2.insert(9, 4);
		assertTrue(test2.myCount == 7);
		assertEquals(test2.toString(), "1 2 3 4 9 5 6");
		

	}
	
	public void testIsEmpty() {
		IntSequence test = new IntSequence(20);
		assertTrue(test.isEmpty());
		test.add(1);
		test.add(5);
		test.add(7);
		assertTrue(!test.isEmpty());
	}
	
	public void testSize() {
		IntSequence test = new IntSequence(20);
		assertTrue(test.size() == 0);
		test.add(1);
		test.add(5);
		test.add(7);
		assertTrue(test.size() == 3);
	}
	
	public void testElementAt() {
		IntSequence test = new IntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		assertTrue(test.elementAt(0) == 1);
		assertTrue(test.elementAt(1) == 5);
	}
	
	public void testRemove() {
		IntSequence test = new IntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		test.remove(1);
		assertTrue(test.size() == 2);
		assertEquals(test.toString(), "1 7");
		
		IntSequence test2 = new IntSequence(200);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		test2.add(4);
		test2.add(5);
		test2.add(6);
		test2.remove(4);
		assertTrue(test2.myCount == 5);
		assertEquals(test2.toString(), "1 2 3 4 6");
		
	}
	
	public void testContains(){
		IntSequence test = new IntSequence(20);
		test.add(1);
		test.add(5);
		test.add(7);
		assertTrue(test.contains(5));
		
		IntSequence test2 = new IntSequence(200);
		test2.add(1);
		test2.add(2);
		test2.add(3);
		test2.add(4);
		test2.add(5);
		test2.add(6);
		assertTrue(!test2.contains(7));

	}
}
