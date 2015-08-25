import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTreeTest {

	@Test
	public void testIsCompletelyBalanced() {
		   BinaryTree a = new BinaryTree();
		   assertTrue(a.isCompletelyBalanced());
		   
		   BinaryTree b = new BinaryTree();
		   b.fillSampleTree1();
		   assertTrue(b.isCompletelyBalanced());
		   
		   BinaryTree c = new BinaryTree();
		   c.fillSampleTree2();
		   assertFalse(c.isCompletelyBalanced());
		   
		   BinaryTree d = new BinaryTree();
		   d.fillSampleTree3();
		   assertFalse(d.isCompletelyBalanced());
		   
		   BinaryTree e = new BinaryTree();
		   e.fillSampleTree4();
		   assertTrue(e.isCompletelyBalanced());
		   
		   BinaryTree f = new BinaryTree();
		   f.fillSampleTree5();
		   assertTrue(f.isCompletelyBalanced());
		   
		   BinaryTree g = new BinaryTree();
		   g.fillSampleTree6();
		   assertFalse(g.isCompletelyBalanced());
	}
	
	@Test
	public void testHeight() {
		   BinaryTree a = new BinaryTree();
		   assertEquals(a.height(), 0);
		   
		   BinaryTree b = new BinaryTree();
		   b.fillSampleTree1();
		   assertEquals(b.height(), 2);
		   
		   BinaryTree c = new BinaryTree();
		   c.fillSampleTree2();
		   assertEquals(c.height(), 4);
		   
		   BinaryTree d = new BinaryTree();
		   d.fillSampleTree3();
		   assertEquals(d.height(), 4);
		   
		   BinaryTree e = new BinaryTree();
		   e.fillSampleTree4();
		   assertEquals(e.height(), 1);
		   
		   BinaryTree f = new BinaryTree();
		   f.fillSampleTree5();
		   assertEquals(f.height(), 3);
		   
		   BinaryTree g = new BinaryTree();
		   g.fillSampleTree6();
		   assertEquals(g.height(), 2);
		   
		}

}
