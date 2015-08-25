import static org.junit.Assert.*;

import org.junit.Test;


public class ListNodeTest {

	@Test
	public void test() {
		
		ListNode object4 = new ListNode(4, null);
		ListNode object3 = new ListNode(3, object4);
		ListNode object2 = new ListNode(2, object3);
		ListNode object1 = new ListNode(1, object2);
		
//		System.out.println(object1.length());
		System.out.println(object1.get(0));
		System.out.println(object1.get(3));
		System.out.println(object1.get(0));
		assertTrue(object1.get(0) == (Object) 1);
		
		try {
			object1.get(5);
			System.out.println(object1.get(5));
		}
		catch (IllegalArgumentException E) {
			System.out.println(E.getMessage());
		}
		
		
	}

}
