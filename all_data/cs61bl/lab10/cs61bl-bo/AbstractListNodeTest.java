import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void testSize() {
		EmptyListNode tail = new EmptyListNode();
		NonemptyListNode head = new NonemptyListNode(5, tail);
		for (int i = 4; i > 0; i--) {
			head = new NonemptyListNode(i, head);
		}
		assertEquals(head.size(), 5);
	}
	
	@Test
	public void testToString() {
		EmptyListNode tail = new EmptyListNode();
		NonemptyListNode head = new NonemptyListNode(5, tail);
		for (int i = 4; i > 0; i--) {
			head = new NonemptyListNode(i, head);
		}
		assertEquals(head.toString(true), "( 1 2 3 4 5 )");
	}
	
	@Test
	public void testEquals() {
		EmptyListNode tail = new EmptyListNode();
		NonemptyListNode head = new NonemptyListNode(5, tail);
		for (int i = 4; i > 0; i--) {
			head = new NonemptyListNode(i, head);
		}
		EmptyListNode tail2 = new EmptyListNode();
		NonemptyListNode head2 = new NonemptyListNode(5, tail2);
		for (int i = 4; i > 0; i--) {
			head2 = new NonemptyListNode(i, head2);
		}
		assertTrue(head.equals(head2));
		head2 = new NonemptyListNode(0,head2);
		assertFalse(head.equals(head2));
	}
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
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.append(new NonemptyListNode("a", new EmptyListNode()));
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.append(new NonemptyListNode("b", new EmptyListNode()));
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	@Test
	public void testReverse() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( b a )", l3.reverse().toString());
	}
	
	@Test
	public void testStraightforward() {

	    AbstractListNode empty1 = new EmptyListNode();
	    AbstractListNode empty2 = new EmptyListNode();

	    empty1 = empty1.appendInPlace (empty2);

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
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("e");
	    AbstractListNode l4 = new EmptyListNode();
	    AbstractListNode l5 = l4.add("c");
	    AbstractListNode l6 = l5.add("d");
	    AbstractListNode l7 = l6.add("z");
	    
	    assertEquals("( a c d e z )", AbstractListNode.merge(l3, l7).toString());
	}
}
