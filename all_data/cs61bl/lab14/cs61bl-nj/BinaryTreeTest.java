import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree test = new BinaryTree();
		assertTrue(test.height()==0);
		test.fillSampleTree1();
		assertTrue(test.height()==2);
		test.fillSampleTree2();
		assertTrue(test.height()==4);
	}
	public void testBalance() {
		BinaryTree test = new BinaryTree();
		assertTrue(test.isCompletelyBalanced());
		test.fillSampleTree1();
		assertTrue(test.isCompletelyBalanced());
		test.fillSampleTree2();
		assertFalse(test.isCompletelyBalanced());
		test.fillSampleTree3();
		assertTrue(test.isCompletelyBalanced());
		test.fillSampleTree4();
		assertTrue(test.isCompletelyBalanced());
	}
}
