import java.util.*;
import junit.framework.TestCase;

public class ListTest extends TestCase {

	public void testConstructor() {
		List l1 = new List();
		assertEquals ("( )", l1.toString());
		assertTrue (l1.isEmpty());
		assertTrue (l1.size()==0);
	}
	
	public void testAddToFront() {
		List l1 = new List();
		l1.addToFront ("b");
		assertEquals ("( b )", l1.toString());
		assertFalse (l1.isEmpty());
		assertTrue (l1.size()==1);
		l1.addToFront ("a");
		assertEquals ("( a b )", l1.toString());
		assertFalse (l1.isEmpty());
		assertTrue (l1.size()==2);
	}
	
	public void testAdd() {
		List l1 = new List();
		l1.add ("z");
		assertEquals ("( z )", l1.toString());
		assertFalse (l1.isEmpty());
		assertTrue (l1.size()==1);
		l1.add ("y");
		assertEquals ("( z y )", l1.toString());
		assertFalse (l1.isEmpty());
		assertTrue (l1.size()==2);
	}
	
	public void testEquals() {
		List l1 = new List();
		List l2 = new List();
		List l3 = new List();
		assertEquals (l1, l2);
		l1.add("a");
		assertFalse (l1.equals(l2));	// arg empty, this not
		l2.add("a");
		assertEquals (l1, l2);
		assertFalse (l3.equals(l1));	// this empty, arg not
		l2.add("b");
		assertFalse (l1.equals(l2));	// this shorter than arg
		l1.add("b");
		assertEquals (l1, l2);
		l1.add("c");
		assertFalse (l1.equals(l2));	// this longer than arg
		l3.add("a");
		l3.add("a");
		assertFalse (l2.equals(l3));	// same length, different elements
	}
	
	public void testIterator() {
		List l1 = new List();
		Iterator iter = l1.iterator();
		assertFalse (iter.hasNext());
		l1.add("a");
		l1.add("b");
		iter = l1.iterator();
		assertTrue (iter.hasNext());
		assertTrue (iter.hasNext());
		assertEquals ("a", iter.next());
		assertEquals ("b", iter.next());
		assertFalse (iter.hasNext());
		assertFalse (iter.hasNext());
	}

	public void testAppendInPlace() {
		List l1 = new List();
		List l2 = new List();
		l1.appendInPlace(l2);
		assertEquals (l1, l2);
		assertEquals ("( )", l1.toString());
		assertEquals ("( )", l2.toString());
		l1.add("a");
		l1.appendInPlace(l2);
		assertEquals ("( a )", l1.toString());
		assertEquals ("( )", l2.toString());
		l2.appendInPlace(l1);
		assertEquals ("( a )", l1.toString());
		assertEquals ("( a )", l2.toString());
		List l3 = new List();
		l3.add("x");
		l3.add("y");
		l3.appendInPlace(l1);
		assertEquals("( x y a )", l3.toString());
		assertEquals("( a )", l1.toString());
		l3.add("b");
		assertEquals("( x y a b )", l3.toString());
		assertEquals("( a b )", l1.toString());
		assertEquals("( a b )", l2.toString());
	}

}
