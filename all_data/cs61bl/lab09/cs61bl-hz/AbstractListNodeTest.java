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

}
