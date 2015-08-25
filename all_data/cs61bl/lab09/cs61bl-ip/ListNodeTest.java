import static org.junit.Assert.*;

import org.junit.Test;

public class ListNodeTest {

	@Test
	public void testGet() {
		ListNode c = new ListNode("3", null);
		ListNode b = new ListNode("2", c);
		ListNode a = new ListNode(1, b);
		
		assertTrue(a.get(0).equals(1));
		assertTrue(a.get(1).equals("2"));
		assertTrue(a.get(2).equals("3"));
	}
}
