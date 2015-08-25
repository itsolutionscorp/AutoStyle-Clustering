import static org.junit.Assert.*;

import java.util.Iterator;

import junit.framework.TestCase;

import org.junit.Test;


public class SequenceTest extends TestCase{

	public void testIterator() {
		Sequence s = new Sequence(10);
		s.add(0);
		s.add(3);
		s.add(5);
		s.add(9);
		Iterator<Integer> b = s.iterator();
		
		assertTrue(b.next() == 0);
		assertTrue(b.hasNext());
		assertTrue(b.next() == 3);
		assertTrue(b.hasNext());
		assertTrue(b.next() == 5);
		assertTrue(b.hasNext());
		assertTrue(b.next() == 9);
		assertFalse(b.hasNext());
		
	}
	
	public void testAdd() {
		Sequence a = new Sequence(5);
		a.add(3);
		a.add("Hello");
		a.add(1);
		a.add("World");
		assertEquals(a.size(), 4);
	}

	public void testInsert() {
		Sequence a = new Sequence(10);
		a.add(3);
		a.add("Doop");
		a.add(5);
		a.add("Moop");
		a.insert(10, 2);
		assertEquals(a.elementAt(0), 3);
		assertEquals(a.elementAt(1), "Doop");
		assertEquals(a.elementAt(2), 10);
		assertEquals(a.elementAt(3), 5);
		assertEquals(a.elementAt(4), "Moop");
		
	}

	public void testIsEmpty() {
		Sequence a = new Sequence(10);
		assertTrue(a.isEmpty());
		a.add(1);
		assertFalse(a.isEmpty());
		a.remove(0);
		assertTrue(a.isEmpty());
		a.add("Wahoo");
		assertFalse(a.isEmpty());
	}

	public void testSize() {
		Sequence a = new Sequence(3);
		assertEquals(a.size(), 0);
		a.add(1);
		assertEquals(a.size(), 1);
		a.add("HUEHUEHUE");
		assertEquals(a.size(), 2);
		a.add(3);
		assertEquals(a.size(), 3);
	}
	
	public void testRemove() {
		Sequence a = new Sequence(10);
		a.add(3);
		a.add(-7);
		a.add("Seven");
		a.add(-11);
		a.add(0);
		a.add(6);
		a.add("BUttsS");
		a.remove(2);
		assertEquals(a.elementAt(1), -7);
		assertEquals(a.elementAt(2), -11);
		assertEquals(a.elementAt(3), 0);
		assertEquals(a.elementAt(4), 6);
	}

	public void testElementAt() {
		Sequence a = new Sequence(3);
		a.add(2);
		assertEquals(a.elementAt(0), 2);
		a.add("wahoo");
		assertEquals(a.elementAt(1), "wahoo");
		a.add(1);
		assertEquals(a.elementAt(2), 1);
	}

	public void testToString() {
		Sequence a = new Sequence(5);
		a.add(3);
		a.add("hello");
		a.add(1);
		String test = a.toString();
		assertEquals(test, "3 hello 1");
	}
	
	public void testContains() {
		Sequence a = new Sequence(10);
		a.add(3);
		a.add(-7);
		a.add("");
		a.add(-11);
		a.add("zero");
		a.add(6);
		a.add(9);
		a.remove(2);
		assertFalse(a.contains(2));
		assertTrue(a.contains(-7));
		assertTrue(a.contains(-11));
		assertTrue(a.contains("zero"));
	}
}
