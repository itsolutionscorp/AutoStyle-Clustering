import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void testHeight() {
		BinaryTree t1 = new BinaryTree();
		assertEquals(0, t1.height()); //null tree
		t1.fillSampleTree1();
		assertEquals(2, t1.height());
		BinaryTree t2 = new BinaryTree();
		t2.fillSampleTree2();
		assertEquals(4, t2.height());
		BinaryTree t3 = new BinaryTree();
		t3.fillSampleTree3();
		assertEquals(3, t3.height());
	}
	
	@Test
	public void testBalance(){
		BinaryTree t1 = new BinaryTree();
		assertTrue(t1.isCompletelyBalanced());
		t1.fillSampleTree2();
		BinaryTree t2 = new BinaryTree();
		t2.fillSampleTree2();
		assertFalse(t2.isCompletelyBalanced());
		BinaryTree t3 = new BinaryTree();
		t2.fillSampleTree2();
		assertTrue(t3.isCompletelyBalanced());
	}

}
