import junit.framework.TestCase;


public class ListNodeTest extends TestCase {

	public void testGet() {
		ListNode n2 = new ListNode("world", null);
		ListNode n1 = new ListNode("hello", n2);
		assertEquals("hello", n1.get(0));
		assertEquals("world", n1.get(1));
		try {
			n1.get(2);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			
		}
	}
	
}
