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
	public void testToSTring () {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
		System.out.println(firstNode.toString());
		assertTrue (firstNode.toString().equals("(Power Crazy)"));
	}
	@Test 
	public void equals () {
		NonemptyListNode secondNode = new NonemptyListNode("Crazy");
		NonemptyListNode firstNode = new NonemptyListNode("Power", secondNode);
		NonemptyListNode secondSecondNode = firstNode;
		assertTrue (firstNode.equals(secondSecondNode));
	}
}
