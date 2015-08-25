import junit.framework.TestCase;


public class AbstractListNodeTest extends TestCase {
	public void main(String args[]) {
		testAdd();
	}
	
	public void testAdd() {
		
		AbstractListNode L = new NonemptyListNode("a");
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	public void testAppend() {
		
		AbstractListNode X = new EmptyListNode();
		AbstractListNode Y = new NonemptyListNode(1);
		AbstractListNode Z = Y.append(X);
		assertEquals(Z.toString(), "( 1 )");
		AbstractListNode A = X.append(Y);
		assertEquals(A.toString(), "( 1 )");
		AbstractListNode B = new NonemptyListNode(1);
		AbstractListNode C = new NonemptyListNode(2);
		AbstractListNode D = B.append(C);
		assertEquals(D.toString(), "( 1 2 )");
		assertEquals(B.toString(), "( 1 )");
		AbstractListNode E = D.append(D);
		assertEquals(E.toString(), "( 1 2 1 2 )");
	}

	public void testReverse() {
		
		AbstractListNode B = new NonemptyListNode(1);
		AbstractListNode C = new NonemptyListNode(2);
		AbstractListNode D = B.append(C);
		AbstractListNode E = new NonemptyListNode(3);
		AbstractListNode F = D.append(E);
		AbstractListNode G = F.reverse();
		assertEquals("( 3 2 1 )", G.toString());
	}
	
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

	public void testMerge() {
		
		AbstractListNode A = new NonemptyListNode(1);
		AbstractListNode B = new NonemptyListNode(5);
		AbstractListNode.merge(A, B);
		assertEquals("( 1 5 )", A.toString());
		AbstractListNode C = new NonemptyListNode(2);
		AbstractListNode D = new NonemptyListNode(3);
		AbstractListNode.merge(C, D);
		assertEquals("( 2 3 )", C.toString());

		AbstractListNode.merge(A, C);
		assertEquals("( 1 2 3 5 )", A.toString());
	}

	
}

