import junit.framework.TestCase;
public class AbstractListNodeTest extends TestCase {

    public static Comparable min(Comparable c1, Comparable c2) {
  	  if (c1.compareTo(c2) < 0) {
  	    return c1;
  	  } else {
  	    return c2;
  	  }
  	}
    
public static AbstractListNode merge(AbstractListNode a, AbstractListNode b ) {
    // Fill this out
	AbstractListNode current;
	AbstractListNode start;
	if (min(a.get(0),b.get(0))==a.get(0)){
		start = a;
		current = a;
		a = a.next();
	} else {
		start = b;
		current = b;
		b = b.next();
	}
	while(!(a.isEmpty() || b.isEmpty())){ 
		
		if (min(a.get(0),b.get(0))==b.get(0)){
			current.setNext(b);
			b = b.next();
			current = current.next();
		} else {
			current.setNext(a);
			a = a.next();
			current = current.next();
		}
	}
	if (a.isEmpty()){
		current.setNext(b);
	}
	if (b.isEmpty()){
		current.setNext(a);
	}
	return start;
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
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    AbstractListNode r1 = new EmptyListNode();
	    AbstractListNode r2 = r1.add("c");
	    AbstractListNode r3 = r2.add("d");
	    AbstractListNode r4 = r3.add("e");
	    assertEquals("( a b c d e )",l3.append(r4).toString());
	    assertEquals("( c d e )", l1.append(r4).toString());
	    assertEquals("( a b )", l3.append(r1).toString());
	    assertEquals("( )", l1.append(r1).toString());
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( c d e )", r4.toString());
	}
	
	public void testReverse() {
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( )", l1.reverse().toString());
	    assertEquals("( b a )", l3.reverse().toString());
	    assertEquals("( a b )", l3.toString());
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
//
//	    assertEquals ("( a )", list1.toString());
//	    assertEquals ("( a )", list2.toString());
//
//	    list2 = list2.appendInPlace(list1);
//
//	    assertEquals ("( a )", list1.toString());
//	    assertEquals ("( a a )", list2.toString());
//	}
	
	public void testMerge() {
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add(1);
	    AbstractListNode l3 = l2.add(3);
	    AbstractListNode r1 = new EmptyListNode();
	    AbstractListNode r2 = r1.add(2);
	    AbstractListNode r3 = r2.add(4);
	    AbstractListNode r4 = r3.add(6);
	    AbstractListNode result;
	    result = merge(r4,l3);
	    assertEquals(result.item(),1);
	    assertEquals(result.get(1),2);
	    assertEquals(result.get(2),3);
	    assertEquals(result.get(3),4);
	    assertEquals(result.get(4),6);
	}
		public void testMerge2() {
			AbstractListNode l1 = new EmptyListNode();
		    AbstractListNode l2 = l1.add(1);
		    AbstractListNode l3 = l2.add(5);
		    AbstractListNode r1 = new EmptyListNode();
		    AbstractListNode r2 = r1.add(2);
		    AbstractListNode r3 = r2.add(4);
		    AbstractListNode r4 = r3.add(6);
		    AbstractListNode result;
		    result = merge(r4,l3);

		    assertEquals(result.item(),1);
		    assertEquals(result.get(1),2);
		    assertEquals(result.get(2),4);
		    assertEquals(result.get(3),5);
		    assertEquals(result.get(4),6);
		}
		public void testMerge3() {
			AbstractListNode l1 = new EmptyListNode();
		    AbstractListNode l2 = l1.add(0);
		    AbstractListNode l3 = l2.add(1);
		    AbstractListNode r1 = new EmptyListNode();
		    AbstractListNode r2 = r1.add(2);
		    AbstractListNode r3 = r2.add(4);
		    AbstractListNode r4 = r3.add(6);
		    AbstractListNode result;
		    result = merge(r4,l3);
		    assertEquals(result.item(),0);
		    assertEquals(result.get(1),1);
		    assertEquals(result.get(2),2);
		    assertEquals(result.get(3),4);
		    assertEquals(result.get(4),6);
		}
		
		public void testMerge4() {
			AbstractListNode l1 = new EmptyListNode();
		    AbstractListNode l2 = l1.add(0);
		    AbstractListNode l3 = l2.add(1);
		    AbstractListNode l4 = l3.add(2);
		    AbstractListNode r1 = new EmptyListNode();
		    AbstractListNode r2 = r1.add(3);
		    AbstractListNode r3 = r2.add(4);
		    AbstractListNode r4 = r3.add(6);
		    AbstractListNode result;
		    result = merge(r4,l4);
		    assertEquals(result.item(),0);
		    assertEquals(result.get(1),1);
		    assertEquals(result.get(2),2);
		    assertEquals(result.get(3),3);
		    assertEquals(result.get(4),4);
		    assertEquals(result.get(5),6);
		}
		
		
}
