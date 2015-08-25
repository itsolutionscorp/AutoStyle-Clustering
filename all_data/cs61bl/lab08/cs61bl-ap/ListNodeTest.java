import junit.framework.TestCase;


public class ListNodeTest extends TestCase {
	public void testGet() {
		 ListNode d = new ListNode(7);
		 ListNode c = new ListNode(44,d);
		 ListNode b = new ListNode(23,c);
		 ListNode a = new ListNode(12,b);
		 assertEquals(23, a.get(1));
		 assertEquals(12, a.get(0));
		 try {
				a.get(6);
		 } catch (IllegalArgumentException e) {
					System.err.println ("Invalid Position.");
		 }
	}
	
	
}
