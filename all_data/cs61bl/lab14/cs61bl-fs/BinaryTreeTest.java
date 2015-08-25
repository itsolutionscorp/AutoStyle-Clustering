import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree tree = new BinaryTree();
		assertEquals(0, tree.height());
		tree.fillSampleTree1();
		assertEquals(2, tree.height());
		tree.fillSampleTree2();
		assertEquals(4, tree.height());
		tree.fillSampleTree3();
		assertEquals(4, tree.height());
	}
	
	public void testIsCompletelyBalanced() {
		BinaryTree tree = new BinaryTree();
		assertTrue(tree.isCompletelyBalanced());
		tree.fillSampleTree1();
		assertTrue(tree.isCompletelyBalanced());
		tree.fillSampleTree2();
		assertFalse(tree.isCompletelyBalanced());
		tree.fillSampleTree3();
		assertFalse(tree.isCompletelyBalanced());
		tree.fillSampleTree4();
		assertTrue(tree.isCompletelyBalanced());
	}
}
