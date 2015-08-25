
import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree t;
        t = new BinaryTree();
        t.fillSampleTree3();
        assertEquals(t.height(), 4);
        t.fillSampleTree1();
        assertEquals(t.height(), 2);
        t.fillSampleTree2();
        assertEquals(t.height(), 4);
	}
	
	public void testBalanced() {
		BinaryTree t;
        t = new BinaryTree();
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree3();
        assertFalse(t.isCompletelyBalanced());
        t.fillSampleTree1();
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree2();
        assertFalse(t.isCompletelyBalanced());
        t.fillSampleTree4();
        assertTrue(t.isCompletelyBalanced());
	}
}

