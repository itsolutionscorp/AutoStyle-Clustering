import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void testHeight() {
		BinaryTree t = new BinaryTree();
		t.fillSampleTree5();
		assertEquals(5, t.height());
		BinaryTree t2 = new BinaryTree();
		t2.fillSampleTree4();
		assertEquals(3, t2.height());
		BinaryTree t3 = new BinaryTree();
		t3.fillSampleTree3();
		assertEquals(4, t3.height());
	}
	@Test
	public void testBalanced() {
		BinaryTree t = new BinaryTree();
		assertEquals(true, t.isCompletelyBalanced());
		BinaryTree t2 = new BinaryTree();
		t2.fillSampleTree4();
		assertEquals(true, t.isCompletelyBalanced());
		BinaryTree t3 = new BinaryTree();
		t3.fillSampleTree3();
		assertEquals(false, t3.isCompletelyBalanced());
		BinaryTree t4 = new BinaryTree();
		t4.fillSampleTree5();
		assertEquals(false, t4.isCompletelyBalanced());
		
	}
	

}
