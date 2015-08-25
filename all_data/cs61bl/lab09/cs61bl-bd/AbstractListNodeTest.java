import junit.framework.TestCase;


public class AbstractListNodeTest extends TestCase {

	public void testSize() {
		NonemptyListNode lst4 = new NonemptyListNode("is");
		NonemptyListNode lst3 = new NonemptyListNode("name", lst4);
		NonemptyListNode lst2 = new NonemptyListNode("my", lst3);
		NonemptyListNode lst1 = new NonemptyListNode("hello", lst2);
		assertEquals(4, lst1.size());
		
		EmptyListNode lst5 = new EmptyListNode();
		assertEquals(0, lst5.size());
	}
	
	public void testGet() {
		NonemptyListNode n2 = new NonemptyListNode("world");
		NonemptyListNode n1 = new NonemptyListNode("hello", n2);
		assertEquals("hello", n1.get(0));
		assertEquals("world", n1.get(1));
		try {
			n1.get(2);
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	public void testToString() {
		NonemptyListNode n2 = new NonemptyListNode("world");
		NonemptyListNode n1 = new NonemptyListNode("hello", n2);
		assertEquals("( hello world )", n1.toString());
	}
	
	public void testEquals() {
		NonemptyListNode n1 = new NonemptyListNode("nancy", new NonemptyListNode("chang"));
		NonemptyListNode n3 = new NonemptyListNode("chang");
		NonemptyListNode n2 = new NonemptyListNode("nancy", n3);
		assertEquals(true, n1.equals(n2));
	}
	
}
