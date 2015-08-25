import static org.junit.Assert.*;

import org.junit.Test;


public class ListNodeTest {

	@Test
	public void testGet() {
		ListNode secondNode = new ListNode("Crazy");
		ListNode firstNode = new ListNode("Power", secondNode);
		System.out.println(firstNode.get(1));
		assertTrue (firstNode.get(1) == "Crazy");
	}

}
