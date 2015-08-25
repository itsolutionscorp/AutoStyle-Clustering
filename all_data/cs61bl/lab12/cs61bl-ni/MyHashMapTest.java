import java.util.Iterator;

import junit.framework.TestCase;


public class MyHashMapTest extends TestCase {
	public void testAdd() {
		MyHashMap<String, Integer> test1 = new MyHashMap<String, Integer>(3);
		assertFalse(test1.containsKey("a"));
		assertEquals(null, test1.put("a", 5));
		assertTrue(test1.containsKey("a"));
		assertEquals((Integer) 5, test1.get("a"));
		assertEquals((Integer) 5, test1.put("a", 1));
		assertEquals((Integer) 1, test1.get("a"));
		assertTrue(test1.containsKey("a"));
		assertEquals(null, test1.get("b"));
		assertEquals(null, test1.remove("b"));
		assertEquals((Integer) 1, test1.remove("a"));
		assertFalse(test1.containsKey("a"));
		assertEquals(null, test1.put("Z", 23));
		assertEquals(null, test1.put("A", 12));
		assertEquals(null, test1.put("a", 12));
		assertEquals(null, test1.put("zero", 0));
		assertEquals(6, test1.capacity());
		assertEquals(null, test1.put("one", 1));
		assertEquals(12, test1.capacity());
		assertTrue(test1.containsValue(12));
		assertTrue(test1.containsValue(23));
		assertTrue(test1.containsValue(0));
		assertTrue(test1.containsValue(1));
		assertFalse(test1.containsValue(2));
		
		MyHashMap<String, Integer> test2 = new MyHashMap<String, Integer>(10);
		test2.put("c", 3);
		test2.put("1", 50);
		test2.put("Z", 40);
		test2.put("std", 2);
		assertEquals((Integer) 3, test2.get("c"));
		assertEquals((Integer) 50, test2.get("1"));
		assertEquals((Integer) 40, test2.get("Z"));
		assertEquals((Integer) 2, test2.get("std"));
		assertEquals(4, test2.size());
		assertTrue(test2.containsValue(40));
		assertTrue(test2.containsValue(2));
		assertEquals(10, test2.capacity());
	}
	
	public void testIter() {
		MyHashMap<String, Integer> test1 = new MyHashMap<String, Integer>(3);
		Iterator<String> iter1 = test1.iterator();
		assertFalse(iter1.hasNext());
		assertEquals(null, iter1.next());
		
		MyHashMap<String, Integer> test2 = new MyHashMap<String, Integer>(3);
		test2.put("a", 3);
		test2.put("1", 50);
		test2.put("b", 40);
		Iterator<String> iter2 = test2.iterator();
		assertTrue(iter2.hasNext());
		assertEquals("a", iter2.next());
		assertTrue(iter2.hasNext());
		assertEquals("1", iter2.next());
		assertTrue(iter2.hasNext());
		assertEquals("b", iter2.next());
		assertFalse(iter2.hasNext());
		assertEquals(null, iter2.next());
	}
}
