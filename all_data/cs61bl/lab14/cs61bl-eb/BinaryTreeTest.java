import static org.junit.Assert.*;

import org.junit.Test;



public class BinaryTreeTest {

	@Test
	public void test() {
		BinaryTree t;
		t = new BinaryTree();
		t.fillSampleTree3();
		System.out.println("t.height() " + t.height());
		assertEquals(3, t.height()); 
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
		assertEquals(true, t.isCompletelyBalanced()); 
	}

	@Test
	public void test2() {
		BinaryTree t;
		t = new BinaryTree();
		t.fillSampleTree2();
		System.out.println("t.height() " + t.height());
		assertEquals(4, t.height()); 
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
		assertEquals(false, t.isCompletelyBalanced()); 
	}

	@Test
	public void test1() {
		BinaryTree t;
		t = new BinaryTree();
		t.fillSampleTree1();
		System.out.println("t.height() " + t.height());
		assertEquals(2, t.height()); 
		System.out.println("t.isCompletelyBalanced() " + t.isCompletelyBalanced() + "\n\n");
		assertEquals(true, t.isCompletelyBalanced()); 
	}


}
