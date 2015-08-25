import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void testSize() {
		AbstractListNode node2 = new NonemptyListNode(new String("two"));
		AbstractListNode node1 = new NonemptyListNode(new String("one"), node2);
		AbstractListNode node0 = new NonemptyListNode(new String("zero"), node1);
		
		assertTrue(node0.size() == 3);
		assertTrue(node1.size() == 2);
		assertTrue(node2.size() == 1);
		assertTrue(node2.next().size() == 0);
	}
	
	@Test 
	public void testGet() {
		AbstractListNode node2 = new NonemptyListNode(new String("two"));
		AbstractListNode node1 = new NonemptyListNode(new String("one"), node2);
		AbstractListNode node0 = new NonemptyListNode(new String("zero"), node1);
		
		assertTrue(node0.get(0).toString().equals("zero"));
		assertTrue(node0.get(1).toString().equals("one"));
		assertTrue(node0.get(2).toString().equals("two"));
	}
	
	@Test 
	public void testToString1() {
		AbstractListNode node2 = new NonemptyListNode(new String("two"));
		AbstractListNode node1 = new NonemptyListNode(new String("one"), node2);
		AbstractListNode node0 = new NonemptyListNode(new String("zero"), node1);
		
		assertTrue(node0.toString().equals("( zero one two )"));
	}

	@Test 
	public void testToString2() {
		AbstractListNode node2 = new NonemptyListNode(new Integer(2));
		AbstractListNode node1 = new NonemptyListNode(new Integer(1), node2);
		AbstractListNode node0 = new NonemptyListNode(new Integer(0), node1);
		
		assertTrue(node0.toString().equals("( 0 1 2 )"));
	}
	
	@Test 
	public void testToString3() {
		AbstractListNode node0 = new EmptyListNode();
		
		assertTrue(node0.toString().equals("( )"));
	}
	
	@Test 
	public void testEquals() {
		AbstractListNode n2 = new NonemptyListNode(new Integer(2));
		AbstractListNode n1 = new NonemptyListNode(new Integer(1), n2);
		AbstractListNode n0 = new NonemptyListNode(new Integer(0), n1);
		
		AbstractListNode m2 = new NonemptyListNode(new Integer(2));
		AbstractListNode m1 = new NonemptyListNode(new Integer(1), m2);
		AbstractListNode m0 = new NonemptyListNode(new Integer(0), m1);
		
		assertTrue(n0.toString().equals(m0.toString()));
		assertTrue(n0.equals(m0));
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
	public void testAppend1() {
		AbstractListNode l1 = new EmptyListNode();
		AbstractListNode l2 = new NonemptyListNode("a");
		AbstractListNode l3 = l1.append(l2);
		assertEquals("( a )", l3.toString());
		AbstractListNode l4 = new NonemptyListNode("b", new NonemptyListNode("c"));
		AbstractListNode l5 = l3.append(l4);
		assertEquals("( a b c )", l5.toString());
	}
	
	@Test
	public void testAppend2() {
		AbstractListNode l1 = new EmptyListNode();
		AbstractListNode l2 = new NonemptyListNode("a");
		AbstractListNode l3 = l1.append(l2);
		AbstractListNode l4 = l2.append(l1);
		AbstractListNode l5 = new NonemptyListNode("b");
		AbstractListNode l6 = l2.append(l5);
		assertEquals("( a )", l3.toString());
		assertEquals("( a )", l4.toString());
		assertEquals("( a )", l2.toString());
		assertEquals("( a b )", l6.toString());
	}
	
	@Test
	public void testReverse() {
		AbstractListNode l1 = new NonemptyListNode("a", new NonemptyListNode("b", new NonemptyListNode("c", new NonemptyListNode("d", new NonemptyListNode("e")))));
		AbstractListNode l2 = l1.reverse();
		assertEquals("( e d c b a )", l2.toString());
		AbstractListNode l3 = new EmptyListNode().reverse();
		assertEquals("( )", l3.toString());
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
	    
	    AbstractListNode test1 = new NonemptyListNode("a", new NonemptyListNode("b", new NonemptyListNode("c")));
	    AbstractListNode test2 = test1.appendInPlace(new NonemptyListNode("d"));
	    assertEquals("( a b c d )", test2.toString());
	}
	
	@Test
	public void testTrickyAppendInPlace() {

	    AbstractListNode list1 = new EmptyListNode();
	    AbstractListNode list2 = new NonemptyListNode ("a");

	    list1 = list1.appendInPlace(list2);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a )", list2.toString());

	    list2 = list2.appendInPlace(list1);
	    
	    assertTrue (list1.get(0) == "a");
        assertTrue (list1.get(1) == "a");
        assertTrue (list1.get(2) == "a");
	}
	
	@Test
	public void testMerge() {
		AbstractListNode c = new NonemptyListNode(1);
		AbstractListNode d = new NonemptyListNode(2);
		AbstractListNode n = AbstractListNode.merge(c, d);
		assertEquals("( 1 2 )", n.toString());
		
		AbstractListNode a = new NonemptyListNode(1, new NonemptyListNode(2, new NonemptyListNode(6, new NonemptyListNode(7))));
		AbstractListNode b = new NonemptyListNode(3, new NonemptyListNode(4, new NonemptyListNode(5, new NonemptyListNode(8, new NonemptyListNode(9)))));
		AbstractListNode m = AbstractListNode.merge(a, b);
		assertEquals ("( 1 2 3 4 5 6 7 8 9 )", m.toString());
	}
}
