import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Iterator;
public class SequenceTest {

	@Test
	public void testaddSequence() {
		Sequence<Integer> s = new Sequence<Integer>(10);
		s.add(5); //tests add too!
		s.add(4);
		s.add(3);
		s.add(2);
		s.add(1);
		s.add(0);
		assertTrue(s.elementAt(0) == 5);
		assertTrue(s.elementAt(1) == 4);
		assertTrue(s.elementAt(2) == 3);
		assertTrue(s.elementAt(3) == 2);
		assertTrue(s.elementAt(4) == 1);
		assertTrue(s.elementAt(5) == 0);
		Sequence<String> t = new Sequence<String>(10);
		t.add("fuck"); //tests add too!
		t.add("this");
		t.add("CS is");
		t.add("so");
		t.add("fucking");
		t.add("hard");
		assertTrue(t.elementAt(0) == "fuck");
		assertTrue(t.elementAt(1) == "this");
		assertTrue(t.elementAt(2) == "CS is");
		assertTrue(t.elementAt(3) == "so");
		assertTrue(t.elementAt(4) == "fucking");
		assertTrue(t.elementAt(5) == "hard");
	}
	
	@Test
	public void testtoString() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		t.add(5); //tests add too!
		t.add(4);
		t.add(3);
		t.add(2);
		t.add(1);
		t.add(0);
		assertTrue(t.toString().equals("5 4 3 2 1 0"));
		Sequence<String> s = new Sequence<String>(10);
		s.add("fuck"); //tests add too!
		s.add("this");
		s.add("CS is");
		s.add("so");
		s.add("fucking");
		s.add("hard");
		assertTrue(s.toString().equals("fuck this CS is so fucking hard"));
	}
	
	@Test
	public void testRemove() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		t.add(0); //tests add too!
		t.add(1);
		t.add(2);
		t.add(3);
		t.add(4);
		t.add(5);
		t.remove(3);
		assertTrue(t.toString().equals("0 1 2 4 5"));
		t.remove(4);
		assertTrue(t.toString().equals("0 1 2 4"));
		t.remove(0);
		assertTrue(t.toString().equals("1 2 4"));
		Sequence<String> s = new Sequence<String>(10);
		s.add("fuck"); //tests add too!
		s.add("this");
		s.add("CS is");
		s.add("so");
		s.add("fucking");
		s.add("hard");
		assertTrue(s.toString().equals("fuck this CS is so fucking hard"));
		s.remove(3);
		assertTrue(s.toString().equals("fuck this CS is fucking hard"));
		s.remove(4);
		assertTrue(s.toString().equals("fuck this CS is fucking"));
		s.remove(0);
		assertTrue(s.toString().equals("this CS is fucking"));
	}
	
	public void testInsert() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		t.add(5); //tests add too!
		t.add(4);
		t.add(3);
		assertTrue(t.toString().equals("5 4 3"));
		t.insert(0, 0);
		assertTrue(t.toString().equals("0 5 4 3"));
		t.insert(2, 2);
		assertTrue(t.toString().equals("0 5 2 4 3"));
		t.insert(4, 7);
		assertTrue(t.toString().equals("0 5 2 4 7 3"));
		Sequence<String> s = new Sequence<String>(10);
		s.add("Hello"); //tests add too!
		s.add("World");
		s.add("!");
		assertTrue(s.toString().equals("Hello World !"));
		s.insert(0, "LOL");
		assertTrue(s.toString().equals("LOL Hello World !"));
		s.insert(2, "joyous");
		assertTrue(s.toString().equals("LOL Hello joyous World !"));
		s.insert(4, "ohai");
		assertTrue(s.toString().equals("LOL Hello joyous World ohai !"));
	}
	
	@Test
	public void testisEmpty() {
		Sequence<Integer> t = new Sequence<Integer>(3);
		assertTrue (t.isEmpty());
		t.add(3); //tests add too!
		assertFalse(t.isEmpty());
		Sequence<String> s = new Sequence<String>(3);
		assertTrue (s.isEmpty());
		s.add("Hi"); //tests add too!
		assertFalse(s.isEmpty());
		}
	
	@Test
	public void testSize() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		assertTrue (t.size() == 0);
		t.add(5); //tests add too!
		assertTrue (t.size() == 1);
		t.add(5);
		t.add(5);
		t.add(5);
		t.add(5);
		assertTrue (t.size() == 5);
		Sequence<String> s = new Sequence<String>(10);
		assertTrue (s.size() == 0);
		s.add("b"); //tests add too!
		assertTrue (s.size() == 1);
		s.add("b");
		s.add("b");
		s.add("b");
		s.add("b");
		assertTrue (s.size() == 5);
	}
	
	@Test
	public void testContains() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		t.add(5); //tests add too!
		t.add(4);
		t.add(3);
		assertTrue(t.contains(3) && t.contains(4) && t.contains(5));
		assertFalse(t.contains(0));
		Sequence<String> s = new Sequence<String>(10);
		s.add("a"); //tests add too!
		s.add("b");
		s.add("c");
		assertTrue(s.contains("a") && s.contains("b") && s.contains("c"));
		assertFalse(s.contains("z"));
		}
	@Test
	public void testIterator() {
		Sequence<Integer> t = new Sequence<Integer>(10);
		t.add(5); //tests add too!
		t.add(4);
		t.add(3);
		Iterator<Integer> i = t.iterator();
		int a = i.next();
		assertTrue(a == 5);
		assertTrue(i.hasNext());
		int b = i.next();
		assertTrue(b == 4);
		assertTrue(i.hasNext());
		int c = i.next();
		assertTrue(c == 3);
		assertFalse(i.hasNext());
		Sequence<String> s = new Sequence<String>(10);
		s.add("a"); //tests add too!
		s.add("b");
		s.add("c");
		Iterator<String> f = s.iterator();
		String A = f.next();
		assertTrue(A == "a");
		assertTrue(f.hasNext());
		String B = f.next();
		assertTrue(B == "b");
		assertTrue(f.hasNext());
		String C = f.next();
		assertTrue(C == "c");
		assertFalse(f.hasNext());
		
	}
	}
