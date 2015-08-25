import junit.framework.TestCase;


public class AbstractListNodeTest extends TestCase {
	public void testSize() {
		AbstractListNode test0 = new EmptyListNode();
		AbstractListNode test1 = new NonemptyListNode(3, null);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		assertEquals(test3.size(), 3);
		assertEquals(test0.size(), 0);
		assertEquals(test1.size(), 1);
	}
	
	public void testGet() {
		AbstractListNode test1 = new NonemptyListNode(3, null);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		assertEquals(test3.get(2), 3);;
		assertEquals(test3.get(0), 1);
	}
	
	public void testString() {
		AbstractListNode test0 = new EmptyListNode();
		AbstractListNode test1 = new NonemptyListNode(3, null);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		assertEquals(test0.toString(), "( )");
		assertEquals(test3.toString(), "( 1 2 3 )");
	}
	
	public void testEquals() {
		AbstractListNode empty1 = new EmptyListNode();
		AbstractListNode empty2 = new EmptyListNode();
		AbstractListNode test1 = new NonemptyListNode(3, null);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		AbstractListNode test4 = new NonemptyListNode(3, null);
		NonemptyListNode test5 = new NonemptyListNode(2, test4);
		NonemptyListNode test6 = new NonemptyListNode(1, test5);
		AbstractListNode test7 = new NonemptyListNode(4, null);
		NonemptyListNode test8 = new NonemptyListNode(2, test7);
		NonemptyListNode test9 = new NonemptyListNode(1, test8);
		
		assertTrue(empty1.equals(empty2));
		assertFalse(empty1.equals(test3));
		assertFalse(test3.equals(empty2));
		assertTrue(test3.equals(test6));
		assertFalse(test3.equals(test5));
		assertFalse(test3.equals(test9));
		
	}
	
	public void testSmallest() {
		AbstractListNode test0 = new NonemptyListNode(0, null);
		AbstractListNode test1 = new NonemptyListNode(3, test0);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		assertEquals(test3.smallest(), 0);
	}
	
	public void testAdd() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	public void testAppend() {
		AbstractListNode test1 = new NonemptyListNode(3, null);
		NonemptyListNode test2 = new NonemptyListNode(2, test1);
		NonemptyListNode test3 = new NonemptyListNode(1, test2);
		
		AbstractListNode test4 = new NonemptyListNode(5, null);
		NonemptyListNode test5 = new NonemptyListNode(4, test4);
		
		AbstractListNode test6 = new NonemptyListNode(5, null);
		NonemptyListNode test7 = new NonemptyListNode(4, test6);
		NonemptyListNode test8 = new NonemptyListNode(3, test7);
		AbstractListNode test9 = new NonemptyListNode(2, test8);
		NonemptyListNode test10 = new NonemptyListNode(1, test9);
		
		AbstractListNode test11 = new NonemptyListNode(3, null);
		NonemptyListNode test12 = new NonemptyListNode(2, test11);
		NonemptyListNode test13 = new NonemptyListNode(1, test12);
		AbstractListNode test14 = new NonemptyListNode(5, null);
		NonemptyListNode test15 = new NonemptyListNode(4, test14);
		assertEquals(test10, test3.append(test5));
		assertEquals(test3, test13);
		assertEquals(test5, test15);
		
		EmptyListNode empty1 = new EmptyListNode();
		assertEquals(test3.append(empty1), test13);
		assertEquals(empty1.append(test3), test13);
	}
	
	public void testReverse() {
		AbstractListNode test1 = new NonemptyListNode(5, null);
		NonemptyListNode test2 = new NonemptyListNode(4, test1);
		NonemptyListNode test3 = new NonemptyListNode(3, test2);
		NonemptyListNode test4 = new NonemptyListNode(2, test3);
		NonemptyListNode test5 = new NonemptyListNode(1, test4);
		
		AbstractListNode test6 = new NonemptyListNode(1, null);
		AbstractListNode test7 = new NonemptyListNode(2, test6);
		NonemptyListNode test8 = new NonemptyListNode(3, test7);
		NonemptyListNode test9 = new NonemptyListNode(4, test8);
		NonemptyListNode test10 = new NonemptyListNode(5, test9);
		
		AbstractListNode test11 = new NonemptyListNode(5, null);
		NonemptyListNode test12 = new NonemptyListNode(4, test11);
		NonemptyListNode test13 = new NonemptyListNode(3, test12);
		NonemptyListNode test14 = new NonemptyListNode(2, test13);
		NonemptyListNode test15 = new NonemptyListNode(1, test14);
		
		EmptyListNode empty = new EmptyListNode();
		EmptyListNode empty2 = new EmptyListNode();
		assertEquals(test10, test5.reverse());
		assertEquals(test5, test15);
		assertEquals(empty.reverse(), empty2);
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
	
	public void testTrickyAppendInPlace() {

	    AbstractListNode list1 = new EmptyListNode();
	    AbstractListNode list2 = new NonemptyListNode ("a");

	    list1 = list1.appendInPlace(list2);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a )", list2.toString());

	    //list2 = list2.appendInPlace(list1);

	    //assertEquals ("( a )", list1.toString());
	    //assertEquals ("( a a )", list2.toString());
	}

	public void testMerge() {
		AbstractListNode test1 = new NonemptyListNode(6, null);
		NonemptyListNode test2 = new NonemptyListNode(4, test1);
		NonemptyListNode test3 = new NonemptyListNode(3, test2);
		NonemptyListNode test4 = new NonemptyListNode(2, test3);
		
		NonemptyListNode test5 = new NonemptyListNode(5, null);
		NonemptyListNode test6 = new NonemptyListNode(1, test5);
		
		AbstractListNode test7 = new NonemptyListNode(6, null);
		NonemptyListNode test8 = new NonemptyListNode(5, test7);
		NonemptyListNode test9 = new NonemptyListNode(4, test8);
		NonemptyListNode test10 = new NonemptyListNode(3, test9);
		NonemptyListNode test11 = new NonemptyListNode(2, test10);
		AbstractListNode test12 = new NonemptyListNode(1, test11);
		
		assertEquals(test12, test4.merge(test4, test6));
	}
}