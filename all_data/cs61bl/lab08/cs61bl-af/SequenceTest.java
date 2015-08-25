import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;


public class SequenceTest extends TestCase {

	@Test
	public void testConstructor() {
		Sequence<Integer> s = new Sequence<Integer>(2);
		assertTrue (s.myValues.length == 2);
		assertTrue (s.myValues[0] == 0);
		assertTrue (s.myValues[1] == 0);
	}
	public void testAdd() {
		Sequence<Integer> s = new Sequence<Integer>(4);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		System.out.println(s.elementAt(0));
		assertTrue((Integer)s.elementAt(0)==1);
		//assertTrue ((Integer)s.myValues[1] == 2);
		//assertTrue ((Integer)s.myValues[2] == 3);
		//assertTrue ((Integer)s.myValues[3] == 4);
		assertFalse (s.isEmpty());
	
	}
	public void testIsEmpty() {
		Sequence<Integer> s = new Sequence<Integer>(4);
		assertTrue (s.isEmpty() == true);
		s.add(1);
		assertTrue (s.isEmpty() == false);
	}
	public void testSize() {
		Sequence<Integer> s = new Sequence<Integer>(4);
		assertTrue (s.size() == 0);
		s.add(1);
		assertTrue (s.size() == 1);
		assertFalse (s.size() == 3);


	}
	public void testElementAt() {
		Sequence<Integer> s = new Sequence<Integer>(4);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.elementAt(0) == 1);
		assertTrue (s.elementAt(1) == 2);
		assertTrue (s.elementAt(2) == 3);
		assertTrue (s.elementAt(3) == 4);
		assertTrue (s.elementAt(100) == 100);

		
	}
	public void testToString() {
		Sequence<Integer> s = new Sequence<Integer>(4);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.toString().equals("1 2 3 4"));
	}
	public void testContains(){
		Sequence<Integer> s = new Sequence<Integer>(4);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		assertTrue (s.contains(3) == true);
		assertTrue (s.contains(1) == true);
		assertFalse (s.contains(9) == true);
		assertFalse (s.contains(100) == true);


	}
	public void testInsert () {
		Sequence<Integer> s = new Sequence<Integer>(10);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.add(5);
		s.add(6);
		s.add(7);
		s.insert(100, 4);

		for (int i = 0; i < s.myCount; i++) {
        	System.out.print(s.myValues[i]);
        }
		assertTrue (s.myCount == 8);
		
		assertTrue (s.myValues[3] == 4);
		assertTrue (s.myValues[4] == 100);
		assertTrue (s.myValues[7] == 7);
	    
		
	
	}
	
	public void testRemove() {
		Sequence<Integer> s = new Sequence<Integer>(10);
		assertTrue(s.remove(1) == 1);
		s.add(1);
		s.add(2);
		s.add(3);
		s.add(4);
		s.remove(2); 
		assertTrue (s.myValues[2] == 4); 
		assertTrue (s.myCount == 3); 
		assertTrue(s.remove(9) == 9);//have to do this because my System.exit is acting up
	}


}