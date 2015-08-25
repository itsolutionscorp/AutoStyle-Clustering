import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

		@Test
		public void testBinaryTree() {
			BinaryTree t1 = new BinaryTree();
			assertEquals(t1.height(), 0);
			assertTrue(t1.isCompletelyBalanced());
			BinaryTree t2 = new BinaryTree();
			t2.fillSampleTree3();
			t1.fillWithOne();
			assertEquals(t1.height(), 1);
			assertEquals(t2.height(), 4);
			assertFalse(t2.isCompletelyBalanced());
			t2.fillSampleTree2();
			assertEquals(t2.height(), 4);
			assertFalse(t2.isCompletelyBalanced());
			t2.fillSampleTree1();
			assertEquals(t2.height(), 2);
			assertTrue(t2.isCompletelyBalanced());
			BinaryTree t3 = new BinaryTree();
			t3.fillSizeThree();
			assertEquals(t3.height(), 3);
			assertTrue(t3.isCompletelyBalanced());
		}

}


