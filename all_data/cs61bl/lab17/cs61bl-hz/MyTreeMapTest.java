import static org.junit.Assert.*;

import org.junit.Test;

public class MyTreeMapTest {

	@Test
	public void testContainsKey() {
		MyTreeMap<String, String> m = new MyTreeMap<String, String>();
		m.put("c", "C");
		m.put("a", "A");
		m.put("b", "B");
		m.put("e", "E");
		m.put("d", "D");
		assertTrue(m.containsKey("a"));
		assertTrue(m.containsKey("b"));
		assertTrue(m.containsKey("c"));
		assertTrue(m.containsKey("d"));
		assertTrue(m.containsKey("e"));
		assertFalse(m.contains("f"));
	}
	
	@Test
	public void testContains() {
		MyTreeMap<String, String> m = new MyTreeMap<String, String>();
		m.put("c", "C");
		m.put("a", "A");
		m.put("b", "B");
		m.put("e", "E");
		m.put("d", "D");
		assertTrue(m.contains("A"));
		assertTrue(m.contains("B"));
		assertTrue(m.contains("C"));
		assertTrue(m.contains("D"));
		assertTrue(m.contains("E"));
	}
	
	@Test
	public void testGet() {
		MyTreeMap<String, String> m = new MyTreeMap<String, String>();
		m.put("c", "C");
		m.put("a", "A");
		m.put("b", "B");
		m.put("e", "E");
		m.put("d", "D");
		assertTrue(m.get("a").equals("A"));
		assertTrue(m.get("b").equals("B"));
		assertTrue(m.get("c").equals("C"));
		assertTrue(m.get("d").equals("D"));
		assertTrue(m.get("e").equals("E"));
		assertTrue(m.get("f") == null);
		assertTrue(m.put("c", "F").equals("C"));
	}
	
	@Test
	public void testRemove() {
		MyTreeMap<String, String> m = new MyTreeMap<String, String>();
		m.put("c", "C");
		m.put("a", "A");
		m.put("b", "B");
		m.put("e", "E");
		m.put("d", "D");
		assertTrue(m.remove("c").equals("C"));
		assertTrue(m.remove("a").equals("A"));
		assertTrue(m.remove("b").equals("B"));
		assertTrue(m.remove("e").equals("E"));
		assertTrue(m.remove("d").equals("D"));
		assertFalse(m.containsKey("a"));
		assertFalse(m.containsKey("b"));
		assertFalse(m.containsKey("c"));
		assertFalse(m.containsKey("d"));
		assertFalse(m.containsKey("e"));
	}

}
