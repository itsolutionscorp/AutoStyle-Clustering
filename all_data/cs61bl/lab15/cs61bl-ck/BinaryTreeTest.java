import junit.framework.TestCase;

public class BinaryTreeTest extends TestCase {
	public void testHeight() {
		BinaryTree t;
        t = new BinaryTree();
        assertEquals(t.height(), 0);
        t.fillSampleTree0();
        assertEquals(t.height(), 1);
        t.fillSampleTree1();
        assertEquals(t.height(), 2);
        t.fillSampleTree2();
        assertEquals(t.height(), 4);    
        t.fillSampleTree3();
        assertEquals(t.height(), 4);
        t.fillSampleTree4();
        assertEquals(t.height(), 4);
        t.fillSampleTree5();
        assertEquals(t.height(), 2);
	}
	
	public void testisbalanced () {
		BinaryTree t;
        t = new BinaryTree();
        assertEquals(t.height(), 0);
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree0();
        assertEquals(t.height(), 1);
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree1();
        assertEquals(t.height(), 2);
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree2();
        assertEquals(t.height(), 4); 
        assertFalse(t.isCompletelyBalanced());
        t.fillSampleTree3();
        assertFalse(t.isCompletelyBalanced());
        t.fillSampleTree4();
        assertTrue(t.isCompletelyBalanced());
        t.fillSampleTree5();
        assertFalse(t.isCompletelyBalanced());
        
		
	}
}
