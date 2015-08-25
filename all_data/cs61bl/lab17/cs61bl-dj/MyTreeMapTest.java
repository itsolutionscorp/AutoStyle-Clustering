import junit.framework.TestCase;


public class MyTreeMapTest extends TestCase {

	public void testMyTreeMap() {
		MyTreeMap m = new MyTreeMap();
		m.put("hey", 1);
		m.put("yo", 1);
		m.put("tiger", 3);
		m.put("apple", 5);
		assertTrue(m.size() == 4);
		assertTrue(m.containsKey("tiger"));
		
		m.remove("apple");
		assertTrue(!m.containsKey("apple"));
		
		assertTrue(m.size() == 3);
		m.remove("tiger");
		assertTrue(!m.containsKey("tiger"));
		
		
	}	
}
