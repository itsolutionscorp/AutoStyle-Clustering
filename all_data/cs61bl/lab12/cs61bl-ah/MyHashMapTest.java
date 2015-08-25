import java.util.Iterator;

import junit.framework.TestCase;

public class MyHashMapTest extends TestCase {

	public void testPut() {
		MyHashMap capybara = new MyHashMap(2);
		assertTrue(capybara.capacity() == 2);
		capybara.put("a", new Integer(1));
		capybara.put("b", new Integer(2));
		assertTrue(capybara.capacity() == 4);
		assertTrue(capybara.put("a", new Integer(11)).equals(new Integer(1)));
		capybara.put("c", new Integer(2));
		assertTrue(capybara.capacity() == 8);
	}

	public void testGet() {
		MyHashMap capybara = new MyHashMap(2);
		capybara.put("a", new Integer(1));
		capybara.put("b", new Integer(2));
		capybara.put("c", new Integer(2));
		assertEquals(capybara.get("a"), new Integer(1));
		assertNull(capybara.get("wug"));
	}

	public void testRemove() {
		MyHashMap capybara = new MyHashMap(2);
		capybara.put("a", new Integer(1));
		capybara.put("b", new Integer(2));
		capybara.put("c", new Integer(2));
		assertEquals(capybara.remove("a"), new Integer(1));
		assertNull(capybara.get("a"));
		assertNull(capybara.remove("a"));
	}

	public void testIterator() {
		MyHashMap capybara = new MyHashMap(2);
		capybara.put("a", new Integer(1));
		capybara.put("b", new Integer(2));
		capybara.put("c", new Integer(2));
		Iterator i = capybara.iterator();
		assertTrue(i.hasNext());
		assertEquals(i.next(), "a");
		i.next();
		assertEquals(i.next(), "c");
		assertFalse(i.hasNext());

	}

//	public void testHashing() {
//		MyHashMap capybara = new MyHashMap(35, 0.99);
//		// 10 100 32 45 58 126 1 29 200 400 15
//		int[] x = {10,100,32,45,58,126,1,29,200,400,15};
//		for(int q = 0;q<x.length;q++){
//			capybara.put(new Integer(x[q]), "noot noot");
//		}
//		boolean hits=true;
//		for(int q = 0;q<capybara.capacity();q++){
//			hits=capybara.blah[q].size()<4;
//		}
//		assertTrue(hits);
//	}
}
