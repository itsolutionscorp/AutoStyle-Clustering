import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void test() {
		BinaryTree t;
        t = new BinaryTree();
        assertTrue(t.height() == 0);
        assertTrue(t.isCompletelyBalanced());
        
        t.fillSampleTree1();
        assertTrue(t.height() == 1);
        assertTrue(t.isCompletelyBalanced());
        
        t.fillSampleTree2();
        assertTrue(t.height() == 3);
        assertFalse(t.isCompletelyBalanced());
        
		t.isCompletelyBalancedTree();
		assertTrue(t.height() == 3);
        assertTrue(t.isCompletelyBalanced());   
	}

}
