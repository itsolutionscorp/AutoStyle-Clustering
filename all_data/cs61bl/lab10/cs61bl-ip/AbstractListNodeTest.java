import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractListNodeTest {
	@Test
	public void testAdd() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	@Test
	public void testAppend() {
	    AbstractListNode n = new EmptyListNode();
	    AbstractListNode c = new NonemptyListNode("3", n);
	    AbstractListNode b = new NonemptyListNode("2", c);
	    AbstractListNode a = new NonemptyListNode("1", b);
	    
	    AbstractListNode f = new NonemptyListNode("6", n);
	    AbstractListNode e = new NonemptyListNode("5", f);
	    AbstractListNode d = new NonemptyListNode("4", e);
	    
	    AbstractListNode test = a.append(d);
	    assertEquals("( 1 2 3 4 5 6 )", test.toString());
	}
	
	@Test
	public void testStraightforward() {

	    AbstractListNode empty1 = new EmptyListNode();
	    AbstractListNode empty2 = new EmptyListNode();

	    empty1 = empty1.appendInPlace(empty2);
	    
	    assertEquals ("( )", empty1.toString());
	    assertEquals ("( )", empty2.toString());

	    AbstractListNode a = new NonemptyListNode("a");

	    a = a.appendInPlace(empty1);

	    assertEquals ("( a )", a.toString());
	    assertEquals ("( )", empty1.toString());

	    AbstractListNode b = new NonemptyListNode("b");
	    AbstractListNode c = new NonemptyListNode("c");

	    b = b.appendInPlace(c);

	    assertEquals ("( b c )", b.toString());
	    assertEquals ("( c )", c.toString());
	}
	
	@Test
	public void testMerge() {
	    AbstractListNode n = new EmptyListNode();
	    AbstractListNode c = new NonemptyListNode("5", n);
	    AbstractListNode b = new NonemptyListNode("3", c);
	    AbstractListNode a = new NonemptyListNode("1", b);
	    
	    AbstractListNode f = new NonemptyListNode("6", n);
	    AbstractListNode e = new NonemptyListNode("4", f);
	    AbstractListNode d = new NonemptyListNode("2", e);
	    
	    AbstractListNode test = AbstractListNode.merge(a, d);
	    assertEquals("( 1 2 3 4 5 6 )", test.toString());
	}
}
