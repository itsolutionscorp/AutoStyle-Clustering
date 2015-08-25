import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void testHeight() {
		BinaryTree t;
        t = new BinaryTree();
        assertTrue(t.height() == 0);
        
        t.fillSampleTree1();
        assertTrue(t.height() == 2);
        t.fillSampleTree2();
        assertTrue(t.height() == 4);
        t.fillSampleTree3();
        assertTrue(t.height() == 4);
        t.fillSampleTree4();
        assertTrue(t.height() == 6);
	}
	
	@Test
	public void testComletelyBalanced() {
		BinaryTree t;
        t = new BinaryTree();
        assertTrue(t.isCompletelyBalanced()); // empty tree is balanced
        
        t.fillOneTreeNode();
        assertTrue(t.isCompletelyBalanced()); // a tree containing just one node
        
        t.fillSampleTree1();
        assertTrue(t.isCompletelyBalanced()); // a tree with two branches

        t.fillBalancedHeight3();   // a completely balanced tree of height 3
        assertTrue(t.isCompletelyBalanced()); 
        
        t.fillSampleTree3();
        assertFalse(t.isCompletelyBalanced());
        
        t.fillSampleTree4();
        assertFalse(t.isCompletelyBalanced());
	}

}
