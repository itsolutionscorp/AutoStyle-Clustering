import junit.framework.TestCase;


public class AbstractListNodeTest extends TestCase {
	public void testSize(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		assertEquals(l1.size(),2);
		assertTrue(l3.size()==0);
	}
	
	public void testGet(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		assertEquals(l1.get(1),"two");
		try{
			assertNull(l1.get(2));
		}
		catch(IllegalArgumentException e){
			System.err.println("If an error is expected, is it truly an error?");
		}
		
	}
	
	public void testToString(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		assertEquals(l1.toString(),"( one two )");
	}
	
	public void testEquals(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		EmptyListNode l6 = new EmptyListNode();
		NonemptyListNode l5 = new NonemptyListNode("two",l6);
		NonemptyListNode l4 = new NonemptyListNode("one",l5);
		assertTrue(l1.equals(l4));
	}
	
	public void testAdd() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	public void testAppend(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		EmptyListNode l6 = new EmptyListNode();
		NonemptyListNode l5 = new NonemptyListNode("two",l6);
		NonemptyListNode l4 = new NonemptyListNode("one",l5);
		AbstractListNode l_test=l1.append(l4);
		//Test both nonempty
		assertTrue(l_test.toString().equals("( one two one two )"));
		l_test=l3.append(l4);
		//Test list empty
		assertTrue(l_test.toString().equals("( one two )"));
		l_test=l1.append(l6);
		//Test argument empty
		assertTrue(l_test.toString().equals(l1.toString()));
		//Test original list remains same
		assertEquals(l1.toString(),"( one two )");
	}
	
	public void testReverse(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		EmptyListNode l6 = new EmptyListNode();
		NonemptyListNode l5 = new NonemptyListNode("two",l6);
		NonemptyListNode l4 = new NonemptyListNode("one",l5);
		AbstractListNode l_test = l1.reverse();
		assertEquals(l_test.toString(),"( two one )");
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
	
//	public void testTrickyAppendInPlace() {
//
//	    AbstractListNode list1 = new EmptyListNode();
//	    AbstractListNode list2 = new NonemptyListNode ("a");
//
//	    list1 = list1.appendInPlace(list2);
//	    System.out.println(list1.toString());
//	    System.out.println(list2.toString());
//	    assertEquals ("( a )", list1.toString());
//	    assertEquals ("( a )", list2.toString());
//
//	    list2 = list2.appendInPlace(list1);
//	    System.out.println(list1.toString());
//	    System.out.println(list2.toString());
//
//	    assertEquals ("( a )", list1.toString());
//	    assertEquals ("( a a )", list2.toString());
//	}
	
	public void testMerge(){
		EmptyListNode l3 = new EmptyListNode();
		NonemptyListNode l2 = new NonemptyListNode("two",l3);
		NonemptyListNode l1 = new NonemptyListNode("one",l2);
		EmptyListNode l6 = new EmptyListNode();
		NonemptyListNode l5 = new NonemptyListNode("two",l6);
		NonemptyListNode l4 = new NonemptyListNode("one",l5);
		AbstractListNode l = AbstractListNode.merge(l1, l4);
		assertEquals(l.toString(),"( one one two two )");
	}
}
