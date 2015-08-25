import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;


public class MyHashMapTest {

	@Test
	public void testSize() {		
		MyHashMap test1 = new MyHashMap();
		test1.put("Kelsey", 123);
		assertEquals(test1.size(), 1);
		test1.put("Anabel", 321);
		assertEquals(test1.size(), 2);
		test1.put("Bababe", 345);
		assertEquals(test1.size(), 3);
		test1.put("Hanners", 654);
		assertEquals(test1.size(), 4);
	}

	@Test
	public void testContainsValue() {
		MyHashMap test1 = new MyHashMap();
		test1.put("Kelsey", 123);
		test1.put("Anabel", 321);
		test1.put("Bababe", 345);
		test1.put("Hanners", 654);
		
		assertTrue(test1.containsValue(123));
		assertTrue(test1.containsValue(321));
		assertTrue(test1.containsValue(345));
		assertTrue(test1.containsValue(654));
		assertFalse(test1.containsValue(1212312));
	}

	@Test
	public void testPut() {
		MyHashMap test1 = new MyHashMap();
		test1.put("Kelsey", 123);
		test1.put("Anabel", 321);
		test1.put("Bababe", 345);
		test1.put("Hanners", 654);
		int hi = (int) test1.put("Kelsey", 678);
		test1.put("BAnana", 786);
		assertEquals(hi, 123);
	}

	@Test
	public void testExpand() {
		MyHashMap test1 = new MyHashMap(5);
		test1.put("Kelsey", 123);
		test1.put("Anabel", 321);
		test1.put("Bababe", 345);
		test1.put("Hanners", 654);
		
		assertTrue(test1.containsKey("Kelsey"));
		assertTrue(test1.containsKey("Anabel"));
		assertTrue(test1.containsKey("Bababe"));
		assertTrue(test1.containsKey("Hanners"));
		
		assertEquals(test1.get("Kelsey"), 123);
		assertEquals(test1.get("Anabel"), 321);
		assertEquals(test1.get("Bababe"), 345);
		assertEquals(test1.get("Hanners"), 654);
	}
	
	@Test
	public void testRemove() {
		MyHashMap test1 = new MyHashMap(5);
		test1.put("Kelsey", 123);
		test1.put("Anabel", 321);
		test1.put("Bababe", 345);
		test1.put("Hanners", 654);
		assertEquals(test1.remove("Hanners"), 654);
		assertFalse(test1.containsKey("Hanners"));
	}
	
	@Test
	public void testIterator() {
		MyHashMap test1 = new MyHashMap(5);
		test1.put("Kelsey", 123);
		test1.put("Anabel", 321);
		test1.put("Bababe", 345);
		test1.put("Hanners", 654);
		Iterator iter = test1.iterator();
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		assertFalse(iter.hasNext());
	}

}
