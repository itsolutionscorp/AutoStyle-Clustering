import static org.junit.Assert.*;

import org.junit.Test;


public class ArrayBSTTest {

	@Test
	public void testContains() {
		ArrayBST myABST = new ArrayBST();
		myABST.insert(1);
		assertTrue(myABST.contains(1));
		myABST.insert(2);
		myABST.insert(3);
		myABST.insert(4);
		myABST.insert(5);
		assertTrue(myABST.contains(2));
		assertTrue(myABST.contains(3));
		assertTrue(myABST.contains(4));
		assertTrue(myABST.contains(5));
		assertFalse(myABST.contains(6));

		
		

	}

}
