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
}
