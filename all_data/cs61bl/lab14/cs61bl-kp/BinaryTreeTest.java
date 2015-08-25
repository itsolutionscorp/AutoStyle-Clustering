import junit.framework.TestCase;


public class BinaryTreeTest extends TestCase {
	public void testEVERYTHING() {
		BinaryTree t;
        t = new BinaryTree();
        t.fillSampleTree1();
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree3();
        assertEquals(t.height(), 4);
        assertFalse(t.isCompletelyBalanced());
        t.fillSampleTree4();
        assertTrue(t.isCompletelyBalanced());
	}
}
