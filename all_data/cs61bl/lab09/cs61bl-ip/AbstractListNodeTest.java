import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractListNodeTest {

	@Test
	public void testSize() {
		NonemptyListNode d = new NonemptyListNode("4", null);
		NonemptyListNode c = new NonemptyListNode("3", d);
		NonemptyListNode b = new NonemptyListNode("2", c);
		NonemptyListNode a = new NonemptyListNode("1", b);
		
		assertEquals(a.size(), 4);
	}

	@Test
	public void testGet() {
		NonemptyListNode d = new NonemptyListNode("4", null);
		NonemptyListNode c = new NonemptyListNode(3, d);
		NonemptyListNode b = new NonemptyListNode("2", c);
		NonemptyListNode a = new NonemptyListNode(1, b);
		
		assertTrue(a.get(0).equals(1));
		assertTrue(a.get(1).equals("2"));
		assertTrue(a.get(2).equals(3));
		assertTrue(a.get(3).equals("4"));
	}
	
	@Test
	public void testToString() {
		NonemptyListNode d = new NonemptyListNode("4", null);
		NonemptyListNode c = new NonemptyListNode("3", d);
		NonemptyListNode b = new NonemptyListNode("2", c);
		NonemptyListNode a = new NonemptyListNode("1", b);
		
		assertTrue(a.toString().equals("( 1 2 3 4 )"));
	}
	
	@Test
	public void testEquals() {
		NonemptyListNode d = new NonemptyListNode("4", null);
		NonemptyListNode c = new NonemptyListNode("3", d);
		NonemptyListNode b = new NonemptyListNode("2", c);
		NonemptyListNode a = new NonemptyListNode("1", b);

		NonemptyListNode h = new NonemptyListNode("4", null);
		NonemptyListNode g = new NonemptyListNode("3", d);
		NonemptyListNode f = new NonemptyListNode("2", c);
		NonemptyListNode e = new NonemptyListNode("1", b);
		
		assertTrue(a.equals(e));
	}
}
