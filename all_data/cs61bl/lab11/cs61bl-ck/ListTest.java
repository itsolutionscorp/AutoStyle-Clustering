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
		
		l1.add("a");
		l2.add("c");
		l2.add("a");
		assertTrue(l1.equals(l2));
		assertEquals (l1, l2);
	
	
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
		assertTrue(l1.isOK());
		assertEquals (l1, l2);
		assertEquals ("( )", l1.toString());
		assertEquals ("( )", l2.toString());
		l1.add("a");
		l1.appendInPlace(l2);
		assertTrue(l1.isOK());
		assertEquals ("( a )", l1.toString());
		assertEquals ("( )", l2.toString());
		l2.appendInPlace(l1);
		assertTrue(l1.isOK());
		assertEquals ("( a )", l1.toString());
		assertEquals ("( a )", l2.toString());
		assertTrue(l1.isOK());
		List l3 = new List();
		l3.add("x");
		l3.add("y");
		l3.appendInPlace(l1);
		assertTrue(l1.isOK());
		assertEquals("( x y a )", l3.toString());
		assertEquals("( a )", l1.toString());
		l3.add("b");
		assertTrue(l3.isOK());
		assertEquals("( x y a b )", l3.toString());
		assertEquals("( a b )", l1.toString());
		assertEquals("( a b )", l2.toString());
	}
	
	public void testremove() {
		List l1 = new List();
//		System.out.println(l1.isOK());
		List l2 = new List();
		List l3 = new List();
//		System.out.println(l1.equals(l2));
		l1.add("a");
//		System.out.println(l1.isOK());
		l1.add("b");
		l1.add("a");
		l1.add("a");
		l1.add("c");
		l1.add("d");
		l1.add("a");
		l1.add("e");
		l1.add("a");
		assertEquals(l1.toString(), "( a b a a c d a e a )");
		assertTrue(l1.isOK());
		l1.remove("a");
		assertEquals(l1.toString(), "( b c d e )");
		assertTrue(l1.isOK());
		l1.remove("e");
		assertEquals(l1.toString(), "( b c d )");
		assertTrue(l1.isOK());
		l1.remove("");
		l2.remove("e");
		assertTrue(l1.isOK());
	}
	
	public void testdoubleInPlace() {
		List l1 = new List();
		l1.add("a");
		l1.add("b");
		l1.add("c");
		l1.add(2);
		l1.add(3);
		assertTrue(l1.isOK());
		assertEquals(l1.toString(), "( a b c 2 3 )");
		l1.doubleInPlace();
		assertTrue(l1.isOK());
		assertEquals(l1.toString(), "( a a b b c c 2 2 3 3 )");
		
		List l2 = new List();
		l2.doubleInPlace();
		assertEquals(l2.toString(), "( )");
		assertTrue(l2.isOK());
	}
	
	
	public void testreverse() {
		List l1 = new List();
		l1.add("a");
		l1.add("b");
		l1.add("c");
		assertEquals(l1.toString(), "( a b c )");
		l1.reverse();
		assertEquals(l1.toString(), "( c b a )");
		assertTrue(l1.isOK());
		
		
		
		List l2 = new List();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		l2.reverse();
		assertEquals(l2.toString(), "( 3 2 1 )");
		l2.reverse();
		assertEquals(l2.toString(), "( 1 2 3 )");
		assertTrue(l2.isOK());
		
		
	}

}
