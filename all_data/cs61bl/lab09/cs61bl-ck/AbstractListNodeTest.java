import static org.junit.Assert.*;

import org.junit.Test;


public class AbstractListNodeTest {

	@Test
	public void test() {
	}
	
	public void testsize() {
		
		EmptyListNode test4 = new EmptyListNode();
		NonemptyListNode test3 = new NonemptyListNode(66, test4);
		NonemptyListNode test2 = new NonemptyListNode(77, test3);
		NonemptyListNode test1 = new NonemptyListNode(88, test2);
		NonemptyListNode test = new NonemptyListNode(99, test1);
		
		assertEquals(test.size(), 4);
	}
	
	public void testget() {
		EmptyListNode test4 = new EmptyListNode();
		NonemptyListNode test3 = new NonemptyListNode(66, test4);
		NonemptyListNode test2 = new NonemptyListNode(77, test3);
		NonemptyListNode test1 = new NonemptyListNode(88, test2);
		NonemptyListNode test = new NonemptyListNode(99, test1);
		
		assertEquals(test.get(1), 88);
		assertEquals(test.get(0), 99);
		assertEquals(test.get(2), 77);
		
	}
	
	public void testtoString() {
		EmptyListNode test4 = new EmptyListNode();
		NonemptyListNode test3 = new NonemptyListNode(66, test4);
		NonemptyListNode test2 = new NonemptyListNode(77, test3);
		NonemptyListNode test1 = new NonemptyListNode(88, test2);
		NonemptyListNode test = new NonemptyListNode(99, test1);
		
		assertEquals(test.toString(), "99 88 77 66");
		
	}
	
	public void testequals() {
		EmptyListNode test4 = new EmptyListNode();
		NonemptyListNode test3 = new NonemptyListNode(66, test4);
		NonemptyListNode test2 = new NonemptyListNode(77, test3);
		NonemptyListNode test1 = new NonemptyListNode(88, test2);
		NonemptyListNode test = new NonemptyListNode(99, test1);
		
		
		EmptyListNode test41 = new EmptyListNode();
		NonemptyListNode test31 = new NonemptyListNode(66, test41);
		NonemptyListNode test21 = new NonemptyListNode(77, test31);
		NonemptyListNode test11 = new NonemptyListNode(88, test21);
		NonemptyListNode test01 = new NonemptyListNode(99, test11);
		
		
		EmptyListNode test32 = new EmptyListNode();
		NonemptyListNode test88 = new NonemptyListNode(77, test32);
		NonemptyListNode test22 = new NonemptyListNode(77, test88);
		NonemptyListNode test12 = new NonemptyListNode(88, test22);
		NonemptyListNode test02 = new NonemptyListNode(99, test12);
		
		
		assertTrue(test.equals(test01));
		assertFalse(test.equals(test02));		
	}

}
