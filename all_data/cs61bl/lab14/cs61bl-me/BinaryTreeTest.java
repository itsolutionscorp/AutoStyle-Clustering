import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void test() {
		BinaryTree test = new BinaryTree();
		test.fillSampleTree2();
		assertEquals(4, test.height());
		assertTrue(!test.isCompletelyBalanced());
		BinaryTree test2 = new BinaryTree();
		test2.fillSampleTree1();
		assertEquals(2, test2.height());
		assertTrue(test2.isCompletelyBalanced());
	}

}
