import static org.junit.Assert.*;

import org.junit.Test;


public class ListNodeTest {

	@Test
	public void testGet() {
		ListNode a = new ListNode("a");
		ListNode b = new ListNode("b", a);
		ListNode c = new ListNode("c", b);
		ListNode d = new ListNode("d", c);
		assertTrue(d.get(0) == "d");
		assertTrue(d.get(1) == "c");
		assertTrue(d.get(2) == "b");
		assertTrue(d.get(3) == "a");
		try {
			d.get(4);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}

}
