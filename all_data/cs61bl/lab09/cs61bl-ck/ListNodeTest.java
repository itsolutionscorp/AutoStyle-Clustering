import static org.junit.Assert.*;

import org.junit.Test;


public class ListNodeTest {

	@Test
	public void test() {
	}
	
	public void testget() {
		ListNode test1 =  new ListNode(88, new ListNode(77, null));
		ListNode test = new ListNode(99, test1);
		assertEquals(test.get(0), 99);
//		assertEquals(test.isEmpty(), false);
		assertEquals(test.get(1), 88);
		assertEquals(test.get(2), 77);
		try {
			test.get(4);
		} catch(IllegalArgumentException E) {
			System.err.print("out fo bounds");
		}
		
	}

}
