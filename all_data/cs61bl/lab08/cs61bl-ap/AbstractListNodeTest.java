import junit.framework.TestCase;


public class AbstractListNodeTest extends TestCase {
	public void testSize() {
		EmptyListNode e = new EmptyListNode();
		NonemptyListNode d = new NonemptyListNode(7, e);
		NonemptyListNode c = new NonemptyListNode(44,d);
		NonemptyListNode b = new NonemptyListNode(23,c);
		NonemptyListNode a = new NonemptyListNode(12,b);
		assertEquals(4, a.size());
		assertEquals(2, c.size());
		assertFalse(78==d.size());
		
		EmptyListNode f = new EmptyListNode();
		assertEquals(0, f.size());

	}
	
	public void testGet() {
		 EmptyListNode d = new EmptyListNode();
		 NonemptyListNode c = new NonemptyListNode(44,d);
		 NonemptyListNode b = new NonemptyListNode(23,c);
		 NonemptyListNode a = new NonemptyListNode(12,b);
		 assertEquals(23, a.get(1));
		 assertEquals(12, a.get(0));
		 try {
				a.get(6);
		 } catch (IllegalArgumentException e) {
					System.err.println ("Invalid Position.");
		 }
	}
	
	public void testToString() {
		 EmptyListNode d = new EmptyListNode();
		 NonemptyListNode c = new NonemptyListNode(44,d);
		 NonemptyListNode b = new NonemptyListNode(23,c);
		 NonemptyListNode a = new NonemptyListNode(12,b);
		 assertEquals("( 12 23 44 )", a.toString());
		 assertEquals("( 44 )", c.toString());
		 assertEquals("()", d.toString());
	}
	public void testEquals() {
		 EmptyListNode d = new EmptyListNode();
		 NonemptyListNode c = new NonemptyListNode(44,d);
		 NonemptyListNode b = new NonemptyListNode(23,c);
		 NonemptyListNode a = new NonemptyListNode(12,b);
		 EmptyListNode g = new EmptyListNode();
		 NonemptyListNode f = new NonemptyListNode(44,g);
		 NonemptyListNode e = new NonemptyListNode(23,f);
		 NonemptyListNode x = new NonemptyListNode(12,e);
		 
		 
		 assertTrue(a.equals(x));
		 assertFalse(f.equals(e));
		 assertTrue(d.equals(g));
	}
}
