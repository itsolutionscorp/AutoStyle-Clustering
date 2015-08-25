import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void testConstructor() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode a = new NonemptyListNode("a", empty);
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode( "c", b);
		AbstractListNode abn = c;
		assertTrue (abn.item().equals("c"));
	}
	
	@Test
	public void testSize() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode a = new NonemptyListNode("a", empty);
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode( "c", b);
		AbstractListNode abn = c;
		assertTrue(abn.size() == 3);
		assertTrue(abn.next().size() == 2);
		assertTrue (abn.next().next().size() == 1);
		assertTrue (empty.size() == 0);
	}
	
	@Test
	public void testGet() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode a = new NonemptyListNode("a", empty);
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode( "c", b);
		AbstractListNode abn = c;
		assertTrue (abn.get(0).size() == 3);
		assertTrue (abn.get(1).size() == 2);
		assertTrue (abn.get(2).size() == 1);
	}

	@Test
	public void testToString() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode a = new NonemptyListNode("a", empty);
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode( "c", b);
		AbstractListNode abn = c;
		assertTrue(abn.toString().equals("( c b a )"));
		
	}
	
	@Test
	public void testEquals() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode a = new NonemptyListNode("a", empty);
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode( "c", b);
		AbstractListNode abn = c;
		
		EmptyListNode empty1 = new EmptyListNode();
		NonemptyListNode a1 = new NonemptyListNode("a", empty1);
		NonemptyListNode b1 = new NonemptyListNode("b", a1);
		NonemptyListNode c1 = new NonemptyListNode( "c", b1);
		AbstractListNode ALLISON = c1;
		assertTrue(ALLISON.equals(abn));
		
		EmptyListNode empty2 = new EmptyListNode();
		assertFalse(ALLISON.equals(empty2));
	}
	
}
