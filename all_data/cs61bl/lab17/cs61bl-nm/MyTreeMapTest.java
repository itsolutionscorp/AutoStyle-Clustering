import static org.junit.Assert.*;

import org.junit.Test;


public class MyTreeMapTest {

@Test
	public void testPutGetContains() {
		MyTreeMap<Integer, String> myMap1 = new MyTreeMap<Integer, String>();
		myMap1.put(5, "Shantanu");
		myMap1.put(6,  "Kyle");
		myMap1.put(4,"Terry");
		myMap1.put(3, "Quan");
		myMap1.put(16, "Toot");
		myMap1.put(17, "Gregory");
		myMap1.put(29, "Jindle");
		myMap1.put(20, "Bobby");
		myMap1.put(15, "Tittyboy");
		
		assertEquals(myMap1.size(), 9);
		assertEquals(myMap1.get(5), "Shantanu");
		assertEquals(myMap1.get(6), "Kyle");
		assertEquals(myMap1.get(4), "Terry");
		assertEquals(myMap1.get(3), "Quan");
		assertEquals(myMap1.get(16), "Toot");
		assertEquals(myMap1.get(17), "Gregory");
		assertEquals(myMap1.get(29), "Jindle");
		assertEquals(myMap1.get(20), "Bobby");
		assertEquals(myMap1.get(15), "Tittyboy");
		
		assertTrue(myMap1.containsKey(5));
		assertTrue(myMap1.containsKey(16));
		assertTrue(myMap1.containsKey(4));
		assertTrue(myMap1.containsKey(6));
		assertTrue(myMap1.containsKey(3));
		assertTrue(myMap1.containsKey(17));
		assertTrue(myMap1.containsKey(29));
		assertTrue(myMap1.containsKey(20));
		assertTrue(myMap1.containsKey(15));

	}
@Test
	public void testRemove(){
		MyTreeMap<Integer, String> myMap1 = new MyTreeMap<Integer, String>();
		myMap1.put(5, "Shantanu");
		myMap1.put(6,  "Kyle");
		myMap1.put(4,"Terry");
		myMap1.put(3, "Quan");
		myMap1.put(16, "Toot");
		myMap1.put(17, "Gregory");
		myMap1.put(29, "Jindle");
		myMap1.put(20, "Bobby");
		myMap1.put(15, "Tittyboy");
		
		assertEquals(myMap1.remove(20), "Bobby");
		assertEquals(myMap1.remove(15), "Tittyboy");
		assertNull(myMap1.remove(15));
		assertNull(myMap1.remove(22000));
		assertEquals(myMap1.remove(6), "Kyle");
		assertEquals(myMap1.remove(5), "Shantanu");
		
		assertEquals(myMap1.size(), 5);
		
	}

}