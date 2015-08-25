import static org.junit.Assert.*;

import org.junit.Test;


public class BinaryTreeTest {

	@Test
	public void testhight() {
		BinaryTree t;
		t = new BinaryTree();
		assertEquals(0, t.height());
		t.fillSampleTree1();
		assertEquals(2, t.height());
		t.fillSampleTree2();
		assertEquals(4, t.height());
		t.fillSampleTree3();
		assertEquals(4, t.height());
		t.fillSampleTree4();
		assertEquals(3, t.height());
	}
	@Test
	public void testisCompletelyBalanced(){
		BinaryTree t;
		t = new BinaryTree();
		assertEquals(true, t.isCompletelyBalanced());
		t.fillSampleTree1();
		assertEquals(true, t.isCompletelyBalanced());
		t.fillSampleTree2();
		assertEquals(false, t.isCompletelyBalanced());
		t.fillSampleTree3();
		assertEquals(false, t.isCompletelyBalanced());
		t.fillSampleTree4();
		assertEquals(true, t.isCompletelyBalanced());
	}
}
