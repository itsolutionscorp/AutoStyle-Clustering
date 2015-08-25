import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;


public class BSTTest {

	@Test
	public void testBST() {
		LinkedList a1 = new LinkedList();
		a1.add("a");
		a1.add("b");
		a1.add("c");
		a1.add("d");
		a1.add("e");
		a1.add("f");
		
		BST b = new BST(a1);
		assertTrue(b.myRoot.myItem.equals("d"));
		assertTrue(b.myRoot.myLeft.myItem.equals("b"));
		assertTrue(b.myRoot.myRight.myItem.equals("f"));
		assertTrue(b.myRoot.myRight.myLeft.myItem.equals("e"));
		assertTrue(b.myRoot.myRight.myRight == null);
		
		
		
	}

}
