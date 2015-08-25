import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void test() {
		//size test
		NonemptyListNode object3 = new NonemptyListNode(5, null);
		NonemptyListNode object2 = new NonemptyListNode(3, object3);
		NonemptyListNode object1 = new NonemptyListNode(1, object2);
		EmptyListNode emptyobject = new EmptyListNode();
		System.out.println(object3.size());
		System.out.println(object2.size());
		System.out.println(object1.size());
		System.out.println(emptyobject.size());
		assertTrue(emptyobject.size() == 0);
		assertTrue(object3.size() == 1);
		assertTrue(object2.size() == 2);
		assertTrue(object1.size() == 3);
		
		//stringtest
		System.out.println(object1.toString());
		System.out.println(object2.toString());
		System.out.println(object3.toString());
		System.out.println(emptyobject.toString());
		assertTrue(object1.toString().equals( "( 1 3 5 )"));
		assertTrue(object2.toString().equals( "( 3 5 )"));
		assertTrue(object3.toString().equals("( 5 )"));
		assertTrue(emptyobject.toString() == "()");

	}
	
}
