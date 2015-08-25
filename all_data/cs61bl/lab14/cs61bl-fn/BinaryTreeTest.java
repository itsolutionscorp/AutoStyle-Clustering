import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree t = new BinaryTree();
		assertTrue(t.height() == 0);
        t.fillSampleTree1();
        assertTrue(t.height() == 2);
        t.fillSampleTree2();
        assertTrue(t.height() == 4);
        t.fillSampleTree3();
        assertTrue(t.height() == 3);
	}
	
	public void testIsCompletelyBalanced() {
		BinaryTree t = new BinaryTree();
		assertTrue(t.isCompletelyBalanced());
		t.fillSampleTree1();
		assertTrue(t.isCompletelyBalanced());
		t.fillSampleTree2();
		assertFalse(t.isCompletelyBalanced());
		t.fillSampleTree3();
		assertTrue(t.isCompletelyBalanced());
	}
}
