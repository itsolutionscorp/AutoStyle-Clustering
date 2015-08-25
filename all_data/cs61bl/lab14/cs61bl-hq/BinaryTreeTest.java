import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree t = new BinaryTree();
		t.fillSampleTree1();
		assertTrue (t.height() == 2);
		assertTrue (t.isCompletelyBalanced());
		t.fillSampleTree2();
		assertTrue (t.height() == 4);
		assertFalse (t.isCompletelyBalanced());
		t.fillSampleTree3();
		assertTrue (t.height() == 4);
		assertFalse (t.isCompletelyBalanced());
	}
}
