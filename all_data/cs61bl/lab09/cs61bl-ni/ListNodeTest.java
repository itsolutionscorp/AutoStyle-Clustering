import junit.framework.TestCase;


public class ListNodeTest extends TestCase {
	public void testGet() {
		ListNode test1 = new ListNode(3, null);
		ListNode test2 = new ListNode(2, test1);
		ListNode test3 = new ListNode(1, test2);
		assertEquals(1, test3.get(0));
		assertEquals(2, test3.get(1));
		assertEquals(3, test3.get(2));
	}
}
