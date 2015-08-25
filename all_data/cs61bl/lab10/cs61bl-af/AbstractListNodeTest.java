import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void testSize() {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
	//	System.out.println(firstNode.size());
		assertTrue (firstNode.size() == 2);	
		}

	
	@Test
	public void testGet() {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
		assertTrue (firstNode.get(1) == "Crazy");
	}
	@Test
	public void testSmallestHelper() {
		NonemptyListNode thirdNode = new NonemptyListNode("aaaaaaaaaaaa");
		NonemptyListNode secondNode = new NonemptyListNode("Crazy", thirdNode);

		NonemptyListNode firstNode = new NonemptyListNode("zzzzzzzzzz", secondNode);
		assertTrue (firstNode.smallest() == "Crazy");
	}
	@Test 
	public void equals () {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
		NonemptyListNode secondSecondNode = new NonemptyListNode("Crazy");
		NonemptyListNode secondFirstNode = new NonemptyListNode("Power", secondSecondNode);
		assertTrue (firstNode.equals(secondFirstNode));
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
	public void testToSTring () {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
		assertTrue (firstNode.toString().equals("( Power Crazy )"));
	}
	
	
	@Test
	public void testcopy() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    assertEquals(l3.toString(),l3.copy().toString());
	    assertEquals(l2.toString(), l2.copy().toString());
	}
	
	@Test
	public void testappend() {
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    AbstractListNode l4 = new EmptyListNode();
	    AbstractListNode l5 = l4.add("c");
	    AbstractListNode l6 = l5.add("d");
	    assertEquals("( a b c d )",l3.append(l6).toString());   
	}
	
	public void testreverse() {
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    AbstractListNode l3 = l2.add("b");
	    AbstractListNode l4 = l3.add("c");
	    assertEquals("( b a)",l3.reverse().toString()); 
	    assertEquals("( c b a )", l4.reverse().toString());
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
	
	public void testmerge() {
		AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add(1);
	    AbstractListNode l3 = l2.add(3);
	    AbstractListNode l4 = l3.add(6);
	    
	    AbstractListNode l5 = new EmptyListNode();
	    AbstractListNode l6 = l5.add(2);
	    assertEquals((" 1 3 6"),AbstractListNode.merge(l4,l5).toString()); 
	    assertEquals("( 1 2 3 6)", AbstractListNode.merge(l4,l6).toString());
	    
	}
	
}
