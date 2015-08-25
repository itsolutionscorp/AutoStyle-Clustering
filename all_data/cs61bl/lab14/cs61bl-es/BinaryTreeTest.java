import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void testHeight() {
		BinaryTree t = new BinaryTree();
        assertTrue(t.height() == 0);
        
        t.fillSampleTree1();
        assertTrue(t.height() == 2);
        
        t.fillSampleTree2();
        assertTrue(t.height() == 4);
        
        t.fillSampleTree3();
        assertTrue(t.height() == 4);
        
        t.fillSampleTree4();
        assertTrue(t.height() == 5);
        
        t.fillSampleTree5();
        assertTrue(t.height() == 3);
        
        t.fillSampleTree6();
        assertTrue(t.height() == 1);
	}

	@Test
	public void testIsCompletelyBalanced() {
		BinaryTree t = new BinaryTree();
        assertTrue(t.isCompletelyBalanced());
        
        t.fillSampleTree1();
        assertTrue(t.isCompletelyBalanced());
        
        t.fillSampleTree2();
        assertFalse(t.isCompletelyBalanced());
        
        t.fillSampleTree3();
        assertFalse(t.isCompletelyBalanced());
        
        t.fillSampleTree4();
        assertFalse(t.isCompletelyBalanced());
        
        t.fillSampleTree5();
        assertTrue(t.isCompletelyBalanced());
        
        t.fillSampleTree6();
        assertTrue(t.isCompletelyBalanced());
	}
}
