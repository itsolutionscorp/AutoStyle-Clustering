import junit.framework.TestCase;

public class AbstractListNodeTest extends TestCase
{
	public void testSmallest()
	{
		AbstractListNode root = new NonemptyListNode("2", new NonemptyListNode("1", new NonemptyListNode("3", new EmptyListNode())));
		assertEquals("1", root.smallest());
	}
	public void testAdd()
	{
		AbstractListNode l1 = new EmptyListNode();
		AbstractListNode l2 = l1.add("a");
		assertEquals("( a )", l2.toString());
		AbstractListNode l3 = l2.add("b");
		assertEquals("( a b )", l3.toString());
		assertEquals("( a )", l2.toString());
	}
	public void testAppend()
	{
		AbstractListNode l1 = new EmptyListNode();
		l1 = l1.add("1");
		l1 = l1.add("2");
		l1 = l1.add("3");
		AbstractListNode l2 = new NonemptyListNode("4", new NonemptyListNode("5", new NonemptyListNode("6", null)));
		AbstractListNode l3 = l1.append(l2);
		assertEquals("( 1 2 3 4 5 6 )", l3.toString());
		assertEquals("( 1 2 3 )", l1.toString());
		assertEquals("( 4 5 6 )", l2.toString());
	}
	public void testReverse()
	{
		AbstractListNode l1 = new EmptyListNode();
		l1 = l1.add("1");
		l1 = l1.add("2");
		l1 = l1.add("3");
		AbstractListNode l2 = new NonemptyListNode("4", new NonemptyListNode("5", new NonemptyListNode("6", null)));
		AbstractListNode l3 = l1.append(l2);
		AbstractListNode l4 = new EmptyListNode().reverse();
		AbstractListNode l5 = l3.reverse();
		assertEquals("( 1 2 3 4 5 6 )", l3.toString());
		assertEquals("( 1 2 3 )", l1.toString());
		assertEquals("( 4 5 6 )", l2.toString());
		assertEquals("( 6 5 4 3 2 1 )", l5.toString());
		assertEquals("( )", l4.toString());
	}
	public void testStraightforward()
	{

		AbstractListNode empty1 = new EmptyListNode();
		AbstractListNode empty2 = new EmptyListNode();

		empty1 = empty1.appendInPlace(empty2);

		assertEquals("( )", empty1.toString());
		assertEquals("( )", empty2.toString());

		AbstractListNode a = new NonemptyListNode("a");

		a = a.appendInPlace(empty1);

		assertEquals("( a )", a.toString());
		assertEquals("( )", empty1.toString());

		AbstractListNode b = new NonemptyListNode("b");
		AbstractListNode c = new NonemptyListNode("c");

		b = b.appendInPlace(c);

		assertEquals("( b c )", b.toString());
		assertEquals("( c )", c.toString());
	}
	public void testMerge()
	{
		AbstractListNode empty1 = new EmptyListNode();
		AbstractListNode empty2 = new EmptyListNode();
		empty1 = AbstractListNode.merge(empty1, empty2);
		assertEquals("( )", empty1.toString());
		assertEquals("( )", empty2.toString());
		
		AbstractListNode a = new NonemptyListNode(1);
		a = AbstractListNode.merge(a, empty1);
		assertEquals("( 1 )", a.toString());
		a = AbstractListNode.merge(empty1, a);
		assertEquals("( 1 )", a.toString());
		
		AbstractListNode list1 = new NonemptyListNode(1, new NonemptyListNode(4, new NonemptyListNode(5)));
		AbstractListNode list2 = new NonemptyListNode(0, new NonemptyListNode(3, new NonemptyListNode(7)));
		list1 = AbstractListNode.merge(list1, list2);
		assertEquals("( 0 1 3 4 5 7 )", list1.toString());
		assertEquals("( 0 1 3 4 5 7 )", list2.toString());
	}
}
