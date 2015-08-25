import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractListNodeTest {

	@Test
	public void testSize() {
		NonemptyListNode a = new NonemptyListNode("a");
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode("c", b);
		NonemptyListNode d = new NonemptyListNode("d", c);
		assertTrue(d.size() == 4);
	}
	
	@Test
	public void testGet() {
		NonemptyListNode a = new NonemptyListNode("a");
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode("c", b);
		NonemptyListNode d = new NonemptyListNode("d", c);
		assertTrue(d.get(0) == "d");
		assertTrue(d.get(1) == "c");
		assertTrue(d.get(2) == "b");
		assertTrue(d.get(3) == "a");
		try {
			d.get(4);
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void testtoString() {
		NonemptyListNode b = new NonemptyListNode(3);
		NonemptyListNode c = new NonemptyListNode(2, b);
		NonemptyListNode d = new NonemptyListNode(1, c);
		assertTrue("( 1 2 3 )".equals(d.toString()));
		EmptyListNode a = new EmptyListNode();
		assertTrue("( )".equals(a.toString()));
	}
	
	@Test
	public void testEquals() {
		NonemptyListNode a = new NonemptyListNode("a");
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode("c", b);
		NonemptyListNode d = new NonemptyListNode("d", c);
		NonemptyListNode e = new NonemptyListNode("a");
		NonemptyListNode f = new NonemptyListNode("b", e);
		NonemptyListNode g = new NonemptyListNode("c", f);
		NonemptyListNode h = new NonemptyListNode("d", g);
		assertTrue(e.equals(a));
		NonemptyListNode x = new NonemptyListNode("i");
		NonemptyListNode y = new NonemptyListNode("j", x);
		NonemptyListNode z = new NonemptyListNode("k", y);
		NonemptyListNode i = new NonemptyListNode("i");
		NonemptyListNode j = new NonemptyListNode("j", i);
		NonemptyListNode k = new NonemptyListNode("z", j);
		assertFalse(z.equals(k)); //last element different
		assertFalse(z.equals(d) && z.equals(h)); // longer
		assertFalse(z.equals(c) && z.equals(g)); // shorter
		NonemptyListNode m = new NonemptyListNode("m");
		NonemptyListNode n = new NonemptyListNode("j", m);
		NonemptyListNode o = new NonemptyListNode("o", n);
		NonemptyListNode p = new NonemptyListNode("p");
		NonemptyListNode q = new NonemptyListNode("j", p);
		NonemptyListNode r = new NonemptyListNode("o", q);
		assertFalse(r.equals(o)); // first element different
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
		NonemptyListNode a = new NonemptyListNode("a");
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode("c", b);
		NonemptyListNode d = new NonemptyListNode("d");
		assertEquals("( c b a )", c.toString());
		assertEquals("( b a )", b.toString());
		AbstractListNode x = c.append(d);
		assertEquals("( c b a d )", x.toString());
		assertEquals("( c b a )", c.toString());
		assertEquals("( b a )", b.toString());
		EmptyListNode e = new EmptyListNode();
		assertEquals("( )", e.toString());
		AbstractListNode y = x.append(e);
		assertEquals("( c b a d )", y.toString());
		assertEquals("( )", e.toString());
		EmptyListNode E = new EmptyListNode();
		assertEquals("( )", E.toString());
		AbstractListNode z = E.append(y);
		assertEquals("( c b a d )", z.toString());
		assertEquals("( )", E.toString());
		AbstractListNode D = c.append(c);
		assertEquals("( c b a c b a )", D.toString());
		assertEquals("( b a )", b.toString());
	}
	
	@Test
	public void testReverse() {
		NonemptyListNode a = new NonemptyListNode("a");
		NonemptyListNode b = new NonemptyListNode("b", a);
		NonemptyListNode c = new NonemptyListNode("c", b);
		AbstractListNode e = c.reverse();
		assertEquals("( c b a )", c.toString());
		assertEquals("( a b c )", e.toString());
		EmptyListNode E = new EmptyListNode();
		AbstractListNode d = E.reverse();
		assertEquals("( )", d.toString());
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
	
	public void testTrickyAppendInPlace() { //this test sucks

	    AbstractListNode list1 = new EmptyListNode();
	    AbstractListNode list2 = new NonemptyListNode ("a");

	    list1 = list1.appendInPlace(list2);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a )", list2.toString());

	    list2 = list2.appendInPlace(list1);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a a )", list2.toString());
	}
	
	@Test
	public void testMerge() {
		AbstractListNode a = new NonemptyListNode(7);
		AbstractListNode b = new NonemptyListNode(4, a);
		AbstractListNode c = new NonemptyListNode(3, b);
		AbstractListNode d = new NonemptyListNode(1, c);
		AbstractListNode e = new NonemptyListNode(12);
		AbstractListNode f = new NonemptyListNode(10, e);
		AbstractListNode g = new NonemptyListNode(5, f);
		AbstractListNode h = new NonemptyListNode(2, g);
		System.out.println(d.toString());
		System.out.println(h.toString());
//		System.out.println((AbstractListNode.merge(h, d)).toString());
		assertEquals(AbstractListNode.merge(h, d).toString(), "( 1 2 3 4 5 7 10 12 )");
		
	}

}
